package src.ex05;

import java.util.Scanner;
import java.util.UUID;

public class Menu {
    private TransactionsService service;
    private Scanner scanner;
    boolean devMode;
    boolean firstInit;
    
    public Menu(boolean mode) {
        service = new TransactionsService();
        scanner = new Scanner(System.in);
        devMode = mode;
        firstInit = true;
    }

    public void start() {
        printMenu();
        menuActions();
    }
    private void printMenu() {
        if (firstInit) {
            System.out.println();
            firstInit = false;
        } else {
            System.out.println("---------------------------------------------------------");
        }
        System.out.println("1. Add a user");
        System.out.println("2. View user balances");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        if(devMode) {
            System.out.println("5. DEV – remove a transfer by ID");
            System.out.println("6. DEV – check transfer validity");
            System.out.println("7. Finish execution");
        } else {
            System.out.println("5. Finish execution");
        }
    }
    private void menuActions() {
        int input = 0;
        try {
            input = scanner.nextInt();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        if (input == 1) {
            addUser();
        } else if (input == 2) {
            userBalance();
        } else if (input == 3) {
            createTransfer();
        } else if (input == 4) {
            getTransactions();
        } else if (!devMode && input == 5 || devMode && input == 7) {
            System.exit(0);
        } else if (devMode) {
            if (input == 5) {
                removeTransferById();
            } else if (input == 6) {
                checkTransfer();
            } 
        } else {
            System.out.println("Incorrect input. Please try again");
            start();
        }
    }
    private void addUser() {
        try {
            System.out.println("Enter a user name and a balance");
            String name = scanner.next();
            Integer balance = scanner.nextInt();
            User newUser = new User(name, balance);
            service.addUser(newUser);
            System.out.printf("User with id = %d is added\n", newUser.getIdentifier());
            start();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        } finally {
            start();
        }
    }
    private void userBalance() {
        try {
            System.out.println("Enter a user ID");
            Integer userID = scanner.nextInt();
            User user = service.getUsersBase().getById(userID);
            System.out.printf("%s - %d\n", user.getName(), user.getBalance());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        } finally {
            start();
        }
    }
    private void createTransfer() {
        try {
            System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
            Integer senderId = scanner.nextInt();
            Integer recipientId = scanner.nextInt();
            Integer transferAmount = scanner.nextInt();
            service.addTransaction(senderId, recipientId, transferAmount);
            System.out.println("The transfer is completed");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        } finally {
            start();
        }
    }
    private void getTransactions() {
        try {
            System.out.println("Enter a user ID");
            Integer userId = scanner.nextInt();
            User user = service.getUsersBase().getById(userId);
            Transaction[] transactions = user.getTransactions().toArray();
            for (Transaction tr : transactions) {
                System.out.printf("To %s(id = %d) %d with id = %s\n",
                        tr.getRecipient().getName(), tr.getRecipient().getIdentifier(),
                        tr.getAmount(), tr.getIdentifier());
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        } finally {
            start();
        }
    }
    private void removeTransferById() {
        try {
            System.out.println("Enter a user ID and a transfer ID");
            Integer userId = scanner.nextInt();
            String transId = scanner.next();
            UUID transferId = UUID.fromString(transId);
            User user = service.getUsersBase().getById(userId);
            int amount = 0;
            User recipient = new User();
            for(Transaction trans : user.getTransactions().toArray()) {
                if(trans.getIdentifier().equals(transferId)) {
                    amount = trans.getAmount();
                    recipient = trans.getRecipient();
                }
            }
            service.removeTransaction(transferId, userId);
            System.out.printf("Transfer To %s(id = %d) %d removed\n",
                    recipient.getName(), recipient.getIdentifier(), amount);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        } finally {
            start();
        }
    }
    private void checkTransfer() {
        try {
            for (Transaction transaction : service.checkTransactions()) {
                System.out.printf("%s(id = %d) has an unknown transfer id = " +
                                "%s " + "from %s(id = %d) for %d\n",
                        transaction.getRecipient().getName(), transaction.getRecipient().getIdentifier(),
                        transaction.getIdentifier(), transaction.getSender().getName(),
                        transaction.getSender().getIdentifier(), transaction.getAmount());
            }
        } catch (RuntimeException e) {
            System.out.println("All transactions is valid");
        } finally {
            start();
        }
    }
}