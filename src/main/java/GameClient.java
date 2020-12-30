import object.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameClient extends JComponent {
    private int screenWidth;
    private int screenHeight;
    private PlayerTank playerTank;
    private boolean stop;
    private CopyOnWriteArrayList<GameObject> objects = new CopyOnWriteArrayList<>();
    public static Image[] BulletImage = new Image[8];
    public static Image[] eTankImage=new Image[8];


    public GameClient() {
        this(800, 600);
    }

    public GameClient(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));

        init();

        new Thread(() -> {
            while (!stop) {
                repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void init() {
        Image[] brickImage = {Tools.getImage("brick.png")};
        Image[] iTankImage = new Image[8];
//        Image[] eTankImage = new Image[8];

        String[] sub = {"U.png", "D.png", "L.png", "R.png", "LU.png", "RU.png", "LD.png", "RD.png"};

        for (int i = 0; i < iTankImage.length; i++) {
            iTankImage[i] = Tools.getImage("itank" + sub[i]);
            eTankImage[i] = Tools.getImage("etank" + sub[i]);
            BulletImage[i] = Tools.getImage("missile" + sub[i]);
        }
        playerTank = new PlayerTank(500, 100, Direction.DOWN, iTankImage);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                objects.add(new EnemyTank(350 + j * 100, 500 + 80 * i, Direction.UP,  eTankImage));
            }
        }
        Wall[] walls = {
                new Wall(250, 150, true, 15, brickImage),
                new Wall(150, 200, false, 15, brickImage),
                new Wall(800, 200, false, 15, brickImage),
        };


        objects.add(playerTank);
        objects.addAll(Arrays.asList(walls));


    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, screenWidth, screenHeight);
        g.setColor(Color.black);
        g.drawString("hp = "+playerTank.hp,0,10);
        for (GameObject object : objects) {
            object.draw(g);
        }

        for(GameObject object:objects){
            if(!object.isAlive()){
                objects.remove(object);
            }
        }


//        System.out.println(objects.size());

        checkGameStatus();


    }

    public void keyPressed(KeyEvent e) {
        boolean[] dirs = playerTank.getDirs();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                dirs[0] = true;
                break;
            case KeyEvent.VK_DOWN:
                dirs[1] = true;
                break;
            case KeyEvent.VK_LEFT:
                dirs[2] = true;
                break;
            case KeyEvent.VK_RIGHT:
                dirs[3] = true;
                break;
            case KeyEvent.VK_CONTROL:
                playerTank.fire();
                break;
            case KeyEvent.VK_A:
                playerTank.superFire();
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        boolean[] dirs = playerTank.getDirs();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                dirs[0] = false;
                break;
            case KeyEvent.VK_DOWN:
                dirs[1] = false;
                break;
            case KeyEvent.VK_LEFT:
                dirs[2] = false;
                break;
            case KeyEvent.VK_RIGHT:
                dirs[3] = false;
                break;
        }

    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public List<GameObject> getObjects() {
        return objects;
    }
    public void addGameObject(GameObject object){
        objects.add(object);
    }

    public void checkGameStatus(){
        boolean gameWin = true;

        for(GameObject object:objects){
            if(object instanceof EnemyTank){
                gameWin=false;
            }
        }
        if(gameWin){
            for(int i = 0;i<3;i++){
                for(int j= 0;j<4;j++){
                    addGameObject(new EnemyTank(350 + j * 100, 500 + 80 * i, Direction.UP,  eTankImage));
                }
            }
        }
    }
}
