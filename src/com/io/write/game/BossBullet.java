package com.io.write.game;
import java.awt.*;

public class BossBullet {
    int x;
    int y;
    int width;
    int height;
    int n = 10; //定义开火频率控制变量
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
    //画自己
    public void drawMe(Graphics g){
        g.drawImage(img, x, y, null);
        move();
    }
    //移动
    public void move(){
        y+=6;
    }

    //	获取所在位置矩形
    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);

    }
}
