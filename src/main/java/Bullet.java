import object.Direction;
import object.GameObject;

import java.awt.*;
import java.util.List;

public class Bullet extends Tank {


    public Bullet(int x, int y, Direction direction, boolean enemy, Image[] image) {
        super(x, y, direction, enemy, image);
        setSpeed(15);
    }

    @Override
    public void draw(Graphics g) {
        if (!alive) {
            return;
        }
        move();
        g.drawImage(image[direction.ordinal()], x, y, null);
    }

    @Override
    public boolean collisionObject() {
        boolean isCollision = false;
        List<GameObject> objects = TankGame.getGameClient().getObjects();

        for (GameObject object : objects) {
            if (object == this) {
                continue;
            }
            if (object instanceof Tank) {
                if (((Tank) object).isEnemy() == isEnemy()) {
                    continue;
                }
            }
            if (getRectangle().intersects(object.getRectangle())) {

                if (object instanceof Tank) {
                    ((Tank)object).getHurt(1);
//                    object.setAlive(false);

                }
                isCollision =true ;
            }
        }
        return isCollision;
    }

    @Override
    public boolean collision() {
        boolean isCollision = collisionBound();
        if (!isCollision) {
            isCollision=collisionObject();
        }
        if(isCollision){
            alive = false;
            return true;
        }
        return false;
    }
}
