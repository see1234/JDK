package ChatApp.Client;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;

public class Main  {
    public static void main(String[] args) {
        Client client = new Client();
        ClientGui clientGui = new ClientGui(client);
        clientGui.start();
    }

}