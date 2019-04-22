package com.io.write.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class BackGround {
	int x;
	int y;
	int y1 = 0;
	int y2 = -700;
	int width;
	int height;
	int space = 3; //背景图片移动间距
	Image face = Toolkit.getDefaultToolkit().getImage("src/images/bg.png");
	public BackGround() {
	}

	public BackGround(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void drawMe(Graphics g){
//		g.setColor(Color.blue);//设置画笔的颜色
//		g.fillRect(this.x, this.y, this.width, this.height); //绘制一个填充的矩形
		g.drawImage(face, x, y1, width, height, null);
		g.drawImage(face, x, y2, width, height, null);
		move();
	}
	
	public void move(){
		y1+=space;
		if(y1>=700){
			y1 = 0;
		}
		y2+=space;
		if(y2>=0){
			y2 = -700;
		}
		
	}
	
	
}
