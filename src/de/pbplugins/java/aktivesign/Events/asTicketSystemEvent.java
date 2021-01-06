package de.pbplugins.java.aktivesign.Events;

import de.pbplugins.java.aktivesign.AktiveSign;
import net.risingworld.api.events.EventMethod;
import net.risingworld.api.events.Listener;
import net.risingworld.api.events.player.PlayerCommandEvent;
import net.risingworld.api.objects.Player;

public class asTicketSystemEvent implements Listener {

    private final AktiveSign plugin;

    public asTicketSystemEvent(AktiveSign plugin) {
        this.plugin = plugin;
    }

    @EventMethod
    public void onPlayerCommandEvent(PlayerCommandEvent event) {
        Player player = event.getPlayer();
        String command = event.getCommand();
        String[] cmd = command.split(" ");

        if (cmd.length == 1) {
            if (cmd[0].toLowerCase().equals("/ticket") || cmd[0].toLowerCase().equals("/as")) {
                //TODO HILFE TICKET
            }
        }
        if (cmd[0].toLowerCase().equals("/ticket")) {
            if (cmd.length == 4) {
                if (cmd[1].toLowerCase().equals("give")){
                    Player player2 = plugin.getServer().getPlayer(cmd[2]);
                    if (player2 != null){
                        plugin.TicketSystem.addTicket(player, cmd[3]);
                    }
                }
                
            }
        }
    }
}
