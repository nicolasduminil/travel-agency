package fr.simplex_software.travel_agency.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.simplex_software.travel_agency.web.rest.TestUtil;

public class AccomodationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccomodationDTO.class);
        AccomodationDTO accomodationDTO1 = new AccomodationDTO();
        accomodationDTO1.setId(1L);
        AccomodationDTO accomodationDTO2 = new AccomodationDTO();
        assertThat(accomodationDTO1).isNotEqualTo(accomodationDTO2);
        accomodationDTO2.setId(accomodationDTO1.getId());
        assertThat(accomodationDTO1).isEqualTo(accomodationDTO2);
        accomodationDTO2.setId(2L);
        assertThat(accomodationDTO1).isNotEqualTo(accomodationDTO2);
        accomodationDTO1.setId(null);
        assertThat(accomodationDTO1).isNotEqualTo(accomodationDTO2);
    }
}
