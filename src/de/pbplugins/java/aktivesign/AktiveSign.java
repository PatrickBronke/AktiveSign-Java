package de.pbplugins.java.aktivesign;

import de.pbplugins.java.aktivesign.ChatSystem.asChat;
import de.pbplugins.java.aktivesign.Events.asHomesEvents;
import de.pbplugins.java.aktivesign.Events.asMailSystemEvents;
import de.pbplugins.java.aktivesign.Events.asPlayerConnectEvent;
import de.pbplugins.java.aktivesign.Events.asSignEvents;
import de.pbplugins.java.aktivesign.Events.asTicketSystemEvent;
import de.pbplugins.java.aktivesign.Homes.asHomes;
import de.pbplugins.java.aktivesign.Objects.asChest;
import de.pbplugins.java.aktivesign.Objects.asPlayer;
import de.pbplugins.java.aktivesign.Objects.asSign;
import de.pbplugins.java.aktivesign.Permissions.asPermissions;
import de.pbplugins.java.aktivesign.TicketSystem.asTicketSystem;
import de.pbplugins.java.aktivesign.iConomy.asiConomy;
import net.risingworld.api.Plugin;

public class AktiveSign extends Plugin {

    public String PluginName;
    public int debug;
    boolean PluginStop = false;
    public asTicketSystem TicketSystem;
    public asiConomy Money;
    public asSign Sign;
    public asPlayer Player;
    public asChest Chest;
    public asChat Chat;
    public asHomes Home;
    private boolean TicketBlock;

    public cConfig Config;
    public asPermissions Permissions;

    public boolean bTicket = false;

    //EVENTE
    private asTicketSystemEvent TicketSystemEvent;
    private asSignEvents SignEvents;
    private asPlayerConnectEvent PlayerConnectEvent;
    private asMailSystemEvents MailSystemEvents;

    /**
     * Only Event-Method! Do not use it!
     *
     */
    public asHomesEvents HomesEvents;

    @Override
    public void onEnable() {
        PluginName = "[" + getDescription("name") + "] ";
        System.out.println(PluginName + "Enabled");
        Config = new cConfig(this);
        TicketBlock = true;

        Money = new asiConomy(this);
        Sign = new asSign(this);
        Player = new asPlayer(this);
        Chest = new asChest(this);
        Chat = new asChat(this);
        Home = new asHomes(this);
        Permissions = new asPermissions(this);

        //Events
        TicketSystemEvent = new asTicketSystemEvent(this);
        SignEvents = new asSignEvents(this);
        PlayerConnectEvent = new asPlayerConnectEvent(this);
        MailSystemEvents = new asMailSystemEvents(this);
        HomesEvents = new asHomesEvents(this);

        System.out.println(PluginName + "------------------------------");
        System.out.println(PluginName + "Initialize plugin parts");
        iniPlugin();
        System.out.println(PluginName + "Initialize plugin parts - done");
        System.out.println(PluginName + "------------------------------");
        
    }

