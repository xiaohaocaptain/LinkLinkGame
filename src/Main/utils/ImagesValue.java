package Main.utils;

import javax.swing.*;

public class ImagesValue{
    //获取文件统一路径前缀
    public static String prefix=System.getProperty("user.dir")+"/src/graphics/Icon/";
    public static ImageIcon apple;//苹果
    public static ImageIcon apricot;//杏
    public static ImageIcon banana;//香蕉
    public static ImageIcon cantaloupe;//香瓜
    public static ImageIcon starFruit;//杨桃
    public static ImageIcon custard;//野苹果
    public static ImageIcon dragonFruit;//火龙果
    public static ImageIcon jackFruit;//菠萝蜜
    public static ImageIcon mango;//芒果
    public static ImageIcon peach;//桃子
    public static ImageIcon pomegranate;//石榴

    public static void initialize() {
        //集中初始化处理图片
        apple=new ImageIcon(prefix+"apple.png");
        apricot=new ImageIcon (prefix+"apricot.png");
        banana=new ImageIcon(prefix+"banana.png");
        cantaloupe=new ImageIcon(prefix+"cantaloupe.png");
        starFruit=new ImageIcon(prefix+"star fruit.png");
        custard=new ImageIcon(prefix+"custard apple.png");
        dragonFruit=new ImageIcon(prefix+"dragon fruit.png");
        jackFruit= new ImageIcon(prefix+"jack fruit.png");
        mango= new ImageIcon(prefix+"mango.png");
        peach=new ImageIcon(prefix+"peach.png");
        pomegranate=new ImageIcon(prefix+"pomegranate.png");
    }
    public static ImageIcon returnIcon(int number){
        switch (number){
            case 1:
                return apple;
            case 2:
                return apricot;
            case 3:
                return banana;
            case 4:
                return cantaloupe;
            case 5:
                return starFruit;
            case 6:
                return custard;
            case 7:
                return dragonFruit;
            case 8:
                return jackFruit;
            case 9:
                return mango;
            case 10:
                return peach;
            case 11:
                return pomegranate;
            default:
                return null;
        }
    }
}
