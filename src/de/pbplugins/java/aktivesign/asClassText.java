/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.pbplugins.java.aktivesign;

import de.chaoswg.SprachAPI;
import java.util.ArrayList;

/**
 *
 * @author Schmull
 */
public class asClassText extends SprachAPI {
    @Override
    protected void setDatenFunktion(){
        Sprache = new ArrayList();
        Sprache.add("en");
        Sprache.add("de");
        setSprache(Sprache);
        
        Daten = new String[][] { 
            //Name  , en   ,  de
            {"PreisZahl","The price must be a number + [Currency]! (1 [Currency])","Der Preis muss eine Zahl sein + [Währung]! (1 [Währung])"},
            {"NichtGenugGeld","You do not have enough money!","Du hast nicht genug Geld!"},
            {"NoAdmin","You are not an admin!","Du bist kein Admin!"},
            {"NoDebug","Debug not enabled!","Debug nicht aktiv!"},
            {"SignWErr","Sign is misspelled!","Schild ist falsch geschrieben!"},
            {"SignColor","You see the color in the first interaction!","Du siehst die Farbe in der ersten Interaktion!"},
            {"SignCorrect","The sign is correct!","Das Schild ist richtig!"},
            {"SignNotCorrect","The sign is not correct","Das Schild ist falsch!"},
            
            {"FlyNotAllowed","You can't fly here!","Fliegen ist hier nicht erlaubt!"},
            
            {"Chest_Interakt_Err","That is not your chest!","Das ist nicht deine Truhe!"},
            
            {"Sign_Heal_Hunger","Your hunger has been satisfied!","Dein Hunger wurde gestillt!"},
            {"Sign_Heal_Durst","Your thirst was satisfied!","Dein Durst wurde gestillt!"},
            {"Sign_Heal_Food","Thirst and hunger were satisfied!","Durst und Hunger wurden gestillt!"},
            {"Sign_Heal_Knochen","Your bones have been healed!","Deine Knochen wurden geheilt!"},
            {"Sign_Heal_Blut","The bleeding was stopped!","Die Blutung wurde gestoppt!"},
            {"Sign_Heal_LP_T1","You were healed for ","Du wurdest um "},
            {"Sign_Heal_LP_T2"," points!"," Punkte geheilt!"},
            {"Sign_Heal_All","You were completely healed!","Du wurdest komplett geheilt!"},
            
            {"Sign_Spawn","You were teleported to the spawn!","Du wurdest zum Spawn teleportiert!"},
            
            {"Sign_setGroup","Your group has been changed!","Deine group wurde geändert!"},
            
            {"Sign_Weather","The weather has changed!","Das Wetter wurde geändert!"},
            {"Sign_Time","The time has changed!","Die Zeit wurde geändert!"},
            
            {"Sign_Gamemode","Your 'Gamemode' has been changed!","Dein 'Gamemode' wurde geändert!"},
            
            {"Sign_Buy_OK","Thank you for the purchase!","Danke für den Einkauf!"},
            {"Sign_Buy_Zurück","You got your money back!","Du hast dein Geld zurück bekommen!"},
            {"Sign_Buy_Anzahl1","You received the item ","Du hast "},
            {"Sign_Buy_Anzahl2"," times","x das Item "},
            {"Sign_Buy_Anzahl3","!"," erhalten!"},
            
            {"Sign_Sell_MengeID","Quantity and ID must be a number!","Menge und ID müssen eine Zahl sein!"},
            {"Sign_Sell_NoItem","The item is not right!","Das Item passt nicht!"},
            {"Sign_Sell_NoHand","You have to hold an item in your hand!","Du musst ein Item in der Hand halten!"},
            {"Sign_Sell_OK","Thanks for the goods!","Danke für die Ware!"},
            
            {"Sign_Free","You could not receive the item!","Du konntest das Item nicht erhalten!"},
            
            {"Sign_AdminHelp","Player need help!","Player brauch Hilfe!"},
            
            {"Sign_Warp_NotExist","Warp with this name does not exist!","Warp mit diesem Namen gibt es nicht!"},
            {"Sign_Warp_OK_T1","You were teleported to ","Du wurdest nach "},
            {"Sign_Warp_OK_T2","!"," teleportiert!"},
            
            {"Warp_Create","Warp created!","Warp erstellt!"},
            {"Warp_Exist","Warp with this name already exists!","Warp mit diesem Namen existiert bereits!"},
            {"Warp_Delete_OK","Warp deleted!","Warp gelöscht!"},
            {"Warp_Delete_Err","Warp could not be deleted!","Warp konnte nicht gelöscht werden!"},
            {"Warp_Change_OK","Warp changed!","Warp geändert!"},
            {"Warp_Change_Err","Warp could not be changed!","Warp konnte nicht geändert werden!"},
            
            {"proveSign_Heal_ZahlMenge","The amount must be a number!","Die Menge muss eine Zahl sein!"},
            {"proveSign_Heal_ZahlGr","The amount must be between 1 and 100!","Die Menge muss zwischen 1 und 100 liegen!"},
            {"proveSign_Gamemode","Use in line 2: creative or survival!","Nutze in Zeile 2: creative oder survival"},
            {"proveSign_NoWarp","Warp with this name does not exist!","Warp mit diesem Namen gibt es nicht!"},
            {"proveSign_Balance","Use in line 2: 'cash', 'bank' or 'all'","Nutze in Zeile 2: 'cash', 'bank' oder 'all'"},
            {"proveSign_Bank","Use in line 2: 'in' or 'out'","Nutze in Zeile 2: 'in' oder 'out'"},
            {"proveSign_Weather_Sofort","Please write after the colon: 'true' or 'false'","Bitte schreibe nach den Doppelpunkt: 'true' oder 'false'"},
            {"proveSign_Weather_Länge","Line 2: The length is not 2! (<typ>:<true|false>)","Zeile 2: Die länge ist nicht 2! (<typ>:<true|false>)"},
            {"proveSign_Weather_DP","Line 2: No colon available!","Zeile 2: Kein Doppelpunkt vorhanden!"},
            
            {"command_protection_on","All the signs you place now will be protected!","Alle Schilder, die du jetzt platziert, werden geschützt!"},
            {"command_protection_off","All the signs you place now are no longer protected!","Alle Schilder, die du jetzt platziert, werden nicht mehr geschützt!"},
            {"command_destroy_on","You are now allowed to delete protected signs!!","Du darfst jetzt geschützte Schilder löschen!"},
            {"command_destroy_off","Line 2: No colon available!","You can no longer delete protected signs!"},
            {"command_edit_on","You can now edit the signs!","Du kannst nun die Schilder bearbeiten!"},
            {"command_edit_off","You can not edit the signs!","Du kannst die Schilder nicht mehr bearbeiten!"},
            
            {"destroy_err","You can not destroy this sign because it is protected!","Du kannst dieses Schild nicht zerstören, da es geschützt ist!"},
            {"create_err","You can not create this sign!","Du darfst dieses Schild nicht setzen!"},
            {"inter_cheast","Interact with a chest now!","Interagiere jetzt mit einer Truhe!"},
            {"remove_err","You can not remove this sign!","Du darfst dieses Schild nicht entfernen!"},
            {"edit_err","You can not edit this sign!","Du darfst dieses Schild nicht bearbeiten!"},
            {"protection_ok","The sign is now protected!","Das Schild ist nun geschützt!"},
            {"protection_err","The sign can not be protected!","Das Schild kann nicht geschützt werden!"},
            {"protection_dub","The sign is already protected!","Das Schild ist bereits geschützt!"},
            {"protection_remove","The sign are no longer protected!","Das Schild ist nicht mehr geschützt!"},
            
            {"GUI_BTC_GUIText","To withdraw money from the bank!","Geld von der Bank auszahlen!"},
            {"GUI_CTB_GUIText","Deposit money into the bank!","Geld in die Bank einzahlen!"},
            
            {"GUI_Q_1","Did you realy want to change the slots?","Willst du wirklich die Slots ändern?"},
            {"GUI_Q_2","Kosten gesammt: ","Total cost: "},
            
            {"GUI_QH_1","Did you realy want to buy a home?","Willst du dir wirklich ein Home kaufen?"},
            {"GUI_QH_2","Kosten gesammt: ","Total cost: "},
            
            {"GUI_QH_ERR_1","Did you realy want to buy a home?","Du hast bereits die maximale Anzahl an Homes!"},
            
            {"GUI_ATT_A1","Edit signs:","Schilder bearbeiten:"},
            {"GUI_ATT_A2","Protect signs:","Schilder schützen:"},
            {"GUI_ATT_A3","Destroy or delete signs:","Schilder zerstören oder löschen:"},
            
            

            {"Variable 1","en 1","de 1"},
            {"Variable 2","en 2","de 2"}
                
            //Erklähr mir bitte nochmal die Zeichen: %s, %d 
            // Variablenwerte die Später eingesetzt werden
            // %s = String
            // %d = Integer/Double
            // %f.2 = Float (x.yy)
            // das kann aber nur von String.Format() verstanden werden
            // da geht noch mehr
            // https://dzone.com/articles/java-string-format-examples
            // https://docs.oracle.com/javase/7/docs/api/java/util/Formatter.html
            //
        };
        setDaten(Daten);
    }
}
