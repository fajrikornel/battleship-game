package battleship.battleground;

import battleship.battleground.BattlegroundImpl;
import battleship.battleground.BattlegroundResultParser;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BattlegroundResultParserTest {

    @Test
    public void printCorrectBattlegroundFormat() {
        int battlegroundSize = 3;
        int numOfShips = 3;
        Set<List<Integer>> shipPositions = new HashSet<>();
        shipPositions.add(new ArrayList<>(List.of(1,0)));
        shipPositions.add(new ArrayList<>(List.of(2,0)));
        shipPositions.add(new ArrayList<>(List.of(0,2)));

        BattlegroundImpl battleground = new BattlegroundImpl(
                battlegroundSize,
                numOfShips,
                shipPositions
        );

        Set<List<Integer>> missileCoordinates = new HashSet<>();
        missileCoordinates.add(new ArrayList<>(List.of(0,0)));
        missileCoordinates.add(new ArrayList<>(List.of(2,0)));
        missileCoordinates.add(new ArrayList<>(List.of(2,2)));

        missileCoordinates.forEach(missile -> battleground.attacked(missile));

        String actualBattlegroundResult = BattlegroundResultParser.getBattlegroundReport(battleground);

        String correctBattlegroundResult = String.join("\n",
                "O _ B ",
                "B _ _ ",
                "X _ O ");

        assertEquals(actualBattlegroundResult, correctBattlegroundResult);
    }
}