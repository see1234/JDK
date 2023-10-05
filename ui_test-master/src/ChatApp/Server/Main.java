package ChatApp.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {
        Server server = new Server(4001);
        ServerGui serverGui = new ServerGui(server);
        serverGui.start();

    }
}