package kojin15.src.Block;


import kojin15.src.mod_blackholechest;
import org.lwjgl.opengl.GL11;
import net.minecraft.src.*;
import net.minecraft.src.ItemStack;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.IInventory;


public class Guiblackholechest extends GuiContainer {

    private IInventory player;
    private Tileblackholechest tile;
    private mod_blackholechest core;

    public static int button = 0;

    String ItemButtonText = null;


    public Guiblackholechest(InventoryPlayer inventoryplayer, Tileblackholechest tileblackholechest) {
        super(new Containerblackholechest(inventoryplayer, tileblackholechest));
        player = inventoryplayer;
        tile = (Tileblackholechest) tileblackholechest;
        super.allowUserInput = false;
        super.xSize = 176;
        super.ySize = 166;
    }

    public static int getButtonNum(){ return button; }

    public static int setButtonNum(int par1){ return button = par1; }

    public void actionPerformed(GuiButton guibutton) {
        if (guibutton.id == 0) {
            if (getButtonNum() != 5){
                setButtonNum(getButtonNum()+1);
            }
            else {
                setButtonNum(0);
            }
        }
    }

    protected void drawGuiContainerForegroundLayer() {
        controlList.clear();
        GuiButton ItemButton = new GuiButton(0, (width - xSize) / 2 + 139, (height - ySize) / 2 + 70, 30, 12, core.getButtonName(button));
        controlList.add(ItemButton);
        StringTranslate st = StringTranslate.getInstance();
        super.fontRenderer.drawString("IN", 11, 28, 0x000000);
        super.fontRenderer.drawString("OUT", 44, 28, 0x000000);
        super.fontRenderer.drawString(st.translateKey(tile.getInvName()), 8, 5, 0x404040);
        super.fontRenderer.drawString(st.translateKey(player.getInvName()), 8, 72, 0x404040);
        if (tile.getSize() == tile.getMaxSize()) {
            super.fontRenderer.drawString("MAX", 3, 60, 0xff0000);
        }
        ItemStack item = this.tile.getStackInSlot(2);
        if (item != null) {
            String name = item.getItem().getItemDisplayName(item);
            super.fontRenderer.drawString(name, 70, 18, 0x404040);
        }
        long ItemSize = this.tile.getSize();

        switch (button) {
            case 0:
                String number = core.getJPnumber(ItemSize);
                super.fontRenderer.drawString(number, this.xSize - (35 + ((number.length() - 3) * 6)), 60, 4210752);
                break;
            case 1:
                String Items = ItemSize + ": Items";
                super.fontRenderer.drawString(Items, this.xSize - (35 + ((Items.length() - 6) * 6)), 60, 4210752);
                break;
            case 2:
                String Stack1 = (ItemSize / 64) + ": Stack";
                super.fontRenderer.drawString(Stack1, this.xSize - (35 + ((Stack1.length() - 6) * 6)), 60, 4210752);
                break;
            case 3:
                String Stack2 = (ItemSize / (6 * 9 * 64)) + ": LC";
                super.fontRenderer.drawString(Stack2, this.xSize - (35 + ((Stack2.length() - 4) * 6)), 60, 4210752);
                break;
            case 4:
                String Stack3 = (ItemSize / (9 * 13 * 5 * 64)) + ": MC";
                super.fontRenderer.drawString(Stack3, this.xSize - (35 + ((Stack3.length() - 4) * 6)), 60, 4210752);
                break;
            case 5:
                String Stack4 = (ItemSize / (2000000000)) + ": 20BiC";
                super.fontRenderer.drawString(Stack4, this.xSize - (35 + ((Stack4.length() - 6) * 6)), 60, 4210752);
        }
    }

    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3){
        int tex = mc.renderEngine.getTexture("/kojin15/resourse/gui/BHCcontainer.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(tex);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }

}
