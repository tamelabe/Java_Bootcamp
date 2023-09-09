package src.ex00;

import java.util.UUID;

public class Transaction {
    private UUID identifier;
    private User recipient;
    private User sender;
    private TransferCategory category;
    private Integer amount;

     Transaction() {}
    public Transaction(User otherSender, User otherRecipient, TransferCategory otherCategory, Integer otherAmount) {
        setIdentifier();
        setRecipient(otherRecipient);
        setSender(otherSender);
        setCategory(otherCategory);
        setAmount(otherAmount);
    }

    public void printInfo() {
        System.out.println("ID: " + identifier);
        System.out.println("Sender: " + sender);
        System.out.println("Recipient: " + recipient);
        System.out.printf("Transfer category: %s", category == TransferCategory.CREDIT ? "Credit" : "Debit");
        System.out.println("Transfer amount: " + recipient);
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public User getRecipient() {
        return recipient;
    }
    public User getSender() {
        return sender;
    }
    public TransferCategory getCategory() {
        return category;
    }
    public Integer getAmount() {
        return amount;
    }
    public void setIdentifier() {
        identifier = UUID.randomUUID();
    }
    public void setRecipient(User otherRecipient) {
        recipient = otherRecipient;
    }
    public void setSender(User otherSender) {
        sender = otherSender;
    }
    public void setCategory(TransferCategory otherCategory) {
        category = otherCategory;
    }
    public void setAmount(Integer otherAmount) {
        if ((otherAmount >= 0 && category == TransferCategory.CREDIT) ||
        otherAmount <= 0 && category == TransferCategory.DEBIT) {
            System.err.println("Transaction: invalid amount or category");
            return;
        }
        amount = otherAmount;
    }


    public enum TransferCategory {
        DEBIT,
        CREDIT
    }

}