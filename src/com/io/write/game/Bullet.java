package com.io.write.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Bullet {
	int x;
	int y;
	int width = 10;
	int height = 25;
	int hpUp = 1;
	int hpDown = 1;
	int cnt = 21;
	Image face = Toolkit.getDefaultToolkit().getImage("src/images/bullet1.png");
	Image faceHostile = Toolkit.getDefaultToolkit().getImage("src/images/bullet_1.png");
	Image faceBoss1 = Toolkit.getDefaultToolkit().getImage("src/images/bullet_1.png");
	public Bullet() {
	}

	public Bullet(int x, int y) {
		this.x = x+20;
		this.y = y;
	}
	
	public void drawMe(Graphics g){
//		g.setColor(Color.blue);//���û��ʵ���ɫ
//		g.fillRect(this.x, this.y, this.width, this.height); //����һ�����ľ���
		g.drawImage(face, x-9, y, width, height, null);
		g.drawImage(face, x+9, y, width, height, null);
		move();
		
		
	}
	
//	public void drawMeX(Graphics g){
//		System.exit(0);
//		g.setColor(Color.blue);//���û��ʵ���ɫ
//		g.fillRect(this.x, this.y, this.width, this.height); //����һ�����ľ���
//		g.drawImage(face, x+9, y, width, height, null);
//		moveX();
//	}
//
//	public void moveX() {
//		if(y>2){
//			y = y-15;
//			x = x+3;
//		}else
//			hpUp = 0;
//
//	}
	
	public void move() {
		if(y>2){
			y = y-15;
		}else
			hpUp = 0;
		
	}
//	��
	public void drawMeDown(Graphics g){
//		g.setColor(Color.blue);//���û��ʵ���ɫ
//		g.fillRect(this.x, this.y, this.width, this.height); //����һ�����ľ���
		g.drawImage(faceHostile, x+10, y+40, width, height, null);
		moveDown();
		
	}
//	�����ƶ�
	public void moveDown() {
		if(y<700)
			y = y+3;
		else
			hpDown = 0;
		
	}
	public void drawMeDownBoss1(Graphics g){
//		g.setColor(Color.blue);//���û��ʵ���ɫ
//		g.fillRect(this.x, this.y, this.width, this.height); //����һ�����ľ���
		g.drawImage(faceHostile, x+25, y+40, width, height, null);
		g.drawImage(faceHostile, x+55, y+40, width, height, null);
		moveDownBoss1();

	}
//	�����ƶ�
	public void moveDownBoss1() {
		if(cnt>5) {
			if (y < 700) {
				y = y + 100;
//				System.exit(0);
			}
			else
				hpDown = 0;
			cnt = 0;
		}
		cnt ++;
	}
//	��ȡ����λ�þ���
	public Rectangle getRect() {
		return new Rectangle(x, y, width, height);

	}
	
}
