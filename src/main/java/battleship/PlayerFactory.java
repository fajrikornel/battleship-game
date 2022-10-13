package battleship;

public interface PlayerFactory {
    Player getPlayerOrCreatePlayerIfNotCreated();
}