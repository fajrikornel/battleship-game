package battleship.player;

import java.lang.reflect.Field;

public class PlayerReportParser {

    public static String getPlayerReport(PlayerReport player) throws IllegalAccessException {
        String result = "";
        for (Field field : player.getClass().getDeclaredFields())
            result += field.getName() + ":" + field.get(player) + "\n";
        return result;
    }
}
