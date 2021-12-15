package ch.ffhs.drugstore.shared.exceptions;

public class DrugNotFoundException extends DrugstoreException {
    public DrugNotFoundException(int code) {
        super("DrugNotFoundException", code);
    }

}
