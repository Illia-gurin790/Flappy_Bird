package util;

import java.awt.Color;
import java.awt.Graphics2D;

public class SpriteRenderer {
    /**
     * Draws a pixel sprite given an integer pixel map and a palette.
     * pixelMap[y][x] = palette index (0 = transparent)
     */
    public static void draw(Graphics2D g, int[][] pixelMap, int x, int y, int scale, Color[] palette) {
        for (int py = 0; py < pixelMap.length; py++) {
            int[] row = pixelMap[py];
            for (int px = 0; px < row.length; px++) {
                int idx = row[px];
                if (idx <= 0) continue;
                Color c = null;
                if (idx - 1 < palette.length) {
                    c = palette[idx - 1];
                }
                if (c == null) continue;
                g.setColor(c);
                g.fillRect(x + px * scale, y + py * scale, scale, scale);
            }
        }
    }
}
