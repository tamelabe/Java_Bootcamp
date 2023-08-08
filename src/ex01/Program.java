package src.ex01;

public class Program {
    public static void main(String[] args) {
        User Nikita = new User("Sanya", 300);
        User Farukh = new User( "Farukh", 999999);
        Nikita.printInfo();
        Farukh.printInfo();
        User Slava = new User();
        Slava.setName("Slava");
        Slava.setBalance(1000);
        Slava.printInfo();
    }
}