package worldteleport.worldteleport;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.world.WorldUnloadEvent;

import static worldteleport.worldteleport.SettingsLoad.worldList;
import static worldteleport.worldteleport.SettingsLoad.worldNameList;

public class WorldUnload {
    public void worldUnloadMain(WorldUnloadEvent event){
        World world = event.getWorld();
        String worldName = world.getName();
        worldList.remove(world);
        worldNameList.remove(worldName);
    }
}
