/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.pbplugins.java.aktivesign.Objects;

import de.pbplugins.java.aktivesign.AktiveSign;
import de.pbplugins.java.aktivesign.Homes.asHomeBuyList;
import java.util.ArrayList;
import java.util.List;
import net.risingworld.api.objects.Player;
import net.risingworld.api.objects.Sign;
import net.risingworld.api.objects.Weather;

/**
 *
 * @author Administrator
 */
public class asSign {

    private final AktiveSign plugin;
    public Attribute Attribute;
    public final ProzessStatus ProzessStatus;
    public final asHomeBuyList HomeBuyList;

    private List<String> SignList;
    private List<String> UserSignList;
    private final String Rot = "[#ff0000]", Grün = "[#00ff00]", Orange = "[#ffa500]", Schwarz = "[#000000]";

    public asSign(AktiveSign plugin) {
        this.plugin = plugin;
        this.Attribute = new Attribute();
        this.ProzessStatus = new ProzessStatus();
        this.HomeBuyList = new asHomeBuyList(plugin);
    }

    public void iniSignList() {
        System.out.println(plugin.PluginName + "Initalisiere SignList");
        SignList = new ArrayList<>();

        SignList.add("[Ticket]");
        SignList.add("[Gamemode]");
        SignList.add("[setGroup]");
        //SignList.add("[Home]");
        SignList.add("[Heal]");
        SignList.add("[Weather]");
        SignList.add("[setWeather]");
        //SignList.add("[]");
        //SignList.add("[]");
        //SignList.add("[]");
        //SignList.add("[]");
        iniUserSigns();
    }

    private void iniUserSigns() {
        System.out.println(plugin.PluginName + "Initalisiere UserSignList");
        UserSignList = new ArrayList<>();

        //UserSignList.add("[UserShop]");
        //UserSignList.add("[AdminHelp]");
        UserSignList.add("[Home]");
        //UserSignList.add("[]");
        //UserSignList.add("[]");
        //UserSignList.add("[]");
        //UserSignList.add("[]");
        //UserSignList.add("[]");
        //UserSignList.add("[]");
        //UserSignList.add("[]");
        //UserSignList.add("[]");
    }

