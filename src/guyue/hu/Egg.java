package guyue.hu;

import java.awt.*;
import java.awt.event.*;

/**
 * @author guyue
 * @date 2017年12月13日 下午9:06:59
 * @class describ:
 */
public class Egg {
	private int x, y;
	private Direction dir;
	
	public Egg(int x, int y, Direction dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
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
		}
	}
	
	public void draw(Graphics g) {
		g.fillRect(x, y, Yard.UNIT, Yard.UNIT);
		this.move();
	}
	
	public void move() {
		switch(dir) {
		case U :
			y -= 25;
			break;
		case D :
			y += 25;
			break;
		case L :
			x -= 25;
			break;
		case R :
			x += 25;
			break;
		case STOP :
			break;
		}
	}
}
