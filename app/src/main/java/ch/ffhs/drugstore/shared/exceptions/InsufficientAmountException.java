package ch.ffhs.drugstore.shared.exceptions;

public class InsufficientAmountException extends DrugstoreException {

    public InsufficientAmountException(int code) {
        super("InsufficientAmountException", code);
    }
}
