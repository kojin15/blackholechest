package kojin15.src;

import kojin15.src.Block.Blockblackholechest;
import kojin15.src.Block.Tileblackholechest;
import kojin15.src.Item.Itemblackholechest;
import net.minecraft.src.*;
import net.minecraft.src.mod_CompactEngine;
import net.minecraft.src.forge.*;
import net.minecraft.src.ItemStack;

import static net.minecraft.src.mod_CompactEngine.blockID_infinityChest;
import static net.minecraft.src.mod_CompactEngine.itemID_energyChecker;

public class mod_blackholechest extends BaseMod {

    public static Block BlackHoleChest;
    public static Item BlackholechestItem;

    public static String texture = "/kojin15/resourse/texture.png";

    @MLProp(info="BHCblockID", min=125, max=4095)
    public static int BHCblockID = 2000;




    private boolean isCE = false;

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
            ModLoader.addRecipe(new ItemStack(BlackHoleChest), "ABA", "BCB", "ABA", 'A', Block.chest, 'B', Block.glass, 'C', Block.blockDiamond);
            ModLoader.addRecipe(new ItemStack(BlackHoleChest), "AAA", "AAA", "AAA", 'A', Block.sand);
        }
        /*if (isCE){
            ModLoader.addRecipe(new ItemStack(BlackHoleChest), "ABA", "BCB", "ABA", 'A', new ItemStack(blockID_infinityChest,1,0), 'B', Block.glass, 'C', new ItemStack(itemID_energyChecker,1,27));

        }*/
    }


}