package fr.loyto.testspigot;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class AtmCommandExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			
			if(PlayerMoney.playerMoney.getBoolean(p.getUniqueId().toString() + ".atmban")) {
				p.sendMessage(ChatColor.RED + "Vous êtes banni du /atm.");
			} else {
				int atmGain = PlayerMoney.playerMoney.getInt(p.getUniqueId().toString() + ".atmtime")*Main.config.getInt("plugin.atm.argent-par-minute");
				PlayerMoney.setMoney(p, PlayerMoney.getMoney(p) + atmGain);
				p.sendMessage(ChatColor.GREEN + "Vous avez eu " + Integer.toString(atmGain) + "€ avec l'ATM.");
				if(atmGain != 0) {
					PlayerMoney.setAtmTime(p, 0);
				}
			}
			
		}

		return true;
	}

}
