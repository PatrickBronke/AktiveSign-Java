package de.pbplugins.java.aktivesign.ChatSystem;

import de.pbplugins.java.aktivesign.AktiveSign;
import java.util.ArrayList;
import java.util.List;
import net.risingworld.api.objects.Player;


public class asChat {
    
    private final AktiveSign plugin;
    public asMailSystem MailSystem;
    public Attribute Attribute;
    private List<String> ChatResaverList;
    private final String Rot = "[#ff0000]", Grün = "[#00ff00]", Orange = "[#ffa500]";
    
    public asChat(AktiveSign plugin){
        this.plugin = plugin;
        this.MailSystem = new asMailSystem(plugin);
        this.Attribute = new Attribute();
        this.ChatResaverList = new ArrayList<>();
    }
    
    public void stopChat(Player player){
        Attribute.set.ChatStop(player, true);
        player.sendTextMessage(Rot + "Chat stopped!");
    }
    
    public void startChat(Player player){
        Attribute.set.ChatStop(player, false);
        player.sendTextMessage(Grün + "Chat started!");
        if (!Attribute.get.ChatResaver(player).isEmpty()){
            player.sendTextMessage(Orange + "------Missed messages------");
            Attribute.get.ChatResaver(player).forEach((msg) -> {
                player.sendTextMessage(msg);
            });
            player.sendTextMessage(Orange + "---------------------------");
            Attribute.get.ChatResaver(player).clear();
        }
    }
    
    public class Attribute{
        
        private final String ChatStop, ChatResaver;
        public get get;
        public set set;
        
        public Attribute(){
            this.get = new get();
            this.set = new set();
            ChatStop = plugin.PluginName + "-Chat" + "ChatStop";
            ChatResaver = plugin.PluginName + "-Chat" + "ChatResaver";
        }
        
        public class get{
            
            public List<String> ChatResaver(Player player){
                return (List<String>)player.getAttribute(ChatResaver);
            }
            
            public boolean ChatStop(Player player){
                return (boolean)player.getAttribute(ChatStop);
            }
        }
        
        public class set{
            
            public void NewChatResaver(Player player){
                player.setAttribute(ChatResaver, ChatResaverList);
            }
            
            public void ChatStop(Player player, boolean value){
                player.setAttribute(ChatStop, value);
            }
        }
    }
}
