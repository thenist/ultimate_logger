package thenist.ultimatelogger;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import thenist.ultimatelogger.listeners.joinlogger;
import thenist.ultimatelogger.listeners.itemlogger;
public final class Ultimatelogger extends JavaPlugin {
    private static org.bukkit.plugin.Plugin p;
    @Override
    public void onEnable() {
        // Plugin startup logic
        p=this;
        if (!getDataFolder().exists()) getDataFolder().mkdir();
        getServer().getPluginManager().registerEvents(new joinlogger(),this);
        getServer().getPluginManager().registerEvents(new itemlogger(),this);
        getLogger().info("Ultimate Logger Enabled.");
    }
    public static org.bukkit.plugin.Plugin get(){
        return p;
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
