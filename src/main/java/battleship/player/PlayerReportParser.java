package battleship.player;

import java.lang.reflect.Field;

public class PlayerReportParser {

    public static String getPlayerReport(PlayerReport player) throws IllegalAccessException {
        StringBuilder result = new StringBuilder();
        for (Field field : player.getClass().getDeclaredFields())
            result.append(field.getName())
                    .append(":")
                    .append(field.get(player))
                    .append("\n");
        return result.toString();
    }
}
