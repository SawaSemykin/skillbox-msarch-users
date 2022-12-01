package ru.skillbox.transactionManager;

import java.util.function.Supplier;

public interface TransactionAction<T> extends Supplier<T> {
}
