package kojin15;

import kojin15.Block.BlockSample;
import kojin15.Block.TileEntitySample;
import net.minecraft.src.*;
import net.minecraft.src.forge.*;
import net.minecraft.src.ItemStack;

public class mod_sample extends BaseMod {

    public static Block SampleBlockChest;
    public int SampleblockID = 2000;
    public static String texture = "/texture.png";

    public mod_sample()
    {

    }
    public void load()
    {
        MinecraftForgeClient.preloadTexture(texture);
        SampleBlockChest = new BlockSample(SampleblockID,0,false);
        SampleBlockChest.setTextureFile(texture);
        SampleBlockChest.setBlockName("SampleChest");
        ModLoader.registerBlock(SampleBlockChest);
        ModLoader.registerTileEntity(TileEntitySample.class, "SampleChest");
        ModLoader.addName(SampleBlockChest, "SampleChest");
        ModLoader.addRecipe(new ItemStack(SampleBlockChest),"ABA","BCB","ABA",'A',Block.chest,'B',Block.glass,'C',Block.blockDiamond);
    }

    public String getVersion()
    {
        return "1_2_5_01";
    }

}