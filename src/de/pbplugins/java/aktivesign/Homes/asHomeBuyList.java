package de.pbplugins.java.aktivesign.Homes;

import de.pbplugins.java.aktivesign.AktiveSign;
import de.pbplugins.java.aktivesign.asConfig;
import java.util.ArrayList;
import java.util.List;

public class asHomeBuyList {

    private final asConfig BuyListData;
    public final List<Integer> HomeBuyList;

    public asHomeBuyList(AktiveSign plugin) {
        HomeBuyList = new ArrayList<>();
        String[][] sysConfigArray = {
            //Name         Wert
            {"01", "0"},
            {"02", "0"},
            {"03", "0"},
            {"04", "0"},
            {"05", "0"},
            {"06", "0"},
            {"07", "0"},
            {"08", "0"},
            {"09", "0"},
            {"10", "0"},
            {"11", "0"},
            {"12", "0"},
            {"13", "0"},
            {"14", "0"},
            {"15", "0"},
            {"16", "0"},
            {"17", "0"},
            {"18", "0"},
            {"19", "0"},
            {"20", "0"}

        };

        BuyListData = new asConfig("HomeBuy", sysConfigArray, plugin, 0);
    }

    public void iniHomeBuyList() throws NumberFormatException{
        HomeBuyList.add(0);
        HomeBuyList.add(Integer.parseInt(BuyListData.getValue("01")));
        HomeBuyList.add(Integer.parseInt(BuyListData.getValue("02")));
        HomeBuyList.add(Integer.parseInt(BuyListData.getValue("03")));
        HomeBuyList.add(Integer.parseInt(BuyListData.getValue("04")));
        HomeBuyList.add(Integer.parseInt(BuyListData.getValue("05")));
        HomeBuyList.add(Integer.parseInt(BuyListData.getValue("06")));
        HomeBuyList.add(Integer.parseInt(BuyListData.getValue("07")));
        HomeBuyList.add(Integer.parseInt(BuyListData.getValue("08")));
        HomeBuyList.add(Integer.parseInt(BuyListData.getValue("09")));
        HomeBuyList.add(Integer.parseInt(BuyListData.getValue("10")));
        HomeBuyList.add(Integer.parseInt(BuyListData.getValue("11")));
        HomeBuyList.add(Integer.parseInt(BuyListData.getValue("12")));
        HomeBuyList.add(Integer.parseInt(BuyListData.getValue("13")));
        HomeBuyList.add(Integer.parseInt(BuyListData.getValue("14")));
        HomeBuyList.add(Integer.parseInt(BuyListData.getValue("15")));
        HomeBuyList.add(Integer.parseInt(BuyListData.getValue("16")));
        HomeBuyList.add(Integer.parseInt(BuyListData.getValue("17")));
        HomeBuyList.add(Integer.parseInt(BuyListData.getValue("18")));
        HomeBuyList.add(Integer.parseInt(BuyListData.getValue("19")));
        HomeBuyList.add(Integer.parseInt(BuyListData.getValue("20")));
    }

}
