package com.quentillionaire.dddminimap.Render.texture;


import static org.lwjgl.opengl.GL11.*;

public class MapTexture extends Texture {

    public int textureSize;

    public MapTexture(int textureSize, boolean linearScaling) {
        super(textureSize, textureSize, 0x00000000, GL_LINEAR, GL_LINEAR, GL_REPEAT);

        this.setLinearScaling(linearScaling);

        //this.textureRegions = textureSize >> Region.SHIFT;
        this.textureSize = textureSize;
        //this.regionArray = new Region[this.textureRegions * this.textureRegions];
    }
}
