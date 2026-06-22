package util;

import java.awt.Color;
import java.awt.Font;

public class Constants {
    public static final int WINDOW_WIDTH = 400;
    public static final int WINDOW_HEIGHT = 600;
    public static final int FLOOR_HEIGHT = 100;

    public static final int BIRD_START_X = 100;
    public static final int BIRD_START_Y = 250;
    public static final int BIRD_RADIUS = 20;
    public static final double GRAVITY = 0.55;
    public static final double JUMP_SPEED = -10.0;
    public static final double MAX_FALL_SPEED = 12.0;

    public static final int PIPE_WIDTH = 80;
    public static final int PIPE_GAP = 170;
    public static final int PIPE_MIN_HEIGHT = 80;
    public static final int PIPE_SPEED = 4;
    public static final int PIPE_SPACING = 240;

    public static final int TIMER_DELAY = 16;

    public static final Color SKY_COLOR = new Color(88, 170, 255);
    public static final Color GROUND_COLOR = new Color(217, 196, 130);
    public static final Color BIRD_COLOR = new Color(255, 200, 0);
    public static final Color PIPE_COLOR = new Color(50, 175, 50);
    public static final Color PIPE_BORDER = new Color(45, 145, 45);

    public static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 34);
    public static final Font SCORE_FONT = new Font("Arial", Font.BOLD, 40);
    public static final Font GAME_OVER_FONT = new Font("Arial", Font.BOLD, 32);
    public static final Font INFO_FONT = new Font("Arial", Font.PLAIN, 18);
}
