package src.ex03;

public class UsersArrayList implements UsersList {
    private User[] userArray;
    private int size;
    private int capacity;

    public UsersArrayList() {
        size = 0;
        capacity = 10;
        userArray = new User[capacity];
    }

    public void printInfo() {
        System.out.println("Size: " + size);
        System.out.println("Capacity: " + size);
        System.out.println("-------USERS-------");
        for (int i = 0; i < size; ++i) {
            System.out.println(i + ". ");
            System.out.println("    " + "ID: " + userArray[i].getIdentifier());
            System.out.println("    " + "Name: " + userArray[i].getName());
            System.out.println("    " + "Balance: " + userArray[i].getBalance());
        }
        System.out.println("-------END OF BASE-------");
    }

    @Override
    public void addUser(User user) {
        if (size + 1 == capacity)
            extendCapacity();
        userArray[size] = user;
        ++size;
    }

    @Override
    public User getById(Integer id) {
        for (int i = 0; i < size; ++i) {
            if (id.equals(userArray[i].getIdentifier()))
                return userArray[i];
        }
        throw new UserNotFoundException("ID: User not found");
    }

    @Override
    public User getByIndex(int index) {
        if (index < 0 || index > size)
            throw new UserNotFoundException("Index: User not found");
        return userArray[index];
    }

    @Override
    public Integer getCount() {
        return size;
    }

    private void extendCapacity() {
        User[] newArray = new User[capacity * 2];
        for (int i = 0; i < capacity; ++i)
            newArray[i] = userArray[i];
        capacity *= 2;
        userArray = newArray;
    }

}