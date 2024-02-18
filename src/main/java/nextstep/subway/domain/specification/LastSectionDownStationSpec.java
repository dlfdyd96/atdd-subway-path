package nextstep.subway.domain.specification;

import nextstep.subway.domain.entity.Line;
import nextstep.subway.domain.exception.BusinessException;
import nextstep.subway.domain.specification.shared.AbstractSpecification;

public class LastSectionDownStationSpec extends AbstractSpecification<Long> {
    private final Line line;

    public LastSectionDownStationSpec(Line line) {
        this.line = line;
    }

    @Override
    public boolean isSatisfiedBy(Long downStationId) {
        if (line.getSections().isEmpty()) {
            return true;
        }
        return line.getSections().get(line.getSections().size() - 1).
                getDownStation().getId().
                equals(downStationId);
    }

    @Override
    public void check(Long downStationId) {
        if (!isSatisfiedBy(downStationId))
            throw new BusinessException("상행역은 해당 노선에 등록되어있는 하행 종점역이어야 합니다.");
    }
}
