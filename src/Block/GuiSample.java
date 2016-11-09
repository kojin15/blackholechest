package kojin15.src.Block;

import org.lwjgl.opengl.GL11;
import net.minecraft.src.*;
import net.minecraft.src.ItemStack;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.IInventory;


public class GuiSample extends GuiContainer
{
    private IInventory player;
    private TileEntitySample tile;

    public GuiSample(InventoryPlayer inventoryplayer, TileEntitySample tileEntitySample)
    {
        super(new ContainerSample(inventoryplayer, tileEntitySample));
        player = inventoryplayer;
        tile = (TileEntitySample)tileEntitySample;
        super.allowUserInput = false;
        super.xSize = 176;
        super.ySize = 166;
    }

    protected void drawGuiContainerForegroundLayer() {
        StringTranslate st = StringTranslate.getInstance();
        super.fontRenderer.drawString(st.translateKey(tile.getInvName()), 8, 5, 0x404040);
        super.fontRenderer.drawString(st.translateKey(player.getInvName()), 8, 72, 0x404040);
        String text = (this.tile).getSize()+": Items";
        this.fontRenderer.drawString(text, this.xSize-(35+((text.length()-7)*6)), 60, 4210752);
        ItemStack item = this.tile.getStackInSlot(1);
        if(item != null) {
            String name = item.getItem().getItemDisplayName(item);
            super.fontRenderer.drawString(name, 50, 20, 4210752);
        }
    }

    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3){
        int tex = mc.renderEngine.getTexture("/kojin15/resourse/gui/samplecontainer.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(tex);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }

}
