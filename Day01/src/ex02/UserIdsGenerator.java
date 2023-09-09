package src.ex02;

public class UserIdsGenerator {
    public static UserIdsGenerator getInstance() {
        if (generator == null) {
            generator = new UserIdsGenerator();
        }
        return generator;
    }

    private UserIdsGenerator() { counter = 0; }
    public Integer generateId() { return ++counter; }

    private static UserIdsGenerator generator;
    private static Integer counter;
}