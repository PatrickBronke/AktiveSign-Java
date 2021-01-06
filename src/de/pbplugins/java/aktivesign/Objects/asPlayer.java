/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.pbplugins.java.aktivesign.Objects;

import de.pbplugins.java.aktivesign.AktiveSign;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.risingworld.api.objects.Inventory;
import net.risingworld.api.objects.Item;
import net.risingworld.api.objects.Player;
import net.risingworld.api.utils.Definitions;


public class asPlayer {
    
    private AktiveSign plugin;
    
    public asPlayer(AktiveSign plugin){
        this.plugin = plugin;
    }
    
    public boolean removeEquippedItem(Player player, int amounth) {
        boolean prüfer = false;
        if (player.getEquippedItem().getStacksize() >= amounth) {
            player.getInventory().removeItem(player.getInventory().getQuickslotFocus(), Inventory.SlotType.Quickslot, amounth);
            prüfer = true;
        }

        return prüfer;
    }

    public boolean addItem(Player player, Definitions.ItemDefinition itemDef, int var, int amounth) {
        Item item = player.getInventory().insertNewItem(itemDef.getID(), var, amounth);
        return (item != null);
    }

    public boolean addItem(Player player, Definitions.ObjectDefinition obDef, int var, int amounth) {
        Item item = player.getInventory().insertNewObjectItem(obDef.getID(), var, amounth);
        return (item != null);
    }

    public boolean addItem(Player player, Definitions.ClothingDefinition cloDef) {
        Item item = player.getInventory().insertNewClothingItem(cloDef.getID(), 0, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF);
        return (item != null);
    }

    public int removeAllObjects(Player player, short ObjectID, int var, boolean stack) {
        boolean stopp = false;
        int anzahl = 0;
        int maxQSlot = player.getInventory().getSlotCount(Inventory.SlotType.Quickslot);
        int maxISlot = player.getInventory().getSlotCount(Inventory.SlotType.Inventory);

        for (int slot = 0; slot <= maxQSlot || stopp == true; slot++) {
            Item item = player.getInventory().getItem(slot, Inventory.SlotType.Quickslot);
            if (item != null) {
                Item.Attribute attribute = item.getAttribute();
                if (attribute != null) {
                    if (attribute instanceof Item.ObjectAttribute) {
                        if (ObjectID == ((Item.ObjectAttribute) attribute).getObjectID()) {
                            if (stack) {
                                if (item.getStacksize() == item.getMaxStacksize()) {
                                    anzahl++;
                                    player.getInventory().removeItem(slot, Inventory.SlotType.Quickslot, item.getStacksize());
                                }
                            } else {
                                anzahl = anzahl + item.getStacksize();
                                player.getInventory().removeItem(slot, Inventory.SlotType.Quickslot, item.getStacksize());
                            }

                        }
                    }
                }
            }
        }

        for (int slot = 0; slot <= maxISlot || stopp == true; slot++) {
            Item item = player.getInventory().getItem(slot, Inventory.SlotType.Inventory);
            if (item != null) {
                Item.Attribute attribute = item.getAttribute();
                if (attribute != null) {
                    if (attribute instanceof Item.ObjectAttribute) {
                        if (ObjectID == ((Item.ObjectAttribute) attribute).getObjectID()) {
                            if (stack) {
                                if (item.getStacksize() == item.getMaxStacksize()) {
                                    anzahl++;
                                    player.getInventory().removeItem(slot, Inventory.SlotType.Inventory, item.getStacksize());
                                }
                            } else {
                                anzahl = anzahl + item.getStacksize();
                                player.getInventory().removeItem(slot, Inventory.SlotType.Inventory, item.getStacksize());
                            }

                        }
                    }
                }
            }
        }
        return anzahl;
    }

