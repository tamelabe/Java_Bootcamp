package src.ex04;


import java.util.UUID;

interface TransactionsList {
    void addTransaction(Transaction transaction);
    void removeById(UUID id);
    Transaction[] toArray();
}