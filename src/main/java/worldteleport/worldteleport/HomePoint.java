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

import static worldteleport.worldteleport.SettingsLoad.*;

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

                //debug
                if(homePoint.containsKey(player.getName())){
                    player.teleport(homePoint.get(player.getName()));
                    player.sendMessage("Teleported(location):"+homePoint.get(player.getName()));
                }else{
                    player.sendMessage("teleport error");
                }

            }else if(args[0].equals("set")){
                //set
                homePoint.put(player.getName(),player.getLocation());

                //debug
                player.sendMessage("Location(written):"+player.getLocation());

            }else if(args[0].equals("debug")){
                //debug
                if(!(sender.isOp())){
                    return false;
                }
                sender.sendMessage("homePoint:"+homePoint);
                sender.sendMessage("playerNameList:"+FC.getString("playerNameList"));
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
