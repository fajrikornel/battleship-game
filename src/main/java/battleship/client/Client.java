package battleship.client;

import battleship.state.Context;

import java.util.List;

public interface Client {
    void setContext(Context context);
    void execute();
    String getStateString();
    List<String> getPlayerReportsString();
    List<String> getBattlegroundResultsString();
}
