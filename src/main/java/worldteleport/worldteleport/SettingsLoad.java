package worldteleport.worldteleport;

import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.*;

public class SettingsLoad {
    public static FileConfiguration FC;

    public static List<World> worldList = Bukkit.getWorlds();
    public static ArrayList<String> worldNameList = new ArrayList<>();
    public static Map<String,Location> spawnPoint = new HashMap<>();
    public static HashMap<String,Location> homePointAlready = new HashMap<>();
    public static HashMap<String,Location> homePointYet = new HashMap<>();


    public void fc(FileConfiguration fileConfiguration){
        FC = fileConfiguration;
        this.loadConfig();
    }

    public void loadConfig(){

        for(World world : worldList){
            String worldName = world.getName();
            Location defaultSpawn = world.getSpawnLocation();
            spawnPoint.put(worldName,defaultSpawn);
            worldNameList.add(worldName);

            //world load
            WorldCreator worldCreator = new WorldCreator(worldName);
            Bukkit.createWorld(worldCreator);
        }
        Bukkit.getServer().getLogger().info("[WorldTeleport]:WorldsLoad complete.");

        if(FC.getInt("players")==0){
            // no players home point data
        }else{
            // write here
        }
    }
}
