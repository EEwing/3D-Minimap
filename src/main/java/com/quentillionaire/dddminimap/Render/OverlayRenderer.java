package com.quentillionaire.dddminimap.Render;

import com.quentillionaire.dddminimap.Utillity.Logger;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

public class OverlayRenderer {

    public double mapSize = 0.25;

    public Size size = Size.MEDIUM;

    public enum Size {
        SMALL(100),
        MEDIUM(200),
        LARGE(500);

        private int size = 0;

        Size(int s) {
            this.size = s;
        }

        public int getSize() {
            return size;
        }
    }

    @SubscribeEvent
    public void render(RenderGameOverlayEvent event) {
        GL11.glPushMatrix();
          GL11.glColor3d(0, 0, 0);
          GL11.glScaled(mapSize * size.getSize(), mapSize * size.getSize(), 0.0);
          GL11.glBegin(GL11.GL_QUADS);
            GL11.glVertex2d(0, 0);
            GL11.glVertex2d(0, 1);
            GL11.glVertex2d(1, 1);
            GL11.glVertex2d(1, 0);
          GL11.glEnd();
        GL11.glPopMatrix();
        Logger.info("Rendering");
    }
}
