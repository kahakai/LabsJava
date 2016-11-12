package homework.lab10;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Map;
import java.util.TreeMap;

public class Client {
    private static Map<String, Byte> commands = new TreeMap<>();

    public static void main(String[] args) {
        if (args.length >= 3 && args.length <= 4) {
            try (Socket socket = args.length == 3 ?
                    new Socket(InetAddress.getLocalHost(), Integer.parseInt(args[2])) :
                    new Socket(args[2], Integer.parseInt(args[3]))) {
                System.out.println("Initialized");
                session(socket, args[0], args[1]);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            } finally {
                System.err.println("Goodbye...");
            }
        } else {
            System.err.println("Invalid number of arguments!\n" +
                                "Usage: nick name [host] [port]");
        }
    }

    private static void session(Socket socket, String nick, String name) {
        ObjectInputStream is = null;
        ObjectOutputStream os = null;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            os = new ObjectOutputStream(socket.getOutputStream());
            is = new ObjectInputStream(socket.getInputStream());

            Session session = new Session(nick, name);
            if (openSession(session, is, os)) {
                Message message;

                do {
                    message = getCommand(session, reader);
                } while (processCommand(message, is, os));
                closeSession(session, os);
            }
        } catch (ClassNotFoundException e) {
            try {
                os.close();
                is.close();
            } catch (IOException ioe) {
                System.err.println(ioe.getMessage());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static boolean openSession(Session session, ObjectInputStream is, ObjectOutputStream os)
            throws IOException, ClassNotFoundException {
        os.writeObject(new MessageConnect(session.userNick, session.userName));

        MessageConnectResult result = (MessageConnectResult) is.readObject();
        if (!result.error()) {
            System.out.println("Connected");
            session.connected = true;

            return true;
        } else {
            System.err.println("Unable to connect: " + result.getErrorMessage());
            return false;
        }
    }

    private static void closeSession(Client.Session session, ObjectOutputStream os) throws IOException {
        if (session.connected) {
            session.connected = false;
            os.writeObject(new MessageDisconnect());
        }
    }

    private static Message getCommand(Session session, BufferedReader reader) throws IOException {
        while (true) {
            printPrompt();

            String message = reader.readLine();
            byte id = translateCmd(message);

            switch (id) {
                case 2:
                    return new MessageDisconnect();
                case 1:
                    return new MessageConnect(session.userNick, session.userName);
                case 3:
                    return new MessageUser();
                case 4:
                    return new MessageCheckMail();
                case 5:
                    return inputLetter(reader);
                default:
                    System.err.println("Unknown command!");
            }
        }
    }

    private static MessageLetter inputLetter(BufferedReader reader) throws IOException {
        System.out.print("Enter the message: ");
        return new MessageLetter(reader.readLine());
    }

    private static byte translateCmd(String cmd) {
        return cmd == null ? 0 : commands.get(cmd.trim());
    }

    private static void printPrompt() {
        System.out.println();
        System.out.print("(q)uit / (m)ail / (u)sers / (l)etter > ");
    }

    private static boolean processCommand(Message message, ObjectInputStream is, ObjectOutputStream os)
            throws IOException, ClassNotFoundException {
        if (message != null && message.getID() != CMD.CMD_DISCONNECT) {
            os.writeObject(message);

            /*MessageResult result;
            try {
                result = (MessageResult) is.readObject();
            } catch (IOException e) {
                return false;
            }*/

            MessageResult result = (MessageResult) is.readObject();
            if (result.error()) {
                System.err.println(result.getErrorMessage());
            } else {
                switch (result.getID()) {
                    case 3:
                        printUsers((MessageUserResult) result);
                        break;
                    case 4:
                        printMail((MessageCheckMailResult) result);
                        break;
                    case 5:
                        System.out.println("OK...");
                        break;
                    default:
                        assert false;
                }
            }

            return true;
        } else {
            return false;
        }
    }

    private static void printMail(MessageCheckMailResult result) {
        if (result.letters.length > 0) {
            System.out.println("Your mail {");
            for (String letter : result.letters) {
                System.out.println("\t" + letter);
            }
            System.out.println("}");
        } else {
            System.out.println("No mail...");
        }
    }

    private static void printUsers(MessageUserResult result) {
        if (result.userNicks != null) {
            System.out.println("Users {");
            for (String nickName : result.userNicks) {
                System.out.println("\t" + nickName);
            }
            System.out.println("}");
        }
    }

    static {
        commands.put("q", (byte) 2);
        commands.put("quit", (byte) 2);
        commands.put("m", (byte) 4);
        commands.put("mail", (byte) 4);
        commands.put("u", (byte) 3);
        commands.put("users", (byte) 3);
        commands.put("l", (byte) 5);
        commands.put("letter", (byte) 5);
    }

    private static class Session {
        boolean connected = false;
        String userNick;
        String userName;

        Session(String userNick, String userName) {
            this.userNick = userNick;
            this.userName = userName;
        }
    }
}