    public int removeAllClothing(Player player, short ClothingID, boolean stack) {
        boolean stopp = false;
        int anzahl = 0;
        int maxQSlot = player.getInventory().getSlotCount(Inventory.SlotType.Quickslot);
        int maxISlot = player.getInventory().getSlotCount(Inventory.SlotType.Inventory);

        for (int slot = 0; slot <= maxQSlot || stopp == true; slot++) {
            Item item = player.getInventory().getItem(slot, Inventory.SlotType.Quickslot);
            if (item != null) {
                Item.Attribute attribute = item.getAttribute();
                if (attribute != null) {
                    if (attribute instanceof Item.ClothingAttribute) {
                        if (ClothingID == ((Item.ClothingAttribute) attribute).getClothingID()) {
                            if (stack) {
                                if (item.getStacksize() == item.getMaxStacksize()) {
                                    anzahl++;
                                    player.getInventory().removeItem(slot, Inventory.SlotType.Quickslot, item.getStacksize());
                                }
                            } else {
                                anzahl = anzahl + item.getStacksize();
                                player.getInventory().removeItem(slot, Inventory.SlotType.Quickslot, item.getStacksize());
                            }

                        }
                    }
                }
            }
        }

        for (int slot = 0; slot <= maxISlot || stopp == true; slot++) {
            Item item = player.getInventory().getItem(slot, Inventory.SlotType.Inventory);
            if (item != null) {
                Item.Attribute attribute = item.getAttribute();
                if (attribute != null) {
                    if (attribute instanceof Item.ClothingAttribute) {
                        if (ClothingID == ((Item.ClothingAttribute) attribute).getClothingID()) {
                            if (stack) {
                                if (item.getStacksize() == item.getMaxStacksize()) {
                                    anzahl++;
                                    player.getInventory().removeItem(slot, Inventory.SlotType.Inventory, item.getStacksize());
                                }
                            } else {
                                anzahl = anzahl + item.getStacksize();
                                player.getInventory().removeItem(slot, Inventory.SlotType.Inventory, item.getStacksize());
                            }

                        }
                    }
                }
            }
        }
        return anzahl;
    }

    public int removeAllItems(Player player, short ItemID, int var, boolean stack) {
        boolean stopp = false;
        int anzahl = 0;
        int maxQSlot = player.getInventory().getSlotCount(Inventory.SlotType.Quickslot);
        int maxISlot = player.getInventory().getSlotCount(Inventory.SlotType.Inventory);

        for (int slot = 0; slot <= maxQSlot || stopp == true; slot++) {
            Item item = player.getInventory().getItem(slot, Inventory.SlotType.Quickslot);
            if (item != null) {
                if (ItemID == item.getTypeID() && var == item.getVariation()) {
                    if (stack) {
                        if (item.getStacksize() == item.getMaxStacksize()) {
                            anzahl++;
                            player.getInventory().removeItem(slot, Inventory.SlotType.Quickslot, item.getStacksize());
                        }
                    } else {
                        anzahl = anzahl + item.getStacksize();
                        player.getInventory().removeItem(slot, Inventory.SlotType.Quickslot, item.getStacksize());
                    }
                }
            }
        }

        for (int slot = 0; slot <= maxISlot || stopp == true; slot++) {
            Item item = player.getInventory().getItem(slot, Inventory.SlotType.Inventory);
            if (item != null) {
                if (ItemID == item.getTypeID() && var == item.getVariation()) {
                    if (stack) {
                        if (item.getStacksize() == item.getMaxStacksize()) {
                            anzahl++;
                            player.getInventory().removeItem(slot, Inventory.SlotType.Inventory, item.getStacksize());
                        }
                    } else {
                        anzahl = anzahl + item.getStacksize();
                        player.getInventory().removeItem(slot, Inventory.SlotType.Inventory, item.getStacksize());
                    }
                }
            }
        }
        return anzahl;
    }

    public boolean removeObject(Player player, short ObjectID, int var, int menge) {
        boolean stopp = false;
        int maxQSlot = player.getInventory().getSlotCount(Inventory.SlotType.Quickslot);
        int maxISlot = player.getInventory().getSlotCount(Inventory.SlotType.Inventory);

        for (int slot = 0; slot <= maxQSlot && stopp == false; slot++) {
                Item item = player.getInventory().getItem(slot, Inventory.SlotType.Quickslot);
                if (item != null) {
                    Item.Attribute attribute = item.getAttribute();
                    if (attribute != null) {
                        if (attribute instanceof Item.ObjectAttribute) {
                            if (ObjectID == ((Item.ObjectAttribute) attribute).getObjectID()) {
                                if (item.getVariation() == var) {
                                    if (item.getStacksize() >= menge) {
                                        player.getInventory().removeItem(slot, Inventory.SlotType.Quickslot, menge);
                                        stopp = true;
                                    }
                                }
                            }
                        }
                    }
                }
        }
        if (!stopp) {
            for (int slot = 0; slot <= maxISlot && stopp == false; slot++) {
                    Item item = player.getInventory().getItem(slot, Inventory.SlotType.Inventory);
                    if (item != null) {
                        Item.Attribute attribute = item.getAttribute();
                        if (attribute instanceof Item.ObjectAttribute) {
                            if (ObjectID == ((Item.ObjectAttribute) attribute).getObjectID()) {
                                if (item.getVariation() == var) {
                                    if (item.getStacksize() >= menge) {
                                        player.getInventory().removeItem(slot, Inventory.SlotType.Inventory, menge);
                                        stopp = true;
                                    }
                                }
                            }
                        }
                    }
                
            }
        }
        return stopp;
    }

