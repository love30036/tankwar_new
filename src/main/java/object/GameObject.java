package object;

import java.awt.*;

public abstract class GameObject {
    protected int x;
    protected int y;
    protected Image[] image;
    private int width;
    private int height;

    public GameObject(int x, int y, Image[] image) {
        this.x = x;
        this.y = y;
        this.image = image;
        width=image[0].getWidth(null);
        height=image[0].getHeight(null);
    }

    public abstract void draw(Graphics g);

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