    private void iniPlugin() {
        try {
            System.out.println(PluginName + "INI: Config");
            if (Config.iniConfig()) {
                System.out.println(PluginName + "INI: Config - OK");
                debug = Config.Debug;
                System.out.println(PluginName + "INI: debug = " + debug);
                System.out.println(PluginName + "INI: Permission");
                if (Permissions.iniPermissions()) {
                    System.out.println(PluginName + "INI: Permission - OK");
                    System.out.println(PluginName + "INI: Starting PlayerConnectEvent");
                    registerEventListener(PlayerConnectEvent);
                    System.out.println(PluginName + "INI: SignList");
                    Sign.iniSignList();
                    System.out.println(PluginName + "INI: SignList - OK");
                    registerEventListener(SignEvents);
                    if (!TicketBlock && Config.UseSign_Ticket) {
                        System.out.println(PluginName + "INI: TicketSystem");
                        try {
                            TicketSystem = new asTicketSystem(this);
                            bTicket = true;
                        } catch (Exception ex) {
                            System.err.println(PluginName + "INI ERR: TicketSystem could not be initialized!");
                            System.err.println(PluginName + "INI ERR: Events have been stopped");
                            System.err.println(PluginName + "INI ERR: ----Error Report----");
                            ex.printStackTrace();
                            System.err.println(PluginName + "INI ERR: ---------------------");
                            StopPlugin();

                        }
                        if (bTicket) {
                            registerEventListener(TicketSystemEvent);
                            System.out.println(PluginName + "INI: TicketSystem - OK");
                        }
                    }
                    boolean bHome = false;
                    if (Config.UseSign_Home) {
                        System.out.println(PluginName + "INI: Homes");
                        try {
                            Home.Database.iniDB();
                            Home.HomeBuyList.iniHomeBuyList();
                            Home.Database.HomeMax.iniMaxList();
                            bHome = true;
                        } catch (NumberFormatException ex) {
                            System.err.println(PluginName + "INI ERR: Homes could not be initialized!");
                            System.err.println(PluginName + "INI ERR: NumberFormatException at HomeBuyList!");
                            System.err.println(PluginName + "INI ERR: Prove the 'HomeBuy.property'!");
                            System.err.println(PluginName + "INI ERR: Events have been stopped");
                            StopPlugin();

                        } catch (Exception ex) {
                            System.err.println(PluginName + "INI ERR: Homes could not be initialized!");
                            System.err.println(PluginName + "INI ERR: Events have been stopped");
                            System.err.println(PluginName + "INI ERR: ----Error Report----");
                            ex.printStackTrace();
                            System.err.println(PluginName + "INI ERR: ---------------------");
                            StopPlugin();

                        }

                        if (bHome) {
                            registerEventListener(HomesEvents);
                            System.out.println(PluginName + "INI: Homes - OK");

                        }
                    }
                    registerEventListener(MailSystemEvents);
                } else {
                    System.err.println(PluginName + "INI ERR: Permissions has an error!");
                    System.err.println(PluginName + "INI ERR: In order not to damage the system, the plugin is switched off!");
                    StopPlugin();

                }
            } else {
                System.err.println(PluginName + "INI ERR: Config has an error!");
                System.err.println(PluginName + "INI ERR: In order not to damage the system, the plugin is switched off!");
                StopPlugin();

            }
        } catch (Exception ex) {
            System.out.println(PluginName + "INI ERR: At the start it throw an exeption.");
            System.err.println(PluginName + "INI ERR: ----Error Report----");
            ex.printStackTrace();
            System.err.println(PluginName + "INI ERR: ---------------------");
            StopPlugin();

        }
    }

    public void StopPlugin() {
        System.err.println(PluginName + "Stop Plugin...");
        unregisterEventListener(TicketSystemEvent);
        unregisterEventListener(SignEvents);
        unregisterEventListener(PlayerConnectEvent);
        unregisterEventListener(MailSystemEvents);
        unregisterEventListener(HomesEvents);
        PluginStop = true;
        System.out.println(PluginName + "Desabled");

    }

    @Override
    public void onDisable() {
        if (!PluginStop) {
            System.out.println(PluginName + "Desabled");
        }
    }

    public class cConfig {

        private asConfig sysConfig;
        private final AktiveSign plugin;

        public String DebugLevel, UseSign_AdminHelp_Group;
        public int Debug, Home_max, Home_start, UseSign_Fly_WaringTime;
        public float Chest_Slot_Price;

        public boolean AuthorDebug, Protection, AutoSave, AutoSaveOnlyAdmin, Chest_SlotChange_OnlyAdmin, GlobalChest_ByCommand,
                Teleport, Teleport_OnlyAdmin, Teleport_Question, UseSign_Free, UseSign_Heal, UseSign_Time, UseSign_Weather, UseSign_Warp, UseSign_GetSize,
                UseSign_SpawnNPC, UseSign_Gamemode, UseSign_Buy, UseSign_BuyPlot, UseSign_Sell, UseSign_SellAll, UseSign_Spawn, UseSign_Ticket, UseSign_Fly,
                UseSign_Transmitter, UseSign_UserShop, UseSign_Bank, UseSign_setGroup, UseSign_TeamSpeak, UseSign_Wetness, UseSign_Journal, UseSign_GlobalChest,
                UseSign_Home, UseSign_Balance, UseSign_AdminHelp, ChatMailSystem, ChatStopCommand, Flying, GetSize;

        public cConfig(AktiveSign plugin) {
            this.plugin = plugin;
        }

