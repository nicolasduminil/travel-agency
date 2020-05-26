package fr.simplex_software.travel_agency.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.simplex_software.travel_agency.web.rest.TestUtil;

public class DealTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Deal.class);
        Deal deal1 = new Deal();
        deal1.setId(1L);
        Deal deal2 = new Deal();
        deal2.setId(deal1.getId());
        assertThat(deal1).isEqualTo(deal2);
        deal2.setId(2L);
        assertThat(deal1).isNotEqualTo(deal2);
        deal1.setId(null);
        assertThat(deal1).isNotEqualTo(deal2);
    }
}
