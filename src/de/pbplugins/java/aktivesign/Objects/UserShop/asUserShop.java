/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.pbplugins.java.aktivesign.Objects.UserShop;

import de.pbplugins.java.aktivesign.AktiveSign;
import net.risingworld.api.objects.Player;

/**
 *
 * @author Administrator
 */
public class asUserShop {
    
    private final AktiveSign plugin;
    
    public asUserShop(AktiveSign plugin){
        this.plugin = plugin;
    }
    
    public boolean prozessSign(Player player, String line1, String line2, String line3, String line4, boolean use) {
        
        return false;
    }
    
    public void Assistant(Player player, int step){
        
    }
    
    
    public class Attribute{
        
        public get get;
        public set set;
        
        private String AssistandStep, AssistandString;
        
        public Attribute(){
            this.get = new get();
            this.set = new set();
            
        }
        
        public class get{
            
        }
        
        public class set{
            
        }
        
    }
}