        private boolean iniConfig() {

            try {
                String[][] sysConfigArray = {
                    //Name         Wert
                    {"Debug", "0"},
                    {"DebugLevel", "ALL"},
                    //{"Protection", "true"},
                    //{"AutoSave", "false"},
                    //{"AutoSaveOnlyAdmin", "true"},
                    //{"ChatMailSystem", "true"},
                    //{"ChatStopCommand", "true"},
                    //{"Chest_Slot_Price", "1"},
                    //{"Chest_SlotChange_OnlyAdmin", "false"},
                    //{"GlobalChest_ByCommand", "true"},
                    //{"Home_max", "1"},
                    //{"Home_start", "1"},
                    //{"Teleport", "true"},
                    //{"Teleport_OnlyAdmin", "true"},
                    //{"Teleport_Question", "true"},
                    //{"Teleport_Cost", "0"},
                    //{"UseSign_Mail", "true"},
                    //{"UseSign_AdminHelp", "true"},
                    //{"UseSign_Free", "true"},
                    //{"UseSign_Heal", "true"},
                    //{"UseSign_Time", "true"},
                    //{"UseSign_Weather", "true"},
                    //{"UseSign_Warp", "true"},
                    //{"UseSign_SpawnNPC", "true"},
                    {"UseSign_Gamemode", "true"},
                    //{"UseSign_Buy", "true"},
                    //{"UseSign_BuyPlot", "false"},
                    //{"UseSign_Sell", "true"},
                    //{"UseSign_SellAll", "true"},
                    //{"UseSign_Spawn", "true"},
                    //{"UseSign_Fly", "true"},
                    //{"UseSign_Fly_WaringTime", "20"},
                    //{"UseSign_GetSize", "true"},
                    //{"UseSign_Ticket", "false"}, 
                    //{"UseSign_Transmitter", "true"},
                    //{"UseSign_UserShop", "true"},
                    //{"UseSign_Bank", "true"},
                    {"UseSign_setGroup", "true"} //<<<<-------------- LETZTE
                    //{"UseSign_AdminHelp_Group", ""},
                    //{"UseSign_Diving", "true"},
                    //{"UseSign_SprintSpeed", "true"},
                    //{"UseSign_WalkSpeed", "true"},
                    //{"UseSign_TeamSpeak", "true"},
                    //{"UseSign_JumpSpeed", "true"},
                    //{"UseSign_Wetness", "true"},
                    //{"UseSign_Journal", "true"},
                    //{"UseSign_ShowMap", "true"},
                    //{"UseSign_GlobalChest", "true"},
                    //{"UseSign_Home", "true"} 
                //{"Flying", "true"},
                //{"GetSize", "true"},
                //{"UseSign_Balance", "true"}
                //{"UseSign_Teleport", "true"}
                };

                sysConfig = new asConfig("System", sysConfigArray, plugin, 0);

                //AuthorDebug = Boolean.parseBoolean(sysConfig.getValue("AuthorDebug"));
                //Protection = Boolean.parseBoolean(sysConfig.getValue("Protection"));
                //AutoSave = Boolean.parseBoolean(sysConfig.getValue("AutoSave"));
                //AutoSaveOnlyAdmin = Boolean.parseBoolean(sysConfig.getValue("AutoSaveOnlyAdmin"));
                //Chest_SlotChange_OnlyAdmin = Boolean.parseBoolean(sysConfig.getValue("Chest_SlotChange_OnlyAdmin"));
                //ChatMailSystem = Boolean.parseBoolean(sysConfig.getValue("ChatMailSystem"));
                //ChatStopCommand = Boolean.parseBoolean(sysConfig.getValue("ChatStopCommand"));
                //GlobalChest_ByCommand = Boolean.parseBoolean(sysConfig.getValue("GlobalChest_ByCommand"));
                //UseSign_AdminHelp = Boolean.parseBoolean(sysConfig.getValue("UseSign_AdminHelp"));
                //Teleport = Boolean.parseBoolean(sysConfig.getValue("Teleport"));
                //Teleport_OnlyAdmin = Boolean.parseBoolean(sysConfig.getValue("Teleport_OnlyAdmin"));
                //Teleport_Question = Boolean.parseBoolean(sysConfig.getValue("Teleport_Question"));
                //UseSign_Free = Boolean.parseBoolean(sysConfig.getValue("UseSign_Free"));
                //UseSign_Heal = Boolean.parseBoolean(sysConfig.getValue("UseSign_Heal"));
                //UseSign_Time = Boolean.parseBoolean(sysConfig.getValue("UseSign_Time"));
                //UseSign_Weather = Boolean.parseBoolean(sysConfig.getValue("UseSign_Weather"));
                //UseSign_Warp = Boolean.parseBoolean(sysConfig.getValue("UseSign_Warp"));
                //UseSign_SpawnNPC = Boolean.parseBoolean(sysConfig.getValue("UseSign_SpawnNPC"));
                UseSign_Gamemode = Boolean.parseBoolean(sysConfig.getValue("UseSign_Gamemode"));
                //UseSign_Buy = Boolean.parseBoolean(sysConfig.getValue("UseSign_Buy"));
                //UseSign_BuyPlot = Boolean.parseBoolean(sysConfig.getValue("UseSign_BuyPlot"));
                //UseSign_Sell = Boolean.parseBoolean(sysConfig.getValue("UseSign_Sell"));
                //UseSign_SellAll = Boolean.parseBoolean(sysConfig.getValue("UseSign_SellAll"));
                //UseSign_Spawn = Boolean.parseBoolean(sysConfig.getValue("UseSign_Spawn"));
                //UseSign_Ticket = Boolean.parseBoolean(sysConfig.getValue("UseSign_Ticket"));
                //UseSign_Transmitter = Boolean.parseBoolean(sysConfig.getValue("UseSign_Transmitter"));
                //UseSign_UserShop = Boolean.parseBoolean(sysConfig.getValue("UseSign_UserShop"));
                //UseSign_Bank = Boolean.parseBoolean(sysConfig.getValue("UseSign_Bank"));
                UseSign_setGroup = Boolean.parseBoolean(sysConfig.getValue("UseSign_setGroup"));
                //UseSign_TeamSpeak = Boolean.parseBoolean(sysConfig.getValue("UseSign_TeamSpeak"));
                //UseSign_Wetness = Boolean.parseBoolean(sysConfig.getValue("UseSign_Wetness"));
                //UseSign_Journal = Boolean.parseBoolean(sysConfig.getValue("UseSign_Journal"));
                //UseSign_GlobalChest = Boolean.parseBoolean(sysConfig.getValue("UseSign_GlobalChest"));
                UseSign_Home = /*Boolean.parseBoolean(sysConfig.getValue("UseSign_Home"));*/ false;
                //UseSign_Balance = Boolean.parseBoolean(sysConfig.getValue("UseSign_Balance"));
                //UseSign_GetSize = Boolean.parseBoolean(sysConfig.getValue("UseSign_GetSize"));
                //UseSign_Fly = Boolean.parseBoolean(sysConfig.getValue("UseSign_Fly"));
                //UseSign_Fly_WaringTime = Integer.parseInt(sysConfig.getValue("UseSign_Fly_WaringTime"));
                //Flying = Boolean.parseBoolean(sysConfig.getValue("Flying"));
                //GetSize = Boolean.parseBoolean(sysConfig.getValue("GetSize"));
                //Chest_Slot_Price = Float.parseFloat(sysConfig.getValue("Chest_Slot_Price"));
                Debug = Integer.parseInt(sysConfig.getValue("Debug"));
                Home_max = /*Integer.parseInt(sysConfig.getValue("Home_max"));*/ 0;
                Home_start = /*Integer.parseInt(sysConfig.getValue("Home_start"));*/ 0;
                DebugLevel = sysConfig.getValue("DebugLevel");
                //UseSign_AdminHelp_Group = sysConfig.getValue("UseSign_AdminHelp_Group");
            } catch (NumberFormatException ex) {
                System.err.println(PluginName + "INI CONFIG ERR: Config has an error!");
                System.err.println(PluginName + "INI ERR: ----Error Report----");
                ex.printStackTrace();;
                System.err.println(PluginName + "INI ERR: ---------------------");
                return false;
            } catch (Exception ex) {
                System.err.println(PluginName + "INI CONFIG ERR: Config has an error!");
                System.err.println(PluginName + "INI ERR: ----Error Report----");
                ex.printStackTrace();
                System.err.println(PluginName + "INI ERR: ---------------------");
                return false;
            }
            return true;
        }
    }

}
