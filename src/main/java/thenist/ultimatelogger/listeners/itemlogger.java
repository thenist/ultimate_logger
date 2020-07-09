package thenist.ultimatelogger.listeners;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.*;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import thenist.ultimatelogger.Ultimatelogger;
import thenist.ultimatelogger.utils;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class itemlogger implements Listener {
    java.io.File Datafolder = Ultimatelogger.get().getDataFolder();
    Logger logger = Ultimatelogger.get().getLogger();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    @EventHandler
    public void onPlayerItemDrop(final PlayerDropItemEvent e){
        Player player = e.getPlayer();
        try{
            if(!Files.exists(Paths.get(Datafolder+"/"+player.getUniqueId()+"/Item.log"))){
                Files.createFile(Paths.get(Datafolder + "/" + player.getUniqueId() + "/Item.log"));
            }
            Location location = player.getLocation();
            Date date = new Date();
            Files.write(Paths.get(Datafolder+"/"+player.getUniqueId() + "/Item.log"), ("A Player "+player.getName()+"("+player.getUniqueId()+") has dropped item "+e.getItemDrop().getItemStack().getType().name().replace("_"," ")+" x"+e.getItemDrop().getItemStack().getAmount()+" in "+utils.locationtostring(location)+" at "+formatter.format(date)+"\n").getBytes(), StandardOpenOption.APPEND);
        }catch(java.io.IOException ex){
            e.setCancelled(true);
            utils.ioerrorkick(player);
        }
    }
    @EventHandler
    public void onPlayerItemMove(final InventoryDragEvent e){
        //TODO:Implement Player Inventory logging
    }
    @EventHandler
    public void onPlayerItemPickup(final EntityPickupItemEvent e){
        if(e.getEntityType().equals(org.bukkit.entity.EntityType.PLAYER)){
            Player player = (org.bukkit.entity.Player) e.getEntity();
            try {
                if (!Files.exists(Paths.get(Datafolder + "/" + player.getUniqueId()))) {
                    Files.createFile(Paths.get(Datafolder + "/" + player.getUniqueId() + "/Item.log"));
                }
                Location location = player.getLocation();
                Date date = new Date();
                Files.write(Paths.get(Datafolder+"/"+player.getUniqueId()+"/Item.log"), ("A Player "+player.getName()+"("+player.getUniqueId()+") has picked up item "+e.getItem().getItemStack().getType().name().replace("_"," ")+" x"+e.getItem().getItemStack().getAmount()+" which was located in "+utils.locationtostring(e.getItem().getLocation())+" in "+utils.locationtostring(location)+" at "+formatter.format(date)+"\n").getBytes(), StandardOpenOption.APPEND);
            }catch(java.io.IOException ex){
                e.setCancelled(true);
                utils.ioerrorkick(player);
            }
        }
    }
}
