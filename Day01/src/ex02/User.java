package src.ex02;

public class User {
    public User() {
        identifier = UserIdsGenerator.getInstance().generateId();
        balance = 0;
    }
    public User(String otherName, Integer otherBalance) {
        identifier = UserIdsGenerator.getInstance().generateId();
        name = otherName;
        balance = otherBalance;
    }

    public void printInfo() {
        System.out.println("Identifier: " + identifier);
        System.out.println("Name: " + name);
        System.out.println("Balance: " + balance);
    }

    public Integer getIdentifier() { return identifier; }
    public String getName() { return name; }
    public Integer getBalance() { return balance; }

    public void setBalance(Integer otherBalance) {
        if (balance < 0) {
            System.err.println("USER: Balance cannot be negative");
            return;
        }
        balance = otherBalance;
    }
    public void setName(String otherName) {
        name = otherName;
    }

    private final Integer identifier;
    private String name;
    private Integer balance;
}