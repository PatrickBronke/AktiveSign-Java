package de.pbplugins.java.aktivesign.TicketSystem;

import de.pbplugins.java.aktivesign.AktiveSign;
import net.risingworld.api.Server;
import net.risingworld.api.objects.custom.CustomItem;
import net.risingworld.api.utils.ImageInformation;
import net.risingworld.api.utils.ModelInformation;

public final class asTickets {

    private final AktiveSign plugin;
    private final Server server;
    CustomItem item;
    private final ModelInformation model;

    public final String uuidGameMode, uuidTicket1, uuidTicket2, uuidEvent, uuidHeal,
            uuidWeather, uuidTime, uuidBlue, uuidGreen, uuidGold, uuidHomes, uuidFly,
            uuidSetGroup, uuidGetSize, uuidWarp, uuidSpawnNPC, uuidSlotChange, uuidVIP;

    public final String nameGameMode, nameTicket1, nameTicket2, nameEvent, nameHeal,
            nameWeather, nameTime, nameBlue, nameGreen, nameGold, nameHomes, nameFly,
            nameSetGroup, nameGetSize, nameWarp, nameSpawnNPC, nameSlotChange, nameVIP;

    public asTickets(AktiveSign plugin) {
        this.plugin = plugin;
        this.server = plugin.getServer();
        this.model = new ModelInformation(plugin, plugin.getPath() + "/resources/Tickets/Ticket.obj");
        System.out.println(plugin.PluginName + "INI: TicketModel = " + model);
        uuidGameMode = "AktiveSignTicketGameMode";
        uuidTicket1 = "AktiveSignTicket1";
        uuidTicket2 = "AktiveSignTicket2";
        uuidEvent = "AktiveSignTicketEvent";
        uuidHeal = "AktiveSignTicketHeal";
        uuidWeather = "AktiveSignTicketWeather";
        uuidTime = "AktiveSignTicketTime";
        uuidBlue = "AktiveSignTicketBlue";
        uuidGreen = "AktiveSignTicketGreen";
        uuidGold = "AktiveSignTicketGold";
        uuidHomes = "AktiveSignTicketHomes";
        uuidFly = "AktiveSignTicketFly";
        uuidSetGroup = "AktiveSignTicketSetGroup";
        uuidGetSize = "AktiveSignTicketGetSize";
        uuidWarp = "AktiveSignTicketWarp";
        uuidSpawnNPC = "AktiveSignTicketSpawnNPC";
        uuidSlotChange = "AktiveSignTicketSlotChange";
        uuidVIP = "AktiveSignTicketVIP";

        nameGameMode = "GameModeTicket";
        nameTicket1 = "Ticket1";
        nameTicket2 = "Ticket2";
        nameEvent = "EventTicket";
        nameHeal = "HealTicket";
        nameWeather = "WeatherTicket";
        nameTime = "TimeTicket";
        nameBlue = "BlueTicket";
        nameGreen = "GreenTicket";
        nameGold = "GoldTicket";
        nameHomes = "HomesTicket";
        nameFly = "FlyTicket";
        nameSetGroup = "SetGroupTicket";
        nameGetSize = "SetSizeTicket";
        nameWarp = "WarpTicket";
        nameSpawnNPC = "SpawnNpcTicket";
        nameSlotChange = "SlotChangeTicket";
        nameVIP = "VipTicket";
        iniTickets();
    }

    private void iniTickets() {
        iniTicket1();
        iniTicket2();
    }

    private void iniGameMode() {
        item = new CustomItem(uuidGameMode, nameGameMode);

    }

    private void iniTicket1() {
        System.out.println(plugin.PluginName + "INI: Ticket1");
        
        ImageInformation texture = new ImageInformation(plugin, plugin.getPath() + "/resources/Tickets/Ticket1.png");
        ImageInformation icon = new ImageInformation(plugin, plugin.getPath() + "/resources/Tickets/Ticket1 ICON.png");
        
        System.out.println(plugin.PluginName + "INI: Ticket texture = " + texture);
        System.out.println(plugin.PluginName + "INI: Ticket icon = " + icon);
        
        item = new CustomItem(uuidTicket1, nameTicket1);
        item.setModel(model, texture, 0.2f);
        item.setIcon(icon);
        item.setHand(CustomItem.Hand.Right);
        item.setMaxStacksize(1);
        item.setLocalizedNames("en=Ticket1", "de=Ticket1");
        item.setItemPosition(0f, 0f, 0f);
        item.setItemRotation(0f, 0f, 0f, 1f);
        item.setFPBodyPosition(0f, 0f, 0f);
        item.setFPBodyRotation(0f, 0f, 0f, 1f);
        server.registerCustomItem(item);
    }

    private void iniTicket2() {
        System.out.println(plugin.PluginName + "INI: Ticket2");
        ImageInformation texture = new ImageInformation(plugin, plugin.getPath() + "/resources/Tickets/Ticket2.png");
        ImageInformation icon = new ImageInformation(plugin, plugin.getPath() + "/resources/Tickets/Ticket2 ICON.png");
        System.out.println(plugin.PluginName + "INI: Ticket texture = " + texture);
        System.out.println(plugin.PluginName + "INI: Ticket icon = " + icon);
        
        
        item = new CustomItem(uuidTicket2, nameTicket2);
        item.setModel(model, texture, 0.2f);
        item.setIcon(icon);
        item.setHand(CustomItem.Hand.Right);
        item.setMaxStacksize(1);
        item.setLocalizedNames("en=Ticket2", "de=Ticket2");
        item.setItemPosition(0f, 0f, 0f);
        item.setItemRotation(0f, 0f, 0f, 1f);
        item.setFPBodyPosition(0f, 0f, 0f);
        item.setFPBodyRotation(0f, 0f, 0f, 1f);
        server.registerCustomItem(item);
    }

    private void iniEvent() {
        item = new CustomItem(uuidEvent, nameEvent);
    }

    private void iniHeal() {
        item = new CustomItem(uuidHeal, nameHeal);
    }

    private void iniGold() {
        item = new CustomItem(uuidGold, nameGold);
    }

    private void iniWeather() {
        item = new CustomItem(uuidWeather, nameWeather);
    }

    private void iniHomes() {
        item = new CustomItem(uuidHomes, nameHomes);
    }

    private void iniFly() {
        item = new CustomItem(uuidFly, nameFly);
    }

    private void iniSetGroup() {
        item = new CustomItem(uuidSetGroup, nameSetGroup);
    }

    private void iniGetSize() {
        item = new CustomItem(uuidGetSize, nameGetSize);
    }

    private void iniWarp() {
        item = new CustomItem(uuidWarp, nameWarp);
    }

    private void iniTime() {
        item = new CustomItem(uuidTime, nameTime);
    }

    private void iniSpawnNPC() {
        item = new CustomItem(uuidSpawnNPC, nameSpawnNPC);
    }

    private void iniSlotChange() {
        item = new CustomItem(uuidSlotChange, nameSlotChange);
    }

    private void iniBlueTicket() {
        item = new CustomItem(uuidBlue, nameBlue);
    }

    private void iniGreenTicket() {
        item = new CustomItem(uuidGreen, nameGreen);
    }

    private void iniVipTicket() {
        item = new CustomItem(uuidVIP, nameVIP);
    }
}
