package ru.skillbox.transactionManager;

public interface TransactionManager {

    <T> T doInTransaction(TransactionAction<T> action);
}
