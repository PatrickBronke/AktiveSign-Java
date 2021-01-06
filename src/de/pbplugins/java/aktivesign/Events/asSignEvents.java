package de.pbplugins.java.aktivesign.Events;

import de.pbplugins.java.aktivesign.AktiveSign;
import net.risingworld.api.events.EventMethod;
import net.risingworld.api.events.Listener;
import net.risingworld.api.events.player.PlayerObjectInteractionEvent;
import net.risingworld.api.events.player.PlayerSetSignTextEvent;
import net.risingworld.api.objects.Player;
import net.risingworld.api.objects.Sign;
import net.risingworld.api.utils.Definitions;

public class asSignEvents implements Listener {

    private final AktiveSign plugin;
    private final String Rot = "[#ff0000]", Grün = "[#00ff00]", Orange = "[#ffa500]", Schwarz = "[#000000]";
    private final int rot = 0xFF0000, grün = 0x00FF00, orange = 0xffa500;
    private final int debug;

    public asSignEvents(AktiveSign plugin) {
        this.plugin = plugin;
        debug = plugin.Config.Debug;
    }

    @EventMethod
    public void onPlayerObjectInteractionEvent(PlayerObjectInteractionEvent event) {
        Player player = event.getPlayer();
        Definitions.ObjectDefinition def = event.getObjectDefinition();
        Sign sign = null;

        if (def.isSign()) {
            if (debug >= 1) {
                System.out.println(plugin.PluginName + "Debug: Is Sign");
            }
            sign = plugin.getWorld().getSign(event.getObjectInfoID());
            if (sign != null) {
                if (!sign.getLineText(0).contains("#")) {
                    if (!plugin.Sign.Attribute.get.EditSign(player)) {
                        
                        if (!plugin.Sign.Attribute.get.Protection(player)) {
                            if (!plugin.Sign.Attribute.get.DestroySign(player)) {

                                int prozess = plugin.Sign.prozessSign(player, sign, true);
                                if (plugin.debug > 0) {
                                    System.out.println(plugin.PluginName + "Debug: prozess = " + prozess);
                                }
                                
                                if (prozess == plugin.Sign.ProzessStatus.Kein_AktiveSign){
                                    event.setCancelled(false);
                                } else {
                                    event.setCancelled(true);
                                }
                                
                                if (prozess != plugin.Sign.ProzessStatus.Alles_OK) {
                                    if (prozess == plugin.Sign.ProzessStatus.Schild) {
                                        player.sendTextMessage(Rot + "Leider ist das Schild falsch geschrieben!");
                                    } else if (prozess == plugin.Sign.ProzessStatus.Permission) {
                                        player.sendTextMessage(Rot + "Du hast nicht genug Geld oder keine Berechtigung dieses Schild zu verwenden!");
                                    } else if (prozess == plugin.Sign.ProzessStatus.Geld) {
                                        player.sendTextMessage(Rot + "Du hast nicht genug Geld!");
                                    }

                                }
                            }
                        }
                    }
                } else {
                    //TODO Protection prüfen
                    event.setCancelled(false);
                }
            }
        }
        if (plugin.debug > 0){
            System.out.println("InteractSign Cancelled = " + event.isCancelled());
        }
    }

    @EventMethod
    public void onPlayerSetSignTextEvent(PlayerSetSignTextEvent event) {
        Player player = event.getPlayer();
        String line1, line2, line3, line4;
        line1 = event.getLineText(0);
        line2 = event.getLineText(1);
        line3 = event.getLineText(2);
        line4 = event.getLineText(3);

        int prozess = plugin.Sign.prozessSign(player, line1, line2, line3, line4, false);
        if (plugin.debug > 0) {
            System.out.println(plugin.PluginName + "Debug: EditSign - prozess = " + prozess);
        }
        if (prozess == plugin.Sign.ProzessStatus.Kein_AktiveSign) {
            event.setCancelled(false);
        } else if (prozess == plugin.Sign.ProzessStatus.Config) {
            event.setCancelled(true);
        } else if (prozess == plugin.Sign.ProzessStatus.Permission) {
            event.setCancelled(true);
            player.sendTextMessage(Rot + "Du hast nicht genügend Berechtigung um dieses Schild zu setzen!");
        } else if (prozess == plugin.Sign.ProzessStatus.Schild) {
            event.setCancelled(false);
            event.setLine(0, rot, line1);
            player.sendTextMessage(Rot + "Das Schild wurde falsch geschrieben!");

        } else if (prozess == plugin.Sign.ProzessStatus.Alles_OK) {
            event.setCancelled(false);
            event.setLine(0, grün, line1);
        } else {
            event.setCancelled(false);
        }
        if (plugin.debug > 0){
            System.out.println("SetSignText Cancelled = " + event.isCancelled());
        }
    }
}
