package RewardsandPunishments;

import RewardsAndPunishments.RegularResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RegularResourceTest {

    private RegularResource r1;

    @BeforeEach
    void setUp()  {
        r1 = new RegularResource();
    }

    @Test
    public void BonusResourceTest()  {
        assertEquals("Reward", r1.name);
        assertNotNull(r1.image);
        assertEquals(5, r1.points);
        assertEquals(true, r1.collision);
    }
}
