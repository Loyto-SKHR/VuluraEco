package fr.loyto.testspigot;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.md_5.bungee.api.ChatColor;

public class PluginListener implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(p.getFirstPlayed() == 0) {
			p.sendMessage(ChatColor.GREEN + "Bienvenue sur le serveur " + ChatColor.YELLOW + p.getDisplayName() + " !");
		}
		if(PlayerMoney.playerMoney.getBoolean(p.getUniqueId().toString()) != true) {
			PlayerMoney.newPlayer(p);
		}
	}
}
