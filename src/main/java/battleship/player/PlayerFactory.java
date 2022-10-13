package battleship.player;

public interface PlayerFactory {
    Player getPlayerOrCreatePlayerIfNotCreated();
}