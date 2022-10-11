package battleship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {
    private Battleground battleground;
    protected boolean isAlive = true;
    private int numOfMissiles;

    public Player(int numOfMissiles) {
        this.numOfMissiles = numOfMissiles;

        PlayerPropertyValidator propertyValidator = new PlayerPropertyValidator(this.numOfMissiles);
        propertyValidator.validatePlayerProperties();
    }

    public void setBattleground(Battleground battleground) {
        this.battleground = battleground;
        battleground.updateAliveStatus();
    }

    public boolean isAlive() {
        return isAlive;
    }

    protected void setAliveStatus(boolean status) {
        this.isAlive = status;
    }

    public int getNumOfMissiles() {
        return numOfMissiles;
    }

    public void attack(Player target, int[] attackCoordinate) {
        if (numOfMissiles > 0) {
            target.attacked(attackCoordinate);
            numOfMissiles -= 1;
        }
    }

    public void attacked(int[] attackCoordinate) {
        battleground.attacked(attackCoordinate);
    }
}
