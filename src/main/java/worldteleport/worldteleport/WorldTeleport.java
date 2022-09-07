package worldteleport.worldteleport;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class WorldTeleport extends JavaPlugin {

    public void load(){
        FileConfiguration fileConfiguration = getConfig();
        new SettingsLoad().fc(fileConfiguration);
    }

    @Override
    public void onEnable() {
        this.load();
        saveDefaultConfig();
        getCommand("home").setExecutor(new HomePoint());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
