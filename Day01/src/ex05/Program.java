package src.ex05;


public class Program {
    public static void main(String[] args) {
        Menu menu;
        if(args.length > 0 && args[0].equals("--profile=dev")) {
            menu = new Menu(true);
        } else {
            menu = new Menu(false);
        }
        menu.start();
    }
}