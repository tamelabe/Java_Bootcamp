package src.ex00;

public class Program {
    public static void main(String[] args) {
        User Nikita = new User(1, "Sanya", 300);
        User Farukh = new User(2, "Farukh", 999999);
        Nikita.printInfo();
        Farukh.printInfo();
        User Slava = new User();
        Slava.setName("Slava");
        Slava.setIdentifier(3);
        Slava.setBalance(1000);
        Slava.setBalance(-1000);
        Slava.printInfo();
        Transaction tr = new Transaction(Nikita, Farukh, Transaction.TransferCategory.DEBIT, 300);
        tr.setAmount(-300);
        tr.printInfo();
        Transaction trr = new Transaction();
        trr.setSender(Slava);
        trr.setRecipient(Farukh);
        trr.setCategory(Transaction.TransferCategory.CREDIT);
        trr.setAmount(1000);
        trr.setAmount(-1000);
        trr.printInfo();
    }
}