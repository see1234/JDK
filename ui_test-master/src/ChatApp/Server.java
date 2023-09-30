package ChatApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Server extends JFrame {
    private static final int POS_X = 500;
    private static final int POS_Y = 500;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 500;
    private boolean isServerWorking = false;

    public Server() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y,WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        setTitle("Chat Server");
        setResizable(false);
        JButton startButton = new JButton("Запустить сервер");
        JButton stopButton = new JButton("Остановить сервер");
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false); // Запретить редактирование текста

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);

        add(buttonPanel, BorderLayout.NORTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!isServerWorking) {
                    isServerWorking = true;
                    textArea.append("Сервер запущен.\n");
                }
                else {
                    textArea.append("Сервер уже запущен.\n");
                }
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (isServerWorking) {
                    isServerWorking = false;
                    textArea.append("Сервер остановлен.\n");
                }
                else {
                    textArea.append("Сервер уже остановлен.\n");
                }
            }

        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new Server();
    }
}