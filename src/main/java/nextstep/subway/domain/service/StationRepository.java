package nextstep.subway.domain.service;

import nextstep.subway.domain.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Long> {
}