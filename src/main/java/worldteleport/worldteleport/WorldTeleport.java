package worldteleport.worldteleport;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.Set;

import static worldteleport.worldteleport.SettingsLoad.*;

public final class WorldTeleport extends JavaPlugin implements Listener {

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

    public void dataWrite(){
        //the data that had this program, write to config.yml
        //debug
        Bukkit.broadcastMessage("Now reloading (WorldTeleport)");
        Bukkit.broadcastMessage("homePoint List:"+homePointAlready);
        Bukkit.broadcastMessage("playerNames:"+homePointAlready.keySet());

        ArrayList<String> alreadyList = new ArrayList<>(homePointAlready.keySet());
        ArrayList<String> yetList = new ArrayList<>(homePointYet.keySet());

    }
}
