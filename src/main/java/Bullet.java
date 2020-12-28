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
        collision();
        g.drawImage(image[direction.ordinal()], x, y, null);
    }

    @Override
    public void collision() {
        if (collisionBound()) {
            alive = false;
            return;
        }
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
                alive = false;
                if (object instanceof Tank) {
                    object.setAlive(false);
                }
                return;
            }
        }
    }
}
