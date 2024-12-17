package Main;

import javax.swing.*;
/*
显示图标的类，存放了各种关于图片的参数
 */
public class ShownIcon  extends JLabel{
    //图片左上角坐标值
    public int x;
    public int y;
    //图片编号，判断是否使同一张照片,1-11
    public int number;
    //记录图片是否被消除，默认没有false
    public boolean isClear=false;
    //显示的图片
    public JLabel showLabel;
    //图片在集合中的位置
    public int listNumber;
    public ShownIcon(){

    }
    public ShownIcon(int x, int y, int number, JLabel showLabel) {
        this.x = x;
        this.y = y;
        this.number = number;
        this.showLabel=showLabel;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public boolean isClear() {
        return isClear;
    }
}
