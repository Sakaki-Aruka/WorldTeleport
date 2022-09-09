package worldteleport.worldteleport;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

import static worldteleport.worldteleport.SettingsLoad.homePointAlready;
import static worldteleport.worldteleport.SettingsLoad.homePointYet;

public class HomePoint implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command,String label,String[] args){
        if(!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;

        if(args.length==1){
            if(args[0].equals("teleport")){
                //teleport
                if(homePointAlready.containsKey(player.getName())){
                    //already
                    Location location = homePointAlready.get(player.getName());
                    player.teleport(location);
                }else{
                    if(homePointYet.containsKey(player.getName())){
                        //yet
                        Location location = homePointYet.get(player.getName());
                        player.teleport(location);
                    }
                    player.sendMessage("§c404 Not Found(HomePoint)");
                }
            }else if(args[0].equals("remove")){
                //remove
                if(homePointAlready.containsKey(player.getName())){
                    homePointAlready.remove(player.getName());
                    homePointAlready.put(player.getName(),null);
                }else{
                    if(homePointYet.containsKey(player.getName())){
                        //yet players point remove
                        homePointYet.remove(player.getName());
                    }
                    player.sendMessage("§c404 Not Found(HomePoint)");
                }

            }else if(args[0].equals("set")){
                //set
                if(homePointAlready.containsKey(player.getName()) && homePointAlready.get(player.getName())==null){
                    String key = player.getName();
                    Location value = player.getLocation();
                    homePointAlready.put(key,value);
                }else if(!(homePointAlready.containsKey(player.getName()))){
                    // no exists key
                    String key = player.getName();
                    Location value = player.getLocation();
                    homePointYet.put(key,value);
                }else{
                    // exists key in the hashmap(homePointYet)
                    player.sendMessage("§c400 Bad Request(HomePoint 1)");
                }
            }
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender,Command command,String alias,String[] args){
        if(args.length==1){
            return Arrays.asList("teleport","remove","set");
        }

        return null;
    }
}
