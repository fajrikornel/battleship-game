package battleship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameOver implements State {
    private Context context;
    private Map<Player, PlayerReport> playerReports;
    private List<Integer> winnersList = new ArrayList<>();
    private String stateString;

    public GameOver(Context context) {
        this.context = context;
    }

    public Player getCurrentPlayer() {
        throw new IllegalStateException("GameOver does not have a currentPlayer.");
    }

    public String getStateString() {
        if (stateString == null) {
            getPlayerReports();
            getWinnersList();
            if (winnersList.size() > 1) {
                String winners = "";
                for (int i = 0; i < winnersList.size(); i++) {
                    winners += "P" + (winnersList.get(i) + 1);
                    if (i != winnersList.size()) {
                        winners += ",";
                    }
                }
                stateString = "GAME OVER. TIED BETWEEN: " + winners;
            } else {
                stateString = "GAME OVER. WINNER: P" + (winnersList.get(0) + 1);
            }
        }
        return stateString;
    }

    private void getWinnersList() {
        List<Player> allPlayers = context.getAllPlayers();
        int currentMostIntactShips = 0;
        for (int i = 0; i < allPlayers.size(); i++) {
            PlayerReport playerReport = playerReports.get(allPlayers.get(i));
            if (playerReport.numOfIntactShips > currentMostIntactShips) {
                winnersList.clear();
                winnersList.add(i);
                currentMostIntactShips = playerReport.numOfIntactShips;
            } else if (playerReport.numOfIntactShips == currentMostIntactShips) {
                winnersList.add(i);
            }
        }
    }

    public Map<Player, PlayerReport> getPlayerReports() {
        if (playerReports == null) {
            playerReports = new HashMap<>();
            List<Player> allPlayers = context.getAllPlayers();
            for (int i = 0; i < allPlayers.size(); i++) {
                PlayerReport playerReport = getPlayerReport(allPlayers.get(i));
                playerReports.put(allPlayers.get(i), playerReport);
            }
        }
        return playerReports;
    }

    public PlayerReport getPlayerReport(Player player) {
        PlayerReport currentPlayerReport = new PlayerReport();
        currentPlayerReport.isAlive = player.isAlive();
        currentPlayerReport.numOfMissilesAvailable = player.getNumOfMissilesAvailable();
        currentPlayerReport.numOfMissilesFired = player.getNumOfMissilesFired();
        currentPlayerReport.numOfMissiles =
                currentPlayerReport.numOfMissilesAvailable + currentPlayerReport.numOfMissilesFired;
        currentPlayerReport.numOfDestroyedShips = player.getNumOfDestroyedShips();
        currentPlayerReport.numOfIntactShips = player.getNumOfIntactShips();
        currentPlayerReport.numOfShips =
                currentPlayerReport.numOfDestroyedShips + currentPlayerReport.numOfIntactShips;

        return currentPlayerReport;
    }

    public void attack(List<Integer> attackCoordinate) {
        throw new IllegalStateException("GameOver state does not have an attack mechanism");
    }
}
