import battleship.*;

import java.util.Set;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        Set<int[]> P1ShipPositions = new HashSet<int[]>();
        P1ShipPositions.add(new int[] {0,0});
        P1ShipPositions.add(new int[] {0,1});
        Set<int[]> P2ShipPositions = new HashSet<int[]>();
        P2ShipPositions.add(new int[] {0,0});
        P2ShipPositions.add(new int[] {0,1});
        Battleship battleship = new Battleship(3,2,1,
                P1ShipPositions, P2ShipPositions);
    }
}
