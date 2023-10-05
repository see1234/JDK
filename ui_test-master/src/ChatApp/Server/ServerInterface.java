package ChatApp.Server;

import java.net.ServerSocket;
import java.util.List;

public interface ServerInterface {
    void startSocket();
    void stopSocket();
    ServerSocket getSocket();
    List<String> getHistory();
}
