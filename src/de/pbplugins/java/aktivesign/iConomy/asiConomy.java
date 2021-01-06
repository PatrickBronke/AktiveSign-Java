package de.pbplugins.java.aktivesign.iConomy;

import de.pbplugins.iConomy;
import de.pbplugins.java.aktivesign.AktiveSign;

public class asiConomy {

    private final iConomy money;
    private final AktiveSign plugin;

    public asiConomy(AktiveSign plugin) {
        this.plugin = plugin;
        this.money = (iConomy)plugin.getPluginByName("iConomy");
    }
    
    public boolean isInstalled(){
        return plugin.getPluginByName("iConomy") != null;
    }
    
    public iConomy getPlugin(){
        return money;
    }
    
    public boolean takeCash(long uid, float amounth){
        return isInstalled() && money.takeCash(uid, amounth);
    }
    
    public boolean takeBank(long uid, float amounth){
        return isInstalled() && money.takeBank(uid, amounth);
    }
    
    public boolean giveBank(long uid, float amounth){
        return isInstalled() && money.giveBank(uid, amounth);
    }
    
    public boolean giveCash(long uid, float amounth){
        return isInstalled() && money.giveCash(uid, amounth);
    }
}
