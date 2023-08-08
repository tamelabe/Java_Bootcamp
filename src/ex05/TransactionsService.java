package src.ex05;

import java.util.UUID;

public class TransactionsService {
    UsersArrayList baseUsers = new UsersArrayList();

    void addUser(User user) {
        baseUsers.addUser(user);
    }
    Integer getUserBalance(Integer userID) {
        return baseUsers.getById(userID).getBalance();
    }

    UsersArrayList getUsersBase() { return baseUsers; }

    void addTransaction(Integer senderID, Integer recipientID, Integer amount) {
        if (senderID == null || recipientID == null || amount == null || amount <= 0) {
            throw new IllegalTransactionException("Illegal params");
        }
        User sender = baseUsers.getById(senderID);
        if (sender.getBalance() < amount) {
            throw new IllegalTransactionException("Insufficient funds");
        }
        User recipient = baseUsers.getById(recipientID);
        Transaction transactionCredit = new Transaction(recipient, sender, TransferCategory.CREDIT, amount);
        Transaction transactionDebit = new Transaction(transactionCredit);
        baseUsers.getById(senderID).addTransaction(transactionDebit);
        baseUsers.getById(recipientID).addTransaction(transactionCredit);
    }

    public Transaction[] getUserTransactions(int userID) throws UserNotFoundException {
        return baseUsers.getById(userID).getTransactions().toArray();
    }

    public void removeTransaction(UUID transactionId, Integer userID) throws UserNotFoundException, TransactionNotFoundException {
        baseUsers.getById(userID).getTransactions().removeById(transactionId);
    }
    public Transaction[] checkTransactions() {
        if (baseUsers.getSize() == 0 || baseUsers.getSize() == 1) return null;
        TransactionsLinkedList allTransaction = new TransactionsLinkedList();
        for (int userId = 1; userId <= baseUsers.getSize(); ++userId) {
            Transaction[] UTArray = baseUsers.getById(userId).getTransactions().toArray();
            for (int i = 0; i != UTArray.length; ++i) {
                try {
                    allTransaction.removeById(UTArray[i].getIdentifier());
                } catch (TransactionNotFoundException e) {
                    allTransaction.addTransaction(UTArray[i]);
                }
            }
        }
        return allTransaction.toArray();
    }
}