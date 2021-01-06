package de.pbplugins.java.aktivesign.Homes;

import de.pbplugins.java.aktivesign.AktiveSign;
import java.util.HashMap;
import net.risingworld.api.objects.Player;
import net.risingworld.api.utils.Vector3f;

public class asHomes {

    private final AktiveSign plugin;
    public final Attribute Attribute;
    public final asHomeDatabase Database;
    public final asHomeBuyList HomeBuyList;

    public asHomes(AktiveSign plugin) {
        this.plugin = plugin;
        this.Attribute = new Attribute();
        this.Database = new asHomeDatabase(plugin);
        this.HomeBuyList = new asHomeBuyList(plugin);
    }

    public Vector3f getHomePos(Player player) {
        return getHomePos(player, "asStandartHome");
    }

    public Vector3f getHomePos(Player player, String name) {
        if (Attribute.get.HomeList(player).containsKey(name)) {
            return Attribute.get.HomeList(player).get(name);
        }
        return null;
    }

    public boolean setNewHome(Player player) {
        return setNewHome(player, "asStandartHome");
    }

    public boolean setNewHome(Player player, String name) {
        Attribute.get.HomeList(player).put(name, player.getPosition());
        return Database.createNewHome(player, name);
    }

    public boolean removeHome(Player player) {
        return removeHome(player, "asStandartHome");
    }

    public boolean removeHome(Player player, String name) {
        Attribute.get.HomeList(player).remove(name);
        return Database.removeHome(player, name);
    }

    public boolean setNewHomeName(Player player, String OldName, String NewName) {
        Vector3f pos = Attribute.get.HomeList(player).get(OldName);
        Attribute.get.HomeList(player).remove(OldName);
        Attribute.get.HomeList(player).put(NewName, pos);
        return Database.setNewHomeName(player, OldName, NewName);
    }

    public boolean setNewHomePos(Player player) {
        return setNewHomePos(player, "asStandartHome");
    }

    public boolean setNewHomePos(Player player, String name) {
        Attribute.get.HomeList(player).put(name, player.getPosition());
        return Database.setNewHomePos(player, name);
    }

    public boolean setNewHomePos(Player player, String name, Vector3f pos) {
        Attribute.get.HomeList(player).put(name, pos);
        return Database.setNewHomePos(player, name, pos);
    }

    public int getAmounth(Player player) {
        return Attribute.get.HomeList(player).size();
    }

    public int getMaxAmounth(Player player) {
        return Database.HomeMax.MaxList.get(player.getUID());
    }

    public void setMaxAmounth(Player player, int amounth) {
        Database.HomeMax.MaxList.put(player.getUID(), amounth);
        Database.HomeMax.setHomeMax(player, amounth);
    }
    
    public boolean hasHome(Player player){
        return hasHome(player, "asStandartHome");
    }
    
    public boolean hasHome(Player player, String name){
        
        return Attribute.get.HomeList(player).containsKey(name);
    }

    public void StopHome() {
        plugin.unregisterEventListener(plugin.HomesEvents);
        plugin.Sign.removeFromSignList("[Home]");
        System.out.println(plugin.PluginName + "Stop HomeEvents and Home-Signs");
    }

    public class Attribute {

        public get get;
        public set set;

        private final String HomeList;

        public Attribute() {
            this.get = new get();
            this.set = new set();

            HomeList = "AktiveSign-Home-HomeList";

        }

        public class get {

            public HashMap<String, Vector3f> HomeList(Player player) {
                return (HashMap<String, Vector3f>) player.getAttribute(HomeList);
            }

        }

        public class set {

            public void NewHomeList(Player player) {
                player.setAttribute(HomeList, Database.getAllHomes(player));
            }
        }
    }
}