    /**
     *
     * @param player
     * @param line1
     * @param line2
     * @param line3
     * @param line4
     * @param use
     * @return
     */
    public int prozessSign(Player player, String line1, String line2, String line3, String line4, boolean use) {
        boolean prüfer = false;
        boolean pConfig = false;
        boolean pSign = false;
        boolean pPerm = false;
        //TODO prozessSign

        if (isAktiveSign(line1)) {
            if (plugin.Config.UseSign_Gamemode && line1.contains("[Gamemode]")) {
                pConfig = true;
                if (line2.equals("Creative") || line2.equals("Survival")) {
                    pSign = true;
                    if (use) {
                        if (proveGM(player, line3, line4)) {
                            if (line2.equals("Creative")) {
                                player.setCreativeModeEnabled(true);
                                pPerm = true;
                            }
                            if (line2.equals("Survival")) {
                                player.setCreativeModeEnabled(false);
                                pPerm = true;
                            }
                        }
                    } else {
                        if (player.isAdmin()) {
                            pPerm = true;
                        }
                    }
                }
            }

            if (plugin.Config.UseSign_Heal && line1.contains("[Heal]")) {
                pConfig = true;
                List<String> heals = new ArrayList<>();
                heals.add("bones");
                heals.add("hunger");
                heals.add("thirst");
                heals.add("bleeding");
                heals.add("all");

                if (heals.contains(line2)) {
                    pSign = true;
                    if (use) {
                        if (plugin.Sign.proveGM(player, line3, line4)) {
                            if (line2.contains("bones")) {
                                player.setBrokenBones(false);
                            }
                            if (line2.contains("bleeding")) {
                                player.setBleeding(false);
                            }
                            if (line2.contains("hunger")) {
                                player.setHunger(100);
                            }
                            if (line2.contains("thirst")) {
                                player.setThirst(100);
                            }
                            if (line2.contains("all")) {
                                player.setBleeding(false);
                                player.setBrokenBones(false);
                                player.setHunger(100);
                                player.setThirst(100);
                                player.setHealth(100);
                            }
                            pPerm = true;
                        }
                    } else {
                        if (player.isAdmin()) {
                            pPerm = true;
                        }
                    }

                } else {
                    int hp = 0;

                    try {
                        hp = Integer.parseInt(line2);
                        pSign = true;
                    } catch (NumberFormatException ex) {
                    }
                    if (pSign) {
                        if (use) {
                            if (plugin.Sign.proveGM(player, line3, line4)) {
                                pPerm = true;

                                if (player.getHealth() + hp >= 100) {
                                    player.setHealth(100);
                                    player.sendTextMessage(Grün + "Du wurdest komplett geheilt!");
                                } else {
                                    player.setHealth(player.getHealth() + hp);
                                    player.sendTextMessage(Grün + "Du wurdest um " + hp + "Punkte geheilt!");
                                }
                            }
                        } else {
                            if (player.isAdmin()) {
                                pPerm = true;
                            }
                        }
                    }
                }
            }
            
            
            if (plugin.Config.UseSign_Heal && line1.contains("[Heal]")) {

                String[] split = line2.split(" ");

                pConfig = true;
                List<String> weather = new ArrayList<>();
                weather.add("clear");
                weather.add("default");
                weather.add("densefog");
                weather.add("fog");
                weather.add("heavyrain");
                weather.add("heavyrainthunder");
                weather.add("overcast");
                weather.add("rain");
                weather.add("rainthunder");
                weather.add("storm");

                if (weather.contains(line2)) {
                    pSign = true;
                    if (split.length == 1) {
                        if (use) {
                            if (proveGM(player, line2, line4)) {
                                pPerm = true;
                                if (line2.contains("clear")) {
                                    plugin.getServer().setWeather(Weather.Clear, false);
                                }
                                if (line2.contains("default")) {
                                    plugin.getServer().setWeather(Weather.Default, false);
                                }
                                if (line2.contains("densefog")) {
                                    plugin.getServer().setWeather(Weather.DenseFog, false);
                                }
                                if (line2.contains("fog")) {
                                    plugin.getServer().setWeather(Weather.Fog, false);
                                }
                                if (line2.contains("heavyrain")) {
                                    plugin.getServer().setWeather(Weather.HeavyRain, false);
                                }
                                if (line2.contains("heavyrainthunder")) {
                                    plugin.getServer().setWeather(Weather.HeavyRainThunder, false);
                                }
                                if (line2.contains("overcast")) {
                                    plugin.getServer().setWeather(Weather.Overcast, false);
                                }
                                if (line2.contains("rain")) {
                                    plugin.getServer().setWeather(Weather.Rain, false);
                                }
                                if (line2.contains("rainthunder")) {
                                    plugin.getServer().setWeather(Weather.RainThunder, false);
                                }
                                if (line2.contains("storm")) {
                                    plugin.getServer().setWeather(Weather.Storm, false);
                                }
                            }
                        } else {
                            if (player.isAdmin()) {
                                pPerm = true;
                            }
                        }
                    }
                    if (split.length == 2) {
                        boolean instant = false;
                        instant = Boolean.parseBoolean(split[1]);
                        if (use) {
                            if (proveGM(player, line2, line4)) {
                                pPerm = true;

                                if (line2.contains("clear")) {
                                    plugin.getServer().setWeather(Weather.Clear, instant);
                                }
                                if (line2.contains("default")) {
                                    plugin.getServer().setWeather(Weather.Default, instant);
                                }
                                if (line2.contains("densefog")) {
                                    plugin.getServer().setWeather(Weather.DenseFog, instant);
                                }
                                if (line2.contains("fog")) {
                                    plugin.getServer().setWeather(Weather.Fog, instant);
                                }
                                if (line2.contains("heavyrain")) {
                                    plugin.getServer().setWeather(Weather.HeavyRain, instant);
                                }
                                if (line2.contains("heavyrainthunder")) {
                                    plugin.getServer().setWeather(Weather.HeavyRainThunder, instant);
                                }
                                if (line2.contains("overcast")) {
                                    plugin.getServer().setWeather(Weather.Overcast, instant);
                                }
                                if (line2.contains("rain")) {
                                    plugin.getServer().setWeather(Weather.Rain, instant);
                                }
                                if (line2.contains("rainthunder")) {
                                    plugin.getServer().setWeather(Weather.RainThunder, instant);
                                }
                                if (line2.contains("storm")) {
                                    plugin.getServer().setWeather(Weather.Storm, instant);
                                }
                            }
                        } else {
                            if (player.isAdmin()) {
                                pPerm = true;
                            }
                        }
                    }
                }
            }

            if (plugin.Config.UseSign_Gamemode && line1.contains("[setGroup]")) {
                pConfig = true;
                pSign = plugin.getServer().getAllPermissionGroups().contains(line2) && plugin.getServer().getAllPermissionGroups().contains(line3);
                if (use) {
                    if (proveGM(player, line2, line4)) {
                        player.setPermissionGroup(line3);
                        pPerm = true;

                    }
                } else {
                    if (player.isAdmin()) {
                        pPerm = true;
                    }
                }
            }

            /*if (plugin.Config.UseSign_Ticket && line1.contains("[Ticket]")) {
            pConfig = true;
            if (!line2.equals("") || !line2.equals(" ")) {
                pSign = true;
                if (use) {
                    if (proveGM(player, line3, line4)) {
                        plugin.TicketSystem.addTicket(player, line4);
                    }
                }
            }
        }*/
            if (plugin.Money.isInstalled()) {

            }

            if (isUserSign(line1)) {

                if (line1.contains("[Home]")) {
                    if (plugin.Config.UseSign_Home) {
                        pConfig = true;
                        pSign = true;
                        if (!line2.contains("buy")) {
                            pPerm = true;
                            if (use) {
                                if (!line2.equals("") || !line2.equals(" ")) {
                                    player.setPosition(plugin.Home.getHomePos(player));
                                } else {
                                    player.setPosition(plugin.Home.getHomePos(player, line2));
                                }

                            }
                        } else {
                            if (use) {
                                if (plugin.Home.getMaxAmounth(player) < plugin.Config.Home_max) {
                                    if (plugin.Money.takeCash(player.getUID(), plugin.Home.HomeBuyList.HomeBuyList.get(plugin.Home.getMaxAmounth(player) + 1))) {
                                        plugin.Home.setMaxAmounth(player, plugin.Home.getMaxAmounth(player) + 1);
                                        player.sendTextMessage(Grün + "Du kannst jetzt insg. " + plugin.Home.getMaxAmounth(player) + " Homes setzten!");
                                    } else {

                                        return ProzessStatus.Geld;
                                    }
                                }
                                pPerm = true;
                            } else if (player.isAdmin()) {
                                pPerm = true;
                            }
                        }
                    }
                }

            }

            /*
        0 = Alles OK
        1 = Permission
        2 = Schild
        3 = Config
        4 = Kein AktiveSign
        5 = Kein Schild
        6 = Geld
             */
        } else {
            return ProzessStatus.Kein_AktiveSign;
        }
        if (!pConfig) {
            if (plugin.debug > 0) {
                System.out.println(plugin.PluginName + "Debug: prozessSign - Config");
            }
            return ProzessStatus.Config;
        } else if (!pSign) {
            if (plugin.debug > 0) {
                System.out.println(plugin.PluginName + "Debug: prozessSign - Sign");
            }
            return ProzessStatus.Schild;
        } else if (!pPerm) {
            if (plugin.debug > 0) {
                System.out.println(plugin.PluginName + "Debug: prozessSign - Permission");
            }
            return ProzessStatus.Permission;
        } else {
            if (plugin.debug > 0) {
                System.out.println(plugin.PluginName + "Debug: prozessSign - OK");
            }
            return ProzessStatus.Alles_OK;
        }
    }

