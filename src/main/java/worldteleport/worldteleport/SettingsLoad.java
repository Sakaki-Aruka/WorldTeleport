package worldteleport.worldteleport;

import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.*;

public class SettingsLoad {
    public static FileConfiguration FC;

    public static List<World> worldList = Bukkit.getWorlds();
    public static ArrayList<String> worldNameList = new ArrayList<>();
    public static Map<String,Location> spawnPoint = new HashMap<>();
    public static Map<String,Location> homePoint = new HashMap<>();

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
            int players = FC.getInt("players");
            ArrayList<String> playerList = new ArrayList<>(Arrays.asList(FC.getString("list").split(",")));
            for(int i=0;i < players ; i++){
                String keyPlayerName = playerList.get(i);
                Location valuePlayerHomePoint = FC.getLocation(keyPlayerName+".home");
                homePoint.put(keyPlayerName,valuePlayerHomePoint);
            }
        }
    }
}
