package com.io.write.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

public class Explode {
	int x;  
	int y;
	int width = 20;
	int height = 20;
	int hp = 5;
	Image face = Toolkit.getDefaultToolkit().getImage("src/images/explode.png");

	public Explode() {
	}

	public Explode(int x, int y) {
		this.x = x;
		this.y = y;
	}
//	画自己方法
	public void drawMe(Graphics g){
//		g.setColor(Color.blue);//设置画笔的颜色
//		g.fillRect(this.x, this.y, this.width, this.height); //绘制一个填充的矩形
		g.drawImage(face, x-5, y, width+=3, height+=3, null);
		hp--;
	}
	

}
