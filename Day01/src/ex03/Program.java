package src.ex03;


public class Program {
    public static void main(String[] args) {
        User Nikita = new User("Sanya", 300);
        User Farukh = new User("Farukh", 999999);
        Transaction tr = new Transaction(Nikita, Farukh, TransferCategory.DEBIT, 300);
        Transaction tr1 = new Transaction(Nikita, Farukh, TransferCategory.CREDIT, -300);
        Transaction tr2 = new Transaction(Farukh, Nikita, TransferCategory.CREDIT, -300);
        Transaction tr3 = new Transaction(Farukh, Nikita, TransferCategory.DEBIT, 300);
        Nikita.addTransaction(tr);
        Nikita.addTransaction(tr1);
        Nikita.addTransaction(tr2);
        Nikita.addTransaction(tr3);
        Farukh.addTransaction(tr);
        Farukh.addTransaction(tr1);
        Farukh.addTransaction(tr2);
        Farukh.addTransaction(tr3);
        System.out.println("Nikita's transactions:");
        Nikita.getTransactions().printTransactions();
        System.out.println("Farukh's transactions:");
        Farukh.getTransactions().printTransactions();
        Transaction[] array = Farukh.getTransactions().toArray();
        System.out.println("Array:");
        array[0].printInfo();
    }
}