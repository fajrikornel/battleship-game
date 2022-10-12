package battleship;

public interface PlayerFactory {
    public Player getPlayerOrCreatePlayerIfNotCreated();
}