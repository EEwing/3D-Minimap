package com.quentillionaire.dddminimap.Render.Renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.DynamicTexture;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;

public class BasicMiniMapRenderer extends MiniMapRenderer {

    private int[] colormap;
    //private DynamicTexture dynamicTexture;

    public BasicMiniMapRenderer() {
        colormap = new int[scale*scale]; // Necessary?
        //dynamicTexture = new DynamicTexture(scale, scale);
        //colormap = dynamicTexture.getTextureData();
        //texture = ByteBuffer.allocate(scale*scale*3);
    }

    @Override
    protected void renderFrame() {
        glPushMatrix();
        glScaled(scale, scale, 0.0);
        glColor4f(0.2f, 0.2f, 0.2f, transparency);
        drawSquareNoTexture();
        glPopMatrix();
    }

    @Override
    protected void renderMap() {
        Minecraft mc = Minecraft.getMinecraft();

        glPushMatrix();
        glColor4f(0.8f, 0.8f, 0.8f, transparency);

        for(int x=0; x<scale; ++x) {
            for(int y=0; y<scale; ++y) {
                int worldX = (int) mc.thePlayer.posX-(scale/2)+x;
                int worldZ = (int) mc.thePlayer.posZ-(scale/2)+y;
                int colorValue = mc.theWorld.getTopBlock(worldX, worldZ).getMaterial().getMaterialMapColor().colorValue;
                //texture.put(3*(y*scale+x) +0, (byte) (colorValue << 24));
                //texture.put(3*(y*scale+x) +1, (byte) (colorValue << 16));
                //texture.put(3*(y*scale+x) +2, (byte) (colorValue << 8));
                //colormap[y*scale+x] = mc.theWorld.getTopBlock(worldX, worldZ).getMapColor(0).colorValue;
            }
        }
        //dynamicTexture.updateDynamicTexture();
        //glTexImage2D();
        drawSquareNoTexture();
        glPopMatrix();
    }

    /** Draws a square */
    private void drawSquare() {
        glBegin(GL_QUADS);
        glVertex2d(0, 0); glTexCoord2d(0, 0);
        glVertex2d(0, 1); glTexCoord2d(0, 1);
        glVertex2d(1, 1); glTexCoord2d(1, 1);
        glVertex2d(1, 0); glTexCoord2d(1, 0);
        glEnd();
    }

    private void drawSquareNoTexture() {
        glBegin(GL_QUADS);
        glVertex2d(0, 0);
        glVertex2d(0, 1);
        glVertex2d(1, 1);
        glVertex2d(1, 0);
        glEnd();
    }
}
