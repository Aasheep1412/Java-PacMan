package game.roles;

import game.LevelPanel;

import javax.swing.*;
import java.awt.*;

/**
 * 吃豆人
 * @author DengYuhan
 * @date 2020/3/31
 */
public class Man extends Role {

    /**朝不同方向运动的图片*/
    private Image manR = new ImageIcon("pictures/man_r.png").getImage();
    private Image manL = new ImageIcon("pictures/man_l.png").getImage();
    private Image manD = new ImageIcon("pictures/man_d.png").getImage();
    private Image manU = new ImageIcon("pictures/man_u.png").getImage();
    private Image man;
    /**步长*/
    private int step;

    public Man(int x, int y, int step, int direction){
        super(x, y, direction);
        this.step = step;
    }

    /**人运动*/
    public void move(int step){
        int px = getX(), py = getY();
        switch(getDirection()) {
            case LevelPanel.UP: py-=step;setY(py);break;
            case LevelPanel.DOWN: py+=step;setY(py);break;
            case LevelPanel.LEFT: px-=step;setX(px);break;
            case LevelPanel.RIGHT: px+=step;setX(px);break;
            default:break;
        }
    }


    /**
     * 判断人在该方向是否可以移动
     * @param kind 标识地图属性的数组
     * @return -1 不能移动，1可移动1格，2可移动2格
     */
    public int canMove(int[][] kind) {
        int x=getX(), y=getY(), step = getStep();
        //步长为1
        if(step==1){
            switch(getDirection()) {
                case LevelPanel.UP:y-=1;break;
                case LevelPanel.DOWN:y+=1;break;
                case LevelPanel.LEFT:x-=1;break;
                case LevelPanel.RIGHT:x+=1;break;
                default:break;
            }
            //超出边界
            if(x<0 || x>(LevelPanel.GRIDSNUM-1) || y<0 || y>(LevelPanel.GRIDSNUM-1)) {
                return -1;
            }
            //不能碰墙
            if(kind[y][x] != LevelPanel.WALL) {
                return 1;
            }
            return -1;
        }
        //步长为2
        else{
            switch(getDirection()) {
                case LevelPanel.UP:
                    //走两格不超边界
                    if(y-LevelPanel.STEPTWO>=0){
                        //走两格会碰墙
                        if(kind[y-LevelPanel.STEPTWO][x]==LevelPanel.WALL){
                            //走一格会碰墙
                            if(kind[y-1][x]==LevelPanel.WALL) {
                                return -1;
                            } else {
                                return 1;
                            }
                        }
                        //走两格不会碰墙
                        else{
                            //走一格会碰墙
                            if(kind[y-1][x]==LevelPanel.WALL) {
                                return -1;
                            } else {
                                return 2;
                            }
                        }
                    }
                    //走一格不超边界
                    else if(y-1>=0){
                        //走一格会碰墙
                        if(kind[y-1][x]==LevelPanel.WALL) {
                            return -1;
                        } else {
                            return 1;
                        }
                    }
                    //走一格超边界
                    else {
                        return -1;
                    }
                case LevelPanel.DOWN:
                    //走两格不超边界
                    if(y+LevelPanel.STEPTWO<=(LevelPanel.GRIDSNUM-1)){
                        //走两格碰墙
                        if(kind[y+LevelPanel.STEPTWO][x]==LevelPanel.WALL){
                            //走一格碰墙
                            if(kind[y+1][x]==LevelPanel.WALL) {
                                return -1;
                            } else {
                                return 1;
                            }
                        }
                        else{
                            if(kind[y+1][x]==LevelPanel.WALL) {
                                return -1;
                            } else {
                                return 2;
                            }
                        }
                    }
                    //走一格不超边界
                    else if(y+1<=(LevelPanel.GRIDSNUM-1)){
                        if(kind[y+1][x]==LevelPanel.WALL) {
                            return -1;
                        } else {
                            return 1;
                        }
                    }
                    else {
                        return -1;
                    }
                case LevelPanel.LEFT:
                    //走两格不超边界
                    if(x-LevelPanel.STEPTWO>=0){
                        if(kind[y][x-LevelPanel.STEPTWO]==LevelPanel.WALL){
                            if(kind[y][x-1]==LevelPanel.WALL) {
                                return -1;
                            } else {
                                return 1;
                            }
                        }
                        else{
                            if(kind[y][x-1]==LevelPanel.WALL) {
                                return -1;
                            } else {
                                return 2;
                            }
                        }
                    }
                    //走一格不超边界
                    else if(x-1>=0){
                        if(kind[y][x-1]==LevelPanel.WALL) {
                            return -1;
                        } else {
                            return 1;
                        }
                    }
                    else {
                        return -1;
                    }
                case LevelPanel.RIGHT:
                    //走两格不超边界
                    if(x+LevelPanel.STEPTWO<=(LevelPanel.GRIDSNUM-1)){
                        if(kind[y][x+LevelPanel.STEPTWO]==LevelPanel.WALL){
                            if(kind[y][x+1]==LevelPanel.WALL) {
                                return -1;
                            } else {
                                return 1;
                            }
                        }
                        else{
                            if(kind[y][x+1]==LevelPanel.WALL) {
                                return -1;
                            } else {
                                return 2;
                            }
                        }
                    }
                    //走一格不超边界
                    else if(x+1<=(LevelPanel.GRIDSNUM-1)){
                        if(kind[y][x+1]==LevelPanel.WALL) {
                            return -1;
                        } else {
                            return 1;
                        }
                    }
                    else {
                        return -1;
                    }
                default:break;
            }
            return -1;
        }
    }

    /**人运动的图片*/
    public Image getImage(){
        switch (getDirection()){
            case LevelPanel.UP: man = manU;break;
            case LevelPanel.DOWN: man = manD;break;
            case LevelPanel.LEFT: man = manL;break;
            case LevelPanel.RIGHT: man = manR;break;
            default: break;
        }
        return man;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

}
