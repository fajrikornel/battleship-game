package battleship.client;

import battleship.player.Player;
import battleship.player.PlayerReport;
import battleship.player.PlayerReportParser;
import battleship.state.Context;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;

public class CLIClient implements Client {
    private final Scanner scanner;
    private final PrintStream out;
    private Context context;

    public CLIClient(InputStream in, PrintStream out) {
        scanner = new Scanner(in);
        this.out = out;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void execute() {
        out.println("TO ATTACK, WRITE COORDINATE IN THIS FORMAT: X,Y");
        String stateString = getStateString();
        while (!stateString.contains("GAME OVER")) {
            executeTurn(stateString);
            stateString = getStateString();
        }
        out.println(stateString); //GameOver
        List<String> battlegroundResults = getBattlegroundResultsString();
        List<String> playerReports = getPlayerReportsString();
        for (int i = 0; i < battlegroundResults.size(); i++) {
            out.println("P" + (i+1) + "'S BATTLEGROUND:");
            out.println(battlegroundResults.get(i));
            out.println("P" + (i+1) + "'S STATS:");
            out.println(playerReports.get(i));
        }
    }

    public String getStateString() {
        return context.getStateString();
    }

    private void executeTurn(String stateString) {
        out.println(stateString);
        out.print("INSERT ATTACK COORDINATE: ");
        int xCoordinate;
        int yCoordinate;
        while (true) {
            try {
                String attackCoordinateString = scanner.nextLine();
                xCoordinate = Integer.parseInt(attackCoordinateString.substring(0,1));
                yCoordinate = Integer.parseInt(attackCoordinateString.substring(2,3));
                break;
            } catch (NumberFormatException e) {
                out.println("INVALID COORDINATE FORMAT. PLEASE TRY AGAIN.");
                out.print("INSERT ATTACK COORDINATE: ");
            }
        }

        List<Integer> attackCoordinate = new ArrayList<>(List.of(xCoordinate, yCoordinate));
        context.attack(attackCoordinate);
    }

    public List<String> getBattlegroundResultsString() {
        return context.getBattlegroundResults();
    }

    public List<String> getPlayerReportsString() {
        Map<Player, PlayerReport> playerReportsMap = context.getPlayerReports();
        List<PlayerReport> playerReportsList = new ArrayList<>(playerReportsMap.values());
        List<String> playerReportsString = new ArrayList<>();

        for (PlayerReport playerReport: playerReportsList) {
            try {
                playerReportsString.add(PlayerReportParser.getPlayerReport(playerReport));
            } catch (Exception e) {
                out.println("ERROR WHILE PARSING PLAYER REPORT");
                System.exit(1);
            }
        }

        return playerReportsString;
    }
}
