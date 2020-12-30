import object.Direction;
import object.GameObject;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Tank extends GameObject {

    protected Direction direction;
    private int speed;
    protected boolean[] dirs = new boolean[4];
    protected boolean enemy;
    protected int hp;

    public Tank(int x, int y, Direction direction, Image[] image) {
        this(x, y, direction, false, image);
    }

    public Tank(int x, int y, Direction direction, boolean enemy, Image[] image) {
        super(x, y, image);
        this.direction = direction;
        speed = 5;
        this.enemy = enemy;
        hp = 1;
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move() {
        oldX = x;
        oldY = y;
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
            case UP_LIFT:
                y -= speed;
                x -= speed;
                break;
            case UP_RIGHT:
                y -= speed;
                x += speed;
                break;
            case DOWN_LIFT:
                y += speed;
                x -= speed;
                break;
            case DOWN_RIGHT:
                y += speed;
                x += speed;
                break;
        }

        collision();

    }

    public boolean[] getDirs() {
        return dirs;
    }


    public boolean collisionObject() {
        List<GameObject> objects = TankGame.getGameClient().getObjects();
        boolean isCollision = false;
        for (GameObject object : objects) {
            if (object != this && !(object instanceof Bullet)&& getRectangle().intersects(object.getRectangle())) {

                isCollision = true;
            }
        }
        return isCollision;
    }

    public boolean collision() {
        boolean isCollision = collisionBound();
        if (!isCollision) {
            isCollision = collisionObject();
        }
        if (isCollision) {
            x = oldX;
            y = oldY;
        }
        return isCollision;
    }

    //    Rectangle rectangle = new Rectangle(TankGame.getGameClient().getScreenWidth(),TankGame.getGameClient().getScreenHeight());
    public boolean collisionBound() {
        boolean collision = false;
        if (x < 0) {
            x = 0;
            collision = true;
        } else if (x > TankGame.getGameClient().getScreenWidth() - width) {
            x = TankGame.getGameClient().getScreenWidth() - width;
            collision = true;
        }
        if (y < 0) {
            y = 0;
            collision = true;
        } else if (y > TankGame.getGameClient().getScreenHeight() - height) {
            y = TankGame.getGameClient().getScreenHeight() - height;
            collision = true;
        }

        return collision;
    }

    private void determineDirection() {
        if (dirs[0] && dirs[2] && !dirs[1] && !dirs[3]) direction = Direction.UP_LIFT;
        else if (dirs[0] && dirs[3] && !dirs[2] && !dirs[1]) direction = Direction.UP_RIGHT;
        else if (dirs[1] && dirs[2] && !dirs[0] && !dirs[3]) direction = Direction.DOWN_LIFT;
        else if (dirs[1] && dirs[3] && !dirs[0] && !dirs[2]) direction = Direction.DOWN_RIGHT;
        else if (dirs[0] && !dirs[3] && !dirs[1] && !dirs[2]) direction = Direction.UP;
        else if (dirs[1] && !dirs[3] && !dirs[0] && !dirs[2]) direction = Direction.DOWN;
        else if (dirs[2] && !dirs[3] && !dirs[0] && !dirs[1]) direction = Direction.LIFT;
        else if (dirs[3] && !dirs[1] && !dirs[0] && !dirs[2]) direction = Direction.RIGHT;

    }

    public void draw(Graphics g) {
        if (!isStop()) {
            determineDirection();
            move();
        }
        g.drawImage(image[(direction.ordinal())], x, y, null);
    }

    public boolean isStop() {
        for (int i = 0; i < dirs.length; i++) {
            if (dirs[i]) {
                return false;
            }
        }
        return true;
    }

    public void fire() {
        TankGame.getGameClient().addGameObject(new Bullet(x + width / 2 - GameClient.BulletImage[0].getWidth(null) / 2,
                y + height / 2 - GameClient.BulletImage[0].getHeight(null) / 2, direction, enemy, GameClient.BulletImage)
        );
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isEnemy() {
        return enemy;
    }

    public void getHurt(int damage) {
        hp -= damage;
        if (hp <= 0) {
            alive = false;
        }

    }
}
