package de.pbplugins.java.aktivesign.Homes;

import de.pbplugins.java.aktivesign.AktiveSign;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import net.risingworld.api.database.Database;
import net.risingworld.api.objects.Player;
import net.risingworld.api.utils.Vector3f;

public class asHomeDatabase {

    private final AktiveSign plugin;
    private final Database Database;
    private final Connection Connection;
    private PreparedStatement pstmt;

    public final HomeMax HomeMax;

    public asHomeDatabase(AktiveSign plugin) {
        this.plugin = plugin;
        this.Database = plugin.getSQLiteConnection(plugin.getPath() + "/database/Homes.db");
        this.Connection = Database.getConnection();
        this.HomeMax = new HomeMax();
    }

    public Database getDB() {
        return Database;
    }

    /**
     * Create a new home with this name
     *
     * @param player The player
     * @param name The name of this home
     * @return
     */
    public boolean createNewHome(Player player, String name) {
        try {
            pstmt = Connection.prepareStatement("INSERT INTO Home (Creator, Name, PosX, PosY, PosZ) VALUES (?, ?, ?, ?, ?)");
            float x = player.getPosition().x, y = player.getPosition().y, z = player.getPosition().z;
            pstmt.setLong(1, player.getUID());
            pstmt.setString(2, name);
            pstmt.setFloat(3, x);
            pstmt.setFloat(4, y);
            pstmt.setFloat(5, z);
            pstmt.executeUpdate();
            pstmt.close();
            
        } catch (SQLException ex) {
            System.out.println(plugin.PluginName + "ERR: SQLException at 'Home.Database.createNewHome()'");
            System.err.println(plugin.PluginName + "ERR: ----Error Report----");
            ex.printStackTrace();
            System.err.println(plugin.PluginName + "ERR: ---------------------");
            return false;
        }
        return true;
    }

    public boolean setNewHomePos(Player player, String name) {
        return setNewHomePos(player, name, player.getPosition());
    }

    public boolean setNewHomePos(Player player, String name, Vector3f pos) {
        try {
            pstmt = Connection.prepareStatement("UPDATE Home SET PosX=?, PosY=?, PosZ=? WHERE 'Creator' = " + player.getUID() + " AND 'Name' = '" + name + "'");
            float x = pos.x, y = pos.y, z = pos.z;
            pstmt.setFloat(1, x);
            pstmt.setFloat(2, y);
            pstmt.setFloat(3, z);
            pstmt.executeUpdate();
            pstmt.close();
            
        } catch (SQLException ex) {
            System.out.println(plugin.PluginName + "ERR: SQLException at 'Home.Database.createNewHome()'");
            System.err.println(plugin.PluginName + "ERR: ----Error Report----");
            ex.printStackTrace();
            System.err.println(plugin.PluginName + "ERR: ---------------------");
            return false;
        }
        return true;
    }

    public boolean removeHome(Player player, String name) {
        try {
                pstmt = Connection.prepareStatement("DELETE FROM Home WHERE 'Creator' = " + player.getUID() + " AND 'Name' = ?");
                pstmt.setString(1, name);
                pstmt.executeUpdate();
                pstmt.close();
            } catch (SQLException ex) {
                System.out.println(plugin.PluginName + "ERR: SQLException at 'Home.Database.HomeMax.setHomeMax()'");
                System.err.println(plugin.PluginName + "ERR: ----Error Report----");
                ex.printStackTrace();
                System.err.println(plugin.PluginName + "ERR: ---------------------");
                return false;
            }
        return true;
    }

    public boolean setNewHomeName(Player player, String OldName, String NewName) {
        
        try {
                pstmt = Connection.prepareStatement("UPDATE Home SET 'Name'=? WHERE 'Creator' = " + player.getUID() + " AND 'Name' ='" + OldName + "'");
                pstmt.setString(1, NewName);
                pstmt.executeUpdate();
                pstmt.close();
            } catch (SQLException ex) {
                System.out.println(plugin.PluginName + "ERR: SQLException at 'Home.Database.HomeMax.setHomeMax()'");
                System.err.println(plugin.PluginName + "ERR: ----Error Report----");
                ex.printStackTrace();
                System.err.println(plugin.PluginName + "ERR: ---------------------");
                return false;
            }
        return true;
    }

