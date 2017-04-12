/**
 * 
 */
package presentacion;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * @author blackphantom
 *
 */
public class Player  {
	public int x,y;
	public int cx,cy;
	private ImageIcon image;
	private Image a;
	private int size;
	private String name; 
	public Player(int posx, int posy,int tam,int cx,int cy){
		size = tam;
		image = new ImageIcon("resources/qbert.png");
		a = image.getImage().getScaledInstance(size*3, size*3, Image.SCALE_DEFAULT);
		x=posx-(size*3)/2;
		y=posy-size*3 ;
		this.cx=cx;
		this.cy=cy;
	}
	
	public void move(char a,char b){
		int velx=size*2;
		int vely=size*3;
		if(a=='L') velx*=-1;
		if(b=='U') vely*=-1;
		x+=velx;
		y+=vely;
		if(cy%2!=0){
			cx=(a=='L')?cx:cx+1;
		}
		else{
			cx=(a=='R')?cx:cx-1;
		}
		cy=(b=='U')?cy-1:cy+1;
	}

	public Image getDraw(){
		return a;
	}


}
