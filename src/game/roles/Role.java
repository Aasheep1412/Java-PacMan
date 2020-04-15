package game.roles;

/**
 * 角色类：人和怪物类的父类
 * @author DengYuhan
 * @date 2020/3/31
 */
public class Role {
    /**所在的网格坐标*/
    private int x;
    private int y;
    /**方向*/
    private int direction;

    public Role(int x, int y, int direction){
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
