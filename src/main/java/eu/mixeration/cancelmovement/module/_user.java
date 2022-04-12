package eu.mixeration.cancelmovement.module;

import org.bukkit.entity.Player;

public class _user {

    public static String getCountry(final Player player) {
        String locale;
        try {
            locale = player.getClass().getMethod("getLocale").invoke(player, (Object[]) null).toString();
        } catch (final Exception exception) {
            try {
                final Player.Spigot spigot = player.spigot();
                locale = spigot.getClass().getMethod("getLocale").invoke(spigot, (Object[]) null).toString();
            } catch (final Exception exception1) {
                locale = "unknow";
            }
        }
        if (locale != null && locale.length() > 1) {
            locale = locale.substring(0, 2);
        } else {
            locale = "unknow";
        }
        return locale;
    }

}
