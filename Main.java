import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Flappy Bird Clone");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            FlappyGame game = new FlappyGame();
            frame.add(game);
            frame.pack();
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            game.start();
        });
    }
}
