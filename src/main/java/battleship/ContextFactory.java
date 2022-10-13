package battleship;

import java.util.List;

public interface ContextFactory {
    Context getContextOrCreateContextIfNotCreated();
}
