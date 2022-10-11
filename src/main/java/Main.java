import battleship.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<int[]> P1ShipPositions = new ArrayList<int[]>();
        P1ShipPositions.add(new int[] {0,0});
        P1ShipPositions.add(new int[] {0,1});
        List<int[]> P2ShipPositions = new ArrayList<int[]>();
        P2ShipPositions.add(new int[] {0,0});
        P2ShipPositions.add(new int[] {0,1});
        Battleship battleship = new Battleship(3,2,1,
                P1ShipPositions, P2ShipPositions);
    }
}
