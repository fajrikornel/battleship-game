package battleship;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

public class CLIClient implements Client {
    private InputStream in;
    private PrintStream out;
    private Context context;
    private Inputter inputter;
    private Displayer displayer;

    public CLIClient(InputStream in, PrintStream out) {
        this.in = in;
        this.out = out;
        this.inputter = new CLIInputter(this.in);
        this.displayer = new CLIDisplayer(this.out);
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void execute() {
        inputter.execute();
    }

    public String getStateString() {
        return context.getStateString();
    }

    public List<String> getBattlegroundResultsString() {
        return context.getBattlegroundResults();
    }
}
