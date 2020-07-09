package thenist.ultimatelogger;
import thenist.ultimatelogger.Ultimatelogger;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import java.nio.file.*;
import java.util.logging.Logger;

public class utils {
    static Logger logger = Ultimatelogger.get().getLogger();
    public static String locationtostring(final Location location){
        return location.getX()+", "+location.getY()+", "+location.getZ();
    }
    public static void ioerrorkick(final Player player){
        logger.warning("IO Error Occurred while creating log for player Kicking...");
        player.kickPlayer("Sorry! Error Has Occurred!");
    }
}
