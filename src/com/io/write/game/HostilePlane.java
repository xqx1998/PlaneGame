package com.io.write.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;

public class HostilePlane {
	int x;
	int y;
	int width = 70;
	int height = 70;
	int cnt = 100;
	int footer;
	int hp = 2;
	Image face = Toolkit.getDefaultToolkit().getImage("src/images/hostile_p1.png");
	//子弹集合
	ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
	
	public HostilePlane() {
	}

	public HostilePlane(int x, int y) {
		this.x = x;
		this.y = y;
	}
//	画自己方法
	public void drawMe(Graphics g){
//		g.setColor(Color.blue);//设置画笔的颜色
//		g.fillRect(this.x, this.y, this.width, this.height); //绘制一个填充的矩形
		g.drawImage(face, x, y, width, height, null);
		fire();
		move();
	}
	
//	开火
	public void fire(){
		//往弹夹填充子弹
		cnt++;
		if(cnt >= 100)
		{
			bulletList.add(new Bullet(x, y));
			cnt = 0;
		}
		
	}
	
	private void move() {
		y = y+1;
		
		if(y>=footer)
			hp = 0;
	}
	
	//获取当前敌人位置的矩形
	public Rectangle getRect() {
		return new Rectangle(x, y, width, height);

	}
	
}
