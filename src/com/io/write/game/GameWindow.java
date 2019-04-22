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
 * ��Ϸ˵����
 * ��Ϸָ�����K.��ʼ P.��ͣ Q.ֹͣ
 * �ɻ�ָ�����space.����  W||up.����  S||down.����  A||left.����  D||right.������
 */
public class GameWindow extends Frame {
    Toolkit tk = Toolkit.getDefaultToolkit();
    // ��Ҷ���
    BackGround bground = new BackGround(0, 0, 500, 700);
    ArrayList<HostilePlane> hostilePlanes = new ArrayList<HostilePlane>();// ����Ϊ��ʼλ��
    // Bullet bullet = new Bullet();
    HostilePlane hostilePlane = new HostilePlane();
    // Image bg = Toolkit.getDefaultToolkit().getImage("src/images/bg.jpg");
    ArrayList<Explode> explodes = new ArrayList<Explode>();
    Image bg = null;
    Image startFace = tk.getImage("src/images/gameStart.png");  //��ʼ����
    Image gameOver = tk.getImage("src/images/gameOver1.jpg");    //����ҳ��
    Image pauseFace = tk.getImage("src/images/gamePause.png");    //��ͣҳ��
    Image gameWin = tk.getImage("src/images/gameWin.png");    //ʤ��ҳ��
    Image bossImg1 = tk.getImage("src/images/Boss1.png"); //Boss1 ͼƬ
    Image bossImg2 = tk.getImage("src/images/Boss2.png"); //Boss1 ͼƬ
    Image bossImg3 = tk.getImage("src/images/Boss3.png"); //Boss1 ͼƬ
    Image boss_1_bullet = tk.getImage("src/images/bossbullet1.png"); //Boss1 �ӵ�ͼƬ
    //����Boss
    Boss boss1 = new Boss(170, 100, 130, 100, bossImg1, 500, boss_1_bullet);
    Music music = new Music();
    int windowWidth = 500; // ���ڿ��
    int windowHeight = 700; // ���ڸ߶�
    int space = 15; // �ɻ��ƶ����
    int top = 35; // �ɻ���������
    int left = 5; // �ɻ���������
    int right = 445; // �ɻ���������
    int footer = 645; // �ɻ���������
    int timeout = 20; // ��ͼ�߳�˯��ʱ
    int cnt = 50; //��ӵл�Ƶ�ʿ��Ʊ���
    int count = 0; //���ֱ���
    int cover = 0; // ��ɱ��Ѫ
    int survive = 8; //���帴�����
    int player_hp_red = 5; //���ӵ����� ����Ѫ����
    int integral_add_0 = 20; //����С����ӻ�����
    int integral_add_1 = 30; //����1��Boss��ӻ�����
    int integral_add_2 = 40; //����2��Boss��ӻ�����
    int integral_add_3 = 50; //����3��Boss��ӻ�����
    int count_v1 = 500;  //boss1 ����ʱ �����ۻ���
    int count_v2 = 1200; //boss1 ����ʱ �����ۻ���
    int count_v3 = 2500; //boss1 ����ʱ �����ۻ���
    boolean isEnemy = false; //Boss���� С���˳�
    boolean isStart = false; // ��Ϸ�Ƿ�ʼ
    boolean isPause = false; //��Ϸ�Ƿ���ͣ
    int upCount_1 = 500;
    Player player = new Player(215, 645, 50, 50, top, footer, left, right); // ����Ϊ��ʼλ��

    public GameWindow() {
        this.setSize(windowWidth, windowHeight);// ���ô���Ĵ�С
        this.setTitle("�ɻ���ս");// ���ô���ı���
        this.setLocation(500, 250);// ���ô����ʼλ��
        this.setResizable(false);// ���ô���Ĵ�С���ɱ�
        this.setVisible(true);// ���ô�����ʾ
        new Thread(new Runnable() { // ��������

            @Override
            public void run() { // �������
                Random random = new Random();
                while (true) {
                    // ��ɱ��Ѫ 50%
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
                    repaint(); // ���ô�����ػ淽��
                    try {
                        Thread.sleep(timeout);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        // Ϊ������Ӽ�������
        this.addWindowListener(new WindowAdapter() {
            @Override // ���û�
            public void windowClosing(WindowEvent e) {
                System.out.println("�رմ���");
                System.exit(0); // +�˳�����
            }
        });
        // Ϊ������Ӽ��̵ļ�������
        this.addKeyListener(new KeyAdapter() {
            // ���û����¼���ʱ���õķ���
            @Override
            public void keyPressed(KeyEvent e) {
                player.space = space;
                System.out.println("����");
                System.out.println(e.getKeyCode());
                int keyCode = e.getKeyCode();
                if (keyCode == 38 || keyCode == e.VK_W) {
                    player.isUp = true;
                    System.out.println("y�᣺" + player.y);
                }
                if (keyCode == 40 || keyCode == e.VK_S) {
                    player.isDown = true;
                    System.out.println("y�᣺" + player.y);
                }
                if (keyCode == 37 || keyCode == e.VK_A) {
                    player.isLeft = true;
                    System.out.println("x�᣺" + player.x);
                }
                if (keyCode == 39 || keyCode == e.VK_D) {
                    player.isRight = true;
                    System.out.println("x�᣺" + player.x);
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
                System.out.println("����");
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
        // System.out.println("���û��Ʒ���");
        // g.setColor(Color.blue);//���û��ʵ���ɫ
        // g.fillRect(x, y, 50, 50); //����һ�����ľ���
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
                        g.setFont(new Font("΢���ź�", 22, 25));
                        g.drawString("HP:", 25, 60);
                        g.setColor(Color.red);
                        g.drawRect(65, 40, 100, 25);
                        g.setColor(Color.yellow);
                        g.fillRect(65, 40, player.hp, 25);
                        g.drawString("���֣�" + count, 25, 100);
                        g.setColor(Color.pink);
                        g.fillRect(65, 40, player.hp, 25);
                        g.drawString("��ɱ��" + cover, 25, 130);
                        g.drawString("ͨ�ط�����" + 2000, 360, 60);
                        g.drawString("" + upCount_1, 370, 90);
                        g.setColor(Color.blue);
                        g.drawString("������" + survive, 25, 160);

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
                            // ������������ӵ�
                            player.bulletList.clear();
                            player.x = -500; //���ɻ��Ƴ���Ϸ����
                        }
                        if (count > count_v1 && boss1.hp > 0) {

                            if (boss1.hp > 0) {
                                boss1.drawMe(g); //����Boss
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
                            // �������е���
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
                                    hp.x = -500; // ������ĵл��Ƴ���Ϸ����
                                    if (hp.bulletList.size() <= 0)
                                        hostilePlanes.remove(i1);
                                }
                            }

                            // �������е����ӵ�
                            for (int i1 = 0; i1 < hostilePlanes.size(); i1++) {
                                HostilePlane hp = hostilePlanes.get(i1);
                                // hp.fire();
                                for (int j = 0; j < hp.bulletList.size(); j++) {
                                    Bullet bullet1 = hp.bulletList.get(j);
                                    // System.out.println("��"+(i+1)+"���ӵ���x:"+bullet.x+",
                                    // y:"+bullet.y);
                                    if (bullet1.hpDown >= 0)
                                        bullet1.drawMeDown(g);
                                    else {
                                        hp.bulletList.remove(j);
                                        System.out.println("��ʧ");
                                        // ����л� �Ƴ��л�

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

                            // ����ӵ���л���ײ
                            hit();
                        }
                    }
                }
            }
        }

    }

    // ����ӵ���л� ��ײ��ⷽ��
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
                    explodes.add(new Explode(hp.x, hp.y)); //��ӱ�ը
                    cover++;  //��ɱ��������
                }
            }
        }


        // �л��ӵ�����ҷɻ� ��ײ���
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


        // ��ҷɻ���л� ��ײ���
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

    //����ӵ���boss ��ײ���
    public void playerHitBoss() {
        for (int i = 0; i < player.bulletList.size(); i++) {
            Bullet bullet = player.bulletList.get(i);
            if (bullet.getRect().intersects(boss1.getRect())) {
                boss1.hp -= 20;
                bullet.hpUp = 0;
                music.boom();
                count += integral_add_1;
                explodes.add(new Explode(bullet.x, bullet.y)); //��ӱ�ը
                cover++;  //��ɱ��������
            }
        }


        // Boss�ӵ�����ҷɻ� ��ײ���
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


    // ˢ�»�����
    @Override
    public void update(Graphics g) {
        // �ж��Ƿ���Ļ��
        if (bg == null) {
            bg = createImage(500, 700);
        }
        // ��ȡbgͼƬ�Ļ���
        Graphics bgGraphics = bg.getGraphics();
        bgGraphics.toString();
        bgGraphics.setColor(Color.white);
        bgGraphics.fillRect(0, 0, 500, 700);
        // ����paint(),����bgͼƬ��ֵ

        paint(bgGraphics);
        g.drawImage(bg, 0, 0, null);

    }

}
