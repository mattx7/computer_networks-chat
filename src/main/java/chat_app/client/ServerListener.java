package chat_app.client;

import com.google.common.base.Preconditions;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * Listens to the server for incoming messages and displays it to the client.
 */
class ServerListener extends Thread {
    private final Logger LOG = Logger.getLogger(ServerListener.class);

    /**
     * Holds client reference.
     */
    private ClientEntity client;

    /**
     * Constructor.
     *
     * @param client not null.
     */
    ServerListener(@NotNull ClientEntity client) {
        Preconditions.checkNotNull(client, "client must not be null.");

        this.client = client;
    }

    /**
     * @see Thread#run()
     */
    public void run() {
        while (true) { // TODO replace while true
            try {
                String msg = (String) client.inputStream.readObject();
                client.display(msg);
            } catch (final Exception e) {
                client.display("Server has closed the connection!");
                break;
            }
        }
    }
}