    public boolean removeClothing(Player player, short ClothingID) {
        boolean stopp = false;
        int maxQSlot = player.getInventory().getSlotCount(Inventory.SlotType.Quickslot);
        int maxISlot = player.getInventory().getSlotCount(Inventory.SlotType.Inventory);

        for (int slot = 0; slot <= maxQSlot && stopp == false; slot++) {
            Item item = player.getInventory().getItem(slot, Inventory.SlotType.Quickslot);
            if (item != null) {
                Item.Attribute attribute = item.getAttribute();
                if (attribute != null) {
                    if (attribute instanceof Item.ClothingAttribute) {
                        if (ClothingID == ((Item.ClothingAttribute) attribute).getClothingID()) {
                            player.getInventory().removeItem(slot, Inventory.SlotType.Quickslot, 1);
                            stopp = true;
                        }
                    }
                }
            }
        }
        if (!stopp) {
            for (int slot = 0; slot <= maxISlot && stopp == false; slot++) {
                Item item = player.getInventory().getItem(slot, Inventory.SlotType.Inventory);
                if (item != null) {
                    Item.Attribute attribute = item.getAttribute();
                    if (attribute != null) {
                        if (attribute instanceof Item.ClothingAttribute) {
                            if (ClothingID == ((Item.ClothingAttribute) attribute).getClothingID()) {
                                player.getInventory().removeItem(slot, Inventory.SlotType.Inventory, 1);
                                stopp = true;
                            }
                        }
                    }
                }
            }
        }
        return stopp;
    }

    public boolean removeItem(Player player, short ItemID, int var, int menge) {
        boolean stopp = false;
        int maxQSlot = player.getInventory().getSlotCount(Inventory.SlotType.Quickslot);
        int maxISlot = player.getInventory().getSlotCount(Inventory.SlotType.Inventory);

        for (int slot = 0; slot <= maxQSlot && stopp == false; slot++) {
            Item item = player.getInventory().getItem(slot, Inventory.SlotType.Quickslot);
            if (item != null) {
                if (ItemID == item.getTypeID()) {
                    if (item.getVariation() == var) {
                        if (item.getStacksize() >= menge) {
                            player.getInventory().removeItem(slot, Inventory.SlotType.Quickslot, menge);
                            stopp = true;
                        }
                    }

                }
            }
        }
        if (!stopp) {
            for (int slot = 0; slot <= maxISlot && stopp == false; slot++) {
                Item item = player.getInventory().getItem(slot, Inventory.SlotType.Inventory);
                if (item != null) {
                    if (ItemID == item.getTypeID()) {
                        if (item.getVariation() == var) {
                            if (item.getStacksize() >= menge) {
                                player.getInventory().removeItem(slot, Inventory.SlotType.Inventory, menge);
                                stopp = true;
                            }
                        }
                    }
                }
            }
        }
        return stopp;
    }
    
    public String UIDtoPlayername(long uid) {
        String name = null;

        try (ResultSet result = plugin.getWorldDatabase().executeQuery("SELECT * FROM `Player` WHERE `UID` = '" + uid + "'")) {
            if (result != null) {
                while (result.next()) {
                    name = result.getString("Name");
                }
            }
        } catch (SQLException ex) {

        }

        return name;
    }
    
    public long PlayernameToUID(String name) {
        long uid = -1;
        try (ResultSet result = plugin.getWorldDatabase().executeQuery("SELECT * FROM `Player` WHERE `Name` = '" + name + "'")) {
            if (result != null) {
                while (result.next()) {
                    uid = result.getLong("UID");
                }
            }
        } catch (SQLException ex) {

        }

        return uid;
    }
    
}
