package battleship.state;

public interface ContextFactory {
    Context getContextOrCreateContextIfNotCreated();
}
