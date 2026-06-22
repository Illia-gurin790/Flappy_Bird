package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import PixelSprites.Bird_down;
import PixelSprites.Bird_up;
import util.Constants;
import util.SpritePalettes;
import util.SpriteRenderer;

public class Bird {
    private int x;
    private int y;
    private double velocity;

    public Bird(int startX, int startY) {
        x = startX;
        y = startY;
        velocity = 0;
    }

    public void update() {
        velocity += Constants.GRAVITY;
        if (velocity > Constants.MAX_FALL_SPEED) {
            velocity = Constants.MAX_FALL_SPEED;
        }
        y += (int) Math.round(velocity);
    }

    public void flap() {
        velocity = Constants.JUMP_SPEED;
    }

    public void draw(Graphics2D g) {
        // Use pixel sprite (20x20) and scale to bird diameter
        int[][] pixels = velocity < 0 ? Bird_up.PIXELS : Bird_down.PIXELS;
        int spriteW = pixels[0].length;
        int spriteH = pixels.length;
        int targetDiameter = Constants.BIRD_RADIUS * 2;
        int scale = Math.max(1, Math.round((float) targetDiameter / spriteW));
        int drawX = x - (spriteW * scale) / 2;
        int drawY = y - (spriteH * scale) / 2;

        SpriteRenderer.draw(g, pixels, drawX, drawY, scale, SpritePalettes.BIRD);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        // Compute a tighter collision radius based on the drawn sprite size
        int[][] pixels = Bird_down.PIXELS; // both up/down share size
        int spriteW = pixels[0].length;
        int targetDiameter = Constants.BIRD_RADIUS * 2;
        int scale = Math.max(1, Math.round((float) targetDiameter / spriteW));
        int spritePixelWidth = spriteW * scale;
        int radius = spritePixelWidth / 2 - 2; // small padding for forgiveness
        if (radius < 6) radius = 6;
        return radius;
    }

    public boolean isTouchingGround() {
        return y + Constants.BIRD_RADIUS >= Constants.WINDOW_HEIGHT - Constants.FLOOR_HEIGHT;
    }

    public boolean isAboveTop() {
        return y - Constants.BIRD_RADIUS <= 0;
    }

    public void reset() {
        x = Constants.BIRD_START_X;
        y = Constants.BIRD_START_Y;
        velocity = 0;
    }
}
