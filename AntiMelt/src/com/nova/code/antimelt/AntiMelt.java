package com.nova.code.antimelt;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiMelt extends JavaPlugin
  implements Listener
{
  Logger log = Logger.getLogger("Minecraft");

  public void onEnable()
  {
    this.log.info(String.format("[%s] By: %s - Has Been Enabled", new Object[] { getDescription().getName(), getDescription().getAuthors() }));
    getServer().getPluginManager().registerEvents(this, this);
  }

  public void onDisable()
  {
    this.log.info(String.format("[%s] By: %s - Has Been Disabled", new Object[] { getDescription().getName(), getDescription().getAuthors() }));
  }

  @EventHandler
  public void onPlayerBreak(BlockBreakEvent event) {
    if (event.getBlock().getType() == Material.ICE) {
      if ((WorldGuardPlugin.class.getClass() != null) && (!event.isCancelled())) {
        WorldGuardPlugin wg = (WorldGuardPlugin)Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
        if (wg.canBuild(event.getPlayer(), event.getBlock())) {
          event.getBlock().setType(Material.AIR);
        }
        return;
      }

      if (!event.isCancelled())
        event.getBlock().setType(Material.AIR);
    }
  }
}