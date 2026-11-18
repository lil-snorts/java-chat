package chat;

import logging.Loggable;

enum LogMessages implements Loggable {
    NEW_CLIENT("new client joined"),
    SERVER_STARTED("Server started"),
    UNEXPECTED_ERROR("Unexpected Error"),
    WAITING_FOR_CLIENT_JOIN("Waiting for new client to join");

    private final String message;

    LogMessages(String message) {
        this.message = message;
    }

    @Override
    public String message() {
        return this.message;
    }
}
