package eu.mixeration.cancelmovement.module;

import eu.mixeration.cancelmovement.mixeration;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;

public class file {

    public static void saveMessageOptions() {
        mixeration.getInstance().getServer().getScheduler().runTask(mixeration.getInstance(), () ->{
            File fileKeys = new File(mixeration.getInstance().getDataFolder(), "config.yml");
            try {
                mixeration.getInstance().getConfig().save(fileKeys);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void loadConfig() {
        FileConfiguration config = mixeration.getInstance().getConfig();
        new File(mixeration.getInstance().getDataFolder(), "config.yml");
        mixeration.getInstance().saveDefaultConfig();
    }
}
