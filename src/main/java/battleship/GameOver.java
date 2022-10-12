package battleship;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameOver implements BattleshipState {
    private BattleshipContext battleshipContext;
    private Map<Player, PlayerReport> playerReports;

    public GameOver(BattleshipContext battleshipContext) {
        this.battleshipContext = battleshipContext;
    }

    public Player getCurrentPlayer() {
        return null;
    }

    public Map<Player, PlayerReport> getPlayerReports() {
        if (playerReports == null) {
            playerReports = new HashMap<>();
            List<Player> allPlayers = battleshipContext.getAllPlayers();
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
