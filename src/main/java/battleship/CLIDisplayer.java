package battleship;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CLIDisplayer implements Displayer {
    private Set<List<Integer>> shipPositions;
    private Set<List<Integer>> destroyedShipPositions;
    private Set<List<Integer>> failedMissileAttackPositions;

    public void displayPlayerReport(PlayerReport player) throws IllegalAccessException {
        for (Field field : player.getClass().getDeclaredFields())
            System.out.println(field.getName() + ":" + field.get(player));
    }

    public void displayBattlegroundReport(Battleground battleground) {
        int battlegroundSize = battleground.getBattlegroundSize();
        this.shipPositions = battleground.getShipPositions();
        this.destroyedShipPositions = battleground.getSuccessfulAttacks();
        this.failedMissileAttackPositions = battleground.getFailedAttacks();

        for (int x = 0; x < battlegroundSize; x++) {
            for (int y = 0; y < battlegroundSize; y++) {

                List<Integer> currentCoordinate = new ArrayList<>(List.of(x,y));
                if (isCoordinateOfDestroyedShip(currentCoordinate)) {
                    System.out.print("X");
                } else if (isCoordinateOfIntactShip(currentCoordinate)) {
                    System.out.print("B");
                } else if (isCoordinateOfFailedMissileAttack(currentCoordinate)) {
                    System.out.print("O");
                } else {
                    System.out.print("_");
                }
                System.out.print(" ");
            }
            System.out.println();
        }

    }

    private boolean isCoordinateOfDestroyedShip(List<Integer> coordinate) { //X
        return destroyedShipPositions.contains(coordinate);
    }

    private boolean isCoordinateOfIntactShip(List<Integer> coordinate) { //B
        return shipPositions.contains(coordinate) &&
                !isCoordinateOfDestroyedShip(coordinate) &&
                !isCoordinateOfFailedMissileAttack(coordinate);
    }

    private boolean isCoordinateOfFailedMissileAttack(List<Integer> coordinate) { //O
        return failedMissileAttackPositions.contains(coordinate);
    }


}
