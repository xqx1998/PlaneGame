package com.io.write.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * 游戏说明：
 * 游戏指令按键：K.开始 P.暂停 Q.停止
 * 飞机指令按键：space.开火  W||up.上移  S||down.下移  A||left.左移  D||right.右移移
 */
public class GameWindow extends Frame {
    Toolkit tk = Toolkit.getDefaultToolkit();
    // 玩家对象
    BackGround bground = new BackGround(0, 0, 500, 700);
    ArrayList<HostilePlane> hostilePlanes = new ArrayList<HostilePlane>();// 参数为起始位置
    // Bullet bullet = new Bullet();
    HostilePlane hostilePlane = new HostilePlane();
    // Image bg = Toolkit.getDefaultToolkit().getImage("src/images/bg.jpg");
    ArrayList<Explode> explodes = new ArrayList<Explode>();
    Image bg = null;
    Image startFace = tk.getImage("src/images/gameStart.png");  //开始界面
    Image gameOver = tk.getImage("src/images/gameOver1.jpg");    //结束页面
    Image pauseFace = tk.getImage("src/images/gamePause.png");    //暂停页面
    Image gameWin = tk.getImage("src/images/gameWin.png");    //胜利页面
    Image bossImg1 = tk.getImage("src/images/Boss1.png"); //Boss1 图片
    Image bossImg2 = tk.getImage("src/images/Boss2.png"); //Boss1 图片
    Image bossImg3 = tk.getImage("src/images/Boss3.png"); //Boss1 图片
    Image boss_1_bullet = tk.getImage("src/images/bossbullet1.png"); //Boss1 子弹图片
    //定义Boss
    Boss boss1 = new Boss(170, 100, 130, 100, bossImg1, 500, boss_1_bullet);
    Music music = new Music();
    int windowWidth = 500; // 窗口宽度
    int windowHeight = 700; // 窗口高度
    int space = 15; // 飞机移动间距
    int top = 35; // 飞机上移上限
    int left = 5; // 飞机左移上限
    int right = 445; // 飞机右移上限
    int footer = 645; // 飞机下移上限
    int timeout = 20; // 画图线程睡眠时
    int cnt = 50; //添加敌机频率控制变量
    int count = 0; //积分变量
    int cover = 0; // 五杀回血
    int survive = 8; //定义复活次数
    int player_hp_red = 5; //被子弹击中 减少血量数
    int integral_add_0 = 20; //击落小兵添加积分数
    int integral_add_1 = 30; //击落1级Boss添加积分数
    int integral_add_2 = 40; //击落2级Boss添加积分数
    int integral_add_3 = 50; //击落3级Boss添加积分数
    int count_v1 = 500;  //boss1 出现时 积分累积数
    int count_v2 = 1200; //boss1 出现时 积分累积数
    int count_v3 = 2500; //boss1 出现时 积分累积数
    boolean isEnemy = false; //Boss出现 小兵退出
    boolean isStart = false; // 游戏是否开始
    boolean isPause = false; //游戏是否暂停
    int upCount_1 = 500;
    Player player = new Player(215, 645, 50, 50, top, footer, left, right); // 参数为起始位置

