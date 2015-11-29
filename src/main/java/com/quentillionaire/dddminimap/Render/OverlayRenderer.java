package com.quentillionaire.dddminimap.Render;

import com.quentillionaire.dddminimap.Render.Renderers.BasicMiniMapRenderer;
import com.quentillionaire.dddminimap.Render.Renderers.MiniMapRenderer;
import com.quentillionaire.dddminimap.Utillity.Logger;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glPopMatrix;

public class OverlayRenderer {

    public double scale = 1f;

    private MiniMapRenderer renderer = new BasicMiniMapRenderer();

    /** Getting the gui size from your Minecraft setting. 0 = small, 1 = medium, 2 = large, 3 = auto */
 /*   private size setMapSize() {
        switch (Minecraft.getMinecraft().gameSettings.guiScale) {
            case 0: size = size.SMALL;
            case 1: size = size.MEDIUM;
            case 2: size = size.LARGE;

            default: size = size.MEDIUM;
        }
        return size;
    }*/

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


    /** Tells the game to render everything on the screen as a overlay */
    @SubscribeEvent
    public void render(RenderGameOverlayEvent event) {
        renderer.render(event.resolution, event.mouseX, event.mouseY);
        //renderBoarderFrame();
        //renderMainFrame();
    }
}