    //----------------------------------------------------------------------------------------------
    public void addToSignList(String Sign) {
        SignList.add(Sign);
    }

    public void removeFromSignList(String Sign) {
        SignList.remove(Sign);
    }

    public boolean isAktiveSign(int SignID) {
        Sign sign = plugin.getWorld().getSign(SignID);
        if (sign != null) {
            return isAktiveSign(sign);
        }
        return false;
    }

    public boolean isAktiveSign(Sign sign) {
        return isAktiveSign(sign.getLineText(0));
    }

    public boolean isAktiveSign(String txt) {
        return SignList.contains(txt);
    }

    public void addUserSign(String Sign) {
        UserSignList.add(Sign);
    }

    public boolean isUserSign(int SignID) {
        Sign sign = plugin.getWorld().getSign(SignID);
        if (sign != null) {
            return isUserSign(sign);
        }
        return false;
    }

    public boolean isUserSign(Sign sign) {
        return isUserSign(sign.getLineText(0));
    }

    public boolean isUserSign(String text) {
        return UserSignList.contains(text);
    }

    public int prozessSign(Player player, int SignID, boolean use) {
        Sign sign = plugin.getWorld().getSign(SignID);
        if (sign != null) {
            return prozessSign(player, sign, use);
        }
        return 5;
    }

