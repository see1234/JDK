package ChatApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;

public class Client extends JFrame {
    private JTextField loginField, passwordField, ipField, portField;
    private JTextArea chatArea, messageArea;
    private JButton connectButton, sendButton;
    private JList<String> userList;
    private PrintWriter chatLogWriter;
    public Client() {

        setTitle("Chat Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());
        JPanel serverPanel = new JPanel(new FlowLayout());
        loginField = new JTextField(10);
        passwordField = new JTextField(10);
        ipField = new JTextField(10);
        portField = new JTextField(5);
        connectButton = new JButton("Подключится");
        serverPanel.add(new JLabel("Логин: "));
        serverPanel.add(loginField);
        serverPanel.add(new JLabel("Пароль: "));
        serverPanel.add(passwordField);
        serverPanel.add(new JLabel("Айпи Адрес: "));
        serverPanel.add(ipField);
        serverPanel.add(new JLabel("Порт: "));
        serverPanel.add(portField);
        serverPanel.add(connectButton);

        JPanel messagePanel = new JPanel(new BorderLayout());
        messageArea = new JTextArea(10, 40);
        chatArea = new JTextArea(10, 40);
        chatArea.setEditable(false);
        try {
            chatLogWriter = new PrintWriter(new FileWriter("chat_log.txt", true));
            loadChat();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sendButton = new JButton("Отправить");
        messagePanel.add(new JScrollPane(chatArea), BorderLayout.CENTER);
        messagePanel.add(messageArea, BorderLayout.NORTH);
        messagePanel.add(sendButton, BorderLayout.SOUTH);
        DefaultListModel<String> userListModel = new DefaultListModel<>();
        userListModel.addElement("Вася");
        userListModel.addElement("Петя");
        userListModel.addElement("Валера");
        userList = new JList<>(userListModel);
        messagePanel.add(new JScrollPane(userList), BorderLayout.EAST);

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        add(serverPanel, BorderLayout.NORTH);
        add(messagePanel, BorderLayout.CENTER);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        messageArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage();
                }
            }
        });
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void sendMessage() {
        String messageText = messageArea.getText();

        chatArea.append("Ты: " + messageText + "\n");
        chatLogWriter.println("Ты: " + messageText);
        chatLogWriter.flush();
        messageArea.setText("");

    }
    public static void main(String[] args) {

                new Client();

    }
    private void loadChat() {
        try (BufferedReader reader = new BufferedReader(new FileReader("chat_log.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                chatArea.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}