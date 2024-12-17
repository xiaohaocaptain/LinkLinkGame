package Main.utils;

import javax.swing.*;
/*
将图片文件导入程序，统一存放
 */
public class UiValue {
    //获取文件统一路径前缀
    public static String prefix=System.getProperty("user.dir")+"/src/graphics/PanelUI/";
    public static ImageIcon gameUi;
    public static ImageIcon gameUi_notime;
    public static ImageIcon gameUi_common;
    public static ImageIcon gameUi_forever;
    public static ImageIcon scoreUi;
    public static ImageIcon scoreUi_notime;
    public static ImageIcon scoreUi_forever;
    public static ImageIcon mainUi;
    public static ImageIcon background;
    public static ImageIcon returnHome1;
    public static ImageIcon returnHome2;
    public static ImageIcon replay1;
    public static ImageIcon replay2;
    public static ImageIcon Next;
    public static ImageIcon Choice_notime1;
    public static ImageIcon Choice_notime2;
    public static ImageIcon Choice_common1;
    public static ImageIcon Choice_common2;
    public static ImageIcon Choice_forever1;
    public static ImageIcon Choice_forever2;
    public static ImageIcon Time90;
    public static ImageIcon Time72;
    public static ImageIcon Time54;
    public static ImageIcon Time36;
    public static ImageIcon Time18;
    public static ImageIcon Time0;

    public static void initialize() {
        gameUi=new ImageIcon(prefix+"GamePanel2.png");
        gameUi_notime=new ImageIcon(prefix+"GamePanel2_notime.png");
        gameUi_common=new ImageIcon(prefix+"GamePanel2_common.png");
        gameUi_forever=new ImageIcon(prefix+"GamePanel2_forever.png");

        scoreUi_forever=new ImageIcon(prefix+"ScorePanel_forever.png");
        scoreUi_notime=new ImageIcon(prefix+"ScorePanel_notime.png");
        scoreUi=new ImageIcon(prefix+"ScorePanel.png");
        mainUi=new ImageIcon(prefix+"GamePanel1.png");
        background=new ImageIcon(prefix+"Background.jpg");
        returnHome1=new ImageIcon(prefix+"ReturnHome1.png");
        returnHome2=new ImageIcon(prefix+"ReturnHome2.png");
        replay1=new ImageIcon(prefix+"Replay1.png");
        replay2=new ImageIcon(prefix+"Replay2.png");
        Next=new ImageIcon(prefix+"Next.png");

        Choice_notime1=new ImageIcon(prefix+"Choice_notime1.png");
        Choice_notime2=new ImageIcon(prefix+"Choice_notime2.png");
        Choice_common1=new ImageIcon(prefix+"Choice_common1.png");
        Choice_common2=new ImageIcon(prefix+"Choice_common2.png");
        Choice_forever1=new ImageIcon(prefix+"Choice_forever1.png");
        Choice_forever2=new ImageIcon(prefix+"Choice_forever2.png");

        Time90=new ImageIcon(prefix+"Time90.png");
        Time72=new ImageIcon(prefix+"Time72.png");
        Time54=new ImageIcon(prefix+"Time54.png");
        Time36=new ImageIcon(prefix+"Time36.png");
        Time18=new ImageIcon(prefix+"Time18.png");
        Time0=new ImageIcon(prefix+"Time0.png");
    }
}
