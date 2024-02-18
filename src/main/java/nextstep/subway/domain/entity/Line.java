package nextstep.subway.domain.entity;

import nextstep.subway.domain.specification.EmptySectionSpec;
import nextstep.subway.domain.specification.LastSectionDownStationSpec;
import nextstep.subway.domain.specification.SameStationSpec;
import nextstep.subway.domain.specification.SingleSectionSpec;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String color;

    @OneToMany(mappedBy = "line", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Section> sections = new ArrayList<>();


    public Line(long id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public Line(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Line() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void update(final String name, final String color) {
        if (name != null) {
            this.name = name;
        }
        if (color != null) {
            this.color = color;
        }
    }

    public void addSection(Station upStation, Station downStation, int distance) {
        var newSection = new Section(this, upStation, downStation, distance);

        var sameDownStationSpec = new SameStationSpec(this);
        var lastSectionDownStationSpec = new LastSectionDownStationSpec(this);
        sameDownStationSpec.check(newSection.getDownStation().getId());
        lastSectionDownStationSpec.check(newSection.getUpStation().getId());

        sections.add(newSection);
    }

    public void deleteSection(Long stationId) {
        var emptySpec = new EmptySectionSpec();
        var lastSectionDownStationSpec = new LastSectionDownStationSpec(this);
        var singleSectionSpec = new SingleSectionSpec();

        emptySpec.check(this);
        singleSectionSpec.check(this);
        lastSectionDownStationSpec.check(stationId);

        sections.remove(sections.size() - 1);
    }
}
