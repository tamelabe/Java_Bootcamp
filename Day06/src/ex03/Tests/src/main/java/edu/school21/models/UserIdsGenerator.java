package edu.school21.models;

public class UserIdsGenerator {
    private static edu.school21.models.UserIdsGenerator generator;
    private static Integer counter;

    public static edu.school21.models.UserIdsGenerator getInstance() {
        if (generator == null) {
            generator = new edu.school21.models.UserIdsGenerator();
        }
        return generator;
    }

    private UserIdsGenerator() { counter = 0; }

    public Integer generateId() { return ++counter; }
}