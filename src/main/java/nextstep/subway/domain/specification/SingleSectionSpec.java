package nextstep.subway.domain.specification;

import nextstep.subway.domain.entity.Line;
import nextstep.subway.domain.exception.BusinessException;
import nextstep.subway.domain.specification.shared.AbstractSpecification;

public class SingleSectionSpec extends AbstractSpecification<Line> {
    @Override
    public boolean isSatisfiedBy(Line line) {
        return line.getSections().size() != 1;
    }

    @Override
    public void check(Line line) throws BusinessException {
        if (!isSatisfiedBy(line))
            throw new BusinessException("상행 종점역과 하행 종점역만 있는 경우(구간이 1개인 경우) 역을 삭제할 수 없습니다.");
    }
}
