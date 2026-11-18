package chat;

import logging.LogPair;
import logging.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller extends Thread {
    private static final int PORT = 8082;
    private ServerSocket serverSocket;
    private ExecutorService excecutor;
    private final Logger logger = new Logger("controller");

    public Controller() {
        excecutor = Executors.newFixedThreadPool(3);
    }

    @Override
    public void run() {
        // listen on port
        try {
            serverSocket = new ServerSocket(PORT);
            logger.info(LogMessages.SERVER_STARTED, LogPair.of("Port", String.valueOf(PORT)));
            // while this has not been interrupted
            while (this.isAlive()) {
                // when new client comes in / sends a request - handle with websocket
                logger.info(LogMessages.WAITING_FOR_CLIENT_JOIN);
                Socket accept = serverSocket.accept();
                excecutor.submit(() -> {
                    logger.info(LogMessages.NEW_CLIENT);
                    new TcpHandler(accept).handle();
                });
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
