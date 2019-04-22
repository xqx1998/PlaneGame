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
	//�ӵ�����
	ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
	
	public HostilePlane() {
	}

	public HostilePlane(int x, int y) {
		this.x = x;
		this.y = y;
	}
//	���Լ�����
	public void drawMe(Graphics g){
//		g.setColor(Color.blue);//���û��ʵ���ɫ
//		g.fillRect(this.x, this.y, this.width, this.height); //����һ�����ľ���
		g.drawImage(face, x, y, width, height, null);
		fire();
		move();
	}
	
//	����
	public void fire(){
		//����������ӵ�
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
	
	//��ȡ��ǰ����λ�õľ���
	public Rectangle getRect() {
		return new Rectangle(x, y, width, height);

	}
	
}
