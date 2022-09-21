package worldteleport.worldteleport;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;


import java.util.ArrayList;
import java.util.Map;

import static worldteleport.worldteleport.SettingsLoad.*;

public final class WorldTeleport extends JavaPlugin implements Listener, CommandExecutor {

    public void load(){
        FileConfiguration fileConfiguration = getConfig();
        new SettingsLoad().fc(fileConfiguration);
    }

    @Override
    public void onEnable() {
        this.load();
        saveDefaultConfig();
        getCommand("home").setExecutor(new HomePoint());
        getCommand("wtp").setExecutor(new Wtp());
        getCommand("homepointreload");
        getServer().getPluginManager().registerEvents(this,this);
    }

    @Override
    public void onDisable() {
        this.dataWrite();
    }

    @EventHandler
    public void onPlayerCommandProcess(PlayerCommandPreprocessEvent event){
        if(event.getMessage().equalsIgnoreCase("/reload")){
            this.dataWrite();
        }
    }

    public void dataWrite() {
        //the data that had this program, write to config.yml

        ArrayList<String> playerList = new ArrayList<>();

        for(Map.Entry<String,Location> entry : homePoint.entrySet()){
            if(entry.getValue() != null) {
                FC.set(entry.getKey() + ".home", entry.getValue());
                saveConfig();
                playerList.add(entry.getKey());
            }
        }

        String playerListString = String.join(",",playerList);
        FC.set("playerNameList",playerListString);
        saveConfig();

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command,String label,String[] args){
        if(sender instanceof Player){
            if(!(sender.isOp())){
                return false;
            }
        }
        // save and load
        this.dataWrite();
        worldList.clear();
        worldNameList.clear();
        spawnPoint.clear();
        homePoint.clear();
        this.load();
        return true;
    }
}
