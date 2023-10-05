package ChatApp.Client;

public interface ClientInterface {
    void connect(String ip, int port);
    void onUpdateReceived();
    void displayChatHistoryOnLogin();
    void disconnect();

    void sendMessage(String message);
}
