package util;

import java.awt.Rectangle;

public class CollisionUtil {
    public static boolean circleIntersectsRect(int cx, int cy, int radius, Rectangle rect) {
        int nearestX = clamp(cx, rect.x, rect.x + rect.width);
        int nearestY = clamp(cy, rect.y, rect.y + rect.height);

        int dx = cx - nearestX;
        int dy = cy - nearestY;
        return dx * dx + dy * dy <= radius * radius;
    }

    private static int clamp(int value, int min, int max) {
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }
}
