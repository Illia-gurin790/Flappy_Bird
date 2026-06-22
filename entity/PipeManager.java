package entity;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import util.Constants;
import util.CollisionUtil;

public class PipeManager {
    private final List<Pipe> pipes;
    private final Random random;

    public PipeManager() {
        pipes = new ArrayList<>();
        random = new Random();
        reset();
    }

    public void update() {
        Iterator<Pipe> iterator = pipes.iterator();
        while (iterator.hasNext()) {
            Pipe pipe = iterator.next();
            pipe.update();
            if (pipe.isOffScreen()) {
                iterator.remove();
            }
        }

        if (pipes.isEmpty() || pipes.get(pipes.size() - 1).getX() < Constants.WINDOW_WIDTH - Constants.PIPE_SPACING) {
            spawnPipe();
        }
    }

    public boolean checkCollision(Bird bird) {
        for (Pipe pipe : pipes) {
            if (CollisionUtil.circleIntersectsRect(bird.getX(), bird.getY(), bird.getRadius(), pipe.getTopRect())
                    || CollisionUtil.circleIntersectsRect(bird.getX(), bird.getY(), bird.getRadius(), pipe.getBottomRect())) {
                return true;
            }
        }
        return false;
    }

    public int collectPassedPipes(int birdX) {
        int gained = 0;
        for (Pipe pipe : pipes) {
            if (!pipe.hasPassed() && birdX > pipe.getX() + Constants.PIPE_WIDTH) {
                pipe.setPassed(true);
                gained++;
            }
        }
        return gained;
    }

    public void draw(Graphics2D g) {
        for (Pipe pipe : pipes) {
            pipe.draw(g);
        }
    }

    public void reset() {
        pipes.clear();
        int start = Constants.WINDOW_WIDTH + 100;
        for (int i = 0; i < 3; i++) {
            pipes.add(new Pipe(start + i * Constants.PIPE_SPACING, getRandomGapY()));
        }
    }

    private void spawnPipe() {
        int x = Constants.WINDOW_WIDTH;
        int gapY = getRandomGapY();
        pipes.add(new Pipe(x, gapY));
    }

    private int getRandomGapY() {
        return Constants.PIPE_MIN_HEIGHT + random.nextInt(Constants.WINDOW_HEIGHT - Constants.FLOOR_HEIGHT - Constants.PIPE_GAP - 2 * Constants.PIPE_MIN_HEIGHT);
    }
}
