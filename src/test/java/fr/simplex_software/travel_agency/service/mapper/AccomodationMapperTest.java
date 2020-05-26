package fr.simplex_software.travel_agency.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AccomodationMapperTest {

    private AccomodationMapper accomodationMapper;

    @BeforeEach
    public void setUp() {
        accomodationMapper = new AccomodationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(accomodationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(accomodationMapper.fromId(null)).isNull();
    }
}
