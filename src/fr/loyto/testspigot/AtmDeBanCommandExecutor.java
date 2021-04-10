package fr.loyto.testspigot;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;

public class AtmDeBanCommandExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(args.length != 1) {
			sender.sendMessage(ChatColor.RED + "Entrez un pseudo!");
			return false;
		}
		
		if(Bukkit.getPlayer(args[0]) != null) {
			PlayerMoney.playerMoney.set(Bukkit.getPlayer(args[0]).getUniqueId().toString() + ".atmban", false);
			PlayerMoney.saveFile();
			sender.sendMessage(ChatColor.RED + "Le joeur " + args[0] + " à été debanni du /atm.");
			Bukkit.getPlayer(args[0]).sendMessage(ChatColor.RED + "Vous avez été debanni du /atm.");
		}

		return true;
	}

}
