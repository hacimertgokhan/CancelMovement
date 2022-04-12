package eu.mixeration.cancelmovement;

import eu.mixeration.cancelmovement.commands.admin;
import eu.mixeration.cancelmovement.events.movement;
import eu.mixeration.cancelmovement.module.message;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class mixeration extends JavaPlugin {
    private static mixeration instance;
    public static synchronized mixeration getInstance() {
        return instance;
    }
    public static synchronized void setInstance(mixeration mixeration) {
        instance = mixeration;
    }

    public String locale = getConfig().getString("cancel-movement.locale");

    @Override
    public void onEnable() {
        setInstance(this);
        eu.mixeration.cancelmovement.module.file.loadConfig();
        if(locale.equalsIgnoreCase("en")) {
            message.console("&a[CancelMovement] &7Locale is &bEnglish ( En )");
        } else if(locale.equalsIgnoreCase("tr")) {
            message.console("&a[CancelMovement] &7Dil ayari &cTurkce ( TR )");
        }
        getCommand("CancelMovement").setExecutor(new admin(this));
        Bukkit.getServer().getPluginManager().registerEvents(new movement(), this);
        if(locale.equalsIgnoreCase("en")) {
            message.console("&a[CancelMovement] &7For support: &bmixeration#5118");
        } else if(locale.equalsIgnoreCase("tr")) {
            message.console("&a[CancelMovement] &7Destek ve sorunlariniz icin: &bmixeration#5118");
        }

    }
}
