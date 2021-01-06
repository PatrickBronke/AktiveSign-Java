package de.pbplugins.java.aktivesign.Events;

import de.pbplugins.java.aktivesign.AktiveSign;
import java.util.List;
import net.risingworld.api.events.EventMethod;
import net.risingworld.api.events.Listener;
import net.risingworld.api.events.player.PlayerChatEvent;
import net.risingworld.api.events.player.PlayerCommandEvent;
import net.risingworld.api.objects.Player;


public class asMailSystemEvents implements Listener{
    
    private final AktiveSign plugin;
    
    
    public asMailSystemEvents(AktiveSign plugin){
        this.plugin = plugin;
    }
    
    
    @EventMethod
    public void onPlayerChatEvent(PlayerChatEvent event){
        Player Sender = event.getPlayer();
        String PlayerName = event.getPlayerChatName();
        String Msg = event.getChatMessage();
        
        event.setCancelled(true);
        plugin.getServer().getAllPlayers().forEach((Empfänger) -> {
            if (plugin.Chat.Attribute.get.ChatStop(Empfänger)){
                plugin.Chat.Attribute.get.ChatResaver(Empfänger).add(PlayerName + ": " + Msg);
                Sender.sendTextMessage("(" + Empfänger.getName() + ": Chat stopped!)");
            } else {
                Empfänger.sendTextMessage(PlayerName + ": " + Msg);
            }
        });
    }
    
    @EventMethod
    public void onPlayerCommandEvent(PlayerCommandEvent event){
        Player player = event.getPlayer();
        String command = event.getCommand();
        String[] cmd = command.split(" ");
        String[] cmd3 = command.split(" ", 3);
        
        if (cmd.length == 1){
            if (cmd[0].toLowerCase().equals("/chat") || cmd[0].toLowerCase().equals("/as") || cmd[0].toLowerCase().equals("/mail")){
                //TODO MailSystem HELP
                
            }
        }
        if (cmd[0].toLowerCase().equals("/chat")){
            if (cmd.length == 2){
                if (cmd[1].toLowerCase().equals("start")){
                    plugin.Chat.startChat(player);
                    
                }
                if (cmd[1].toLowerCase().equals("stop")){
                    plugin.Chat.stopChat(player);
                    
                }
            }
        }
    }
}
