package Main.ui;

import Main.Frame;
import Main.utils.UiValue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainPanel extends JPanel {
    private JLabel mainUi;
    private JLabel background;
    private JLabel Choice_notime;
    private JLabel Choice_common;
    private JLabel  Choice_forever;
    private JLabel author;
    public MainPanel() {


        //设置以绝对位置布局
        setLayout(null);

        //不计时模式
        Choice_notime=new JLabel(UiValue.Choice_notime1);
        Choice_notime.setBounds(360,165,250,75);
        add(Choice_notime);
        Choice_notime.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GamePanel.pattern ="notime";//获取当前模式
                operationStart();
                Frame.frame.cardLayout.show(Frame.frame.cardPanel,"GamePanel");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Choice_notime.setIcon(UiValue.Choice_notime2);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Choice_notime.setIcon(UiValue.Choice_notime1);
            }
        });
        //正常模式
        Choice_common=new JLabel(UiValue.Choice_common1);
        Choice_common.setBounds(360,265,250,75);
        add(Choice_common);
        Choice_common.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GamePanel.pattern ="common";//获取当前模式
                operationStart();

                //计时，确保只start一次
                GamePanel.startTime=System.currentTimeMillis();
                if (!GamePanel.onlyStart){
                    Frame.frame.gamePanel.thread.start();
                    GamePanel.onlyStart=true;
                }
                //唤醒线程
                Frame.frame.gamePanel.resumeThread();

                Frame.frame.cardLayout.show(Frame.frame.cardPanel,"GamePanel");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Choice_common.setIcon(UiValue.Choice_common2);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Choice_common.setIcon(UiValue.Choice_common1);
            }
        });
        //无尽模式
        Choice_forever=new JLabel(UiValue.Choice_forever1);
        Choice_forever.setBounds(360,365,250,75);
        add(Choice_forever);
        Choice_forever.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GamePanel.pattern ="forever";//获取当前模式
                operationStart();

                //计时，确保只start一次
                GamePanel.startTime=System.currentTimeMillis();
                if (!GamePanel.onlyStart){
                    Frame.frame.gamePanel.thread.start();
                    GamePanel.onlyStart=true;
                }
                //唤醒线程
                Frame.frame.gamePanel.resumeThread();

                Frame.frame.cardLayout.show(Frame.frame.cardPanel,"GamePanel");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Choice_forever.setIcon(UiValue.Choice_forever2);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Choice_forever.setIcon(UiValue.Choice_forever1);
            }
        });
        //作者信息
        author=new JLabel("XiaohaoCaptain   Version1.0");
        author.setFont(new Font("宋体",Font.BOLD,15));
        author.setBounds(640,440,250,100);
        add(author);
        //主UI
        mainUi=new JLabel(UiValue.mainUi);
        mainUi.setBounds(-15,-10,1000,600);
        add(mainUi);
        //背景板
        background=new JLabel(UiValue.background);
        background.setBounds(0,0,1000,600);
        add(background);
    }
    public void operationStart(){

        if (GamePanel.pattern.equals("notime")){//不计时模式
            Frame.frame.gamePanel.gameUi.setIcon(UiValue.gameUi_notime);

            //重置变量
            GamePanel.ifTheSecond=false;

            //重置图片内容
            Frame.frame.gamePanel.paint(Frame.frame.gamePanel);
            Frame.frame.gamePanel.showUi();

            //设置模式专属UI
            GamePanel.score=0;
            Frame.frame.gamePanel.scoreUi.setIcon(UiValue.scoreUi_notime);
            GamePanel.showScore_notime.setText(GamePanel.score+"/50");
            GamePanel.showScore_notime.setFont(new Font("宋体",Font.BOLD,40));
        } else if (GamePanel.pattern.equals("common")) {//正常模式
            Frame.frame.gamePanel.gameUi.setIcon(UiValue.gameUi_common);

            //重置变量
            GamePanel.ifTheSecond=false;

            //重置图片内容
            Frame.frame.gamePanel.paint(Frame.frame.gamePanel);
            Frame.frame.gamePanel.showUi();

            //设置模式专属UI
            GamePanel.score=0;
            GamePanel.showTime.setIcon(UiValue.Time90);
            GamePanel.countDown.setFont(new Font("宋体",Font.BOLD,40));
        }else if (GamePanel.pattern.equals("forever")) {//无尽模式
            Frame.frame.gamePanel.gameUi.setIcon(UiValue.gameUi_forever);

            //重置变量
            GamePanel.ifTheSecond=false;

            //重置图片内容
            Frame.frame.gamePanel.paint(Frame.frame.gamePanel);
            Frame.frame.gamePanel.showUi();

            //设置模式专属UI
            GamePanel.showTime.setIcon(UiValue.Time90);
            Frame.frame.gamePanel.scoreUi.setIcon(UiValue.scoreUi_forever);
            GamePanel.countDown.setFont(new Font("宋体",Font.BOLD,40));
            GamePanel.showScore_forever.setText(String.valueOf(GamePanel.score));
            GamePanel.showScore_forever.setFont(new Font("宋体",Font.BOLD,40));
        }
    }
}