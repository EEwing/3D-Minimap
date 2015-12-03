package com.quentillionaire.dddminimap.Render.Renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

import static org.lwjgl.opengl.GL11.*;

public abstract class MiniMapRenderer extends Gui {

    protected int scale = 75;
    protected float zoom = 1f;

    private boolean hasBorder = true;
    protected float borderSize = 1.5f;

    protected static float transparency = .2f;

    protected int safeZone = 5;

    private MapLocation location = MapLocation.LOWER_LEFT;

    protected abstract void renderFrame();

    protected abstract void renderMap();

    public void render(ScaledResolution res, int mouseX, int mouseY) {
        Minecraft mc = Minecraft.getMinecraft();
        glPushMatrix();
            location = MapLocation.UPPER_RIGHT;
            transparency = 0.2f;

            switch(location) {
                case UPPER_RIGHT:
                    glTranslated(res.getScaledWidth()-scale-safeZone, safeZone, 0);
                    break;
                case UPPER_LEFT:
                    glTranslated(safeZone, safeZone, 0);
                    break;
                case LOWER_LEFT:
                    glTranslated(safeZone, res.getScaledHeight()-scale-safeZone, 0);
                    break;
                case LOWER_RIGHT:
                    glTranslated(res.getScaledWidth()-scale-safeZone, res.getScaledHeight()-scale-safeZone, 0);
                    break;
                case CENTER:
                    glTranslated((res.getScaledWidth()-scale)/2, (res.getScaledHeight()-scale)/2, 0);
                    break;
            }

            // Render items
            //if(hasBorder) renderFrame();

            glTranslated(borderSize, borderSize, 0);
            glScaled(scale - 2*borderSize, scale - 2*borderSize, 0.0);
            renderMap();

        glPopMatrix();
    }

    public MapLocation getLocation() {
        return location;
    }

    public enum MapLocation {
        UPPER_LEFT,
        UPPER_RIGHT,
        LOWER_LEFT,
        LOWER_RIGHT,
        CENTER;
    }

    public void enableBorder() {
        hasBorder = true;
    }

    public void disableBorder() {
        hasBorder = false;
    }
}
