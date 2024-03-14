package main;

import Characters.Entity;
import GameTiles.Tile_Manager;

/**
 * Class for collision between the entities, objects and tiles on the maze
 */
public class CollisionChecker {

    GameWindow gw;

    /**
     * Sets the game window for which it checks for collision
     * @param gw - game window on which the object might collide
     */
    public CollisionChecker(GameWindow gw)
    {
        this.gw = gw;
    }

    /**
     * Checks the tile on which the entity moves to
     * @param e - Entity (gatherer or scavenger) that moves on the tile to be checked
     */
    public void checkTile(Entity e)
    {
        //assigns the variables to check for collision
        int entityLeftWorldX = e.worldX + e.solidArea.x;
        int entityRightWorldX = e.worldX + e.solidArea.x + e.solidArea.width;
        int entityTopWorldY = e.worldY + e.solidArea.y;
        int entityBottomWorldY = e.worldY + e.solidArea.y + e.solidArea.height;

        //assigns the variables for the entity's position
        int entityLeftCol = entityLeftWorldX/gw.tileSize;
        int entityRightCol = entityRightWorldX/gw.tileSize;
        int entityTopRow = entityTopWorldY/gw.tileSize;
        int entityBottomRow = entityBottomWorldY/gw.tileSize;

        //checks for collision according to the direction the entity is moving towards
        int tileNum1, tileNum2;
        switch (e.direction) {
            case "up" -> {
                entityTopRow = (entityTopWorldY - e.speed) / gw.tileSize;
                tileNum1 = gw.background.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gw.background.mapTileNum[entityRightCol][entityTopRow];
                if (Tile_Manager.gameTiles[tileNum1].collide || gw.background.gameTiles[tileNum2].collide == true) {
                    e.collisionOn = true;
                }
            }
            case "down" -> {
                entityBottomRow = (entityBottomWorldY + e.speed) / gw.tileSize;
                tileNum1 = gw.background.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gw.background.mapTileNum[entityRightCol][entityBottomRow];
                if (gw.background.gameTiles[tileNum1].collide == true || gw.background.gameTiles[tileNum2].collide == true) {
                    e.collisionOn = true;
                }
            }
            case "left" -> {
                entityLeftCol = (entityLeftWorldX - e.speed) / gw.tileSize;
                tileNum1 = gw.background.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gw.background.mapTileNum[entityLeftCol][entityBottomRow];
                if (gw.background.gameTiles[tileNum1].collide == true || gw.background.gameTiles[tileNum2].collide == true) {
                    e.collisionOn = true;
                }
            }
            case "right" -> {
                entityRightCol = (entityRightWorldX + e.speed) / gw.tileSize;
                tileNum1 = gw.background.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gw.background.mapTileNum[entityRightCol][entityBottomRow];
                if (gw.background.gameTiles[tileNum1].collide == true || gw.background.gameTiles[tileNum2].collide == true) {
                    e.collisionOn = true;
                }
            }
        }
    }

