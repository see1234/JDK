package ChatApp.Client;

public class Main2 {
    public static void main(String[] args) {
        Client client = new Client();
        ClientGui clientGui = new ClientGui(client);
        clientGui.start();
    }


}
