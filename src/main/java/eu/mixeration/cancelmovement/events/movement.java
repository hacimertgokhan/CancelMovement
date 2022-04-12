package eu.mixeration.cancelmovement.events;

import eu.mixeration.cancelmovement.mixeration;
import eu.mixeration.cancelmovement.module.bypass;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class movement implements Listener {

    @EventHandler
    public void cancelMovement(PlayerMoveEvent moveEvent) {
        Player user = moveEvent.getPlayer();
        World world = user.getWorld();
        for(String worlds : mixeration.getInstance().getConfig().getStringList("cancel-movement.worlds")) {
            if (world.getName().equalsIgnoreCase(worlds)) {
                if(bypass.bypassMovement.get(user.getUniqueId()) == null) {
                    moveEvent.setCancelled(true);
                    return;
                }
            }
        }
    }
}
