package guyue.hu;

import java.awt.*;

/**
 * 园子类
 * @author guyue
 * @date 2017年12月13日 下午8:53:07
 * @class describ:
 */
public class Yard {
	public static final int WIDTH = 600;
	public static final int HEIGHT = 500;
	public static final int GAP = 50;
	public static final int UNIT = 10;
	
	public void draw(Graphics g) {
		g.drawRect(GAP, GAP, WIDTH, HEIGHT);
		for(int i=1; i<HEIGHT/UNIT; i++) {
			g.drawLine(GAP, GAP+UNIT*i, GAP+WIDTH, GAP+UNIT*i);
		}
		for(int i=0; i<WIDTH/UNIT; i++) {
			g.drawLine(GAP+UNIT*i, GAP, GAP+UNIT*i, GAP+HEIGHT);
		}
	}
}
