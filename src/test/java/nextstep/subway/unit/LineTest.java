package nextstep.subway.unit;

import nextstep.subway.domain.entity.Line;
import nextstep.subway.domain.entity.Section;
import nextstep.subway.domain.entity.Station;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LineTest {
    @Test
    void addSection() {
        // Line 인스턴스를 만들고
        // addSection 을 호출했을 때
        // Section 에 새로운 구간이 추가된다.

        Line line = new Line("이호선", "green");

        Station upStation = new Station(1L, "강남역");
        Station downStation = new Station(2L, "역삼역");
        Station newStation = new Station(3L, "선릉역");
        int distance = 10;

        line.addSection(upStation, downStation, distance);
        line.addSection(downStation, newStation, distance);

        assertThat(line.getSections()).hasSize(2);
    }

    @Test
    void addSectionInTheMiddle() {
        // Given
        // Line 인스턴스를 만들고
        Line line = new Line("이호선", "green");

        // addSection 을 호출하여 초기 구간을 추가
        Station upStation = new Station(1L, "강남역");
        Station downStation = new Station(2L, "선릉역");
        int distance = 10;
        line.addSection(upStation, downStation, distance);

        // When
        // addSection 을 다시 호출하여 중간에 새로운 구간을 추가
        Station middleStation = new Station(3L, "역삼역");
        line.addSection(upStation, middleStation, 5);

        // Then
        // getSections 메소드를 호출하여 구간 목록을 가져옴
        List<Section> sections = line.getSections();

        // 구간 목록의 크기가 2인지 확인
        assertThat(sections).hasSize(2);

        // 각 구간의 상행역, 하행역, 거리가 올바른지 확인
        assertThat(sections.get(0).getUpStation()).isEqualTo(upStation);
        assertThat(sections.get(0).getDownStation()).isEqualTo(middleStation);
        assertThat(sections.get(0).getDistance()).isEqualTo(5);

        assertThat(sections.get(1).getUpStation()).isEqualTo(middleStation);
        assertThat(sections.get(1).getDownStation()).isEqualTo(downStation);
        assertThat(sections.get(1).getDistance()).isEqualTo(5);
    }

    @Test
    void getStations() {
        // Line 인스턴스를 만들고
        // getStations 을 호출했을 때
        // 등록된 구간의 상행역과 하행역이 순서대로 반환된다.

        Line line = new Line("이호선", "green");

        Station upStation = new Station(1L, "강남역");
        Station downStation = new Station(2L, "역삼역");
        Station newStation = new Station(3L, "선릉역");
        int distance = 10;

        line.addSection(upStation, downStation, distance);
        line.addSection(downStation, newStation, distance);

        assertThat(line.getSections()).hasSize(2);
    }

    @Test
    void removeSection() {

        // Line 인스턴스를 만들고
        Line line = new Line("이호선", "green");

        Station upStation = new Station(1L,"강남역");
        Station downStation = new Station(2L, "역삼역");
        Station newStation = new Station(3L, "선릉역");
        int distance = 10;
        line.addSection(upStation, downStation, distance);
        line.addSection(downStation, newStation, distance);

        // deleteSection 을 호출했을 때
        line.deleteSection(newStation.getId());

        // Section 에 구간이 삭제된다.
        assertThat(line.getSections()).hasSize(1);
    }
}
