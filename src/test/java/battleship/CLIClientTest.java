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

        ContextFactory contextFactory = new ContextFactoryImpl(
                battlegroundSize,
                numOfShips,
                numOfMissiles,
                P1ShipPositions,
                P2ShipPositions
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
        String correctOutputState = "GAME OVER";

        List<String> actualBattlegroundResults = client.getBattlegroundResultsString();
        List<String> correctBattlegroundResults = new ArrayList<String>(List.of(
                String.join("\n",
                        "O _ B ",
                        "B _ _ ",
                        "X _ O "),
                String.join("\n",
                        "O _ B ",
                        "B _ _ ",
                        "X _ O ")
        ));

        assertEquals(actualOutputState, correctOutputState);
        assertEquals(actualBattlegroundResults, correctBattlegroundResults);
    }
}