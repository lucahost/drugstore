package ch.ffhs.drugstore.domain.usecase;

public interface UseCase<T, P> {
  T execute(P params);
}
