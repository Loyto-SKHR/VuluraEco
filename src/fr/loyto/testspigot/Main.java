package fr.loyto.testspigot;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public static File configFile;
	public static FileConfiguration config;
	@Override
	public void onEnable() {
	    Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[Log][VuluraEco][Info] Démarrage du plugin");
	    saveDefaultConfig();
		configFile = new File("plugins/VuluraEco/config.yml");
	    config = YamlConfiguration.loadConfiguration(configFile);
	    
	    CommandExecutor exampleExecutor = new ExampleCommandExecutor();
	    getCommand("example").setExecutor(exampleExecutor);
	    
	    CommandExecutor moneyExecutor = new MoneyCommandExecutor();
	    getCommand("argent").setExecutor(moneyExecutor);
	    
	    CommandExecutor payeExecutor = new PayeCommandExecutor();
	    getCommand("paye").setExecutor(payeExecutor);
	    
	    CommandExecutor atmExecutor = new AtmCommandExecutor();
	    getCommand("atm").setExecutor(atmExecutor);
	    
	    CommandExecutor atmBanExecutor = new AtmBanCommandExecutor();
	    getCommand("atm-ban").setExecutor(atmBanExecutor);
	    
	    CommandExecutor atmDeBanExecutor = new AtmDeBanCommandExecutor();
	    getCommand("atm-deban").setExecutor(atmDeBanExecutor);
	    
	    Listener l =new PluginListener();
	    PluginManager pm = getServer().getPluginManager();
	    pm.registerEvents(l, this);
	    
	    PlayerMoney.gestionThreadAtm();
	}
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[Log][VuluraEco][Info] Arret du plugin");

	    PlayerMoney.gestionThreadAtm();
	}
}
