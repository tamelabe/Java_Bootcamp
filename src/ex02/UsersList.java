package src.ex02;

interface UsersList {
    void addUser(User user);
    User getById(Integer id);
    User getByIndex(int index);
    Integer getCount();
}