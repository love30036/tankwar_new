package object;

import javax.swing.*;
import java.awt.*;

public class Tank {


    private int x;
    private int y;
    private Direction direction;
    private int speed;
    private boolean[] dirs = new boolean[4];

    public Tank(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        speed = 5;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Image getImage() {
        if (direction == Direction.UP) {
            return new ImageIcon("assets/images/itankU.png").getImage();
        }
        if (direction == Direction.DOWN) {
            return new ImageIcon("assets/images/itankD.png").getImage();
        }
        if (direction == Direction.LIFT) {
            return new ImageIcon("assets/images/itankL.png").getImage();
        }
        if (direction == Direction.RIGHT) {
            return new ImageIcon("assets/images/itankR.png").getImage();
        }
        if (direction == Direction.UP_LIFT) {
            return new ImageIcon("assets/images/itankLU.png").getImage();
        }
        if (direction == Direction.UP_RIGHT) {
            return new ImageIcon("assets/images/itankRU.png").getImage();
        }
        if (direction == Direction.DOWN_LIFT) {
            return new ImageIcon("assets/images/itankLD.png").getImage();
        }
        if (direction == Direction.DOWN_RIGHT) {
            return new ImageIcon("assets/images/itankRD.png").getImage();
        }
        return null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move() {
        switch (direction) {
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LIFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
        }
    }

    public boolean[] getDirs() {
        return dirs;
    }

    private void determineDirection() {
        if (dirs[0] && dirs[2] && !dirs[1] && !dirs[3]) direction = Direction.UP_LIFT;
        else if (dirs[0] && dirs[3] && !dirs[2] && !dirs[1]) direction = Direction.UP_RIGHT;
        else if (dirs[1] && dirs[2] && !dirs[0] && !dirs[3]) direction = Direction.DOWN_LIFT;
        else if (dirs[1] && dirs[3] && !dirs[0] && !dirs[2]) direction = Direction.DOWN_RIGHT;
        else if (dirs[0] && !dirs[3] && !dirs[2] && !dirs[1]) direction = Direction.UP;
        else if (dirs[1] && !dirs[3] && !dirs[0] && !dirs[2]) direction = Direction.DOWN;
        else if (dirs[2] && !dirs[3] && !dirs[0] && !dirs[1]) direction = Direction.LIFT;
        else if (dirs[3] && !dirs[1] && !dirs[0] && !dirs[2]) direction = Direction.RIGHT;

    }
}
