package util;

import java.awt.Color;

/**
 * Central palettes for pixel sprites. Index 0 is transparent; palette arrays map 1-based indices.
 */
public class SpritePalettes {
    public static final Color[] BIRD = new Color[] {
        new Color(0, 0, 0),            // 1 outline/eye
        Constants.BIRD_COLOR,           // 2 main body
        new Color(255, 140, 0),         // 3 shading
        new Color(0, 0, 0, 0)           // 4 transparent
    };

    public static final Color[] PIPE = new Color[] {
        Constants.PIPE_BORDER,          // 1 border
        Constants.PIPE_COLOR,           // 2 body
        Constants.PIPE_BORDER,          // 3 border repeat
        new Color(0, 0, 0, 0)           // 4 transparent
    };
}
