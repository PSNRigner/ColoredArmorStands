package net.rigner.coloredarmorstands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

class CASListener implements Listener
{
    private CASPlugin plugin;

    CASListener(CASPlugin plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onRightClick(PlayerInteractAtEntityEvent event)
    {
        ItemStack itemStack = event.getHand() == EquipmentSlot.HAND ? event.getPlayer().getInventory().getItemInMainHand() : event.getPlayer().getInventory().getItemInOffHand();
        if (event.getRightClicked().getType() != EntityType.ARMOR_STAND
                || itemStack == null
                || itemStack.getType() != Material.BLAZE_POWDER
                || !event.getPlayer().hasPermission("cas.change"))
            return ;
        event.setCancelled(true);
        String name = this.plugin.getNameForPlayer(event.getPlayer().getUniqueId());
        if (name == null)
        {
            event.getPlayer().sendMessage(ChatColor.RED + "First select a name for your armorstand using /cas");
            return ;
        }
        event.getRightClicked().setCustomName(name);
        event.getRightClicked().setCustomNameVisible(true);
        event.getPlayer().sendMessage(ChatColor.GREEN + "Name changed.");
    }

    @EventHandler
    public void onDisconnect(PlayerKickEvent event)
    {
        this.plugin.removeStringForPlayer(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onDisconnect(PlayerQuitEvent event)
    {
        this.plugin.removeStringForPlayer(event.getPlayer().getUniqueId());
    }
}
