package com.io.write.game;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

public class Music {
 static	File bgm = new File("src/images/bgm.wav");
 static	 File boom = new File("src/images/boom.wav");
//��Ƶ������
AudioClip bgmau = null;
AudioClip boomau = null;
 
public Music() {
	
		 try {
		boomau =  Applet.newAudioClip(boom.toURL());
	  	bgmau =  Applet.newAudioClip(bgm.toURL());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
}
  	 public  void playBGM(){
  		
  		bgmau.loop();//ѭ������
  		
  	 }
  	 
  	 public  void boom(){
		boomau.play();//ѭ������
  	 }
  	 
	
}
