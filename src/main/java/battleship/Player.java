package battleship;

import java.util.List;

public class Player {
    private Battleground battleground;
    protected boolean isAlive = true;
    private int numOfMissilesAvailable;
    private int numOfMissilesFired;

    public Player(int numOfMissiles) {
        this.numOfMissilesAvailable = numOfMissiles;
        this.numOfMissilesFired = 0;

        PlayerPropertyValidator propertyValidator = new PlayerPropertyValidator(this.numOfMissilesAvailable);
        propertyValidator.validatePlayerProperties();
    }

    public void setBattleground(Battleground battleground) {
        this.battleground = battleground;
        battleground.updateAliveStatus();
    }

    protected Battleground getBattleground() {
        return battleground;
    }

    public boolean isAlive() {
        return isAlive;
    }

    protected void setAliveStatus(boolean status) {
        this.isAlive = status;
    }

    public int getNumOfMissilesAvailable() {
        return numOfMissilesAvailable;
    }

    public int getNumOfMissilesFired() {
        return numOfMissilesFired;
    }

    public int getNumOfDestroyedShips() {
        return battleground.getNumOfDestroyedShips();
    }

    public int getNumOfIntactShips() {
        return battleground.getNumOfIntactShips();
    }

    public void attack(Player target, List<Integer> attackCoordinate) {
        if (numOfMissilesAvailable > 0) {
            target.attacked(attackCoordinate);
            numOfMissilesAvailable -= 1;
            numOfMissilesFired += 1;
        }
    }

    public void attacked(List<Integer> attackCoordinate) {
        battleground.attacked(attackCoordinate);
    }
}