    public GameWindow() {
        this.setSize(windowWidth, windowHeight);// 设置窗体的大小
        this.setTitle("飞机大战");// 设置窗体的标题
        this.setLocation(500, 250);// 设置窗体初始位置
        this.setResizable(false);// 设置窗体的大小不可变
        this.setVisible(true);// 设置窗体显示
        new Thread(new Runnable() { // 创建任务

            @Override
            public void run() { // 任务代码
                Random random = new Random();
                while (true) {
                    // 五杀回血 50%
                    if (cover >= 6) {
                        player.hp += ((100 - player.hp) * 0.5);
                        if (player.hp >= 100)
                            player.hp = 100;
                        cover = 0;
                    }
                    if (isStart) {
                        if (cnt >= 50) {
                            if (!isPause) {
//                                if (isEnemy || count < count_v1 || boss1.hp <= 0) {
//                                if ((!isEnemy) || boss1.hp <= 0) {
                                hostilePlanes.add(new HostilePlane(random.nextInt(450), 0));
                                cnt = 0;
//                                    isEnemy = false;
//                                }
                            }
                        }
                        cnt++;
                    } else
                        hostilePlanes.clear();
                    repaint(); // 调用窗体的重绘方法
                    try {
                        Thread.sleep(timeout);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        // 为窗体添加监听对象
        this.addWindowListener(new WindowAdapter() {
            @Override // 当用户
            public void windowClosing(WindowEvent e) {
                System.out.println("关闭窗体");
                System.exit(0); // +退出程序
            }
        });
        // 为窗体添加键盘的监听对象
        this.addKeyListener(new KeyAdapter() {
            // 当用户按下键盘时调用的方法
            @Override
            public void keyPressed(KeyEvent e) {
                player.space = space;
                System.out.println("按键");
                System.out.println(e.getKeyCode());
                int keyCode = e.getKeyCode();
                if (keyCode == 38 || keyCode == e.VK_W) {
                    player.isUp = true;
                    System.out.println("y轴：" + player.y);
                }
                if (keyCode == 40 || keyCode == e.VK_S) {
                    player.isDown = true;
                    System.out.println("y轴：" + player.y);
                }
                if (keyCode == 37 || keyCode == e.VK_A) {
                    player.isLeft = true;
                    System.out.println("x轴：" + player.x);
                }
                if (keyCode == 39 || keyCode == e.VK_D) {
                    player.isRight = true;
                    System.out.println("x轴：" + player.x);
                }
                // if(keyCode == 40 || keyCode == 38 || keyCode == 37 || keyCode
                // == 39)
                // player.fire();
                if (keyCode == 32) {
                    System.out.println("kkk");
//					System.exit(0);
                    player.isFire = true;
                }

                if (survive >= 1)
                    if (keyCode == e.VK_X) {
                        survive--;
                        player.hp = 100;
                        player.x = 215;
                        player.y = 645;
                    }
                if (keyCode == e.VK_K) {
                    isStart = true;
                    isPause = false;
                }
                if (keyCode == e.VK_Q) {
                    isStart = false;
                }
                if (keyCode == e.VK_P) {
                    isPause = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("按键");
                System.out.println(e.getKeyCode());
                int keyCode = e.getKeyCode();
                if (keyCode == 38 || keyCode == e.VK_W) {
                    player.isUp = false;
                }
                if (keyCode == 40 || keyCode == e.VK_S) {
                    player.isDown = false;
                }
                if (keyCode == 37 || keyCode == e.VK_A) {
                    player.isLeft = false;
                }
                if (keyCode == 39 || keyCode == e.VK_D) {
                    player.isRight = false;
                }
                // if(keyCode == 40 || keyCode == 38 || keyCode == 37 || keyCode
                // == 39)
                // player.fire();
                if (keyCode == 32) {
                    System.out.println("ssss");
                    player.isFire = false;
                }
            }

        });

    }

    @Override
    public void paint(Graphics g) {
        // System.out.println("调用绘制方法");
        // g.setColor(Color.blue);//设置画笔的颜色
        // g.fillRect(x, y, 50, 50); //绘制一个填充的矩形
        bground.drawMe(g);
        if (survive == 0 && player.hp <= 0) {
            g.drawImage(gameOver, 0, 270, 500, 300, null);
        } else {
            if (!isStart)
                g.drawImage(startFace, 0, 0, 500, 700, null);
            else {
                if (isPause)
                    g.drawImage(pauseFace, 0, 200, 500, 300, null);
                else {
                    if (count >= upCount_1) {
                        hostilePlanes.clear();
                        g.drawImage(gameWin, 0, 270, 500, 300, null);
                    } else {
                        g.setColor(Color.white);
                        g.setFont(new Font("微软雅黑", 22, 25));
                        g.drawString("HP:", 25, 60);
                        g.setColor(Color.red);
                        g.drawRect(65, 40, 100, 25);
                        g.setColor(Color.yellow);
                        g.fillRect(65, 40, player.hp, 25);
                        g.drawString("积分：" + count, 25, 100);
                        g.setColor(Color.pink);
                        g.fillRect(65, 40, player.hp, 25);
                        g.drawString("连杀：" + cover, 25, 130);
                        g.drawString("通关分数：" + 2000, 360, 60);
                        g.drawString("" + upCount_1, 370, 90);
                        g.setColor(Color.blue);
                        g.drawString("生命：" + survive, 25, 160);

                        if (player.hp > 0) {
                            System.out.println(player.hp);
                            player.drawMe(g);
                            for (int i = 0; i < player.bulletList.size(); i++) {
                                if (player.bulletList.get(i).hpUp != 0) {
                                    player.bulletList.get(i).drawMe(g);
//						player.bulletList.get(i).drawMeX(g);
                                } else {
                                    if (player.bulletList.size() > 0)
                                        player.bulletList.remove(i);

                                }
                            }
                        } else {
                            // 结束清空所有子弹
                            player.bulletList.clear();
                            player.x = -500; //将飞机移出游戏区域
                        }
                        if (count > count_v1 && boss1.hp > 0) {

                            if (boss1.hp > 0) {
                                boss1.drawMe(g); //画出Boss
                                for (int i = 0; i < boss1.bulletList.size(); i++) {
                                    Bullet bullet = boss1.bulletList.get(i);
                                    if (bullet.hpDown != 0)
                                        bullet.drawMeDownBoss1(g);
                                }
                                hostilePlanes.clear();
                            } else {
                                boss1.x = -500;
                                boss1 = null;
                            }

                            playerHitBoss();
                            for (int i = 0; i < explodes.size(); i++) {
                                Explode explode = explodes.get(i);
                                if (explode.hp > 0)
                                    explode.drawMe(g);
                                else
                                    explodes.remove(i);
                            }

                        } else {
                            // 遍历所有敌人
                            for (int i1 = 0; i1 < hostilePlanes.size(); i1++) {
                                HostilePlane hp = hostilePlanes.get(i1);
                                hp.footer = footer;
                                if (hp.hp != 0) {
                                    hp.drawMe(g);
                                    for (int j = 0; j < hp.bulletList.size(); j++) {
                                        Bullet bullet1 = hp.bulletList.get(j);
                                        if (bullet1.hpDown != 0)
                                            bullet1.drawMeDown(g);
                                        else {
                                            hp.bulletList.remove(j);
                                        }
                                    }
                                } else {
                                    hp.x = -500; // 将击落的敌机移出游戏区域
                                    if (hp.bulletList.size() <= 0)
                                        hostilePlanes.remove(i1);
                                }
                            }

                            // 遍历所有敌人子弹
                            for (int i1 = 0; i1 < hostilePlanes.size(); i1++) {
                                HostilePlane hp = hostilePlanes.get(i1);
                                // hp.fire();
                                for (int j = 0; j < hp.bulletList.size(); j++) {
                                    Bullet bullet1 = hp.bulletList.get(j);
                                    // System.out.println("第"+(i+1)+"颗子弹：x:"+bullet.x+",
                                    // y:"+bullet.y);
                                    if (bullet1.hpDown >= 0)
                                        bullet1.drawMeDown(g);
                                    else {
                                        hp.bulletList.remove(j);
                                        System.out.println("消失");
                                        // 击落敌机 移除敌机

                                        // System.exit(0);
                                    }
                                }
                            }

                            for (int i = 0; i < explodes.size(); i++) {
                                Explode explode = explodes.get(i);
                                if (explode.hp > 0)
                                    explode.drawMe(g);
                                else
                                    explodes.remove(i);
                            }

                            // 玩家子弹与敌机碰撞
                            hit();
                        }
                    }
                }
            }
        }

    }

    // 玩家子弹与敌机 碰撞检测方法
    public void hit() {
        for (int i = 0; i < player.bulletList.size(); i++) {
            Bullet bullet = player.bulletList.get(i);
            for (int j = 0; j < hostilePlanes.size(); j++) {
                HostilePlane hp = hostilePlanes.get(j);
                if (bullet.getRect().intersects(hp.getRect())) {
                    bullet.hpUp = 0;
                    hp.hp = 0;
                    music.boom();
                    count += integral_add_0;
                    explodes.add(new Explode(hp.x, hp.y)); //添加爆炸
                    cover++;  //连杀次数增加
                }
            }
        }


        // 敌机子弹与玩家飞机 碰撞检测
        for (int i = 0; i < hostilePlanes.size(); i++) {
            HostilePlane hostile = hostilePlanes.get(i);
            for (int j = 0; j < hostile.bulletList.size(); j++) {
                Bullet bullet = hostile.bulletList.get(j);
                if (bullet.getRect().intersects(player.getRect())) {
                    bullet.hpDown = 0;
                    player.hp -= 2;
                    music.boom();
                    System.out.println(player.hp);
                    explodes.add(new Explode(player.x, player.y));
                    cover = 0;
                }
            }
        }


        // 玩家飞机与敌机 碰撞检测
        for (int i = 0; i < hostilePlanes.size(); i++) {
            HostilePlane hostile = hostilePlanes.get(i);
            if (hostile.getRect().intersects(player.getRect())) {
                hostile.hp--;
                player.hp -= player_hp_red;
                System.out.println(player.hp);
                cover = 0;
            }
        }
    }

    //玩家子弹与boss 碰撞检测
    public void playerHitBoss() {
        for (int i = 0; i < player.bulletList.size(); i++) {
            Bullet bullet = player.bulletList.get(i);
            if (bullet.getRect().intersects(boss1.getRect())) {
                boss1.hp -= 20;
                bullet.hpUp = 0;
                music.boom();
                count += integral_add_1;
                explodes.add(new Explode(bullet.x, bullet.y)); //添加爆炸
                cover++;  //连杀次数增加
            }
        }


        // Boss子弹与玩家飞机 碰撞检测
        for (int j = 0; j < boss1.bulletList.size(); j++) {
            Bullet bullet = boss1.bulletList.get(j);
            if (bullet.getRect().intersects(player.getRect())) {
                bullet.hpDown = 0;
                player.hp -= 2;
                music.boom();
                System.out.println(player.hp);
                explodes.add(new Explode(player.x, player.y));
                cover = 0;
            }
        }
    }


    // 刷新缓冲流
    @Override
    public void update(Graphics g) {
        // 判断是否有幕布
        if (bg == null) {
            bg = createImage(500, 700);
        }
        // 获取bg图片的画笔
        Graphics bgGraphics = bg.getGraphics();
        bgGraphics.toString();
        bgGraphics.setColor(Color.white);
        bgGraphics.fillRect(0, 0, 500, 700);
        // 条用paint(),传入bg图片的值

        paint(bgGraphics);
        g.drawImage(bg, 0, 0, null);

    }

}
