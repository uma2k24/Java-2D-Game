package Characters;

import main.GameWindow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ScavengerTest {
	private GameWindow gameWindow;
	private Scavenger scavenger;
	
	@BeforeEach
	public void setUp() {
		gameWindow = new GameWindow();
		scavenger = new Scavenger(gameWindow);
	}
	
	@Test
	public void ScavengerConstructorTest() {
		assertNotNull(gameWindow);
		assertEquals("down", scavenger.direction);
		assertEquals(3, scavenger.speed);
	}
	
	@Test
	public void getEnemyImageTest() {
		assertNotNull(scavenger.up1);
		assertNotNull(scavenger.up2);
		assertNotNull(scavenger.down1);
		assertNotNull(scavenger.down2);
		assertNotNull(scavenger.left1);
		assertNotNull(scavenger.left2);
		assertNotNull(scavenger.right1);
		assertNotNull(scavenger.right2);
	}
	
	@Test
	public void getXTest() {
		scavenger.worldX = 10;
		assertEquals(10, scavenger.getX());
		scavenger.worldX = -5;
		assertEquals(-5, scavenger.getX());
		scavenger.worldX = 100000;
		assertEquals(100000, scavenger.getX());
		scavenger.worldX = -100000;
		assertEquals(-100000, scavenger.getX());
	}
	
	@Test
	public void getYTest() {
		scavenger.worldY = 10;
		assertEquals(10, scavenger.getY());
		scavenger.worldY = -5;
		assertEquals(-5, scavenger.getY());
		scavenger.worldY = 100000;
		assertEquals(100000, scavenger.getY());
		scavenger.worldY = -100000;
		assertEquals(-100000, scavenger.getY());
	}
	
	@Test
	public void setXTest() {
		scavenger.setX(10);
		assertEquals(10, scavenger.getX());
		scavenger.setX(-5);
		assertEquals(-5, scavenger.getX());
		scavenger.setX(100000);
		assertEquals(100000, scavenger.getX());
		scavenger.setX(-100000);
		assertEquals(-100000, scavenger.getX());
	}
	
	@Test
	public void setYTest() {
		scavenger.setY(10);
		assertEquals(10, scavenger.getY());
		scavenger.setY(-5);
		assertEquals(-5, scavenger.getY());
		scavenger.setY(100000);
		assertEquals(100000, scavenger.getY());
		scavenger.setY(-100000);
		assertEquals(-100000, scavenger.getY());
	}
	
	@Test
	public void getDirectionTest() {
		assertEquals("down", scavenger.getDirection());
		scavenger.direction = "up";
		assertEquals("up", scavenger.getDirection());
		scavenger.direction = "right";
		assertEquals("right", scavenger.getDirection());
		scavenger.direction = "left";
		assertEquals("left", scavenger.getDirection());
	}
	
	@Test
	public void setDirectionTest() {
		assertEquals("down",scavenger.getDirection());
		scavenger.setDirection("up");
		assertEquals("up", scavenger.getDirection());
		scavenger.setDirection("right");
		assertEquals("right", scavenger.getDirection());
		scavenger.setDirection("left");
		assertEquals("left", scavenger.getDirection());
	}
	
	@Test
	public void setActionTest() {
		scavenger.setAction();
		assertEquals(1,scavenger.actionLockCounter);
		scavenger.actionLockCounter = 119;
		scavenger.setAction();
		assertEquals(0, scavenger.actionLockCounter);
	}
	
	@Test
	public void interactPlayerTest() {
		scavenger.interactPlayer(999);
		assertEquals(false, gameWindow.gameLost);
		scavenger.interactPlayer(2);
		assertEquals(true, gameWindow.gameLost);
	}
}
