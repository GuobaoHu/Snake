package guyue.hu;

import java.awt.*;
import java.awt.event.*;

public class GM extends Frame {
	public static final int GAME_WIDTH = 700;
	public static final int GAME_HEIGHT = 600;
	public static final int WIDTH = 600;
	public static final int HEIGHT = 500;
	public static final int GAP = 50;
	public static final int UNIT = 25;
	private Rectangle Rect;
	
	public static void main(String[] args) {
		GM gm = new GM();
		gm.launch();
	}
	
	public void launch() {
		this.setBounds(300, 300, GAME_WIDTH, GAME_HEIGHT);
		this.addWindowListener(new Close());
		this.setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		g.drawRect(GAP, GAP, WIDTH, HEIGHT);
		for(int i=1; i<HEIGHT/UNIT; i++) {
			g.drawLine(GAP, GAP+UNIT*i, GAP+WIDTH, GAP+UNIT*i);
		}
		for(int i=0; i<WIDTH/UNIT; i++) {
			g.drawLine(GAP+UNIT*i, GAP, GAP+UNIT*i, GAP+HEIGHT);
		}
	}
	
	private class Close extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}
}
