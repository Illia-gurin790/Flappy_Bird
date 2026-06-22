package sprite;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class LogoSprite {
    public static void draw(Graphics2D g, int centerX, int centerY) {
        Font original = g.getFont();
        g.setFont(new Font("Arial", Font.BOLD, 28));
        g.setColor(new Color(255, 255, 255, 220));
        g.drawString("FLAPPY", centerX - 58, centerY);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("BIRD", centerX - 30, centerY + 32);
        g.setFont(original);
        g.setColor(Color.WHITE);
    }
}
