package ch.ffhs.drugstore.shared.exceptions;

/**
 * TODO: add description
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public abstract class DrugstoreException extends Exception {
    private int code;

    public DrugstoreException(int code) {
        super("DrugstoreException");
        setCode(code);
    }

    public DrugstoreException(String message, int code) {
        super(message);
        setCode(code);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
