import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Stack;

/**
 * DodgeGame
 * @author Surabi Kondapaka and Hannah Tsai
 */

public class DodgeGame extends Canvas implements Runnable{

	public int width = 30;
	public int height = 30;

	public int xPos = 500;
	public int yPos = 500;

	public static Thread myThread;

	Stack<Integer> redX = new Stack();
	Stack<Integer> redY = new Stack();
	Stack<Integer> redSpeed = new Stack();

	Stack<Integer> blueX = new Stack();
	Stack<Integer> blueY = new Stack();
	Stack<Integer> blueSpeed = new Stack();	

	int MaxSpeed = 30;

	/**
	 * This main method instantiates a frame, canvas, and adds a window listener to close the window.
	 * It also starts the thread that will run for the rest of the program.
	 * @param args not used
	 */
	public static void main(String[] args){
		Frame myF = new Frame();
		DodgeGame myC = new DodgeGame();

		myF.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});

		myF.add(myC);	
		myF.setSize(1000,1000);
		myF.setVisible(true);

		myThread.start();
	}


	public DodgeGame(){
		myThread = new Thread(this);

		addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e)
			{
				switch (e.getKeyChar()) {
				case 'a':
					xPos = xPos - 30;
					break;
				case 's':
					yPos = yPos + 30;
					break;
				case 'd':
					xPos = xPos + 30;
					break;
				case 'w':
					yPos = yPos - 30;
					break;
				case ' ':
					xPos = 500;
					yPos = 500;
					repaint();
					break;
				case 'q':
					System.exit(0);
					break;
				}
			}
		});

	}

	public void update(Graphics g){
		paint(g);
	}
	boolean draw = true;
	public void paint(Graphics g){
		g.clearRect(0, 0, 1000, 1000);

		g.setColor(Color.magenta);
		g.fillRect(xPos, yPos, width,height);	
		g.setColor(Color.black);
		g.fillRect(600, 500, width,height);	

		makeEnemies(g);

		if(draw){
			drawRed(g);
			drawBlue(g);
			draw = !draw;
		}
		if(!(xPos == 600 && yPos == 500)){
			g.setColor(Color.black);
			g.fillRect(600, 500, width,height);	
		}

	}

	public void drawRed(Graphics g){
		int initX=redX.peek();
		g.setColor(Color.red);
		int x =0;
		int y =0;
		while(redX.peek()!=initX+3){			
			if(!redX.isEmpty()){
				x=redX.pop();
			}
			if(!redY.isEmpty()){
				y=redY.pop();
			}
			int speed = redSpeed.pop();//TODO: Add method that adds to speed stack
			int xPos = x+speed;
			int yPos = speed;
			g.setColor(Color.red);
			g.fillRect(xPos, yPos, width, height);
			
			redX.push(xPos);
			redY.push(yPos);
	//		redSpeed.push((speed));
		//	g.setColor(Color.white);
			//g.fillRect(xPos, yPos, width, height);
			
		}
	}

	public void drawBlue(Graphics g){
		int initX = blueX.peek();
		g.setColor(Color.blue);
		while(blueX.peek()!=initX+3){
			int x=blueX.pop();
			int y=blueY.pop();
			int speed = blueSpeed.pop();
			g.fillRect(x+speed, y+speed, width, height);
			blueX.push(x+speed);
			blueY.push(y+speed);
		}
	}

	public void makeEnemies(Graphics g){
		redX.add((int)(Math.random()*900));
		redY.add((int)(Math.random()*900));
		redSpeed.add((int)(Math.random()*MaxSpeed));
		
		blueX.add((int)(Math.random()*900));
		blueY.add((int)(Math.random()*900));
		blueSpeed.add((int)(Math.random()*MaxSpeed));
	}


	/**
	 * This method runs the current thread, but pauses for 100 ms every cycle.
	 */
	public void run() {
		while(Thread.currentThread() == myThread){
			repaint();
			try {
				Thread.sleep(60);
			} catch (InterruptedException e) {
				System.exit(0);
			}
		}	
	}

}
