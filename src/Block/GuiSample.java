package kojin15.src.Block;

import org.lwjgl.opengl.GL11;
import net.minecraft.src.*;
import net.minecraft.src.ItemStack;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.IInventory;


public class GuiSample extends GuiContainer {

    private IInventory player;
    private TileEntitySample tile;

    int button = 0;

    String ItemButtonText = null;


    public GuiSample(InventoryPlayer inventoryplayer, TileEntitySample tileEntitySample) {
        super(new ContainerSample(inventoryplayer, tileEntitySample));
        player = inventoryplayer;
        tile = (TileEntitySample) tileEntitySample;
        super.allowUserInput = false;
        super.xSize = 176;
        super.ySize = 166;
    }

    public void actionPerformed(GuiButton guibutton) {
        if (guibutton.id == 0) {
            if (button != 4){
                button++;
            }
            else {
                button = 0;
            }
        }
    }

    protected void drawGuiContainerForegroundLayer() {
        if (button == 0){ItemButtonText = "å¬êî";}
        if (button == 1){ItemButtonText = "Items";}
        if (button == 2){ItemButtonText = "Stack";}
        if (button == 3){ItemButtonText = "LC";}
        if (button == 4){ItemButtonText = "MC";}
        controlList.clear();
        GuiButton ItemButton = new GuiButton(0, (width - xSize) / 2 + 139, (height - ySize) / 2 + 70, 30, 12, ItemButtonText);
        controlList.add(ItemButton);
        StringTranslate st = StringTranslate.getInstance();
        super.fontRenderer.drawString("IN", 11, 28, 0x000000);
        super.fontRenderer.drawString("OUT", 44, 28, 0x000000);
        super.fontRenderer.drawString(st.translateKey(tile.getInvName()), 8, 5, 0x404040);
        super.fontRenderer.drawString(st.translateKey(player.getInvName()), 8, 72, 0x404040);
        ItemStack item = this.tile.getStackInSlot(2);
        if (item != null) {
            String name = item.getItem().getItemDisplayName(item);
            super.fontRenderer.drawString(name, 70, 18, 0x404040);
        }
        long ItemSize = this.tile.getSize();
        if (button == 0){}
        if (button == 1){
            String Items = ItemSize+": Items";
            super.fontRenderer.drawString(Items, this.xSize-(35+((Items.length()-7)*6)), 60, 4210752);
        }
        if (button == 2){
            String Stack = (ItemSize/64)+": Stack";
            super.fontRenderer.drawString(Stack, this.xSize-(35+((Stack.length()-7)*6)), 60, 4210752);
        }
        if (button == 3){
            String Stack = (ItemSize/(6*9*64))+": LC";
            super.fontRenderer.drawString(Stack, this.xSize-(35+((Stack.length()-4)*6)), 60, 4210752);
        }
        if (button == 4){
            String Stack = (ItemSize/(9*13*5*64))+": MC";
            super.fontRenderer.drawString(Stack, this.xSize-(35+((Stack.length()-4)*6)), 60, 4210752);
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
