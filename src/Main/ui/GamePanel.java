package Main.ui;

import Main.Frame;
import Main.ShownIcon;
import Main.utils.ImagesValue;
import Main.utils.UiValue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;

public class GamePanel extends JPanel implements Runnable {
    public JLabel gameUi;
    public JLabel scoreUi;
    public JLabel background;
    public JLabel returnHome;
    public JLabel replay;//不计时模式通关后重置按钮
    public static String pattern;
    public static boolean ifTheSecond;
    public static ShownIcon theFirst;
    public static ShownIcon theSecond;
    public static ShownIcon theLastLinkedOne;
    public List<ShownIcon> iconList=new ArrayList<>();
    public static long startTime;//游戏开始时间
    public static long currentTime;//游戏结束时间
    public static int score;//不计时模式
    public static JLabel showScore_notime;//不计时模式使用
    public static JLabel showScore_forever;//无尽模式使用
    public static JLabel showTime;
    public static JLabel countDown;
    public static long duration;
    public Thread thread=new Thread(this);
    public static boolean ifWait;//是否暂停线程
    public static boolean onlyStart=false;//确保线程只能start一次

    public GamePanel() {
        //设置以绝对位置布局
        setLayout(null);


        //越界初始化历史记录参数,后续避免同时点击同一个图片进行作弊消除
        theLastLinkedOne=new ShownIcon();
        theLastLinkedOne.listNumber=1000;

        //专属UI
        countDown=new JLabel();
        countDown.setBounds(772,250,150,50);
        showTime=new JLabel();
        showTime.setBounds(652,170,280,60);
        showScore_notime=new JLabel();
        showScore_notime.setBounds(770,180,150,50);
        showScore_forever=new JLabel();
        showScore_forever.setBounds(790,97,200,50);
        replay=new JLabel(UiValue.replay1);
        replay.setBounds(770,310,50,50);
        //通用UI
        scoreUi=new JLabel(UiValue.scoreUi);
        background=new JLabel(UiValue.background);
        returnHome=new JLabel(UiValue.returnHome1);
        gameUi=new JLabel();
        gameUi.setBounds(-10,-10,650,600);
        scoreUi.setBounds(620,10,350,550);
        background.setBounds(0,0,1000,600);
        returnHome.setBounds(0,0,50,50);

        replay.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //跳转界面为了重置界面图形
                operationEnd();
                showTime.setIcon(null);
                replay.setIcon(UiValue.replay1);//无尽模式使用，将next图标替换回来
                Frame.frame.cardLayout.show(Frame.frame.cardPanel,"MainPanel");
                Frame.frame.mainPanel.operationStart();
                Frame.frame.cardLayout.show(Frame.frame.cardPanel,"GamePanel");
                //仅正常模式和无尽模式通过按钮可以重置时间，唤醒线程
                if (pattern.equals("common")||pattern.equals("forever")){
                    GamePanel.startTime=System.currentTimeMillis();
                    resumeThread();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!pattern.equals("forever")||score%50!=0&&pattern.equals("forever")||score==0){
                    replay.setIcon(UiValue.replay2);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!pattern.equals("forever")||score%50!=0&&pattern.equals("forever")||score==0){
                    replay.setIcon(UiValue.replay1);
                }
            }
        });

        returnHome.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                operationEnd();

                Frame.frame.cardLayout.show(Frame.frame.cardPanel,"MainPanel");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                returnHome.setIcon(UiValue.returnHome2);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                returnHome.setIcon(UiValue.returnHome1);
            }
        });
    }
    public void operationEnd(){
        clearIcon();
        //重置模式的专属变量
        if (pattern.equals("notime")){//不计时模式
            showScore_notime.setText("");
            scoreUi.setIcon(UiValue.scoreUi);
            score=0;
        } else if (pattern.equals("common")) {//正常模式
            showTime.setIcon(null);
            pauseThread();
            countDown.setText("");
            score=0;
        } else if (pattern.equals("forever")) {//无尽模式
            pauseThread();
            showScore_forever.setText("");
            countDown.setText("");
            showTime.setIcon(null);
            scoreUi.setIcon(UiValue.scoreUi);
            if (score%50!=0||score==0){//重开就清零
                score=0;
            }
        }
    }
    public void clearIcon(){
        //清空连连看图片和边框,便于之后开始游戏时重新加载
        for (int number=0;number<iconList.size();number++){
            iconList.get(number).showLabel.setIcon(null);
            iconList.get(number).showLabel.setBorder(null);
            Frame.frame.gamePanel.remove(iconList.get(number).showLabel);//彻底移除label
        }
        iconList.clear();
    }
    //按顺序添加UI，避免UI间的覆盖
    public void showUi(){
        add(countDown);
        add(showTime);
        add(replay);
        add(showScore_forever);
        add(showScore_notime);
        add(scoreUi);
        add(gameUi);
        add(returnHome);
        add(background);
    }
    //加载图片的方法
    public void paint(GamePanel gamePanel){
        Random random=new Random();
        //给iconList先添加100个元素
        for (int x=155,cnt1=0,num=0;cnt1<10;cnt1++,x+=32,num++){
            for (int y=150,cnt2=0;cnt2<10;cnt2++,y+=32,num++){
                //产生1-11的随机数
                int randomNum1=random.nextInt(11)+1;
                iconList.add(new ShownIcon(x,y,randomNum1,new JLabel()));
            }
        }

        //对50-99元素的图像编号进行修改，依次复制0-49，确保图案成对出现
        for(int number=50;number<100;number++){
            iconList.get(number).number=iconList.get(number-50).number;
        }

        //添加边缘空白方块,空白方块第三项数字为-1，便于后续替换显示图片
        for (int x=123,y=118;x<=475;x+=32){//上框
            iconList.add(new ShownIcon(x,y,-1,new JLabel()));
        }
        for (int x=123,y=470;x<=475;x+=32){//下框
            iconList.add(new ShownIcon(x,y,-1,new JLabel()));
        }
        for (int x=123,y=150;y<=438;y+=32){//左框，此处忽略两边
            iconList.add(new ShownIcon(x,y,-1,new JLabel()));
        }
        for (int x=475,y=150;y<=438;y+=32){//右框，此处忽略两边
            iconList.add(new ShownIcon(x,y,-1,new JLabel()));
        }

        //将集合元素打乱
        //Collections.shuffle(gamePanel.iconList);无法使用,此时元素仍然和位置绑定,考虑使用手动打乱
        for (int number=0;number<100;number++){
            //生成随机位置,只交换前100个元素
            int randomNum2=random.nextInt(100);
            //交换当前位置和随机位置的元素的位置
            int tempX=iconList.get(number).x;
            int tempY=iconList.get(number).y;
            iconList.get(number).x=iconList.get(randomNum2).x;
            iconList.get(number).y=iconList.get(randomNum2).y;
            iconList.get(randomNum2).x=tempX;
            iconList.get(randomNum2).y=tempY;
        }

        //打乱后，每一个元素获取在集合中的位置
        for (int number=0;number<iconList.size();number++){
            iconList.get(number).listNumber=number;
        }

        //集中将图片添加至游戏界面
        for (ShownIcon one : iconList) {
            if (one.number==-1){//边缘图片在此处替换状态和显示
                one.showLabel.setIcon(null);
                one.isClear=true;
            }else {
                one.showLabel.setIcon(ImagesValue.returnIcon(one.number));
                one.showLabel.setBounds(one.x, one.y, 32, 32);
            }

            //为每一个图片设置点击效果和逻辑判断
            one.showLabel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!one.isClear && theLastLinkedOne.listNumber != one.listNumber) {//非空白且不能同时点击同一个时
                        one.showLabel.setBorder(BorderFactory.createLineBorder(Color.red, 2));
                        judge(one);
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            gamePanel.add(one.showLabel);
        }
    }
    //连连看消除逻辑
    public void judge(ShownIcon shownIcon){
        if (ifTheSecond){//如果是一组的第二个图片
            theSecond=shownIcon;
            if (theFirst.number==theSecond.number){
                if (line(theFirst.x,theFirst.y,theSecond.x,theSecond.y)){
                    score++;
                    linkAndClear(theFirst,theSecond);
                }else if (oneTurn(theFirst.x,theFirst.y,theSecond.x,theSecond.y)) {
                    score++;                   linkAndClear(theFirst,theSecond);
                } else if (twoTurn(theFirst.x,theFirst.y,theSecond.x,theSecond.y)) {
                    score++;
                    linkAndClear(theFirst,theSecond);
                }
            }
            //消除边框
            iconList.get(theFirst.listNumber).showLabel.setBorder(null);
            iconList.get(theSecond.listNumber).showLabel.setBorder(null);
            //越界初始化历史记录参数
            theLastLinkedOne=new ShownIcon();
            theLastLinkedOne.listNumber=1000;
            //更新得分和结束判断
            if (pattern.equals("notime")){//不计时模式
                GamePanel.showScore_notime.setText(GamePanel.score+"/50");
                if (score==50){
                    JOptionPane.showMessageDialog(this,"通关！");
                }
            }else if (pattern.equals("common")){//正常模式
                if (score==50){
                    pauseThread();//线程暂停
                    JOptionPane.showMessageDialog(this,"通关！");
                }
            } else if (pattern.equals("forever")) {//无尽模式
                GamePanel.showScore_forever.setText(String.valueOf(GamePanel.score));
                if (score%50==0&&score!=0){
                    pauseThread();//线程暂停
                    replay.setIcon(UiValue.Next);//替换重开图标
                    JOptionPane.showMessageDialog(this,"本关完成！");
                }
            }

            ifTheSecond=false;
        }else {//如果是一组的第一个图片
            theFirst=shownIcon;
            //避免同时点击同一个进行消除
            theLastLinkedOne=theFirst;

            ifTheSecond=true;
        }
    }
    @Override
    public void run() {//线程用于在无尽和正常模式进行倒计时
        while (true){
            currentTime=System.currentTimeMillis();
            duration=90-(currentTime-startTime)/1000;//得到秒
            countDown.setText(String.valueOf(duration));

            //更换倒计时长栏图标
            if (duration==90){
                showTime.setIcon(UiValue.Time90);
            } else if (duration==72){
                showTime.setIcon(UiValue.Time72);
            } else if (duration==54) {
                showTime.setIcon(UiValue.Time54);
            } else if (duration==36) {
                showTime.setIcon(UiValue.Time36);
            } else if (duration==18) {
                showTime.setIcon(UiValue.Time18);
            } else if (duration==0) {
                showTime.setIcon(UiValue.Time0);
            }

            //倒计时为0则游戏结束
            if (duration==0){
                pauseThread();
                clearIcon();
                JOptionPane.showMessageDialog(this,"倒计时结束！请重新开始游戏！");
            }

            synchronized (this){
                while (ifWait){
                    try {
                        wait();//等待被唤醒
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
    public void pauseThread(){
        ifWait=true;
    }
    public void resumeThread() {
        ifWait=false;
        synchronized (this) {
            notify(); //唤醒等待的线程
        }
    }
    public void linkAndClear(ShownIcon theFirst,ShownIcon theSecond){//消除图片
        iconList.get(theFirst.listNumber).showLabel.setIcon(null);
        iconList.get(theFirst.listNumber).isClear=true;
        iconList.get(theSecond.listNumber).showLabel.setIcon(null);
        iconList.get(theSecond.listNumber).isClear=true;
    }
    //0折消除的原理：判断两个图片之间在x轴或y轴上是否全空白
    public boolean line(int x1,int y1,int x2,int y2){//0折消除
        if (x1==x2){//同x轴遍历两图片判断y轴上有无图片
            if (Math.min(y1,y2)+32==Math.max(y1,y2)||checkY(y1,y2,x1)){
                return true;
            }
        }else if (y1==y2){//同y轴遍历两图片判断x轴上有无图片
            if (Math.min(x1,x2)+32==Math.max(x1,x2)||checkX(x1,x2,y1)){
                return true;
            }

        }
        return false;
    }
    //1折消除原理：判断是否有一个图片可以同时与这两个图片0折消除
    public boolean oneTurn(int x1,int y1,int x2,int y2){//1折消除
        //此处还要考虑拐角要存在才可以,所以设置两个局部ShownIcon类成员
        ShownIcon x1y2=null,x2y1=null;
        for (ShownIcon one:iconList){
            if (one.x==x1&&one.y==y2){
                x1y2=one;
            }else if (one.x==x2&&one.y==y1) {
                x2y1=one;
            }
        }
        if (line(x1,y1,x1,y2)&&line(x1,y2,x2,y2)&&x1y2.isClear||line(x1,y1,x2,y1)&&line(x2,y1,x2,y2)&&x2y1.isClear){
            return true;
        }
        return false;
    }
    //2折消除原理：判断是否有一个图片可以同时与这两个图片分别0折消除和1折消除
    public boolean twoTurn(int x1,int y1,int x2,int y2){//2折消除
        /*两折消除的逻辑为扫描一点所在的行列找到一点使得此点能够与两点分别0折和1折消除，或扫描二点的行列找到另一点，并且此点不与原
        先的两点重合且为空*/
        for (ShownIcon one:iconList){
            if (one.isClear){//此点必须为空
                if (one.x!=x1||one.y!=y1){//此点不与1点重合
                    if (one.x!=x2||one.y!=y2) {//此点不与2点重合
                        if (line(x1,y1,one.x,one.y)&&oneTurn(one.x,one.y,x2,y2)||line(x2,y2,one.x,one.y)&&oneTurn(one.x,one.y,x1,y1)){
                            //此点能够与原两点分别0折消除和1折消除
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public boolean checkY(int y1,int y2,int x){//判断两个图片之间是否全空白
        //将所有在坐标间的图片添加至集合
        List<ShownIcon> judgeList=new ArrayList<>();
        for (int number=0;number<iconList.size();number++){
            if (iconList.get(number).y>Math.min(y1,y2)&&iconList.get(number).y<Math.max(y1,y2)&&iconList.get(number).x==x){
                judgeList.add(iconList.get(number));
            }
        }
        for (ShownIcon one:judgeList){
            if (!one.isClear()){
                judgeList.clear();
                return false;
            }
        }

        judgeList.clear();
        return true;
    }
    public boolean checkX(int x1,int x2,int y){//判断两个图片之间是否全空白
        //将所有在坐标间的图片添加至集合
        List<ShownIcon> judgeList=new ArrayList<>();
        for (int number=0;number<iconList.size();number++){
            if (iconList.get(number).x>Math.min(x1,x2)&&iconList.get(number).x<Math.max(x1,x2)&&iconList.get(number).y==y){
                judgeList.add(iconList.get(number));
            }
        }
        for (ShownIcon one:judgeList){
            if (!one.isClear()){
                judgeList.clear();
                return false;
            }
        }

        judgeList.clear();
        return true;
    }


}