    public HashMap<String, Vector3f> getAllHomes(Player player) {
        HashMap<String, Vector3f> map = new HashMap<>();
        Vector3f pos;
        try {
            ResultSet result = Database.executeQuery("SELECT * FROM Home WHERE Creator=" + player.getUID());
            if (result != null) {
                while (result.next()) {
                    pos = new Vector3f(result.getFloat("PosX"), result.getFloat("PosY"), result.getFloat("PosZ"));
                    map.put(result.getString("Name"), pos);
                }
                result.close();
            }
        } catch (SQLException ex) {
            System.out.println(plugin.PluginName + "ERR: SQLException at 'Home.Database.getAllHomes()'");
            System.err.println(plugin.PluginName + "ERR: ----Error Report----");
            ex.printStackTrace();
            System.err.println(plugin.PluginName + "ERR: ---------------------");
        }
        return map;
    }

    public long getAnzahl(Player player) {
        long anzahl = 0;
        try {
            ResultSet result = Database.executeQuery("SELECT count(*) FROM Home WHERE Creator=" + player.getUID());
            if (result != null) {
                result.next();
                anzahl = result.getLong(1);
                result.close();
            }
        } catch (SQLException ex) {
            System.out.println(plugin.PluginName + "ERR: SQLException at 'Home.Database.getAnzahl()'");
            System.err.println(plugin.PluginName + "ERR: ----Error Report----");
            ex.printStackTrace();
            System.err.println(plugin.PluginName + "ERR: ---------------------");
        }
        return anzahl;
    }

    public void iniDB() {
        System.out.println(plugin.PluginName + "INI: Home-Database");
        Database.execute("CREATE TABLE IF NOT EXISTS Home ("
                + "ID INTEGER PRIMARY KEY NOT NULL, " //AUTOINCREMENT
                + "Creator BIGINT, "
                + "Name VARCHAR(64), "
                + "PosX FLOAT, "
                + "PosY FLOAT, "
                + "PosZ FLOAT, "
                + "More VARCHAR(64) "
                + "); ");
        Database.execute("CREATE TABLE IF NOT EXISTS HomeMax ("
                + "ID INTEGER PRIMARY KEY NOT NULL, " //AUTOINCREMENT
                + "User BIGINT, "
                + "Max INTEGER "
                + "); ");
        System.out.println(plugin.PluginName + "INI: Home-Database - Done");

    }

    public class HomeMax {

        public HashMap<Long, Integer> MaxList;

        public void iniMaxList() {
            System.out.println(plugin.PluginName + "INI: MaxList");
            MaxList = new HashMap<>();
            try {
                ResultSet result = Database.executeQuery("SELECT * FROM HomeMax");
                if (result != null) {
                    while (result.next()) {
                        MaxList.put(result.getLong("User"), result.getInt("Max"));
                    }
                    result.close();
                }
            } catch (SQLException ex) {
                System.out.println(plugin.PluginName + "INI ERR: SQLException at 'Home.Database.iniMaxList()'");
                System.err.println(plugin.PluginName + "INI ERR: ----Error Report----");
                ex.printStackTrace();
                System.err.println(plugin.PluginName + "INI ERR: ---------------------");
                plugin.StopPlugin();
            }
            System.out.println(plugin.PluginName + "INI: MaxList - Done");
        }

        public boolean prozessPlayer(Player player) {
            if (!MaxList.containsKey(player.getUID())) {
                MaxList.put(player.getUID(), 0);

                try {
                    pstmt = Connection.prepareStatement("INSERT INTO HomeMax (User, Max) VALUES (?,?)");
                    pstmt.setLong(1, player.getUID());
                    pstmt.setInt(2, plugin.Config.Home_start);
                    pstmt.executeUpdate();
                    pstmt.close();
                } catch (SQLException ex) {
                    return false;
                }
            }
            return true;
        }

        public boolean setHomeMax(Player player, int amounth) {
            try {
                pstmt = Connection.prepareStatement("UPDATE HomeMax SET Max=? WHERE User=" + player.getUID());
                pstmt.setInt(1, amounth);
                pstmt.executeUpdate();
                pstmt.close();
            } catch (SQLException ex) {
                System.out.println(plugin.PluginName + "ERR: SQLException at 'Home.Database.HomeMax.setHomeMax()'");
                System.err.println(plugin.PluginName + "ERR: ----Error Report----");
                ex.printStackTrace();
                System.err.println(plugin.PluginName + "ERR: ---------------------");
                return false;
            }
            return true;
        }
    }

}
