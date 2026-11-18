import chat.Client;
import chat.Controller;
import logging.Loggable;
import logging.Logger;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() throws InterruptedException {
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    Logger logger = new Logger("main");

    for (int i = 1; i <= 5; i++) {
        //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
        // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
        logger.debug("" + i);
    }
    logger.debug("Starting controller");

    new Controller().start();
    logger.info(() -> "Started controller");

    while (!Thread.interrupted()) {
        Thread.sleep(500);
        try {
            Client client = new Client();
            logger.debug("new client");
            client.startConnection("localhost", 8082);
            logger.debug("Started connection");
            logger.debug("recieved =-" + client.sendMessage("hello"));
            logger.debug("sent message");

            client.stopConnection();
        } catch (IOException e) {
            logger.error(() -> "Error"
            );
            throw new RuntimeException(e);
        }

    }
}
