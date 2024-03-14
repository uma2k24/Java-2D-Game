package main;

import main.GameWindow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UITest {
    private GameWindow gameWindow;
    private UI ui;

    @BeforeEach
    public void setUp() {
        gameWindow = new GameWindow();
        ui = new UI(gameWindow);
    }

    @Test
    public void UIConstructorTest() {
        assertNotNull(gameWindow);
        assertNotNull(ui);
        assertEquals(0, ui.commandNumber);
        assertEquals(0, ui.notif_timer);
    }
}
