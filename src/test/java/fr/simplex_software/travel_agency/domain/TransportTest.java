package fr.simplex_software.travel_agency.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.simplex_software.travel_agency.web.rest.TestUtil;

public class TransportTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Transport.class);
        Transport transport1 = new Transport();
        transport1.setId(1L);
        Transport transport2 = new Transport();
        transport2.setId(transport1.getId());
        assertThat(transport1).isEqualTo(transport2);
        transport2.setId(2L);
        assertThat(transport1).isNotEqualTo(transport2);
        transport1.setId(null);
        assertThat(transport1).isNotEqualTo(transport2);
    }
}
