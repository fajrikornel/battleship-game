package battleship;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class PlayerReportParserTest {

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

        String actualPlayerReport = "";
        try {
            actualPlayerReport = PlayerReportParser.getPlayerReport(playerReport);
        } catch (Exception e) {
            fail();
        }

        String correctPlayerReport = """
                isAlive:true
                numOfMissiles:2
                numOfMissilesAvailable:0
                numOfMissilesFired:2
                numOfShips:2
                numOfDestroyedShips:1
                numOfIntactShips:1
                """;
        assertEquals(actualPlayerReport, correctPlayerReport);
    }

}