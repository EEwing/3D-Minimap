package com.quentillionaire.dddminimap.Render.texture;

import org.lwjgl.opengl.GL12;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;

public class Texture {

    private int id;
    public int width;
    public int height;

    private IntBuffer pixelBuf;

    public Texture(int w, int h, int fillColor, int minFilter, int maxFilter, int textureWrap) {
        this.id = glGenTextures();
        this.width = w;
        this.height = h;
        this.pixelBuf = ByteBuffer.allocateDirect(w*h*4).order(ByteOrder.nativeOrder()).asIntBuffer();
        this.fillRect(0, 0, w, h, fillColor);
        this.pixelBuf.position(0);
        this.bind();
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, w, h, 0, GL12.GL_BGRA, GL_UNSIGNED_BYTE, this.pixelBuf);
        this.setTexParameters(minFilter, maxFilter, textureWrap);
    }

    public void fillRect(int x, int y, int w, int h, int color) {
        for(int i=x; i<x+w; ++i) {
            for(int j=y; j<y+h; ++j) {
                pixelBuf.put(j*width+i, color);
            }
        }
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, this.id);
    }

    public void setTexParameters(int minFilter, int maxFilter, int textureWrap) {
        this.bind();
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, textureWrap);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, textureWrap);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, minFilter);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, maxFilter);
    }

    public void setLinearScaling(boolean linearScaling) {

    }

}
