package battleship;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CLIDisplayer implements Displayer {
    private PrintStream out;
    private Set<List<Integer>> shipPositions;
    private Set<List<Integer>> destroyedShipPositions;
    private Set<List<Integer>> failedMissileAttackPositions;

    public CLIDisplayer(PrintStream out) {
        this.out = out;
    }

    public void displayPlayerReport(PlayerReport player) throws IllegalAccessException {
        for (Field field : player.getClass().getDeclaredFields())
            out.println(field.getName() + ":" + field.get(player));
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
                    out.print("X");
                } else if (isCoordinateOfIntactShip(currentCoordinate)) {
                    out.print("B");
                } else if (isCoordinateOfFailedMissileAttack(currentCoordinate)) {
                    out.print("O");
                } else {
                    out.print("_");
                }
                out.print(" ");
            }
            int lastRow = battlegroundSize - 1;
            if ( x != lastRow) {
                out.println();
            }
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
