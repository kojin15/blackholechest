package kojin15.src;


import kojin15.src.Block.Blockblackholechest;
import kojin15.src.Block.Tileblackholechest;
import kojin15.src.Item.Itemblackholechest;
import net.minecraft.src.*;
import net.minecraft.src.forge.*;
import net.minecraft.src.ItemStack;

import static net.minecraft.src.mod_CompactEngine.blockID_infinityChest;
import static net.minecraft.src.mod_CompactEngine.itemID_energyChecker;


public class mod_blackholechest extends BaseMod {

    @MLProp(info="BHCblockID", min=125, max=4095)
    public static int BHCblockID = 2400;

    public static Block BlackHoleChest;
    public static String texture = "/kojin15/resourse/texture.png";

    private boolean isCE = false;
    private int button = 0;

    private Tileblackholechest tile;

    private static final char[] TEXTS = new char[]{'万', '億', '兆', '京'};

    public String getVersion()
    {
        return "1_2_5_01";
    }

    public String getPriorities() {
        return "after:net.minecraft.src.mod_CompactEngine;";
    }

    public void load() {

        isCE = ModLoader.isModLoaded("mod_CompactEngine");

        MinecraftForgeClient.preloadTexture(texture);

        BlackHoleChest = new Blockblackholechest(BHCblockID, 0, false);
        BlackHoleChest.setTextureFile(texture);
        BlackHoleChest.setBlockName("Blackholechest");

        ModLoader.registerBlock(BlackHoleChest, Itemblackholechest.class);

        ModLoader.registerTileEntity(Tileblackholechest.class, "BlackHoleChest");

        ModLoader.addName(BlackHoleChest, "BlackHoleChest");
        ModLoader.addName(BlackHoleChest, "ja_JP", "ブラックホールチェスト");


        if (isCE){
            ModLoader.addRecipe(new ItemStack(BlackHoleChest), "ABA", "BCB", "ABA", 'A', new ItemStack(blockID_infinityChest,1,0), 'B', Block.glass, 'C', new ItemStack(itemID_energyChecker,1,27));

        }
        else {
            ModLoader.addRecipe(new ItemStack(BlackHoleChest), "ABA", "BCB", "ABA", 'A', Block.chest, 'B', Block.glass, 'C', Block.blockDiamond);

        }
    }

    public static String getJPnumber(long size){

        String valueStr = Long.toString(size);

        int strLength = valueStr.length();
        int digits = strLength >> 2;
        int rest = strLength - (digits << 2);

        int[] ints = new int[digits + 1];
        for (int i = 0; i < digits; i++) {
            String s = valueStr.substring(strLength - (4 + (i << 2)), strLength - (i << 2));
            ints[i] = Integer.valueOf(s);
        }
        if (rest > 0)
            ints[ints.length - 1] = Integer.valueOf(valueStr.substring(0, rest));

        StringBuffer buffer = new StringBuffer();
        for (int i = ints.length - 1; i > 0; i--) {
            if (ints[i] != 0) buffer.append(ints[i]).append(TEXTS[i - 1]);
        }
        if (ints[0] != 0) buffer.append(ints[0]);

        String JPnumber = buffer + "個";
        return JPnumber;
    }

    public static String getButtonName(int par1){
        String ItemButtonText = null;
        switch (par1){
            case 0:ItemButtonText = "個数";
                break;
            case 1:ItemButtonText = "Items";
                break;
            case 2:ItemButtonText = "Stack";
                break;
            case 3:ItemButtonText = "LC";
                break;
            case 4:ItemButtonText = "MC";
                break;
            case 5:ItemButtonText = "20BiC";
                break;
        }
        return ItemButtonText;
    }
}