package kojin15.src.Block;

import kojin15.src.Item.Itemblackholechest;
import net.minecraft.src.*;
import net.minecraft.src.BlockContainer;

import java.util.ArrayList;
import java.util.Random;

public class Blockblackholechest extends BlockContainer {

    private boolean blockType;

    private Tileblackholechest BHCTileEntity;

    public int getBlockTextureFromSideAndMetadata(int par1, int par2) {
        if (par1 == 1) {
            return this.blockIndexInTexture;
        }
        else if (par1 == 0) {
            return this.blockIndexInTexture;
        }
        else {
            int var3 = this.blockIndexInTexture + 1 + 16;
            if (this.blockType) {
                ++var3;
            }
            return par2 == 2 && par1 == 2 ? var3 : (par2 == 3 && par1 == 5 ? var3 : (par2 == 0 && par1 == 3 ? var3 : (par2 == 1 && par1 == 4 ? var3 : this.blockIndexInTexture + 16)));
        }
    }

    public int getBlockTextureFromSide(int par1) {
        return par1 == 1 ? this.blockIndexInTexture : (par1 == 0 ? this.blockIndexInTexture : (par1 == 3 ? this.blockIndexInTexture + 1 + 16 : this.blockIndexInTexture + 16));
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving player) {

        ItemStack stack = ((EntityPlayer)player).getCurrentEquippedItem();

        if (stack.hasTagCompound()) {

            Tileblackholechest tile = (Tileblackholechest) createNewTileEntity(world, 0);
            int var6 = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
            world.setBlockMetadataWithNotify(x, y, z, var6);
            world.setBlockTileEntity(x, y, z, tile);
            NBTTagCompound compound = stack.getTagCompound().getCompoundTag("ChestItem");

            if (compound != null) {

                ItemStack cheststack = ItemStack.loadItemStackFromNBT(compound.getCompoundTag("Item"));
                long size = compound.getLong("Size");
                tile.setStack(cheststack);
                tile.setSize(size);

            }

        }

    }


    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new Tileblackholechest();
    }


    public void onBlockRemoval(World world, int x, int y, int z)
    {
        BHCTileEntity = (Tileblackholechest) world.getBlockTileEntity(x, y, z);
        super.onBlockRemoval(world, x, y, z);
    }

    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta)
    {
        if(BHCTileEntity != null)
        {
            ItemStack stack = new ItemStack(this, 1, BHCTileEntity.getMetadata());
            if (BHCTileEntity.getItemType() != null) {
                NBTTagCompound compound = new NBTTagCompound();
                stack.setTagCompound(BHCTileEntity.writeToNBTOfItem(compound));
            }
            EntityItem drop = new EntityItem(world, (double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, stack);
            drop.motionX = 0.0D;
            drop.motionY = 0.20000000298023224D;
            drop.motionZ = 0.0D;
            world.spawnEntityInWorld(drop);
        }
        BHCTileEntity = null;
    }

    public Blockblackholechest(int par1, int par2, boolean par3) {
        super(par1, 0, Material.glass);
        Block.useNeighborBrightness[par1] = true;
        this.blockIndexInTexture = par2;
        this.blockType = par3;
    }

    public TileEntity getBlockEntity()
    {
        return new Tileblackholechest();
    }


    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        float p0 = 0.01F;
        float ps = 0.0625F;
        float pe = 0.9375F;
        float pt = 0.875F;
        setBlockBounds(ps, p0, ps, pe, pt, pe);
    }

    public int quantityDropped(Random random)
    {
        return 1;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public float getHardness()
    {
        return 3.0F;
    }

    public float getExplosionResistance(Entity par1Entity) { return 5.0F; }

    public float getBlockBrightness(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {return 10.0F; }

    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer) {
        Tileblackholechest abc = (Tileblackholechest) world.getBlockTileEntity(i, j, k);
        if(abc == null)
        {
            return true;
        }
        else {
            ModLoader.openGUI(entityplayer, new Guiblackholechest(entityplayer.inventory, abc));
            return true;
        }
    }

    public void addCreativeItems(ArrayList itemList)
    {
        itemList.add(new ItemStack(this));
    }

}