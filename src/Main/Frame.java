package Main;

import Main.ui.GamePanel;
import Main.ui.MainPanel;
import Main.utils.ImagesValue;
import Main.utils.UiValue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Frame extends JFrame {
    public MainPanel mainPanel;
    public GamePanel gamePanel;
    public Panel cardPanel;
    public CardLayout cardLayout;
    public static Frame frame;

    public Frame() {
        //设置窗口参数
        setTitle("Link Link Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,600);
        setVisible(true);
        setResizable(false);
        //创建主界面和游戏界面
        mainPanel=new MainPanel();
        gamePanel=new GamePanel();
        //创建卡片布局管理器
        cardPanel=new Panel();
        cardLayout=new CardLayout();
        cardPanel.setLayout(cardLayout);
        //将主界面和游戏界面添加至管理器面板
        cardPanel.add(mainPanel,"MainPanel");
        cardPanel.add(gamePanel,"GamePanel");
        //设置管理器面板默认显示主界面
        cardLayout.show(cardPanel,"MainPanel");
        //将卡片管理器添加至窗口
        add(cardPanel);
            //
            mainPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //JOptionPane.showMessageDialog(mainPanel,e.getX()+","+e.getY());
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
            //
            gamePanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //JOptionPane.showMessageDialog(gamePanel,e.getX()+","+e.getY());
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
            //
    }

    public static void main(String[] args) {
        //初始化图片和UI
        ImagesValue.initialize();
        UiValue.initialize();

        frame=new Frame();
    }
}
