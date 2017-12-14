package guyue.hu;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.JOptionPane;

public class Snake {
	private List<Egg> eggs = new ArrayList<>();
	private Direction dir;
	private Egg head;
	private boolean live;
	private GM gm;
	
	public Snake(GM gm) {
		this.initialize();
		this.gm = gm;
	}
	
	public void initialize() {
		eggs.removeAll(eggs);
		live = true;
		dir = Direction.R;
		eggs.add(new Egg(50+Yard.UNIT*10, 50+Yard.UNIT*10));
		eggs.add(new Egg(50+Yard.UNIT*9, 50+Yard.UNIT*10));
		eggs.add(new Egg(50+Yard.UNIT*8, 50+Yard.UNIT*10));
	}
	
	public List<Egg> getEggs() {
		return eggs;
	}

	public void draw(Graphics g) {
		if(!live) return;
		for(int i=0; i<eggs.size(); i++) {
			Egg eg = eggs.get(i);
			eg.draw(g);
		}
		this.move();
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			dir = Direction.U;
		} else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			dir = Direction.D;
		} else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			dir = Direction.L;
		} else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			dir = Direction.R;
		} else if(e.getKeyCode() == KeyEvent.VK_F2) {
			if(!live) this.initialize();
		}
	}
	
	public void move() {
		this.moving();
		switch(dir) {
		case U :
			eggs.set(0, new Egg(head.getX(), head.getY()-Yard.UNIT));
			break;
		case D :
			eggs.set(0, new Egg(head.getX(), head.getY()+Yard.UNIT));
			break;
		case L :
			eggs.set(0, new Egg(head.getX()-Yard.UNIT, head.getY()));
			break;
		case R :
			eggs.set(0, new Egg(head.getX()+Yard.UNIT, head.getY()));
			break;
		}
		if(this.hitSelf() || this.hitWall()) {
			live = false;
			JOptionPane.showMessageDialog(null, "GAME OVER\r\n按F2重新开始", "", JOptionPane.ERROR_MESSAGE); 
		}
	}
	
	public void moving() {
		head = eggs.get(0);
		for(int i=eggs.size()-1; i>0; i--) {
			eggs.set(i, eggs.get(i-1));
		}
	}
	
	/**
	 * @param e 蛋
	 * @return 迟到了返回true，没吃到返回false
	 */
	public boolean eatEgg(Egg e) {
		if(this.eggs.get(0).getRect().intersects(e.getRect())) {
			this.eggs.add(e);
			gm.addEgg();
			return true;
		}
		return false;
	}
	
	/**
	 * 是否撞到自身的判定
	 * @return 撞到自己返回true，没撞到返回false
	 */
	public boolean hitSelf() {
		for(int i=1; i<eggs.size(); i++) {
			if(eggs.get(0).getRect().intersects(eggs.get(i).getRect())) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * 撞墙判定
	 * @return 撞到了返回true,否则返回false
	 */
	public boolean hitWall() {
		if(eggs.get(0).getX()<50 || eggs.get(0).getX()>50+Yard.WIDTH-Yard.UNIT || 
				eggs.get(0).getY()<50 || eggs.get(0).getY()>50+Yard.HEIGHT-Yard.UNIT) {
			return true;
		}
		return false;
	}
	
}
