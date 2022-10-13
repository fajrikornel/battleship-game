package battleship.player;

import battleship.battleground.Battleground;
import battleship.player.Player;
import battleship.player.PlayerFactory;
import battleship.player.PlayerFactoryImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerFactoryImplTest {

    @Test
    public void whenGetPlayerEnsurePlayerAndBattlegroundIsConnected() {
        int battlegroundSize = 5;
        int numOfShips = 2;
        int numOfMissiles = 5;
        Set<List<Integer>> shipPositions = new HashSet<>();
        shipPositions.add(new ArrayList<>(List.of(0,0)));
        shipPositions.add(new ArrayList<>(List.of(0,1)));

        PlayerFactory factory = new PlayerFactoryImpl(
                battlegroundSize,
                numOfShips,
                numOfMissiles,
                shipPositions);
        Player player = factory.getPlayerOrCreatePlayerIfNotCreated();
        Battleground battleground = player.getBattleground();

        assertTrue(player == battleground.getPlayer());
    }
}