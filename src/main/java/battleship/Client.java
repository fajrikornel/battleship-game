package battleship;

import java.util.List;

public interface Client {
    void setContext(Context context);
    void execute();
    String getStateString();
    List<String> getBattlegroundResultsString();
}
