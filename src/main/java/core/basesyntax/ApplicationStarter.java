package core.basesyntax;

import core.basesyntax.controller.ConsoleHandler;
import core.basesyntax.db.Storage;
import core.basesyntax.lib.Injector;
import java.lang.reflect.InvocationTargetException;

public class ApplicationStarter {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        ConsoleHandler consoleHandler = (ConsoleHandler) Injector.getInstance(ConsoleHandler.class);
        consoleHandler.handle();

        Storage.users.forEach(System.out::println);
        Storage.bets.forEach(System.out::println);
    }
}