    public int prozessSign(Player player, Sign sign, boolean use) {
        return prozessSign(player, sign.getLineText(0), sign.getLineText(1), sign.getLineText(2), sign.getLineText(3), use);
    }

    public boolean proveGM(Player player, String Group, String Money, boolean add) {
        return proveGroup(player, Group) && proveMoney(player, Money, add, false);
    }

    public boolean proveGM(Player player, String Group, String Money, boolean add, boolean bank) {
        return proveGroup(player, Group) && proveMoney(player, Money, add, bank);
    }

    public boolean proveGM(Player player, String Group, String Money) {
        return proveGroup(player, Group) && proveMoney(player, Money, false, false);
    }

    private boolean proveGroup(Player player, String SignLine) {

        if (plugin.debug > 0) {
            System.out.println("proveGroup - SignGroup: '" + SignLine + "'");
            System.out.println("proveGroup - PlayerGroup: '" + player.getPermissionGroup() + "'");
            System.out.println("proveGroup - SignGroup leer: " + (SignLine.equals("") || SignLine.equals(" ")));

            System.out.println("proveGroup - Return: " + (SignLine.contains(player.getPermissionGroup()) || (SignLine.equals("") || SignLine.equals(" "))));
        }

        return SignLine.contains(player.getPermissionGroup()) || (SignLine.equals("") || SignLine.equals(" "));
    }

    private boolean proveMoney(Player player, String SignLine, boolean add, boolean bank) {
        boolean bMoney = false;
        float money = 0f;
        if (!SignLine.equals("") && !SignLine.equals(" ")) {
            if (SignLine.contains("$")) {
                String[] split = SignLine.split(" ");
                try {
                    money = Float.parseFloat(split[0]);
                    bMoney = true;
                } catch (NumberFormatException ex) {
                    return false;
                }
            }
            if (bMoney) {
                if (add) {
                    if (bank) {
                        return plugin.Money.giveBank(player.getUID(), money);
                    } else {
                        return plugin.Money.giveCash(player.getUID(), money);
                    }
                } else {
                    if (bank) {
                        return plugin.Money.takeBank(player.getUID(), money);
                    } else {
                        return plugin.Money.takeCash(player.getUID(), money);
                    }
                }
            } else {
                if (SignLine.contains("t:")) {
                    //TODO Ticket!
                }
            }
        }
        return true;
    }

    public class ProzessStatus {

        public final int Alles_OK, Permission, Schild, Config, Kein_AktiveSign, Kein_Schild, Geld;

        public ProzessStatus() {
            Alles_OK = 0;
            Permission = 1;
            Schild = 2;
            Config = 3;
            Kein_AktiveSign = 4;
            Kein_Schild = 5;
            Geld = 6;
        }
    }

    public class Attribute {

        private String EditSign, Protection, DestroySign;
        public get get;
        public set set;

        public Attribute() {
            EditSign = plugin.PluginName + "-PlayerAttribute" + "EditSign";
            Protection = plugin.PluginName + "-PlayerAttribute" + "Protection";
            DestroySign = plugin.PluginName + "-PlayerAttribute" + "DestroySign";
            this.get = new get();
            this.set = new set();
        }

        public class get {

            public boolean EditSign(Player player) {
                return (boolean) player.getAttribute(EditSign);
            }

            public boolean Protection(Player player) {
                return (boolean) player.getAttribute(Protection);
            }

            public boolean DestroySign(Player player) {
                return (boolean) player.getAttribute(DestroySign);
            }
        }

        public class set {

            public void EditSign(Player player, boolean value) {
                player.setAttribute(EditSign, value);
            }

            public void Protection(Player player, boolean value) {
                player.setAttribute(Protection, value);
            }

            public void DestroySign(Player player, boolean value) {
                player.setAttribute(DestroySign, value);
            }
        }
    }

}
