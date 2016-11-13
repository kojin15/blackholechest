package kojin15.src.Block;


import net.minecraft.src.*;
import net.minecraft.src.forge.ISidedInventory;


public class Tileblackholechest extends TileEntity implements IInventory, ISidedInventory {

    private Containerblackholechest container;
    private Guiblackholechest GuiButton;

    private long MAXSIZE = Long.MAX_VALUE;
    private long size = 0L;

    private ItemStack stack = null;


    public void setContainer(Containerblackholechest container) {
        this.container = container;
    }

    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        this.size = par1NBTTagCompound.getLong("Size");
        int buttonnum = par1NBTTagCompound.getInteger("Buttton");
        GuiButton.setButtonNum(buttonnum);
        NBTTagCompound compound = par1NBTTagCompound.getCompoundTag("Item");
        this.stack = ItemStack.loadItemStackFromNBT(compound);
    }

    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setLong("Size", this.size);
        par1NBTTagCompound.setInteger("Button", GuiButton.getButtonNum());
        NBTTagCompound compound = new NBTTagCompound();
        if(this.stack != null) this.stack.writeToNBT(compound);
        par1NBTTagCompound.setTag("Item", compound);
    }

    public NBTTagCompound writeToNBTOfItem(NBTTagCompound compound){
        NBTTagCompound chestItemCompound = new NBTTagCompound();
        chestItemCompound.setLong("Size", this.size);
        chestItemCompound.setInteger("Button", GuiButton.getButtonNum());
        NBTTagCompound compound1 = new NBTTagCompound();
        if (this.stack != null) this.stack.writeToNBT(compound1);
        chestItemCompound.setTag("Item", compound1);
        compound.setTag("ChestItem", chestItemCompound);
        return compound;
    }

    public long getSize()
    {
        return  this.size;
    }

    public  void  setSize(long par1)
    {
        this.size = par1;
    }

    public void setStack(ItemStack stack) {
        this.stack = stack;
    }

    public int getMetadata()
    {
        return this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
    }

    public int getSizeInventory()
    {
        return 3;
    }

    public ItemStack getItemType() {
        if (this.stack == null) return null;
        ItemStack stack = this.stack.copy();
        stack.stackSize = 0;
        return stack;
    }

    public ItemStack getStackInSlot(int index) {
        if(stack != null) {
            if(index == 0) {
                ItemStack ret = this.stack.copy();
                ret.stackSize = 1;
                return ret;
            }
            if(index == 2) {
                return this.stack;
            }
            if(index == 1 && this.MAXSIZE == this.size) {
                return null;
            }
        }
        return null;
    }

    public ItemStack decrStackSize(int index, int dec) {
        if(index == 2) {
            if(this.stack.stackSize >= dec && this.size > 64) {
                ItemStack ret = this.stack.copy();
                ret.stackSize = dec;
                this.size -= dec;
                if(this.size <= 64) {
                    this.stack.stackSize = (int) this.size;
                }
                return ret;
            }else if(this.size <= 64 && this.size > dec) {
                ItemStack ret = this.stack.copy();
                ret.stackSize = dec;
                this.size -= dec;
                this.stack.stackSize = (int) this.size;
                return ret;
            }else if(this.size <= 64 && this.size <= dec) {
                ItemStack ret = this.stack.copy();
                this.size = 0;
                this.stack = null;
                return ret;
            }
        }
        return null;
    }

    public ItemStack getStackInSlotOnClosing(int par4) {
        return null;
    }

    public void setInventorySlotContents(int index, ItemStack stack) {
        if(index == 1 && stack != null) {
            if(this.stack == null) {
                this.stack = stack;
                this.size = stack.stackSize;
            }
            else if(this.stack.isItemEqual(stack) && this.MAXSIZE - this.size > 0) {
                this.size += stack.stackSize;
                if(this.size < 0) this.size = this.MAXSIZE;
                if(this.size <=64) {
                    this.stack.stackSize = (int)this.size;
                }
                else if(this.size > 64){
                    this.stack.stackSize = 64;
                }
            }
        }
    }

    public void updateEntity() {
        if (this.size >= 64){
            if(this.stack.stackSize == 0){
                this.stack.stackSize = 64;
            }
        }
        else if (this.size < 64 && this.size > 0){
            if(this.stack.stackSize == 0){
                this.stack.stackSize = (int)this.size;
            }
            if(this.size != this.stack.stackSize){
                this.size = this.stack.stackSize;
            }
        }
        else if (this.size == 0){
            this.stack = null;
        }
    }

    public long getMaxSize()
    {
        return MAXSIZE;
    }

    public String getInvName() {
        return "ブラックホールチェスト";
    }

    public int getInventoryStackLimit()
    {
        return  64;
    }

    public boolean isUseableByPlayer(EntityPlayer p_70300_1_)
    {
        return true;
    }

    public void openChest() {
    }

    public void closeChest() {
    }

    public int getStartInventorySide(int side)
    {
        return 1;
    }

    public int getSizeInventorySide(int side) {
        return side == 1 ? 1 : 2;
    }

}
