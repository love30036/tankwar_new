import object.GameObject;

import javax.swing.*;
import java.awt.*;

public class Wall extends GameObject {

    private boolean horizontal;
    private int bricks;

    public Wall(int x, int y, boolean horizontal, int bricks,Image[] image) {
        super(x,y,image);
        this.horizontal = horizontal;
        this.bricks = bricks;
    }
    @Override
    public void draw(Graphics g){
        if(horizontal){
            for(int i = 0;i<bricks;i++){
                g.drawImage(image[0],x+i*width,y,null);
            }
        }else {
            for(int i = 0;i<bricks;i++){
                g.drawImage(image[0],x,y+i*height,null);
            }
        }
    }
    @Override
    public Rectangle getRectangle(){
        return horizontal ? new Rectangle(x,y,bricks*width,height):
                new Rectangle(x,y,width,height*bricks);
    }

}
