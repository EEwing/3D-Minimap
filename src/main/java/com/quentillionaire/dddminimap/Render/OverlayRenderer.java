package com.quentillionaire.dddminimap.Render;

import com.quentillionaire.dddminimap.Utillity.Logger;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

public class OverlayRenderer {

    public double mapSize = 0.25;

    public SizeWidth sizeWidth = SizeWidth.MEDIUM;
    public SizeHeight sizeHeight = SizeHeight.MEDIUM;

    public static int MCWindowHeight = Minecraft.getMinecraft().displayHeight, MCWindowWidth = Minecraft.getMinecraft().displayWidth;

    public enum SizeWidth {
        SMALL(350),
        MEDIUM(500),
        LARGE(650);

        private int sizeWidth = 0;

        SizeWidth(int s) {
            this.sizeWidth = s;
        }

        public int getSize() {
            return sizeWidth;
        }
    }

    public enum SizeHeight {
        SMALL(200),
        MEDIUM(350),
        LARGE(500);

        private int sizeHeight = 0;

        SizeHeight(int s) {
            this.sizeHeight = s;
        }

        public int getSize() {
            return sizeHeight;
        }
    }

    /** Getting the gui size from your Minecraft setting. 0 = small, 1 = medium, 2 = large, 3 = auto */
 /*   private Size setMapSize() {
        switch (Minecraft.getMinecraft().gameSettings.guiScale) {
            case 0: size = size.SMALL;
            case 1: size = size.MEDIUM;
            case 2: size = size.LARGE;

            default: size = size.MEDIUM;
        }
        return size;
    }*/

    private static float borderThickness = 1.5f;
    private static float transparency = 1.0f;

    /** Getting the surrounding blocks in a radius of 8 around the player and storing it in a array */
    private void getSurroundingBlocks(World world, EntityPlayer player) {
        int radius = 8;
        int renderAreaX, renderAreaY, renderAreaZ;
        int playerPosX = (int) player.posX, playerPosY = (int) player.posY, playerPosZ = (int) player.posZ;

        for (renderAreaX = playerPosX - radius; renderAreaX * (-1) < playerPosX + radius; renderAreaX++) {
            for (renderAreaY = playerPosY - radius; renderAreaY * (-1) <  playerPosY + radius; renderAreaY++) {
                for (renderAreaZ = playerPosZ - radius; renderAreaZ * (-1) < playerPosZ + radius; renderAreaZ++) {
                    if (!world.isAirBlock(renderAreaX, renderAreaY, renderAreaZ)) {
                        Block surroundingBlocks[] = {world.getBlock(renderAreaX, renderAreaY, renderAreaX)};
                        Logger.info(surroundingBlocks[renderAreaX] + " | " + surroundingBlocks [renderAreaY] + " | " + surroundingBlocks[renderAreaZ]);
                    }
                }
            }
        }
    }

    /** Draws a square */
    private void drawSquare() {
        GL11.glBegin(GL11.GL_QUADS);
            GL11.glVertex2d(0, 0);
            GL11.glVertex2d(0, 1);
            GL11.glVertex2d(1, 1);
            GL11.glVertex2d(1, 0);
        GL11.glEnd();
    }

    /** Renders another square in the background of the map to give the illusion of a border around it*/
    private void renderBoarderFrame() {
        GL11.glPushMatrix();
            GL11.glTranslated(-10 / 2, -10 / 2, 0);
            GL11.glTranslated(10, 10, 0);
            GL11.glColor4f(0.2f,0.2f,0.2f,transparency);
            GL11.glScaled(mapSize * sizeWidth.getSize(), mapSize * sizeHeight.getSize(), 0.0);
            drawSquare();
        GL11.glPopMatrix();
    }

    /** Renders the main frame canvas */
    private void renderMainFrame() {
        int maxWidth = Minecraft.getMinecraft().displayWidth, maxHeight = Minecraft.getMinecraft().displayHeight;
        GL11.glPushMatrix();
            GL11.glTranslated(-10 / 2, -10 / 2, 0);
            GL11.glTranslated(10, 10, 0);
            GL11.glColor4f(0.8f,0.8f,0.8f,transparency);
            GL11.glScaled((mapSize * sizeWidth.getSize()) - borderThickness, (mapSize * sizeHeight.getSize()) - borderThickness, 0.0);
            drawSquare();
        GL11.glPopMatrix();
    }

    /** Tells the game to render everything on the screen as a overlay */
    @SubscribeEvent
    public void render(RenderGameOverlayEvent event) {
        renderBoarderFrame();
        renderMainFrame();
    }
}
