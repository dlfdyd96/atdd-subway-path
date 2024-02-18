package nextstep.subway.domain.specification;

import nextstep.subway.domain.entity.Line;
import nextstep.subway.domain.exception.BusinessException;
import nextstep.subway.domain.specification.shared.AbstractSpecification;

public class EmptySectionSpec extends AbstractSpecification<Line> {
    @Override
    public boolean isSatisfiedBy(Line line) {
        return !line.getSections().isEmpty();
    }

    @Override
    public void check(Line line) throws BusinessException {
        if (!isSatisfiedBy(line))
            throw new BusinessException("노선에는 구간이 존재해야 합니다.");
    }
}
