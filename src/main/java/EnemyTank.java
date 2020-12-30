import object.Direction;

import java.awt.*;
import java.util.Random;

public class EnemyTank extends Tank implements AI{
    public EnemyTank(int x, int y, Direction direction, Image[] image) {
        super(x, y, direction, true,image);
    }
    public void ai(){

        Random random = new Random();

        if(random.nextInt(20)==1){


            if(random.nextInt(2)==1){
                return;
            }
            getNewDirection();
        }
        if(random.nextInt(50)==1){
            fire();
        }


    }

    @Override
    public void draw(Graphics g) {
        ai();
        super.draw(g);
    }
    public void getNewDirection(){
        dirs =new boolean[4];
        Random random = new Random();

        int dir =  random.nextInt(Direction.values().length);

        if(dir<=Direction.RIGHT.ordinal()){
            dirs[dir]=true;
        }else {
            if(dir==Direction.UP_LIFT.ordinal()){
                dirs[0]=true;
                dirs[2]=true;
            }else if(dir==Direction.UP_RIGHT.ordinal()){
                dirs[0]=true;
                dirs[3]=true;
            }else if(dir==Direction.DOWN_LIFT.ordinal()){
                dirs[1]=true;
                dirs[2]=true;
            }else if(dir==Direction.DOWN_RIGHT.ordinal()){
                dirs[1]=true;
                dirs[3]=true;
            }
        }

    }

    @Override
    public boolean collision() {
        if(super.collision()){
            getNewDirection();
            return true;
        }
        return false;
    }
}
