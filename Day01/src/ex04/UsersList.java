package src.ex04;

public interface UsersList {
    void addUser(User user);
    User getById(Integer id);
    User getByIndex(int index);
    Integer getSize();
}