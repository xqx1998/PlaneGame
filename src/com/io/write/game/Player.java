package com.io.write.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;

public class Player {
	int x;  
	int y;
	int width;
	int height;
	int cnt = 10;
	int space;
	int top;
	int left; //飞机左移上限
	int right; //飞机右移上限
	int footer; //飞机下移上限
	boolean isUp = false;
	boolean isDown = false;
	boolean isLeft = false;
	boolean isRight = false;
	boolean isFire = false;
	int hp = 100;
	Image face = Toolkit.getDefaultToolkit().getImage("src/images/player-2.png");
	//子弹集合
	ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
	
	public Player() {
	}

	public Player(int x, int y, int width, int height, int top, int footer, int left, int right) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.top = top;
		this.footer = footer;
		this.left = left;
		this.right = right;
	}
//	画自己方法
	public void drawMe(Graphics g){
//		g.setColor(Color.blue);//设置画笔的颜色
//		g.fillRect(this.x, this.y, this.width, this.height); //绘制一个填充的矩形
		g.drawImage(face, x, y, width, height, null);
		move();
		if(isFire)
			fire();
	}
	
	public void move(){
		if(isUp){
			y -= space;
			if(y <= top)
				y = top;
			System.out.println("y轴："+y);
		}
		if(isDown){
			y += space;
			if(y >= footer)
				y = footer;
			System.out.println("y轴："+y);
		}
		if(isLeft){
			x -= space;
			if(x <= left)
				x = left;
			System.out.println("x轴："+x);
		}
		if(isRight){
			x += space;
			if(x >= right)
				x = right;
			System.out.println("x轴："+x);
		}
		
	}
	
//	开火
	public void fire(){
		//往弹夹填充子弹
		cnt++;
		if(cnt >= 10)
		{
			bulletList.add(new Bullet(x, y));
			cnt = 0;
		}
		
	}
//	获取所在位置矩形
	public Rectangle getRect() {
		return new Rectangle(x, y, width, height);

	}
	
	
}
