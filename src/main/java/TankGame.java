import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;

public class TankGame {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        GameClient gameClient = new GameClient(1024,768);
        jFrame.add(gameClient);
        jFrame.setTitle("TankWar!!");
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        gameClient.repaint();


    }
}
