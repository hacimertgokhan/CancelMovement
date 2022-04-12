package eu.mixeration.cancelmovement.commands;

import eu.mixeration.cancelmovement.mixeration;
import eu.mixeration.cancelmovement.module.bypass;
import eu.mixeration.cancelmovement.module.file;
import eu.mixeration.cancelmovement.module.message;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class admin implements CommandExecutor {
    public admin(mixeration mixeration) {}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player user = (Player) sender;
            if(command.getName().equalsIgnoreCase("CancelMovement")) {
                if(user.hasPermission("cancelmovement.admin")) {
                    if(args.length == 0) {
                        if (mixeration.getInstance().locale.equalsIgnoreCase("en")) {
                            user.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
                            user.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lHelp commands:"));
                            user.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &7- &f/Cancelmovement bypass &8: &fBypass cancel event"));
                            user.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &7- &f/Cancelmovement reload &8: &fReload plugin file."));
                            user.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
                        } else if (mixeration.getInstance().locale.equalsIgnoreCase("tr")) {
                            user.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
                            user.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lYardım komutları:"));
                            user.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &7- &f/Cancelmovement bypass &8: &fYürümeye başlarsın"));
                            user.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &7- &f/Cancelmovement reload &8: &fEklentiyi yenilersin."));
                            user.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
                        }
                        return true;
                    } else if(args[0].equalsIgnoreCase("reload")) {
                        file.saveMessageOptions();
                        mixeration.getInstance().reloadConfig();
                        mixeration.getInstance().saveConfig();
                        file.saveMessageOptions();
                        if (mixeration.getInstance().locale.equalsIgnoreCase("en")) {
                            message.console("&a[CancelMovement] &7Plugin reloaded by " + user.getName() + ".");
                            message.player(user, "&a[CancelMovement] &7Plugin reloaded.");
                        } else if (mixeration.getInstance().locale.equalsIgnoreCase("tr")) {
                            message.console("&a[CancelMovement] &7Eklenti " + user.getName() + " tarafından yenilendi.");
                            message.player(user, "&a[CancelMovement] &7Eklenti yenilendi.");
                        }
                        return true;
                    } else if(args[0].equalsIgnoreCase("bypass")) {
                        if(bypass.bypassMovement.get(user.getUniqueId()) == null) {
                            bypass.bypassMovement.put(user.getUniqueId(), "yes");
                            if (mixeration.getInstance().locale.equalsIgnoreCase("en")) {
                                message.player(user, "&a[CancelMovement] &7Now you can walk.");
                            } else if (mixeration.getInstance().locale.equalsIgnoreCase("tr")) {
                                message.player(user, "&a[CancelMovement] &7Artık yürüyebilirsin.");
                            }
                        } else {
                            bypass.bypassMovement.remove(user.getUniqueId());
                            if (mixeration.getInstance().locale.equalsIgnoreCase("en")) {
                                message.player(user, "&a[CancelMovement] &7Now you can`t walk.");
                            } else if (mixeration.getInstance().locale.equalsIgnoreCase("tr")) {
                                message.player(user, "&a[CancelMovement] &7Artık yürüyemezsin.");
                            }
                        }
                        return true;
                    }
                } else {
                    message.player(user, mixeration.getInstance().getConfig().getString("messages.no-permission"));
                    return true;
                }
            }
        } else {
            message.console(mixeration.getInstance().getConfig().getString("messages.no-console"));
            return true;
        }
        return false;
    }
}
