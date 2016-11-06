package kojin15.Block;

import org.lwjgl.opengl.GL11;
import net.minecraft.src.*;

public class GuiSample extends GuiContainer
{
    private IInventory player;
    private TileEntitySample tile;

    public GuiSample(InventoryPlayer inventoryplayer, TileEntitySample tileEntitySample)
    {
        super(new ContainerSample(inventoryplayer, tileEntitySample));
        player = inventoryplayer;
        tile = tileEntitySample;
        super.allowUserInput = false;
        super.xSize = 176;
        super.ySize = 166;
    }

    protected void drawGuiContainerForegroundLayer() {
        StringTranslate st = StringTranslate.getInstance();
        super.fontRenderer.drawString(st.translateKey(tile.getInvName()), 8, 5, 0x404040);
        super.fontRenderer.drawString(st.translateKey(player.getInvName()), 8, 72, 0x404040);
    }

    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3){
        int tex = mc.renderEngine.getTexture("/gui/samplecontainer.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(tex);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }

}
