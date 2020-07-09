package thenist.ultimatelogger.listeners;
import thenist.ultimatelogger.utils;
import thenist.ultimatelogger.Ultimatelogger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import org.bukkit.event.player.*;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;

public class joinlogger implements Listener {
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    java.io.File Datafolder = Ultimatelogger.get().getDataFolder();
    Logger logger = Ultimatelogger.get().getLogger();
    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent e){
        org.bukkit.entity.Player player = e.getPlayer();
        if(!Files.exists(Paths.get(Datafolder+"/"+player.getUniqueId()))){
            try{
                Files.createDirectory(Paths.get(Datafolder+"/"+player.getUniqueId()));
            }catch(java.io.IOException ex){
                utils.ioerrorkick(player);
            }
        }
        try{
            if(!Files.exists(Paths.get(Datafolder+"/"+player.getUniqueId()+"/Join.log"))) {
                Files.createFile(Paths.get(Datafolder + "/" + player.getUniqueId() + "/Join.log"));
            }
            Date date = new Date();
            Files.write(Paths.get(Datafolder+"/"+player.getUniqueId()+"/Join.log"),("A Player "+player.getName()+"("+player.getUniqueId()+") has joined server at "+formatter.format(date)+"\n").getBytes(), StandardOpenOption.APPEND);

        }catch(java.io.IOException ex){
            utils.ioerrorkick(player);
        }
    }
    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent e){
        org.bukkit.entity.Player player = e.getPlayer();
        try {
            if(!Files.exists(Paths.get(Datafolder+"/"+player.getUniqueId()+"/Join.log"))) {
                Files.createFile(Paths.get(Datafolder + "/" + player.getUniqueId() + "/Join.log"));
            }
            Date date = new Date();
            Files.write(Paths.get(Datafolder+"/"+player.getUniqueId()+"/Join.log"),("A Player "+player.getName()+"("+player.getUniqueId()+") has quit server at "+formatter.format(date)+"\n").getBytes(), StandardOpenOption.APPEND);
        }catch(java.io.IOException ex){
            logger.warning("IO Error Occurred while logging player quit!");
        }
    }
}
