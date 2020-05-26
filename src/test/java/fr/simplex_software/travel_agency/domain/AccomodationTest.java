package fr.simplex_software.travel_agency.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.simplex_software.travel_agency.web.rest.TestUtil;

public class AccomodationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Accomodation.class);
        Accomodation accomodation1 = new Accomodation();
        accomodation1.setId(1L);
        Accomodation accomodation2 = new Accomodation();
        accomodation2.setId(accomodation1.getId());
        assertThat(accomodation1).isEqualTo(accomodation2);
        accomodation2.setId(2L);
        assertThat(accomodation1).isNotEqualTo(accomodation2);
        accomodation1.setId(null);
        assertThat(accomodation1).isNotEqualTo(accomodation2);
    }
}
