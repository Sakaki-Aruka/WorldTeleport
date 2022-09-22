package worldteleport.worldteleport;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import static worldteleport.worldteleport.SettingsLoad.worldList;

public class WorldsLoad implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command,String label,String[] args){
        if(sender.isOp() || sender instanceof ConsoleCommandSender){
            this.notice("[World Teleport]:Reloading worlds.");
            worldList.clear();
            worldList = Bukkit.getWorlds();
            this.notice("[World Teleport]:Reload finished.");
            return true;
        }else{
            return false;
        }
    }

    private void notice(String message){
        for(Player player : Bukkit.getServer().getOnlinePlayers()){
            if(player.isOp()){
                player.sendMessage(message);
            }
        }
    }
}
