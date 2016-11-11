package kojin15.src;

import kojin15.src.Block.Blockblackholechest;
import kojin15.src.Block.Tileblackholechest;
import net.minecraft.src.*;
import net.minecraft.src.forge.*;
import net.minecraft.src.ItemStack;

public class mod_blackholechest extends BaseMod {

    public static Block BlackHoleChest;
    public int SampleblockID = 2000;
    public static String texture = "/kojin15/resourse/texture.png";
    private Tileblackholechest chest;

    public mod_blackholechest() {

    }
    public void load() {
        MinecraftForgeClient.preloadTexture(texture);
        BlackHoleChest = new Blockblackholechest(SampleblockID,0,false);
        BlackHoleChest.setTextureFile(texture);
        BlackHoleChest.setBlockName("SampleChest");
        ModLoader.registerBlock(BlackHoleChest);
        ModLoader.registerTileEntity(Tileblackholechest.class, "BlackHoleChest");
        ModLoader.addName(BlackHoleChest, "BlackHoleChest");
        ModLoader.addName(BlackHoleChest, "ja_JP" ,"ブラックホールチェスト");
        ModLoader.addRecipe(new ItemStack(BlackHoleChest),"ABA","BCB","ABA",'A',Block.chest,'B',Block.glass,'C',Block.blockDiamond);
    }

    public String getVersion()
    {
        return "1_2_5_01";
    }

}