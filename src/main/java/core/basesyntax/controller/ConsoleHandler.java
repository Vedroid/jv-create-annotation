package core.basesyntax.controller;

import core.basesyntax.dao.BetDao;
import core.basesyntax.dao.BetDaoImpl;
import core.basesyntax.dao.UserDao;
import core.basesyntax.dao.UserDaoImpl;
import core.basesyntax.model.Bet;
import core.basesyntax.model.User;
import java.util.Scanner;

public class ConsoleHandler {
    private static final String EXIT_COMMAND = "q";
    private BetDao betDao = new BetDaoImpl();
    private UserDao userDao = new UserDaoImpl();

    public void handle() {
        Scanner scanner = new Scanner(System.in);
        enterUserName(scanner);
        enterBet(scanner);
        scanner.close();
    }

    private void enterUserName(Scanner scanner) {
        while (true) {
            System.out.println("Enter your name:  (enter 'q' to exit)");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase(EXIT_COMMAND)) {
                return;
            }
            if (!name.isEmpty() && name.matches("[A-Za-z]+")) {
                userDao.add(new User(name));
                return;
            }
            System.out.println("Please enter correct data.");
        }
    }

    private void enterBet(Scanner scanner) {
        while (true) {
            System.out.println("Enter value and risk for your bet:  (enter 'q' to exit)");
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase(EXIT_COMMAND)) {
                return;
            }
            try {
                String[] betData = command.split(" ");
                int value = Integer.parseInt(betData[0]);
                double risk = Double.parseDouble(betData[1]);
                betDao.add(new Bet(value, risk));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Please enter correct data.");
            }
        }
    }
}
