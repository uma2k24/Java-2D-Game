package RewardsAndPunishments;

import java.awt.*;



/**
 * Class of Regular resources in this game and extends the entity class
 * each regular resource add 5 points to score
 */
public class RegularResource extends main.Object{

    /**
     * Construct a regular resource item
     *
     */
    public RegularResource(){
        name = "Reward";

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        image = toolkit.getImage("src/main/resources/Reward.png");

        points = 5;
        collision = true;
    }

}
