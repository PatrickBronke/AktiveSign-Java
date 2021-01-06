package de.pbplugins.java.aktivesign.TicketSystem;

import de.pbplugins.java.aktivesign.AktiveSign;
import net.risingworld.api.objects.Inventory;
import net.risingworld.api.objects.Item;
import net.risingworld.api.objects.Player;

public class asTicketSystem {

    private final AktiveSign plugin;

    public final asTickets Tickets;

    public asTicketSystem(AktiveSign plugin) {
        this.plugin = plugin;
        this.Tickets = new asTickets(plugin);
    }

    public boolean addTicket(Player player, String TicketName) {
        //TODO Ticket - addTicket
        String uuid = NameToUUID(TicketName);
        if (uuid != null) {
            if (player.getInventory().insertNewCustomItem(uuid, 0, 1) != null){
                return true;
            }
        }
        return false;
    }

    public boolean removeTicket(Player player, String TicketName) {
        String uuid = NameToUUID(TicketName);
        Item item = player.getEquippedItem();
        if (item != null && uuid != null) {
            Item.Attribute attribute = item.getAttribute();
            if (attribute != null) {
                if (attribute instanceof Item.CustomItemAttribute) {
                    if (uuid.equals(((Item.CustomItemAttribute) attribute).getUUID())) {
                        player.getInventory().removeItem(player.getInventory().getQuickslotFocus(), Inventory.SlotType.Quickslot);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String NameToUUID(String name) {
        if (name.equals(Tickets.nameBlue)) {
            return Tickets.uuidBlue;
        }
        if (name.equals(Tickets.nameEvent)) {
            return Tickets.uuidEvent;
        }
        if (name.equals(Tickets.nameFly)) {
            return Tickets.uuidFly;
        }
        if (name.equals(Tickets.nameGameMode)) {
            return Tickets.uuidGameMode;
        }
        if (name.equals(Tickets.nameGetSize)) {
            return Tickets.uuidGetSize;
        }
        if (name.equals(Tickets.nameGold)) {
            return Tickets.uuidGold;
        }
        if (name.equals(Tickets.nameGreen)) {
            return Tickets.uuidGreen;
        }
        if (name.equals(Tickets.nameHeal)) {
            return Tickets.uuidHeal;
        }
        if (name.equals(Tickets.nameHomes)) {
            return Tickets.uuidHomes;
        }
        if (name.equals(Tickets.nameSetGroup)) {
            return Tickets.uuidSetGroup;
        }
        if (name.equals(Tickets.nameSlotChange)) {
            return Tickets.uuidSlotChange;
        }
        if (name.equals(Tickets.nameSpawnNPC)) {
            return Tickets.uuidSpawnNPC;
        }
        if (name.equals(Tickets.nameTicket1)) {
            return Tickets.uuidTicket1;
        }
        if (name.equals(Tickets.nameTicket2)) {
            return Tickets.uuidTicket2;
        }
        if (name.equals(Tickets.nameTime)) {
            return Tickets.uuidTime;
        }
        if (name.equals(Tickets.nameVIP)) {
            return Tickets.uuidVIP;
        }
        if (name.equals(Tickets.nameWarp)) {
            return Tickets.uuidWarp;
        }
        if (name.equals(Tickets.nameWeather)) {
            return Tickets.uuidWeather;
        } else {
            return null;
        }
    }

}
