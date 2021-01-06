package de.pbplugins.java.aktivesign.Events;

import de.pbplugins.java.aktivesign.AktiveSign;
import net.risingworld.api.events.EventMethod;
import net.risingworld.api.events.Listener;
import net.risingworld.api.events.player.PlayerConnectEvent;
import net.risingworld.api.objects.Player;

public class asPlayerConnectEvent implements Listener {

    private final AktiveSign plugin;

    public asPlayerConnectEvent(AktiveSign plugin) {
        this.plugin = plugin;
    }

    @EventMethod
    public void onPlayerConnectEvent(PlayerConnectEvent event) {
        try {
            Player player = event.getPlayer();
            plugin.Sign.Attribute.set.EditSign(player, false);
            plugin.Sign.Attribute.set.Protection(player, false);
            plugin.Sign.Attribute.set.DestroySign(player, false);
            plugin.Chat.Attribute.set.ChatStop(player, false);
            plugin.Chat.Attribute.set.NewChatResaver(player);
            if (plugin.Config.UseSign_Home) {
                plugin.Home.Attribute.set.NewHomeList(player);
                plugin.Home.Database.HomeMax.prozessPlayer(player);
            }
        } catch (Exception ex) {
            System.out.println(plugin.PluginName + "INI ERR: At onPlayerConnectEvent it throw an exeption.");
            System.err.println(plugin.PluginName + "INI ERR: ----Error Report----");
            ex.printStackTrace();
            System.err.println(plugin.PluginName + "INI ERR: ---------------------");
            plugin.StopPlugin();
        }
    }

}
