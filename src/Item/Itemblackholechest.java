package kojin15.src.Item;


import net.minecraft.src.*;
import java.util.List;


public class Itemblackholechest extends ItemBlock{

    public Itemblackholechest(int par1) {
        super(par1);
        this.setIconIndex(Block.blocksList[par1 + 256].getBlockTextureFromSide(2));
    }


    public void addInformation(ItemStack par1ItemStack, List par2List) {

        NBTTagCompound tags = par1ItemStack.getTagCompound();
        NBTTagCompound compound = tags != null ? tags.getCompoundTag("ChestItem") : null;

        if (compound != null) {
            ItemStack stack = ItemStack.loadItemStackFromNBT(compound.getCompoundTag("Item"));
            if (stack != null) par2List.add(StatCollector.translateToLocal("itemStack.BlackHallChestItem.item.name") + stack.getItem().getItemDisplayName(stack));
            if (stack != null)par2List.add(StatCollector.translateToLocal("itemStack.BlackHallChestItem.size.name") + compound.getLong("Size"));
            if (stack == null)par2List.add(StatCollector.translateToLocal("itemStack.BlackHallChestItem.null.name"));
        }
        else par2List.add(StatCollector.translateToLocal("itemStack.BlackHallChestItem.null.name"));

    }

}
