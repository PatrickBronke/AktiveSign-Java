package de.pbplugins.java.aktivesign.Events;

import de.pbplugins.java.aktivesign.AktiveSign;
import net.risingworld.api.events.EventMethod;
import net.risingworld.api.events.Listener;
import net.risingworld.api.events.player.PlayerCommandEvent;
import net.risingworld.api.objects.Player;

public class asHomesEvents implements Listener {

    private final AktiveSign plugin;
    private final String Rot = "[#ff0000]", Grün = "[#00ff00]", Orange = "[#ffa500]", Schwarz = "[#000000]";

    public asHomesEvents(AktiveSign plugin) {
        this.plugin = plugin;
    }

    @EventMethod
    public void onPlayerCommandEvent(PlayerCommandEvent event) {
        Player player = event.getPlayer();
        String[] cmd = event.getCommand().split(" ");

        if (cmd.length == 2) {
            if (cmd[0].toLowerCase().equals("/as") || cmd[0].toLowerCase().equals("/home")) {
                if (cmd[1].toLowerCase().equals("help")) {
                    player.sendTextMessage(Orange + "/home");
                    player.sendTextMessage(Orange + "/homes");
                    player.sendTextMessage(Orange + "/home help");
                    player.sendTextMessage(Orange + "/home <Name>");
                    player.sendTextMessage(Orange + "/sethome");
                    player.sendTextMessage(Orange + "/sethome <Name>");
                    player.sendTextMessage(Orange + "/sethome <Old-Name> <New-Name>");
                    player.sendTextMessage(Orange + "/delhome");
                    player.sendTextMessage(Orange + "/delhome <Name>");

                }
            }
        }
        if (cmd[0].toLowerCase().equals("/home")) {
            if (cmd.length == 1) {
                if (plugin.Home.getHomePos(player) != null) {
                    player.setPosition(plugin.Home.getHomePos(player));
                    player.sendTextMessage(Grün + "Du wurdest nach Hause teleportiert!");
                } else {
                    player.sendTextMessage(Rot + "Der Standart-Home wurde nicht gesetzt!");
                }
            }
            if (cmd.length == 2) {
                if (!cmd[1].toLowerCase().equals("help")) {
                    String home = cmd[1].toLowerCase();
                    if (plugin.Home.getHomePos(player, home) != null) {
                        player.setPosition(plugin.Home.getHomePos(player, home));
                        player.sendTextMessage(Grün + "Du wurdest nach '" + home + "' teleportiert!");
                    } else {
                        player.sendTextMessage(Rot + "Der Home '" + home + "' gibt es nicht!");
                    }
                }
            }
        }
        if (cmd[0].toLowerCase().equals("/homes")) {
            if (!plugin.Home.Attribute.get.HomeList(player).isEmpty()) {
                player.sendTextMessage(Orange + "----- Deine Homes -----");
                plugin.Home.Attribute.get.HomeList(player).keySet().forEach((home) -> {
                    player.sendTextMessage(Orange + "- " + home);
                });
                player.sendTextMessage(Orange + "-----------------------");
            } else {
                player.sendTextMessage(Orange + "Du hast keine Homes gesetzt!");
            }
        }
        if (cmd[0].toLowerCase().equals("/sethome")) {
            if (cmd.length == 1) {
                if (plugin.Home.hasHome(player)) {
                    if (plugin.Home.setNewHomePos(player)) {
                        player.sendTextMessage(Grün + "Position des Homes erfoglreich geändert!");
                    } else {
                        player.sendTextMessage(Rot + "Position des Homes konnte nicht geändert werden!");
                        player.sendTextMessage(Rot + "Bitte versuche es später nocheinmal.");
                        player.sendTextMessage(Rot + "Wenn das Problem immernoch besteht, melde dich beim Admin!");
                    }
                } else {
                    if (plugin.Home.setNewHome(player)) {
                        player.sendTextMessage(Grün + "Du hast dein Home gesetzt!");
                    } else {
                        player.sendTextMessage(Rot + "Home konnte nicht gesetzt werden!");
                        player.sendTextMessage(Rot + "Bitte versuche es später nocheinmal.");
                        player.sendTextMessage(Rot + "Wenn das Problem immernoch besteht, melde dich beim Admin!");
                    }
                }
            }
            if (cmd.length == 2) {
                String home = cmd[1].toLowerCase();
                if (!home.equals("help")) {
                    if (plugin.Home.hasHome(player, home)) {
                        if (plugin.Home.setNewHomePos(player, home)) {
                            player.sendTextMessage(Grün + "Position von '" + home + "' erfoglreich geändert!");
                        } else {
                            player.sendTextMessage(Rot + "Position des Homes konnte nicht geändert werden!");
                            player.sendTextMessage(Rot + "Bitte versuche es später nocheinmal.");
                            player.sendTextMessage(Rot + "Wenn das Problem immernoch besteht, melde dich beim Admin!");
                        }
                    } else {
                        if (plugin.Home.getAmounth(player) == -1 || plugin.Home.getAmounth(player) < plugin.Home.getMaxAmounth(player)) {
                            if (plugin.Home.setNewHome(player, home)) {
                                player.sendTextMessage(Grün + "Du hast den Home '" + home + "' gesetzt! (" + plugin.Home.getAmounth(player) + "/" + plugin.Home.getMaxAmounth(player) + ")");
                            } else {
                                player.sendTextMessage(Rot + "Home konnte nicht gesetzt werden!");
                                player.sendTextMessage(Rot + "Bitte versuche es später nocheinmal.");
                                player.sendTextMessage(Rot + "Wenn das Problem immernoch besteht, melde dich beim Admin!");
                            }
                        } else {
                            player.sendTextMessage(Rot + "Du darfst höchstens " + plugin.Home.getMaxAmounth(player) + " Homes anlegen!");
                        }
                    }
                } else {
                    player.sendTextMessage(Rot + "Der Home darf nicht 'help' heissen, da dies ein Befehl ist!");
                }
            }
            if (cmd.length == 3) {
                String OldName = cmd[1].toLowerCase();
                String NewName = cmd[2].toLowerCase();
                if (plugin.Home.hasHome(player, OldName)) {
                    if (plugin.Home.setNewHomeName(player, OldName, NewName)) {
                        player.sendTextMessage(Grün + "Home '" + OldName + "' wurde erfolgreich zu '" + NewName + "' geändert!");
                    } else {
                        player.sendTextMessage(Rot + "Name des Homes konnte nicht geändert werden!");
                        player.sendTextMessage(Rot + "Bitte versuche es später nocheinmal.");
                        player.sendTextMessage(Rot + "Wenn das Problem immernoch besteht, melde dich beim Admin!");
                    }
                } else {
                    player.sendTextMessage(Rot + "Du hast keinen Home mit dem Namen: '" + OldName + "'!");
                    player.sendTextMessage(Rot + "Bitte gebe " + Orange + "'/homes'" + Rot + "ein, um deine Homes zu sehen!");
                }
            }

        }
        if (cmd[0].toLowerCase().equals("/delhome")) {
            if (cmd.length == 1) {
                if (plugin.Home.hasHome(player)) {
                    if (plugin.Home.removeHome(player)) {
                        player.sendTextMessage(Rot + "Home erfolgreich gelöscht!");
                    } else {
                        player.sendTextMessage(Rot + "Home konnte nicht gelöscht werden!");
                        player.sendTextMessage(Rot + "Bitte versuche es später nocheinmal.");
                        player.sendTextMessage(Rot + "Wenn das Problem immernoch besteht, melde dich beim Admin!");
                    }
                } else {
                    player.sendTextMessage(Rot + "Du hast den Standart-Home nicht gesetzt!");
                }
            }
            if (cmd.length == 2) {
                String home = cmd[1].toLowerCase();
                if (plugin.Home.hasHome(player, home)) {
                    if (plugin.Home.removeHome(player, home)) {
                        player.sendTextMessage(Grün + "Der Home '" + home + "' wurde erfolgreich gelöscht!");
                    } else {
                        player.sendTextMessage(Rot + "Home konnte nicht gelöscht werden!");
                        player.sendTextMessage(Rot + "Bitte versuche es später nocheinmal.");
                        player.sendTextMessage(Rot + "Wenn das Problem immernoch besteht, melde dich beim Admin!");
                    }
                } else {
                    player.sendTextMessage(Rot + "Du hast keinen Home mit dem Namen: '" + home + "'!");
                    player.sendTextMessage(Rot + "Bitte gebe " + Orange + "'/homes'" + Rot + "ein, um deine Homes zu sehen!");
                }
            }
        }
    }

}
