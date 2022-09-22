package worldteleport.worldteleport;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

import static worldteleport.worldteleport.SettingsLoad.spawnPoint;
import static worldteleport.worldteleport.SettingsLoad.worldNameList;

public class Wtp implements CommandExecutor, TabCompleter {
    public boolean onCommand(CommandSender sender, Command command,String label,String[] args){
        if(!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;
        //write here
        if(args.length==1){
            if(spawnPoint.containsKey(args[0])){
                //request world exists
                player.teleport(spawnPoint.get(args[0]));
            }else{
                player.sendMessage("§c400 Bad Request (WorldTeleport)");
            }
        }


        return true;
    }

    public List<String> onTabComplete(CommandSender sender,Command command,String alias,String[] args){
        if(args.length==1){
            return new HomePoint().tabCompleterSupport(worldNameList,args[0]);
        }

        return null;
    }
}
