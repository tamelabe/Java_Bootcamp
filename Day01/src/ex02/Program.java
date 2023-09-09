package src.ex02;

public class Program {
    public static void main(String[] args) {
        User Nikita = new User("Sanya", 300);
        User Farukh = new User( "Farukh", 999999);
        User Slava = new User( "Slava", 1000);
        User Rustem = new User( "Rustem", 10);
        User Mentemir = new User( "Mentemir", 555);
        User Lera = new User( "Lera", 777);
        User Kadyr = new User( "Kadyr", 999);
        User Alisa  = new User( "Alisa", 333);
        User Ya  = new User( "Ya", 50);
        User Jaloledin  = new User( "Jaloledin", 222);
        User Sanya  = new User( "Sanya", 666);
        User Maksim  = new User( "Maksim", 99999);
        UsersArrayList base = new UsersArrayList();

        base.addUser(Nikita);
        base.addUser(Farukh);
        base.addUser(Slava);
        base.addUser(Rustem);
        base.addUser(Mentemir);
        base.addUser(Jaloledin);
        base.addUser(Lera);
        base.addUser(Kadyr);
        base.addUser(Alisa);
        base.addUser(Ya);
        base.addUser(Sanya);
        base.addUser(Maksim);
        System.out.println("Count: " + base.getCount());
        System.out.println("Find by index: " + base.getByIndex(5).getName());
        System.out.println("Find by id: " + base.getById(12).getName());
        System.out.println("Find by index: " + base.getByIndex(5).getName());
    }
}