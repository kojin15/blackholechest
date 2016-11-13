package kojin15.src.Block;


import org.lwjgl.opengl.GL11;
import net.minecraft.src.*;
import net.minecraft.src.ItemStack;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.IInventory;


public class Guiblackholechest extends GuiContainer {

    private IInventory player;
    private Tileblackholechest tile;

    private static final char[] TEXTS = new char[]{'–œ', '‰­', '’›', '‹ž'};

    int button = 0;

    String ItemButtonText = null;


    public Guiblackholechest(InventoryPlayer inventoryplayer, Tileblackholechest tileblackholechest) {
        super(new Containerblackholechest(inventoryplayer, tileblackholechest));
        player = inventoryplayer;
        tile = (Tileblackholechest) tileblackholechest;
        super.allowUserInput = false;
        super.xSize = 176;
        super.ySize = 166;
    }

    public void actionPerformed(GuiButton guibutton) {
        if (guibutton.id == 0) {
            if (button != 5){
                button++;
            }
            else {
                button = 0;
            }
        }
    }

    protected void drawGuiContainerForegroundLayer() {
        if (button == 0){ItemButtonText = "ŒÂ”";}
        if (button == 1){ItemButtonText = "Items";}
        if (button == 2){ItemButtonText = "Stack";}
        if (button == 3){ItemButtonText = "LC";}
        if (button == 4){ItemButtonText = "MC";}
        if (button == 5){ItemButtonText = "20BiC";}
        controlList.clear();
        GuiButton ItemButton = new GuiButton(0, (width - xSize) / 2 + 139, (height - ySize) / 2 + 70, 30, 12, ItemButtonText);
        controlList.add(ItemButton);
        StringTranslate st = StringTranslate.getInstance();
        super.fontRenderer.drawString("IN", 11, 28, 0x000000);
        super.fontRenderer.drawString("OUT", 44, 28, 0x000000);
        super.fontRenderer.drawString(st.translateKey(tile.getInvName()), 8, 5, 0x404040);
        super.fontRenderer.drawString(st.translateKey(player.getInvName()), 8, 72, 0x404040);
        if (tile.getSize() == tile.getMaxSize()){
            super.fontRenderer.drawString("MAX", 3, 60, 0xff0000);
        }
        ItemStack item = this.tile.getStackInSlot(2);
        if (item != null) {
            String name = item.getItem().getItemDisplayName(item);
            super.fontRenderer.drawString(name, 70, 18, 0x404040);
        }
        long ItemSize = this.tile.getSize();

        if (button == 0){
            String valueStr = Long.toString(tile.getSize());

            int strLength = valueStr.length();
            int digits = strLength >> 2;
            int rest = strLength - (digits << 2);

            int[] ints = new int[digits + 1];
            for (int i = 0; i < digits; i++) {
                String s = valueStr.substring(strLength - (4 + (i << 2)), strLength - (i << 2));
                ints[i] = Integer.valueOf(s);
            }
            if (rest > 0)
                ints[ints.length - 1] = Integer.valueOf(valueStr.substring(0, rest));

            StringBuffer buffer = new StringBuffer();
            for (int i = ints.length - 1; i > 0; i--) {
                if (ints[i] != 0) buffer.append(ints[i]).append(TEXTS[i - 1]);
            }
            if (ints[0] != 0) buffer.append(ints[0]);
            String number = buffer+"ŒÂ";
            super.fontRenderer.drawString(number, this.xSize-(35+((number.length()-3)*6)), 60, 4210752);
        }
        if (button == 1){
            String Items = ItemSize+": Items";
            super.fontRenderer.drawString(Items, this.xSize-(35+((Items.length()-6)*6)), 60, 4210752);
        }
        if (button == 2){
            String Stack = (ItemSize/64)+": Stack";
            super.fontRenderer.drawString(Stack, this.xSize-(35+((Stack.length()-6)*6)), 60, 4210752);
        }
        if (button == 3){
            String Stack = (ItemSize/(6*9*64))+": LC";
            super.fontRenderer.drawString(Stack, this.xSize-(35+((Stack.length()-4)*6)), 60, 4210752);
        }
        if (button == 4){
            String Stack = (ItemSize/(9*13*5*64))+": MC";
            super.fontRenderer.drawString(Stack, this.xSize-(35+((Stack.length()-4)*6)), 60, 4210752);
        }
        if (button == 5){
            String Stack = (ItemSize/(2000000000))+": 20BiC";
            super.fontRenderer.drawString(Stack, this.xSize-(35+((Stack.length()-6)*6)), 60, 4210752);
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
