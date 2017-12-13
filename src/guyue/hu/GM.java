package guyue.hu;

import java.awt.*;
import java.awt.event.*;

public class GM extends Frame {
	public static final int GAME_WIDTH = 700;
	public static final int GAME_HEIGHT = 600;
	private boolean pFlag = true;
	private Yard yard = new Yard();
	private Egg egg = new Egg(50+Yard.UNIT*10, 50+Yard.UNIT*10, Direction.STOP);

	public static void main(String[] args) {
		GM gm = new GM();
		gm.launch();
	}

	public void launch() {
		this.setBounds(300, 300, GAME_WIDTH, GAME_HEIGHT);
		this.addWindowListener(new Close());
		this.addKeyListener(new KeyMonitor());
		this.setResizable(false);
		new Thread(new RePnt()).start();
		this.setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		yard.draw(g);
		egg.draw(g);
	}

	/**
	 * 双缓冲解决闪烁问题
	 */
	@Override
	public void update(Graphics g) {
		Image img = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		Graphics ig = img.getGraphics();
		Color c = ig.getColor();
		ig.setColor(Color.WHITE);
		ig.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		ig.setColor(c);
		this.paint(ig);
		g.drawImage(img, 0, 0, null);
	}

	private class Close extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}
	
	private class KeyMonitor extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			egg.keyPressed(e);
		}
	}
	
	private class RePnt implements Runnable {
		@Override
		public void run() {
			try	{
				while(pFlag) {
					repaint();
					Thread.sleep(300);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
