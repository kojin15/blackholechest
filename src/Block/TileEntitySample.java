package kojin15.src.Block;

import net.minecraft.src.*;

public class TileEntitySample extends TileEntity
        implements IInventory
{
    private ContainerSample container;

    private long MAXSIZE =Long.MAX_VALUE;

    private long size = 0L;

    private ItemStack stack = null;


    public void setContainer(ContainerSample container) {
        this.container = container;
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        this.size = par1NBTTagCompound.getLong("Size");
        NBTTagCompound compound = par1NBTTagCompound.getCompoundTag("Item");
        this.stack = ItemStack.loadItemStackFromNBT(compound);
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setLong("Size", this.size);
        NBTTagCompound compound = new NBTTagCompound();
        if(this.stack != null) this.stack.writeToNBT(compound);
        par1NBTTagCompound.setTag("Item", compound);
    }

    public long getSize()
    {
        return  this.size;
    }

    public  void  setSize(long par1)
    {
        this.size = par1;
    }

    public int getMetadata()
    {
        return this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
    }

    @Override
    public int getSizeInventory()
    {
        return 2;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        if(stack != null) {
            if(index == 0) {
                ItemStack ret = this.stack.copy();
                ret.stackSize = 1;
                return  ret;
            }
            if(index == 2) {
                return this.stack;
            }
            if(index ==1 && this.MAXSIZE == this.size) {
                return this.stack;
            }
        }

        return null;

    }

    @Override
    public ItemStack decrStackSize(int index, int dec) {
        System.out.println("TileEntitytSample.decrStackSize(int " +index+",int "+dec+")");
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

    @Override
    public ItemStack getStackInSlotOnClosing(int par4) {
        //System.out.println("TileEntitySample.getStackInSlotOnClosing(int "+par4+")");
        return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        System.out.println("TileEntitySample.setInventorySlotContents(int "+index+",item "+ stack+")");
        if(index == 1 && stack != null) {
            if(this.stack == null) {
                this.stack = stack;
                this.size = stack.stackSize;

            }else if(this.stack.isItemEqual(stack) && this.MAXSIZE - this.size > 0) {
                this.size += stack.stackSize;
                if(this.size < 0) this.size = this.MAXSIZE;
                if(this.size <=64) {
                    this.stack.stackSize = (int)this.size;
                }

            }

        }

    }

    public long getMaxSize()
    {
        return MAXSIZE;
    }

    @Override
    public String getInvName() {
        return "container.samplechest";
    }

    @Override
    public int getInventoryStackLimit()
    {
        return  64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_)
    {
        return true;
    }

    @Override
    public void openChest() {

    }

    @Override
    public void closeChest() {

    }

    public boolean isUseableByPlayer(int p_94041_1_, ItemStack p_94041_2_) {
        return true;
    }

}
