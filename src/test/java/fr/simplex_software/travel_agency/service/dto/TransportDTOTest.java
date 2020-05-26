package fr.simplex_software.travel_agency.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.simplex_software.travel_agency.web.rest.TestUtil;

public class TransportDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TransportDTO.class);
        TransportDTO transportDTO1 = new TransportDTO();
        transportDTO1.setId(1L);
        TransportDTO transportDTO2 = new TransportDTO();
        assertThat(transportDTO1).isNotEqualTo(transportDTO2);
        transportDTO2.setId(transportDTO1.getId());
        assertThat(transportDTO1).isEqualTo(transportDTO2);
        transportDTO2.setId(2L);
        assertThat(transportDTO1).isNotEqualTo(transportDTO2);
        transportDTO1.setId(null);
        assertThat(transportDTO1).isNotEqualTo(transportDTO2);
    }
}
