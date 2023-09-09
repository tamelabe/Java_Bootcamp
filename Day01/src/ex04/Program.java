package src.ex04;


public class Program {
    public static void main(String[] args) {
        User Nikita = new User("Nikita", 300);
        User Farukh = new User("Farukh", 999999);
        TransactionsService menu = new TransactionsService();
        menu.addUser(Nikita);
        menu.addUser(Farukh);
        menu.addTransaction(Nikita.getIdentifier(), Farukh.getIdentifier(), 300);
        menu.addTransaction(Farukh.getIdentifier(), Nikita.getIdentifier(), 1000);
        Transaction[] transactions = menu.getUserTransactions(Nikita.getIdentifier());
        System.out.println("USER TRANSACTIONS:");
        for (Transaction transaction : transactions) {
            System.out.println(transaction.getIdentifier());
        }
        System.out.println("USER Balance:" + menu.getUserBalance(Farukh.getIdentifier()));
        System.out.println("REMOVE TRANSACTIONS....");
        menu.removeTransaction(Nikita.getTransactions().toArray()[0].getIdentifier(), Nikita.getIdentifier());
        Transaction[] notMatched = menu.checkTransactions();
        System.out.println("THEN FIND UNMATCHED:");
        for (int i = 0; i < notMatched.length; ++i) {
            System.out.println(notMatched[i].getIdentifier());
        }
    }
}