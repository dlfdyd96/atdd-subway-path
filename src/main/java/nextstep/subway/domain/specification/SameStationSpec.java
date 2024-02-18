package nextstep.subway.domain.specification;

import nextstep.subway.domain.entity.Line;
import nextstep.subway.domain.exception.BusinessException;
import nextstep.subway.domain.specification.shared.AbstractSpecification;

public class SameStationSpec extends AbstractSpecification<Long> {

    private Line line;

    public SameStationSpec(Line line) {
        this.line = line;
    }

    @Override
    public boolean isSatisfiedBy(Long newStationId) {
        return line.getSections().stream()
                .noneMatch(s -> s.getDownStation().getId().equals(newStationId));
    }

    @Override
    public void check(Long newStationId) throws BusinessException {
        if (!isSatisfiedBy(newStationId))
            throw new BusinessException("이미 해당 노선에 등록되어있는 역은 새로운 구간의 하행역이 될 수 없다.");
    }
}
