package chat;

import logging.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

class TcpHandler {
    private static final boolean AUTO_FLUSH_ENABLED = true;
    private final Socket socket;
    private final Logger logger = new Logger(TcpHandler.class);

    TcpHandler(Socket socket) {
        this.socket = socket;
    }

    void handle() {
        Objects.requireNonNull(socket);
        PrintWriter out;
        BufferedReader in;
        String firstLine;

        try {
            out = new PrintWriter(socket.getOutputStream(), AUTO_FLUSH_ENABLED);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            firstLine = in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Objects.requireNonNull(firstLine);
        logger.debug("firstline=" + firstLine);
        if (firstLine.contains("hello")) {
            out.println(":0 hello young man");
        } else {
            out.println("Hello");
            out.println("Received %d bytes".formatted(firstLine.length() * 2));
        }
    }
}
