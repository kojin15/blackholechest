package kojin15.Block;

import net.minecraft.src.*;
import net.minecraft.src.BlockContainer;

import java.util.ArrayList;
import java.util.Random;

public class BlockSample extends BlockContainer
{
    private boolean blockType;

    public int getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
        if (par1 == 1)
        {
            return this.blockIndexInTexture;
        }
        else if (par1 == 0)
        {
            return this.blockIndexInTexture;
        }
        else
        {
            int var3 = this.blockIndexInTexture + 1 + 16;

            if (this.blockType)
            {
                ++var3;
            }

            return par2 == 2 && par1 == 2 ? var3 : (par2 == 3 && par1 == 5 ? var3 : (par2 == 0 && par1 == 3 ? var3 : (par2 == 1 && par1 == 4 ? var3 : this.blockIndexInTexture + 16)));
        }
    }

    public int getBlockTextureFromSide(int par1)
    {
        return par1 == 1 ? this.blockIndexInTexture : (par1 == 0 ? this.blockIndexInTexture : (par1 == 3 ? this.blockIndexInTexture + 1 + 16 : this.blockIndexInTexture + 16));
    }

    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving)
    {
        int var6 = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        par1World.setBlockMetadataWithNotify(par2, par3, par4, var6);
    }

    public BlockSample(int par1, int par2, boolean par3)
    {
        super(par1, 0, Material.glass);
        Block.useNeighborBrightness[par1] = true;
        this.blockIndexInTexture = par2;
        this.blockType = par3;
    }

    public TileEntity getBlockEntity()
    {
        return new TileEntitySample();
    }

    public int idDropped(int i, Random random, int j)
    {
        return this.blockID;
    }

    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
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

    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer)
    {
        TileEntitySample abc = (TileEntitySample) world.getBlockTileEntity(i, j, k);
        if(abc == null)
        {
            return true;
        }
        else {
            ModLoader.openGUI(entityplayer, new GuiSample(entityplayer.inventory, abc));
            return true;
        }
    }

    @Override
    public void addCreativeItems(ArrayList itemList)
    {
        itemList.add(new ItemStack(this));
    }

}