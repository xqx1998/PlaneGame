package com.io.write.game;
import java.awt.*;

public class BossBullet {
    int x;
    int y;
    int width;
    int height;
    int n = 10; //���忪��Ƶ�ʿ��Ʊ���
    Image img;
    int hp;
    public BossBullet() {
    }
    public BossBullet(int x, int y, int width, int height, Image img) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = img;
    }
    //���Լ�
    public void drawMe(Graphics g){
        g.drawImage(img, x, y, null);
        move();
    }
    //�ƶ�
    public void move(){
        y+=6;
    }

    //	��ȡ����λ�þ���
    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);

    }
}
