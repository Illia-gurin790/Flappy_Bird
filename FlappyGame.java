import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import entity.Bird;
import entity.PipeManager;
import sprite.LogoSprite;
import util.Constants;

public class FlappyGame extends JPanel implements ActionListener, KeyListener, MouseListener {
    private static final long serialVersionUID = 1L;

    private final Bird bird;
    private final PipeManager pipeManager;
    private GameState state;
    private int score;
    private int bestScore;
    private final Timer timer;

    public FlappyGame() {
        setPreferredSize(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        setBackground(Constants.SKY_COLOR);
        setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);

        bird = new Bird(Constants.BIRD_START_X, Constants.BIRD_START_Y);
        pipeManager = new PipeManager();
        state = GameState.MENU;
        score = 0;
        bestScore = 0;
        timer = new Timer(Constants.TIMER_DELAY, this);
    }

    public void start() {
        timer.start();
        requestFocusInWindow();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (state == GameState.RUNNING) {
            bird.update();
            pipeManager.update();

            if (bird.isTouchingGround() || bird.isAboveTop()) {
                state = GameState.GAME_OVER;
            }

            if (pipeManager.checkCollision(bird)) {
                state = GameState.GAME_OVER;
            }

            score += pipeManager.collectPassedPipes(bird.getX());
            if (score > bestScore) {
                bestScore = score;
            }
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Background sky
        g.setColor(Constants.SKY_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Pipes and bird
        pipeManager.draw(g);
        bird.draw(g);

        // Ground
        g.setColor(Constants.GROUND_COLOR);
        g.fillRect(0, Constants.WINDOW_HEIGHT - Constants.FLOOR_HEIGHT, Constants.WINDOW_WIDTH, Constants.FLOOR_HEIGHT);

        // Score display
        g.setColor(Color.WHITE);
        g.setFont(Constants.SCORE_FONT);
        String scoreText = String.valueOf(score);
        FontMetrics fm = g.getFontMetrics();
        int scoreX = (Constants.WINDOW_WIDTH - fm.stringWidth(scoreText)) / 2;
        g.drawString(scoreText, scoreX, 60);

        if (state == GameState.MENU) {
            drawMenu(g);
        } else if (state == GameState.GAME_OVER) {
            drawGameOver(g);
        }
    }

    private void drawMenu(Graphics2D g) {
        LogoSprite.draw(g, Constants.WINDOW_WIDTH / 2, 120);
        g.setFont(Constants.TITLE_FONT);
        g.setColor(Color.WHITE);
        String title = "FLAPPY BIRD";
        FontMetrics fm = g.getFontMetrics();
        g.drawString(title, (Constants.WINDOW_WIDTH - fm.stringWidth(title)) / 2, 220);

        g.setFont(Constants.INFO_FONT);
        String hint = "Press SPACE or CLICK to start";
        g.drawString(hint, (Constants.WINDOW_WIDTH - g.getFontMetrics().stringWidth(hint)) / 2, 300);

        String controls = "Use SPACE or MOUSE to flap";
        g.drawString(controls, (Constants.WINDOW_WIDTH - g.getFontMetrics().stringWidth(controls)) / 2, 340);
    }

    private void drawGameOver(Graphics2D g) {
        g.setFont(Constants.GAME_OVER_FONT);
        g.setColor(new Color(255, 255, 255, 240));
        String over = "GAME OVER";
        FontMetrics fm = g.getFontMetrics();
        g.drawString(over, (Constants.WINDOW_WIDTH - fm.stringWidth(over)) / 2, 220);

        g.setFont(Constants.INFO_FONT);
        String result = "Score: " + score + "   Best: " + bestScore;
        g.drawString(result, (Constants.WINDOW_WIDTH - g.getFontMetrics().stringWidth(result)) / 2, 280);

        String restart = "Press SPACE or CLICK to play again";
        g.drawString(restart, (Constants.WINDOW_WIDTH - g.getFontMetrics().stringWidth(restart)) / 2, 340);
    }

    private void resetGame() {
        bird.reset();
        pipeManager.reset();
        score = 0;
        state = GameState.RUNNING;
    }

    private void flap() {
        if (state == GameState.MENU) {
            resetGame();
            return;
        }

        if (state == GameState.GAME_OVER) {
            resetGame();
            return;
        }

        bird.flap();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            flap();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // no-op
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // no-op
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        flap();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // no-op
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // no-op
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // no-op
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // no-op
    }
}
