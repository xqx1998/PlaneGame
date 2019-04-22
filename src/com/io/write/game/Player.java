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
	int left; //�ɻ���������
	int right; //�ɻ���������
	int footer; //�ɻ���������
	boolean isUp = false;
	boolean isDown = false;
	boolean isLeft = false;
	boolean isRight = false;
	boolean isFire = false;
	int hp = 100;
	Image face = Toolkit.getDefaultToolkit().getImage("src/images/player-2.png");
	//�ӵ�����
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
//	���Լ�����
	public void drawMe(Graphics g){
//		g.setColor(Color.blue);//���û��ʵ���ɫ
//		g.fillRect(this.x, this.y, this.width, this.height); //����һ�����ľ���
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
			System.out.println("y�᣺"+y);
		}
		if(isDown){
			y += space;
			if(y >= footer)
				y = footer;
			System.out.println("y�᣺"+y);
		}
		if(isLeft){
			x -= space;
			if(x <= left)
				x = left;
			System.out.println("x�᣺"+x);
		}
		if(isRight){
			x += space;
			if(x >= right)
				x = right;
			System.out.println("x�᣺"+x);
		}
		
	}
	
//	����
	public void fire(){
		//����������ӵ�
		cnt++;
		if(cnt >= 10)
		{
			bulletList.add(new Bullet(x, y));
			cnt = 0;
		}
		
	}
//	��ȡ����λ�þ���
	public Rectangle getRect() {
		return new Rectangle(x, y, width, height);

	}
	
	
}
