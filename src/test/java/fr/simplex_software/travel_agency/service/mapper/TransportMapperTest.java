package fr.simplex_software.travel_agency.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TransportMapperTest {

    private TransportMapper transportMapper;

    @BeforeEach
    public void setUp() {
        transportMapper = new TransportMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(transportMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(transportMapper.fromId(null)).isNull();
    }
}
