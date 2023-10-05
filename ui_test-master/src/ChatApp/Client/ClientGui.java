package ChatApp.Client;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientGui extends JFrame implements ClientGuiInterface {
    private static final int POS_X = 700;
    private static final int POS_Y = 350;
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;
    //Размер клиентской части
    private JTextField loginField, passwordField, ipField, portField;
    private JTextArea chatArea, messageArea;
    private JButton connectButton, sendButton;
    private JPanel serverPanel, messagePanel;
    //Панели, вводы и тд.. из JFrame

    private Client client;

    public ClientGui(Client client) {
        this.client = client;
    }
    @Override
    public void start() {
        init();
    }
    @Override
    public void init() {
        setTitle("Chat Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());
        createPanel();
        onListenerClose();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void createPanel() {
        serverPanel = new JPanel(new FlowLayout());
        loginField = new JTextField("see1",10);
        passwordField = new JTextField("1234",10);
        ipField = new JTextField("localhost", 10);
        portField = new JTextField("4001",5);
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
        chatArea = new JTextArea(10, 40);
        chatArea.setEditable(false);
        messagePanel = new JPanel(new BorderLayout());
        messageArea = new JTextArea(2, 40);
        add(serverPanel, BorderLayout.NORTH);
        addActionConnectServer();

    }
    public void loadGuiChat() {
        createButtons();
        serverPanel.hide();
        messagePanel.add(new JScrollPane(chatArea), BorderLayout.CENTER);
        messagePanel.add(messageArea, BorderLayout.SOUTH);
        messagePanel.add(sendButton, BorderLayout.LINE_END);
        add(messagePanel, BorderLayout.CENTER);
        setBounds(POS_X, POS_Y,WIDTH, HEIGHT);
    }


    public void createButtons() {
        sendButton = new JButton("Отправить");
        addActionListener();
    }

    public void addActionConnectServer() {
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.connect(ipField.getText(), Integer.parseInt(portField.getText()));
                client.displayChatHistoryOnLogin();
                client.onUpdateReceived();

                loadGuiChat();
                Join();
                updateChat();

            }
        });
    }
    public void Join() {
        chatArea.append(loginField.getText() + " зашел в чат");
        client.sendMessage(loginField.getText() + " зашел в чат");
    }
    public void Quit() {
        chatArea.append(loginField.getText() + " вышел из чата");
        client.sendMessage(loginField.getText() + " вышел из чата");
    }
    public void addActionListener() {
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                send();
            }
        });

        messageArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    send();
                }
            }
        });
    }
    public void onListenerClose() {

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(client != null) {
                    Quit();
                    client.disconnect();

                }
            }
        });
    }
    private void send() {
        String messageText = messageArea.getText();
        if (!messageText.isEmpty() && !messageText.equals("\n")) {
            chatArea.append(String.format("Вы: %s%s", messageText.replaceFirst("\n", ""), "\n"));
        }
        client.sendMessage(String.format("%s: %s%s", loginField.getText(), messageText.replaceFirst("\n", ""), "\n"));
        messageArea.setText("");
    }
    private void updateChat() {
        new Thread(() -> {
            while(true) {
                if (client.getHistory().size() > 0) {
                        for (String msg : client.getHistory()) {
                            if (!msg.isEmpty() && !msg.equalsIgnoreCase(msg.split(":")[0] + ": ") && !msg.split(":")[0].equalsIgnoreCase(loginField.getText())) {
                                chatArea.append(msg.replaceFirst("\n", "") + "\n");
                            }
                        }
                        client.getHistory().clear();
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
            }
        }).start();

    }






}