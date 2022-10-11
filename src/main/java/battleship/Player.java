package battleship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {
    private Battleground battleground;
    private int numOfMissiles;

    public Player(
            Battleground battleground,
            int numOfMissiles) {
        this.battleground = battleground;
        this.numOfMissiles = numOfMissiles;

        PlayerPropertyValidator propertyValidator = new PlayerPropertyValidator(this.numOfMissiles);
        propertyValidator.validatePlayerProperties();
    }
}
