package Characters;

import main.GameWindow;

import java.awt.*;
import java.util.Random;
/**
 * A class of a movable entity that contains an object of Coordinates class and implements from the
 * Entity class
 */
public class Scavenger extends Entity{
    public int actionLockCounter = 0;

    /**
     * Creates a Scavenger object that can be shown on screen. Direction is set to 'right' by default
     *
     * @param gw - the game window
     */
    public Scavenger(GameWindow gw) {
        super(gw);

        direction = "down";
        speed = 3;

        getEnemyImage();
    }
    
    /**
     * Sets the image members with the Scavenger resources.
     */
    public void getEnemyImage() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        up1 = toolkit.getImage("src/main/resources/scavenger/scavenger_up_1.png");
        up2 = toolkit.getImage("src/main/resources/scavenger/scavenger_up_2.png");

        down1 = toolkit.getImage("src/main/resources/scavenger/scavenger_down_1.png");
        down2 = toolkit.getImage("src/main/resources/scavenger/scavenger_down_2.png");

        left1 = toolkit.getImage("src/main/resources/scavenger/scavenger_left_1.png");
        left2 = toolkit.getImage("src/main/resources/scavenger/scavenger_left_2.png");

        right1 = toolkit.getImage("src/main/resources/scavenger/scavenger_right_1.png");
        right2 = toolkit.getImage("src/main/resources/scavenger/scavenger_right_2.png");
    }

    /**
     * Returns the X-coordinate of the Scavenger
     */
    public int getX() {
        return worldX;
    }

    /**
     * Returns the Y-coordinate of the Scavenger
     */
    public int getY() {
        return worldY;
    }

    /**
     * Sets the X-coordinate of the Scavenger
     */
    public void setX(int x) {
        worldX = x;
    }

    /**
     * Returns the X-coordinate of the Scavenger
     */
    public void setY(int y) {
        worldY = y;
    }

    /**
     * Returns the direction the Scavenger is facing (up, down, left, right).
     *
     * @return the direction the Scavenger is facing
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Sets the direction the Scavenger is facing.
     *
     * @param direction - either up, down, left, right
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
    * Sets the direction for scavenger movement
    */
    public void setAction(){
        actionLockCounter++;

        if(actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            } else if (i <= 50) {
                direction = "down";
            } else if (i <= 75) {
                direction = "left";
            } else {
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }
    /**
     * Paints and displays the Scavenger to the screen depending on its direction.
     */
    public void draw(Graphics2D g2) {

        Image image = null;

        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
            }
        }

        int screenX = worldX - gw.gatherer.worldX + gw.gatherer.screenX;
        int screenY = worldY - gw.gatherer.worldY + gw.gatherer.screenY;

        if (worldX + gw.tileSize > gw.gatherer.worldX - gw.gatherer.screenX &&
                worldX - gw.tileSize < gw.gatherer.worldX + gw.gatherer.screenX &&
                worldY + gw.tileSize > gw.gatherer.worldY - gw.gatherer.screenY &&
                worldY - gw.tileSize < gw.gatherer.worldY + gw.gatherer.screenY) {
            g2.drawImage(image, screenX, screenY, gw.tileSize, gw.tileSize, null);
        }
    }

    /**
     * implement Scavenger to track the player
     * Checks whether the Scavenger has collided with the Gatherer and animates the Scavenger.
     * @param gathererX : the gatherer's x coordinate
     * @param gathererY : the gatherer's y coordinate
     */
    public void update(int gathererX, int gathererY){
        int disX = Math.abs(worldX-gathererX);
        int disY = Math.abs(worldY-gathererY);

        //implement Scavenger to track the player
        // Scavenger move towards a direction that makes them closest to
        // the current position of the main character
        if(disX>=disY) {
            if (gathererX < worldX) {
                direction = "left";
            } else if (gathererX > worldX) {
                direction = "right";
            }
        }
        else {
            if (gathererY < worldY) {
                direction = "up";
            } else if (gathererY > worldY) {
                direction = "down";
            }
        }

        setAction();
        this.collisionOn = false;
        gw.cch.checkTile(this);
        int checkCollision = gw.cch.checkGatherer(this, true);
        interactPlayer(checkCollision);
        //if not collision with wall, change the Scavenger coordinate
        if (!collisionOn) {
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
            }

        }

        //animates the Scavenger
        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    /**
     * Ends the game if any other number is passed into this method.
     * 
     *  @param i - an integer
     */
    public void interactPlayer(int i) {
        if(i != 999){
            gw.gameLost = true;
        }
    }
    

}
