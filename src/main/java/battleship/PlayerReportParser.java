package battleship;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//TODO this should be a class with static methods. Used by context/battleground
public class PlayerReportParser {

    public static String getPlayerReport(PlayerReport player) throws IllegalAccessException {
        String result = "";
        for (Field field : player.getClass().getDeclaredFields())
            result += field.getName() + ":" + field.get(player) + "\n";
        return result;
    }
}
