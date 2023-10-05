package ChatApp.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ServerGui extends JFrame implements ServerGuiInterface {
    private static final int POS_X = 500;
    private static final int POS_Y = 400;
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;
    //Размеры для приложения сервера




    private JButton startButton, stopButton;
    private JPanel buttonPanel;
    private JTextArea textArea;
    //JFrame кнопки, панели, текста
    private boolean isServerWorking = false;
    //Проверка включен ли сервер
    private Server server; //Загрузка сервера
    public ServerGui(Server server) {
        this.server = server;
    }
    public void start() {
        init();
    }

    public void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y,WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Сервер для чат-мессенджера");
        setResizable(false);
        createPanels();
        setVisible(true);
    }
    private void createPanels() {
        createButtons();
        addActionEvent();
        textArea = new JTextArea();
        textArea.setEditable(false);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        add(buttonPanel, BorderLayout.NORTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        onListenerClose();
    }
    public void onListenerClose() {

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(server != null) {
                    server.stopSocket();
                }
            }
        });
    }
    @Override
    public void addActionEvent() {
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!isServerWorking) {
                    isServerWorking = true;
                    textArea.append("Сервер запущен.\n");
                    server.startSocket();
                    server.loadChat();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Сервер уже запущен.", "Предупреждение", JOptionPane.INFORMATION_MESSAGE);

                }
            }
        });
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (isServerWorking) {
                    isServerWorking = false;
                    textArea.append("Сервер остановлен.\n");
                    server.stopSocket();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Сервер уже остановлен.", "Предупреждение", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }
    public void createButtons() {
        startButton = new JButton("Запустить сервер");
        stopButton = new JButton("Остановить сервер");

    }


}