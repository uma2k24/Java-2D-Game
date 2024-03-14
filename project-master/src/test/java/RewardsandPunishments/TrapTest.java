package RewardsandPunishments;

import RewardsAndPunishments.Trap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TrapTest {
	private Trap trap;
	
	@BeforeEach
	public void setup() {
		trap = new Trap();
	}
	
	@Test
	public void TrapContructorTest() {
		assertEquals("Punishment", trap.name);
		assertNotNull(trap.image);
		assertEquals(-10, trap.points);
		assertEquals(true, trap.collision);
	}
}
