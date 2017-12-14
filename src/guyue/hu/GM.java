package guyue.hu;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GM extends Frame {
	public static final int GAME_WIDTH = 700;
	public static final int GAME_HEIGHT = 600;
	public static final int REGAP = 100;
	private boolean pFlag = true;
	private Yard yard = new Yard();
	private Snake snake = new Snake(this);
	private Egg egg;
	private static Random rnd = new Random();
	private Thread t = new Thread(new RePnt());
	
	public static void main(String[] args) {
		GM gm = new GM();
		gm.launch();
	}

	public void launch() {
		this.setBounds(300, 300, GAME_WIDTH, GAME_HEIGHT);
		this.addWindowListener(new Close());
		this.addKeyListener(new KeyMonitor());
		this.setResizable(false);
		this.addEgg();
		t.start();
		this.setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		g.drawString("length:" + snake.getEggs().size(), 10, 40);
		yard.draw(g);
		egg.draw(g);
		snake.eatEgg(egg);
		snake.draw(g);
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

	public void addEgg() {
		Egg e = new Egg(50+rnd.nextInt(24)*Yard.UNIT, 50+rnd.nextInt(20)*Yard.UNIT);
		while(e.isOverlap(snake)) {
			e = new Egg(50+rnd.nextInt(24)*Yard.UNIT, 50+rnd.nextInt(20)*Yard.UNIT);
		}
		egg = e;
	}
	
	private class Close extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			pFlag = false;
			try {
				t.join();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			System.exit(0);
		}
	}
	
	private class KeyMonitor extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			snake.keyPressed(e);
		}
	}
	
	private class RePnt implements Runnable {
		@Override
		public void run() {
			try	{
				while(pFlag) {
					repaint();
					Thread.sleep(REGAP);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
