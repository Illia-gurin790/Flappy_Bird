package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import PixelSprites.Pipe_down;
import PixelSprites.Pipe_up;
import util.Constants;
import util.SpriteRenderer;

public class Pipe {
    private int x;
    private final int gapY;
    private boolean passed;

    public Pipe(int startX, int gapY) {
        this.x = startX;
        this.gapY = gapY;
        this.passed = false;
    }

    public void update() {
        x -= Constants.PIPE_SPEED;
    }

    public boolean isOffScreen() {
        return x + Constants.PIPE_WIDTH < 0;
    }

    public boolean hasPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public int getX() {
        return x;
    }

    public Rectangle getTopRect() {
        int pad = 6; // inset collision box horizontally for forgiving gameplay
        return new Rectangle(x + pad, 0, Math.max(1, Constants.PIPE_WIDTH - pad * 2), gapY);
    }

    public Rectangle getBottomRect() {
        int pad = 6;
        return new Rectangle(x + pad, gapY + Constants.PIPE_GAP, Math.max(1, Constants.PIPE_WIDTH - pad * 2),
                Constants.WINDOW_HEIGHT - Constants.FLOOR_HEIGHT - (gapY + Constants.PIPE_GAP));
    }

    public void draw(Graphics2D g) {
        // Draw tiled pixel-sprite pipes
        int[][] up = Pipe_up.PIXELS;
        int[][] down = Pipe_down.PIXELS;
        int spriteW = up[0].length;
        int spriteH = up.length;
        int scale = Math.max(1, Constants.PIPE_WIDTH / spriteW);

        // Use central palette for pipes
        java.awt.Color[] palette = SpritePalettes.PIPE;

        // Top pipe: tile from y=0 down to gapY
        int tileH = spriteH * scale;
        int tilesTop = (int) Math.ceil((double) gapY / tileH);
        for (int i = 0; i < tilesTop; i++) {
            int drawY = 0 + i * tileH;
            SpriteRenderer.draw(g, up, x, drawY, scale, palette);
        }

        // Bottom pipe: tile from gapY+GAP down to floor
        int bottomStart = gapY + Constants.PIPE_GAP;
        int bottomHeight = Constants.WINDOW_HEIGHT - Constants.FLOOR_HEIGHT - bottomStart;
        int tilesBottom = (int) Math.ceil((double) bottomHeight / tileH);
        for (int i = 0; i < tilesBottom; i++) {
            int drawY = bottomStart + i * tileH;
            SpriteRenderer.draw(g, down, x, drawY, scale, palette);
        }
    }
}
