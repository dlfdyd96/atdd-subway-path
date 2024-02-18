package nextstep.subway.domain.specification.shared;

import nextstep.subway.domain.entity.Section;
import nextstep.subway.domain.exception.BusinessException;

public abstract class AbstractSpecification<T> implements Specification<T> {
    public abstract boolean isSatisfiedBy(T t);

    public abstract void check(T t) throws BusinessException;
}
