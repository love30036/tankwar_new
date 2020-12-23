package object;

import javax.swing.*;
import java.awt.*;

public class Wall extends GameObject{

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
                g.drawImage(image[0],x+i*getWidth(),y,null);
            }
        }else {
            for(int i = 0;i<bricks;i++){
                g.drawImage(image[0],x,y+i*getHeight(),null);
            }
        }
    }

}
