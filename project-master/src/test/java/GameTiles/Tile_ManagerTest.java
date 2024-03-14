package GameTiles;

import main.GameWindow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class Tile_ManagerTest {
    private GameWindow gw;
    private Game_Tiles[] gT1;
    private int[][] mapN;

    @BeforeEach
    void setUp() {
        gw = new GameWindow();
        gT1 = new Game_Tiles[10];
        mapN = new int[gw.maxWorldCol][gw.maxWorldRow];

    }

    void Tile_ManagerConstructorTest() {
        assertNotNull(gw);
        assertNotNull(gT1);
    }


    @Test
    void loadMap() {
        assertNotNull(mapN);
    }


}
