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
	
	public Egg(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void draw(Graphics g) {
		g.fillRect(x, y, Yard.UNIT, Yard.UNIT);
	}
	
	public Rectangle getRect() {
		return new Rectangle(x, y, Yard.UNIT, Yard.UNIT);
	}
	
	public boolean isOverlap(Snake s) {
		for(int i=0; i<s.getEggs().size(); i++) {
			Egg e = s.getEggs().get(i);
			if(e.getRect().intersects(this.getRect())) {
				return true;
			}
		}
		return false;
	}
	
}
