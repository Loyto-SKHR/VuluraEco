package fr.loyto.testspigot;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class PlayerMoney {
	public static File playerFile = new File("plugins/VuluraEco/player.yml");
    public static FileConfiguration playerMoney = YamlConfiguration.loadConfiguration(playerFile);
    public static Boolean threadDem = false;
    
    public static void saveFile() {
    	playerMoney.options().copyDefaults(true);
		try {
			playerMoney.save(playerFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PlayerMoney.playerMoney = YamlConfiguration.loadConfiguration(PlayerMoney.playerFile);
    }
    
    public static void newPlayer(Player p) {
    	playerMoney.addDefault(p.getUniqueId().toString(), "true");
    	playerMoney.addDefault(p.getUniqueId().toString() + ".name", p.getDisplayName());
    	playerMoney.addDefault(p.getUniqueId().toString() + ".argent", Main.config.getInt("plugin.argent.argent-de-depart"));
    	playerMoney.addDefault(p.getUniqueId().toString() + ".atmban", false);
    	playerMoney.addDefault(p.getUniqueId().toString() + ".atmtime", 0);
		saveFile();
    }
    
    public static int getMoney(Player p) {
    	return playerMoney.getInt(p.getUniqueId().toString() + ".argent");
    }
    
    public static void setMoney(Player p, int i) {
    	playerMoney.set(p.getUniqueId().toString() + ".argent", i);
    	saveFile();
    }
    
    public static int getAtmTime(Player p) {
    	return playerMoney.getInt(p.getUniqueId().toString() + ".atmtime");
    }
    
    public static void setAtmTime(Player p, int i) {
    	playerMoney.set(p.getUniqueId().toString() + ".atmtime", i);
    	saveFile();
    }
    
    public static void gestionThreadAtm() {
    	Thread tAtm = new Thread() {
    		public void run() {
    			while(true){
	    			try {
	    				Thread.sleep(60000);
	    			} catch (InterruptedException e) {
	    				e.printStackTrace();
	    			}
	    			for(Player player : Bukkit.getOnlinePlayers()){
	    				PlayerMoney.setAtmTime(player, PlayerMoney.getAtmTime(player) + 1);
	    			}
    			}
	    	}
	    };
	    
	    if(threadDem) {
	    	tAtm.interrupt();
	    } else {
	    	tAtm.start();
	    	threadDem = true;
	    }
    }
}
