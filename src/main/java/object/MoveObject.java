package object;

import java.awt.*;

public abstract class MoveObject extends GameObject{

    public MoveObject(int x, int y, Image[] image) {
        super(x, y, image);
    }

    public abstract void move();

    //collision and move
}
