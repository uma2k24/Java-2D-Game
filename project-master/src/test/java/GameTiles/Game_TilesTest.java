package GameTiles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Game_TilesTest {
    private Game_Tiles gameT1;

    @BeforeEach
    void setUp()  {
        gameT1 = new Game_Tiles();
    }

    @Test
    public void BonusResourceTest()  {
        assertEquals(false, gameT1.collide);
    }
}
