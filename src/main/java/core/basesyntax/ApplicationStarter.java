package core.basesyntax;

import core.basesyntax.controller.ConsoleHandler;
import core.basesyntax.db.Storage;

public class ApplicationStarter {
    public static void main(String[] args) {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.handle();

        Storage.bets.forEach(bet ->
                System.out.println("Value: " + bet.getValue() + ", risk: " + bet.getRisk()));
    }
}
