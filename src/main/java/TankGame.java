import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TankGame {
    private static GameClient gameClient;
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        gameClient = new GameClient(1024,768);
        jFrame.add(gameClient);
        jFrame.setTitle("TankWar!!");
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        gameClient.repaint();

        jFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                gameClient.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                gameClient.keyReleased(e);
            }
        });



    }

    public static GameClient getGameClient() {
        return gameClient;
    }
}