    /**
     * Checks whether the gatherer is colliding with an object
     * @param e - Entity (gatherer or scavenger) that moves on the tile to be checked
     * @param player - gatherer which collides with the object
     */
    public int checkObject(Entity e, boolean player) {
        int index = 999; //sets the index to a temporary value

        //checks for collision according to the direction the entity is moving towards
        for (int i = 0; i < gw.obj.length; i++) {
            if (gw.obj[i] != null) {
                e.solidArea.x = e.worldX + e.solidArea.x;
                e.solidArea.y = e.worldY + e.solidArea.y;

                gw.obj[i].solidArea.x = gw.obj[i].worldX + gw.obj[i].solidArea.x;
                gw.obj[i].solidArea.y = gw.obj[i].worldY + gw.obj[i].solidArea.y;

                switch (e.direction) {
                    case "up":
                        e.solidArea.y -= e.speed;
                        if (e.solidArea.intersects(gw.obj[i].solidArea)) {
                            if(gw.obj[i].collision == true) {
                                e.collisionOn = true;
                            }

                            if(player == true)
                            {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        e.solidArea.y += e.speed;
                        if (e.solidArea.intersects(gw.obj[i].solidArea)) {
                            if(gw.obj[i].collision == true) {
                                e.collisionOn = true;
                            }

                            if(player == true)
                            {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        e.solidArea.x -= e.speed;
                        if (e.solidArea.intersects(gw.obj[i].solidArea)) {
                            if(gw.obj[i].collision == true) {
                                e.collisionOn = true;
                            }

                            if(player == true)
                            {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        e.solidArea.x += e.speed;
                        if (e.solidArea.intersects(gw.obj[i].solidArea)) {
                            if(gw.obj[i].collision == true) {
                                e.collisionOn = true;
                            }

                            if(player == true)
                            {
                                index = i;
                            }
                        }
                        break;
                }

                e.solidArea.x = e.solidAreaDefaultX;
                e.solidArea.y = e.solidAreaDefaultY;

                gw.obj[i].solidArea.x = gw.obj[i].solidAreaDefaultX;
                gw.obj[i].solidArea.y = gw.obj[i].solidAreaDefaultY;
            }
        }

        return index; //returns the true index
    }

    /**
     * Checks whether the gathere is colliding with a scavenger
     * @param e - Entity (gatherer or scavenger) that moves on the tile to be checked
     * @param player - gatherer which collides with the object
     */
    public int checkScavenger(Entity e, boolean player) {
        int index = 999;

        //checks for collision according to the direction the entity is moving towards
        for (int i = 0; i < gw.scavengers.length; i++) {
            if (gw.scavengers[i] != null) {
                e.solidArea.x = e.worldX + e.solidArea.x;
                e.solidArea.y = e.worldY + e.solidArea.y;

                gw.scavengers[i].solidArea.x = gw.scavengers[i].worldX + gw.scavengers[i].solidArea.x;
                gw.scavengers[i].solidArea.y = gw.scavengers[i].worldY + gw.scavengers[i].solidArea.y;


                switch (e.direction) {
                    case "up":
                        e.solidArea.y -= e.speed;
                        if (e.solidArea.intersects(gw.scavengers[i].solidArea)) {
                            if(gw.scavengers[i].collisionOn == true) {
                                e.collisionOn = true;
                            }

                            if(player == true)
                            {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        e.solidArea.y += e.speed;
                        if (e.solidArea.intersects(gw.scavengers[i].solidArea)) {
                            if(gw.scavengers[i].collisionOn == true) {
                                e.collisionOn = true;
                            }

                            if(player == true)
                            {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        e.solidArea.x -= e.speed;
                        if (e.solidArea.intersects(gw.scavengers[i].solidArea)) {
                            if(gw.scavengers[i].collisionOn == true) {
                                e.collisionOn = true;
                            }

                            if(player == true)
                            {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        e.solidArea.x += e.speed;
                        if (e.solidArea.intersects(gw.scavengers[i].solidArea)) {
                            if(gw.scavengers[i].collisionOn == true) {
                                e.collisionOn = true;
                            }

                            if(player == true)
                            {
                                index = i;
                            }
                        }
                        break;
                }

                e.solidArea.x = e.solidAreaDefaultX;
                e.solidArea.y = e.solidAreaDefaultY;

                gw.scavengers[i].solidArea.x = gw.scavengers[i].solidAreaDefaultX;
                gw.scavengers[i].solidArea.y = gw.scavengers[i].solidAreaDefaultY;
            }
        }

        return index; //returns the index value
    }

    /**
     * Checks whether the scavenger and the gatherer are colliding
     * @param e - Entity (gatherer or scavenger) that moves on the tile to be checked
     * @param enemy - gatherer which collides with the object
     */
    public int checkGatherer(Entity e, boolean enemy) {
        int index = 999;

        //if there is a gatherer on the maze checks for collision
        if (gw.gatherer != null) {
            e.solidArea.x = e.worldX + e.solidArea.x;
            e.solidArea.y = e.worldY + e.solidArea.y;

            gw.gatherer.solidArea.x = gw.gatherer.worldX + gw.gatherer.solidArea.x;
            gw.gatherer.solidArea.y = gw.gatherer.worldY + gw.gatherer.solidArea.y;

            //checks for collision in the particular direction
            switch (e.direction) {
                case "up":
                    e.solidArea.y -= e.speed;
                    if (e.solidArea.intersects(gw.gatherer.solidArea)) {
                        if(gw.gatherer.collisionOn == true) {
                            e.collisionOn = true;
                        }

                        if(enemy == true)
                        {
                            index = 0;
                        }
                    }
                    break;
                case "down":
                    e.solidArea.y += e.speed;
                    if (e.solidArea.intersects(gw.gatherer.solidArea)) {
                        if(gw.gatherer.collisionOn == true) {
                            e.collisionOn = true;
                        }

                        if(enemy == true)
                        {
                            index = 0;
                        }
                    }
                    break;
                case "left":
                    e.solidArea.x -= e.speed;
                    if (e.solidArea.intersects(gw.gatherer.solidArea)) {
                        if(gw.gatherer.collisionOn == true) {
                            e.collisionOn = true;
                        }

                        if(enemy == true)
                        {
                            index = 0;
                        }
                    }
                    break;
                case "right":
                    e.solidArea.x += e.speed;
                    if (e.solidArea.intersects(gw.gatherer.solidArea)) {
                        if(gw.gatherer.collisionOn == true) {
                            e.collisionOn = true;
                        }

                        if(enemy == true)
                        {
                            index = 0;
                        }
                    }
                    break;
            }

            e.solidArea.x = e.solidAreaDefaultX;
            e.solidArea.y = e.solidAreaDefaultY;

            gw.gatherer.solidArea.x = gw.gatherer.solidAreaDefaultX;
            gw.gatherer.solidArea.y = gw.gatherer.solidAreaDefaultY;
        }

        return index; //returns the index value
    }
}
