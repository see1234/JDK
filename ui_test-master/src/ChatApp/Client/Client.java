package ChatApp.Client;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Client implements ClientInterface {

    private Socket socket;
    private List<String> history;
    private int count = 0;
    public Client() {
        history = new ArrayList<String>();
    }

    @Override
    public void connect(String ip, int port) {
        try {
            socket = new Socket(ip, port);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Произошла ошибка: " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }
    @Override
    public void onUpdateReceived() {
        new Thread(() -> {
            while(true) {
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String message;
                    while ((message = reader.readLine()) != null) {
                        history.add(message);
                    }
                    if (reader != null) reader.close();
                    JOptionPane.showMessageDialog(null, "Сервер разорвал соединение", "Предупреждение", JOptionPane.WARNING_MESSAGE);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Произошла ошибка: " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                    System.exit(-1);
                } finally {

                }

            }
        }).start();
    }
    @Override
    public void displayChatHistoryOnLogin() {
        sendMessage("getMsg");//тоже костыль
    }

    @Override
    public void sendMessage(String message) {
        try {
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);
            writer.println(message);
           // socket.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Произошла ошибка: " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
    @Override
    public void disconnect() {
        try {
            if(socket != null && !socket.isClosed()) {
                socket.close();

            }
        } catch (IOException e) {

            e.printStackTrace();
          //  JOptionPane.showMessageDialog(null, "Произошла ошибка: " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
    public List<String> getHistory() {

        return history;
    }
    public int getCount() {
        return count;
    }
    public void addCount() {
        count++;
    }
}
