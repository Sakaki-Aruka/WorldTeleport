package worldteleport.worldteleport;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

import static worldteleport.worldteleport.SettingsLoad.homePoint;

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
                if(homePoint.containsKey(player.getName())){
                    Location location = homePoint.get(player.getName());
                    player.teleport(location);
                }else{
                    player.sendMessage("§c404 Not Found(HomePoint)");
                }
            }else if(args[0].equals("remove")){
                //remove
                if(homePoint.containsKey(player.getName())){
                    homePoint.remove(player.getName());
                }else{
                    player.sendMessage("§c404 Not Found(HomePoint)");
                }

            }else if(args[0].equals("set")){
                //set
                if(!(homePoint.containsKey(player.getName()))){
                    String key = player.getName();
                    Location value = player.getLocation();
                    homePoint.put(key,value);
                }else{
                    player.sendMessage("§c400 Bad Request(HomePoint)");
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
