package battleship;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//TODO this should be a class with static methods. Used by context/battleground
public class BattlegroundResultParser {
    private static Set<List<Integer>> shipPositions = new HashSet<>();
    private static Set<List<Integer>> destroyedShipPositions = new HashSet<>();
    private static Set<List<Integer>> failedMissileAttackPositions = new HashSet<>();

    public static String getBattlegroundReport(Battleground battleground) {
        int battlegroundSize = battleground.getBattlegroundSize();
        shipPositions = battleground.getShipPositions();
        destroyedShipPositions = battleground.getSuccessfulAttacks();
        failedMissileAttackPositions = battleground.getFailedAttacks();

        String result = "";
        for (int x = 0; x < battlegroundSize; x++) {
            for (int y = 0; y < battlegroundSize; y++) {

                List<Integer> currentCoordinate = new ArrayList<>(List.of(x,y));
                if (isCoordinateOfDestroyedShip(currentCoordinate)) {
                    result += "X";
                } else if (isCoordinateOfIntactShip(currentCoordinate)) {
                    result += "B";
                } else if (isCoordinateOfFailedMissileAttack(currentCoordinate)) {
                    result += "O";
                } else {
                    result += "_";
                }
                result += " ";
            }
            int lastRow = battlegroundSize - 1;
            if ( x != lastRow) {
                result += "\n";
            }
        }
        return result;
    }

    private static boolean isCoordinateOfDestroyedShip(List<Integer> coordinate) { //X
        return destroyedShipPositions.contains(coordinate);
    }

    private static boolean isCoordinateOfIntactShip(List<Integer> coordinate) { //B
        return shipPositions.contains(coordinate) &&
                !isCoordinateOfDestroyedShip(coordinate) &&
                !isCoordinateOfFailedMissileAttack(coordinate);
    }

    private static boolean isCoordinateOfFailedMissileAttack(List<Integer> coordinate) { //O
        return failedMissileAttackPositions.contains(coordinate);
    }


}
