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
//	���Լ�����
	public void drawMe(Graphics g){
//		g.setColor(Color.blue);//���û��ʵ���ɫ
//		g.fillRect(this.x, this.y, this.width, this.height); //����һ�����ľ���
		g.drawImage(face, x-5, y, width+=3, height+=3, null);
		hp--;
	}
	

}
