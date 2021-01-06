package de.pbplugins.java.aktivesign.Permissions;

import de.pbplugins.java.aktivesign.AktiveSign;
import net.risingworld.api.objects.Player;


public class asPermissions {
    
    private AktiveSign plugin;
    
    public asPermissions(AktiveSign plugin){
        this.plugin = plugin;
    }
    
    public boolean hasPermission(Player player, String per){
        if (player.isAdmin()){
            return true;
        } else {
            return false;
        }
    }
    
    public boolean iniPermissions(){
        return true;
    }
    
}
