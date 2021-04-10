package fr.loyto.testspigot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PayeCommandExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p1 = (Player)sender;
			
			if(args.length != 2) {
				p1.sendMessage(ChatColor.RED + "Entre un pseudo et une somme!");
				return false;
			}
			
			if(Bukkit.getPlayer(args[0]) != null) {
				Player p2 = Bukkit.getPlayer(args[0]);

				if(Integer.parseInt(args[1]) <= PlayerMoney.getMoney(p1) && Integer.parseInt(args[1]) > 0) {
					PlayerMoney.setMoney(p1, PlayerMoney.getMoney(p1) - Integer.parseInt(args[1]));
					p1.sendMessage(ChatColor.GREEN + "Vous avez envoyé " + Integer.parseInt(args[1]) + "€ à " + ChatColor.YELLOW + p2.getDisplayName() + ChatColor.GREEN + ".");
					PlayerMoney.setMoney(p2, PlayerMoney.getMoney(p2) + Integer.parseInt(args[1]));
					p2.sendMessage(ChatColor.GREEN + "Vous venez de recevoir " + Integer.parseInt(args[1]) + "€ de la part de " + ChatColor.YELLOW + p1.getDisplayName() + ChatColor.GREEN + ".");
				} else {
					p1.sendMessage(ChatColor.RED + "Entre une somme valide!");
				}
			} else {
				p1.sendMessage(ChatColor.RED + "Le joueur n'est pas connecté!");
			}
		}
		
		return true;
	}

}
