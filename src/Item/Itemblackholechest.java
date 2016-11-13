package kojin15.src.Item;


import kojin15.src.Block.Tileblackholechest;
import kojin15.src.mod_blackholechest;
import net.minecraft.src.*;
import java.util.List;


public class Itemblackholechest extends ItemBlock{

    private mod_blackholechest core;

    public Itemblackholechest(int par1) {
        super(par1);
        this.setIconIndex(Block.blocksList[par1 + 256].getBlockTextureFromSide(2));
    }

    public void addInformation(ItemStack par1ItemStack, List par2List) {

        NBTTagCompound tags = par1ItemStack.getTagCompound();
        NBTTagCompound compound = tags != null ? tags.getCompoundTag("ChestItem") : null;

        if (compound != null) {
            ItemStack stack = ItemStack.loadItemStackFromNBT(compound.getCompoundTag("Item"));
            long chestsize = compound.getLong("Size");
            int button = compound.getInteger("Button");
            if (stack != null) par2List.add(StatCollector.translateToLocal(stack.getItem().getItemDisplayName(stack)));
            else par2List.add(StatCollector.translateToLocal("ãÛ"));
            if (stack != null)par2List.add(StatCollector.translateToLocal(core.getJPnumber(chestsize)));
            par2List.add(StatCollector.translateToLocal("ï\é¶ê›íË" + ":" + core.getButtonName(button)));
        }

    }

}
