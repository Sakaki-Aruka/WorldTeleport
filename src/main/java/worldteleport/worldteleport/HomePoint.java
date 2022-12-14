package worldteleport.worldteleport;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
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
                if(homePoint.containsKey(player.getName())){
                    player.teleport(homePoint.get(player.getName()));
                    player.sendMessage("§a200 OK (HomePoint)");
                }else{
                    player.sendMessage("§c404 Not Found (HomePoint)");
                    return false;
                }

            }else if(args[0].equals("set")){
                //set
                homePoint.put(player.getName(),player.getLocation());
                player.sendMessage("§a200 OK (HomePoint)");

            }else if(args[0].equals("debug")){
                //debug
                if(!(sender.isOp())){
                    return false;
                }
                sender.sendMessage("homePoint:"+homePoint);
            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender,Command command,String alias,String[] args){
        if(args.length==1){
            return tabCompleterSupport(new ArrayList<>(Arrays.asList("teleport","set")),args[0]);
        }
        return null;
    }

    public ArrayList<String> tabCompleterSupport(ArrayList<String> array,String input){
        ArrayList<String> settings = new ArrayList<>();
        for(String loop : array){
            if(loop.contains(input)){
                settings.add(loop);
            }
        }
        if(settings.size() != 0){
            return settings;
        }else{
            return null;
        }
    }
}
