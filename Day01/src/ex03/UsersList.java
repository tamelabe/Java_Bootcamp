package src.ex03;

public interface UsersList {
    void addUser(User user);
    User getById(Integer id);
    User getByIndex(int index);
    Integer getCount();
}