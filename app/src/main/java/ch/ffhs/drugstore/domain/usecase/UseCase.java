package ch.ffhs.drugstore.domain.usecase;

import ch.ffhs.drugstore.shared.exceptions.DrugstoreException;

/**
 * Generic {@link UseCase} interface for a use case class.
 *
 * @param <T> the return type of the use case
 * @param <P> the input type of the use case
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public interface UseCase<T, P> {
    /**
     * Executes the use case.
     *
     * @param params the input type of the use case
     * @return T the return type of the use case
     * @throws DrugstoreException error during use case execution
     */
    T execute(P params) throws DrugstoreException;
}
