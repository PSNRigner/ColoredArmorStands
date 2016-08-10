package net.rigner.coloredarmorstands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

class CASCommand implements CommandExecutor
{
    private CASPlugin plugin;

    CASCommand(CASPlugin plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        if (!commandSender.hasPermission("cas.change") || !(commandSender instanceof Player))
            return true;
        if (strings.length == 0)
        {
            commandSender.sendMessage(ChatColor.RED + "Usage : /cas <name>");
            return true;
        }
        String name = String.join(" ", Arrays.asList(strings));
        name = ChatColor.translateAlternateColorCodes('&', name);
        this.plugin.setStringForPlayer(((Player)commandSender).getUniqueId(), name);
        commandSender.sendMessage(ChatColor.GREEN + "Name defined, right click on armorstand with blaze powder to paste it !");
        return true;
    }
}
