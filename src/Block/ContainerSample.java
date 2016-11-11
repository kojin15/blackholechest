package kojin15.src.Block;


import net.minecraft.src.*;



public class ContainerSample extends Container {

    private TileEntitySample chest;
    private InventoryPlayer playerInventry;

    static final int inputslotSize = 1;
    static final int outputslotSize = 1;
    static final int displayslotSize = 1;
    static final int inventorySize = 27;
    static final int hotbarSize = 9;

    static final int inputslotIndex = 0;
    static final int outputslotIndex = 1;
    static final int displayslotIndex = 2;
    static final int inventoryIndex = 3;
    static final int hotbarIndex = 30;

    public ContainerSample(IInventory plaInv, IInventory tileInv) {
        this.chest = (TileEntitySample) tileInv;
        chest.setContainer(this);
        this.playerInventry = (InventoryPlayer) plaInv;

        this.addSlot(new SlotSample(chest , 1, 8, 38,true,false));
        this.addSlot(new SlotSample(chest , 2, 44, 38,false,true));
        this.addSlot(new SlotSample(chest , 0, 124,35,false,false));
        int var3;

        for (var3 = 0; var3 < 3; ++var3) {
            for (int var4 = 0; var4 < 9; ++var4) {
                this.addSlot(new Slot(plaInv, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3) {
            this.addSlot(new Slot(plaInv, var3, 8 + var3 * 18, 142));
        }
    }


    public boolean canInteractWith(EntityPlayer entityplayer) {
        return true;
    }


    public void onCraftGuiClosed(EntityPlayer entityplayer) {
        InventoryPlayer inventoryplayer = entityplayer.inventory;
        if (inventoryplayer.getItemStack() != null) {
            entityplayer.dropPlayerItem(inventoryplayer.getItemStack());
            inventoryplayer.setItemStack(null);
        }
    }


    public ItemStack transferStackInSlot(int par1) {
        ItemStack var2 = null;
        Slot var3 = (Slot)this.inventorySlots.get(par1);
        Slot dis = (Slot)this.inventorySlots.get(displayslotIndex);
        ItemStack disItem = dis.getStack();



        if (var3 != null && var3.getHasStack()) {
            ItemStack var4 = var3.getStack();
            var2 = var4.copy();

            if (par1 == outputslotIndex){

                if (chest.getSize() >= 64){

                    if (!this.mergeItemStack(var4, inventoryIndex, hotbarIndex + hotbarSize, false)) {
                        return null;
                    }
                    chest.setSize(chest.getSize() - 64);
                }
                else if (chest.getSize() < 63){

                    if (!this.mergeItemStack(var4, inventoryIndex, hotbarIndex + hotbarSize, false)) {
                        return null;
                    }
                    chest.setSize(0);
                }
            }

            else if (par1 != inputslotIndex && par1 != outputslotIndex && par1 != displayslotIndex) {
                if (var4 != null) {
                    if (chest.getSize() == 0){
                        if (!this.mergeItemStack(var4, inputslotIndex, inputslotIndex + inputslotSize, false)) {
                                return null;
                        }
                    }
                    else if (var4.isItemEqual(disItem)){
                        if (chest.getMaxSize() - chest.getSize() < (long)var4.stackSize) {
                            long var5 = chest.getMaxSize() - chest.getSize();
                            ItemStack var6 = var4.copy();
                            ItemStack var7 = var4.copy();
                            var6.stackSize = (int) var5;
                            var7.stackSize = var4.stackSize - (int) var5;

                            if (!this.mergeItemStack(var6, inputslotIndex, inputslotIndex + inputslotSize, false)) {
                                return null;
                            }
                            var3.putStack(var7);
                        }
                        else {
                            if (!this.mergeItemStack(var4, inputslotIndex, inputslotIndex + inputslotSize, false)) {
                                return null;
                            }
                        }
                    }
                }
            }
            else if (par1 >= inventoryIndex && par1 < hotbarIndex) {
                if (!this.mergeItemStack(var4, hotbarIndex, hotbarIndex + hotbarSize, false))
                {
                    return null;
                }
            }
            else if (par1 >= hotbarIndex && par1 < hotbarIndex +hotbarSize && !this.mergeItemStack(var4, inventoryIndex, hotbarIndex, false)) {
                return null;
            }
            else if (par1 == displayslotIndex) {
                return null;
            }

            if (var4.stackSize == 0) {
                var3.putStack((ItemStack)null);
            }
            else {
                var3.onSlotChanged();
            }

            if (var4.stackSize == var2.stackSize) {
                return null;
            }
            var3.onPickupFromSlot(var4);
        }
        return var2;
    }
}

class SlotSample extends Slot {

    private boolean in = false , out = false ;


    public SlotSample(IInventory inventry, int index, int x, int y,boolean in,boolean out) {
        super(inventry, index, x, y);
        this.in = in;
        this.out = out;
    }

    @Override
    public boolean isItemValid(ItemStack inStack) {
        if(this.in == true) {
            TileEntitySample tile = (TileEntitySample) this.inventory;
            if(tile.getStackInSlot(0) == null) {
                return true;
            }else if (tile.getStackInSlot(0).isItemEqual(inStack)){
                return true;
            }
        }

        return false;
    }

    public int getSlotStackLimit() {
        TileEntitySample tile = (TileEntitySample) this.inventory;
        long limit = tile.getMaxSize() - tile.getSize();
        return limit < 64 ? (int)limit : 64;
    }

}
