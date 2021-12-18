package ch.ffhs.drugstore.domain.usecase;

/**
 * Generic interface for Use-Cases
 * @param <T> Type
 * @param <P> Parameter
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public interface UseCase<T, P> {
  T execute(P params) throws Exception;
}
