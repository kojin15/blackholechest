package kojin15.Block;

import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;

public class SlotSample extends Slot {

    public SlotSample(IInventory par1IInventory, int par2, int par3, int par4, boolean get, boolean put)
    {
        super(par1IInventory, par2, par3, par4);
        getSlot = get;
        putSlot = put;
    }

    public boolean isItemValid(ItemStack par1ItemStack)
    {
        return putSlot;
    }

    private boolean getSlot;
    private boolean putSlot;

}

