import battleship.client.CLIClient;
import battleship.client.Client;
import battleship.state.Context;
import battleship.state.ContextFactory;
import battleship.state.ContextFactoryImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        int battlegroundSize = Integer.parseInt(args[0]);
        int numOfShips = Integer.parseInt(args[1]);
        int numOfMissiles = Integer.parseInt(args[2]);
        List<Set<List<Integer>>> shipPositionsAllPlayers = parseShipPositionsAllPlayers(args);

        ContextFactory contextFactory = new ContextFactoryImpl(
                battlegroundSize,
                numOfShips,
                numOfMissiles,
                shipPositionsAllPlayers
        );

        Context context = contextFactory.getContextOrCreateContextIfNotCreated();
        Client client = new CLIClient(System.in, System.out);
        client.setContext(context);
        client.execute();
    }

    private static List<Set<List<Integer>>> parseShipPositionsAllPlayers(String[] args) {
        List<Set<List<Integer>>> shipPositionsAllPlayers = new ArrayList<>();
        for (int j = 3; j < args.length; j++) { //for each player
            String shipPositionsUnparsed = args[j];
            Set<List<Integer>> shipPositions = parseShipPositionsString(shipPositionsUnparsed);
            shipPositionsAllPlayers.add(shipPositions);
        }
        return shipPositionsAllPlayers;
    }

    private static Set<List<Integer>> parseShipPositionsString(String shipPositionsString) {
        String[] shipPositionsArray = shipPositionsString.split(":"); // {"0,1","0,2"}
        Set<List<Integer>> shipPositions = new HashSet<>();
        for (String coordinateString : shipPositionsArray) { //for each ship
            int xCoordinate = Integer.parseInt(coordinateString.substring(0, 1));
            int yCoordinate = Integer.parseInt(coordinateString.substring(2, 3));
            shipPositions.add(new ArrayList<>(List.of(xCoordinate, yCoordinate)));
        }
        return shipPositions;
    }
}
