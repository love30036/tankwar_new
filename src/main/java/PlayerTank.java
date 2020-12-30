import object.Direction;

import java.awt.*;

public class PlayerTank extends Tank implements SuperFire {

    public PlayerTank(int x, int y, Direction direction, Image[] image) {
        super(x, y, direction, image);
        hp = 3;
    }

    @Override
    public void superFire() {
        for (Direction direction : Direction.values()) {
            TankGame.getGameClient().addGameObject(new Bullet(x + width / 2 - GameClient.BulletImage[0].getWidth(null) / 2,
                    y + height / 2 - GameClient.BulletImage[0].getHeight(null) / 2, direction, enemy, GameClient.BulletImage)
            );
        }
    }
}
