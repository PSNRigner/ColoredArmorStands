package net.rigner.coloredarmorstands;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CASPlugin extends JavaPlugin
{
    private Map<UUID, String> names;

    @Override
    public void onEnable()
    {
        this.names = new HashMap<>();

        this.getServer().getPluginManager().registerEvents(new CASListener(this), this);
        this.getCommand("cas").setExecutor(new CASCommand(this));
    }

    String getNameForPlayer(UUID uuid)
    {
        return this.names.get(uuid);
    }

    void setStringForPlayer(UUID uuid, String name)
    {
        this.names.put(uuid, name);
    }

    void removeStringForPlayer(UUID uuid)
    {
        this.names.remove(uuid);
    }
}
