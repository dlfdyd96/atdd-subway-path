package nextstep.subway.domain.specification.shared;

public interface Specification<T> {

    boolean isSatisfiedBy(T t);
}
