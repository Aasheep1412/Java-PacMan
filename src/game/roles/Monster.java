package game.roles;

import game.LevelPanel;

import javax.swing.*;
import java.awt.*;

/**
 * 怪物
 * @author DengYuhan
 * @date 2020/3/31
 */
public class Monster extends Role {
    /**三种怪物的图片*/
    private Image mons1 = new ImageIcon("pictures/mons_1.png").getImage();
    private Image mons2 = new ImageIcon("pictures/mons_2.png").getImage();
    private Image mons3 = new ImageIcon("pictures/mons_3.png").getImage();
    private Image mons;

    /**怪物1或2或3*/
    private int index;

    public Monster(int x, int y, int direction, int index){
        super(x, y, direction);
        this.index = index;
    }

    public void setMonster(int x, int y, int direction){
        setX(x);
        setY(y);
        setDirection(direction);
    }

    /**怪兽运动*/
    public void move(int dir){
        int px = getX(), py = getY();
        switch(dir) {
            case LevelPanel.UP: py-=1;setY(py);break;
            case LevelPanel.DOWN: py+=1;setY(py);break;
            case LevelPanel.LEFT: px-=1;setX(px);break;
            case LevelPanel.RIGHT: px+=1;setX(px);break;
            default:break;
        }
    }

    /**判断在此方向上怪物是否可以继续走*/
    public boolean canMove(int[][] kind) {
        int x=getX(), y=getY();
        switch(getDirection()) {
            case LevelPanel.UP:y-=1;break;
            case LevelPanel.DOWN:y+=1;break;
            case LevelPanel.LEFT:x-=1;break;
            case LevelPanel.RIGHT:x+=1;break;
            default:break;
        }
        //超出边界
        if(x<0 || x>(LevelPanel.GRIDSNUM-1) || y<0 || y>(LevelPanel.GRIDSNUM-1)) {
            return false;
        }
        //不能碰墙和怪和大力丸
        if(kind[y][x] == LevelPanel.MAN || kind[y][x] == LevelPanel.BEAN || kind[y][x] == LevelPanel.FLOOR) {
            return true;
        }
        return false;
    }

    /**怪物图片*/
    public Image getImage(){
        switch (index){
            case LevelPanel.MONSTER_1: mons = mons1;break;
            case LevelPanel.MONSTER_2: mons = mons2;break;
            case LevelPanel.MONSTER_3: mons = mons3;break;
            default: break;
        }
        return mons;
    }

    public int getIndex(){
        return index;
    }
}
