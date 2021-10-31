package ch.ffhs.drugstore.usecase;

public abstract class UseCase<T, Params> {
  abstract T execute(Params params);
}
