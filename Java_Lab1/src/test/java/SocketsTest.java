import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class SocketsTest {
    @Test
    public void clientServer() {
        Client client = new Client("127.0.0.1", Server.PORT);
        client.run();

        assertEquals("Hi allowed client", client.msg);
    }
}