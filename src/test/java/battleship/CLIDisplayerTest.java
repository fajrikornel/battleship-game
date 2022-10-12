package battleship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CLIDisplayerTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void printCorrectPlayerReportFormat() {
        PlayerReport playerReport = new PlayerReport();
        playerReport.isAlive = true;
        playerReport.numOfMissiles = 2;
        playerReport.numOfMissilesAvailable = 0;
        playerReport.numOfMissilesFired = 2;
        playerReport.numOfShips = 2;
        playerReport.numOfDestroyedShips = 1;
        playerReport.numOfIntactShips = 1;

        CLIDisplayer displayer = new CLIDisplayer();
        try {
            displayer.displayPlayerReport(playerReport);
        } catch (Exception e) {
            fail();
        }

        String correctDisplay = """
                isAlive:true
                numOfMissiles:2
                numOfMissilesAvailable:0
                numOfMissilesFired:2
                numOfShips:2
                numOfDestroyedShips:1
                numOfIntactShips:1
                """;

        assertEquals(correctDisplay, outputStream.toString());
    }

    @Test
    public void printCorrectBattlegroundFormat() {
        int battlegroundSize = 3;
        int numOfShips = 3;
        Set<List<Integer>> shipPositions = new HashSet<>();
        shipPositions.add(new ArrayList<>(List.of(1,0)));
        shipPositions.add(new ArrayList<>(List.of(2,0)));
        shipPositions.add(new ArrayList<>(List.of(0,2)));

        Battleground battleground = new Battleground(
                battlegroundSize,
                numOfShips,
                shipPositions
        );

        Set<List<Integer>> missileCoordinates = new HashSet<>();
        missileCoordinates.add(new ArrayList<>(List.of(0,0)));
        missileCoordinates.add(new ArrayList<>(List.of(2,0)));
        missileCoordinates.add(new ArrayList<>(List.of(2,2)));

        missileCoordinates.forEach(missile -> battleground.attacked(missile));

        CLIDisplayer cliDisplayer = new CLIDisplayer();
        try {
            cliDisplayer.displayBattlegroundReport(battleground);
        } catch (Exception e) {
            fail();
        }

        String correctDisplay = String.join("\n",
                "O _ B ",
                "B _ _ ",
                "X _ O ",
                "");

        assertEquals(correctDisplay, outputStream.toString());
    }
}