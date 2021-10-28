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
	    
	    getCommand("example").setExecutor(new ExampleCommandExecutor());
	    
	    getCommand("argent").setExecutor(new MoneyCommandExecutor());
	    
	    getCommand("paye").setExecutor(new PayeCommandExecutor());
	    
	    getCommand("atm").setExecutor(new AtmCommandExecutor());
	    
	    getCommand("atm-ban").setExecutor(new AtmBanCommandExecutor());
	    
	    getCommand("atm-deban").setExecutor(new AtmDeBanCommandExecutor());
	    
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
