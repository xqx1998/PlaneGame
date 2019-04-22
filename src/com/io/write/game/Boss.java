package com.io.write.game;

import java.awt.*;
import java.util.ArrayList;

public class Boss {
    int x;
    int y;
    int width;
    int height;
    int n = 10; //定义开火频率控制变量
    Image img;
    Image imgb;
    int hp;
    ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
    public Boss() {
    }
    public Boss(int x, int y, int width, int height, Image img, int hp, Image imgb) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = img;
        this.hp = hp;
        this.imgb = imgb;
    }
    public void drawMe(Graphics g){
        g.drawImage(img, x, y, width, height, null);
        fire();
        move();
    }
    public void fire(){
        if(n>=10) {
            bulletList.add(new Bullet(x, y));
            n = 0;
        }
        n++;
    }

    public void move(){
        if(hp>0) {
            y++;
            if (y >= 400) {
                y = 400;
                x++;
                if (x >= 400) {
                    x = 0;

                }
            }
        }
    }

    //	获取所在位置矩形
    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }
}
