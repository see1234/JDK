package ChatApp.Server;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server implements ServerInterface {


    private int port;
    private List<String> messages;
    private static ServerSocket server; // серверсокет
    private List<Socket> activeSockets;
    private PrintWriter chatLogWriter ;
    boolean isRunning = true;
    public Server(int port) {
        try {
            this.messages = new ArrayList<String>();
            this.port = port;
            this.chatLogWriter = new PrintWriter(new FileWriter("chat_log.txt", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startSocket() {
        try {
            server = new ServerSocket(port);
            onUpdateMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopSocket() {
        try {
            if (server != null && !server.isClosed()) {
                server.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onUpdateMessage() {
        activeSockets = new ArrayList<>();
        new Thread(() -> {



                while (isRunning) {



                        new Thread(() -> {
                            try {

                                Socket clientSocket = server.accept();

                                activeSockets.add(clientSocket);

                                try {
                                    BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                                    OutputStream outputStream = clientSocket.getOutputStream();
                                    PrintWriter writer = new PrintWriter(outputStream, true);
                                    String clientMessage;
                                    while ((clientMessage = reader.readLine()) != null) {

                                        clientMessage = clientMessage.replaceFirst("\n", "");
                                        if (!clientMessage.equalsIgnoreCase("getMsg")) { //уже костыли пошли)))
                                            if (!clientMessage.isEmpty()) {
                                                List<Integer> integerList = new ArrayList<Integer>();
                                                for (int i = 0; i < activeSockets.size(); i++) {
                                                    if(activeSockets.get(i).isClosed()) {
                                                        integerList.add(i);//добавляет для удаления сокетов не актив
                                                    }
                                                    else {
                                                        PrintWriter currWriter = new PrintWriter(activeSockets.get(i).getOutputStream(), true);
                                                        currWriter.println(clientMessage);
                                                        messages.add(clientMessage);
                                                    }
                                                }
                                                for(int i : integerList) {
                                                     activeSockets.remove(activeSockets.get(i));//удаляет сокеты не актив

                                                }
                                                addMessageInLog(clientMessage);
                                            }

                                        } else {
                                            for (String message : messages) {
                                                writer.println(message);
                                            }
                                        }
                                    }
                                    reader.close();
                                    writer.close();
                                } catch (
                                        IOException e) {

                                   // System.err.println("Ошибка при запуске сервера: " + e.getMessage());

                                }

                            } catch (
                                    IOException e) {
                                    isRunning=false;
                                //System.err.println("Ошибка при запуске сервера: " + e.getMessage());

                            }
                        }).start();
                    }


        }).start();
    }
    public ServerSocket getSocket() {
        return server;
    }

    @Override
    public List<String> getHistory() {
        return messages;
    }
    public void addMessageInLog(String msg) {
        chatLogWriter.println(msg);
        chatLogWriter.flush();
    }
    public void loadChat() {
        try (BufferedReader reader = new BufferedReader(new FileReader("chat_log.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                messages.add(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
