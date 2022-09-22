package worldteleport.worldteleport;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldUnloadEvent;

public class Events implements Listener {
    @EventHandler
    public void onWorldUnload(WorldUnloadEvent event){
        new WorldUnload().worldUnloadMain(event);
    }
}
