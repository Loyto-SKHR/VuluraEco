package fr.loyto.testspigot;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class ExampleCommandExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player)sender;
			p.sendMessage(args[0]);
			PlayerMoney.playerMoney = YamlConfiguration.loadConfiguration(PlayerMoney.playerFile);
		}
		
		return true;
	}

}
