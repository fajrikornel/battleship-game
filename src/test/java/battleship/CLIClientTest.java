package battleship;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CLIClientTest {

    @Test
    public void testExecuteUntilGameOver() {
        int battlegroundSize = 3;
        int numOfShips = 2;
        int numOfMissiles = 2;

        Set<List<Integer>> P1ShipPositions = new HashSet<>();
        P1ShipPositions.add(new ArrayList<>(List.of(0,1)));
        P1ShipPositions.add(new ArrayList<>(List.of(0,2)));
        Set<List<Integer>> P2ShipPositions = new HashSet<>();
        P2ShipPositions.add(new ArrayList<>(List.of(1,0)));
        P2ShipPositions.add(new ArrayList<>(List.of(2,0)));

        List<Set<List<Integer>>> shipPositionsAllPlayers = new ArrayList<>();
        shipPositionsAllPlayers.add(P1ShipPositions);
        shipPositionsAllPlayers.add(P2ShipPositions);

        ContextFactory contextFactory = new ContextFactoryImpl(
                battlegroundSize,
                numOfShips,
                numOfMissiles,
                shipPositionsAllPlayers
        );

        Context context = contextFactory.getContextOrCreateContextIfNotCreated();

        String inputString = String.join(
                "\n",
                "1,0",
                "1,0",
                "2,0",
                "2,0");
        InputStream in = new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8));
        OutputStream out = new ByteArrayOutputStream();
        boolean autoFlush = true;
        PrintStream print = new PrintStream(out, autoFlush);
        Client client = new CLIClient(in, print);

        client.setContext(context);

        client.execute();

        String actualOutputState = client.getStateString();
        String correctOutputState = "GAME OVER. WINNER: P1";

        List<String> actualBattlegroundResults = client.getBattlegroundResultsString();
        List<String> correctBattlegroundResults = new ArrayList<String>(List.of(
                String.join("\n",
                        "_ B B ",
                        "O _ _ ",
                        "O _ _ "),
                String.join("\n",
                        "_ _ _ ",
                        "X _ _ ",
                        "X _ _ ")
        ));

        assertEquals(correctOutputState, actualOutputState);
        assertEquals(correctBattlegroundResults, actualBattlegroundResults);
    }
}