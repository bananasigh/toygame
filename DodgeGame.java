import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * DodgeGame
 * @author Surabi Kondapaka and Hannah Tsai
 */

public class DodgeGame extends Canvas implements Runnable{

	public int width = 30;
	public int height = 30;

	public int xPos = 500;
	public int yPos = 500;
	public ArrayList<Enemy> redEnemies= new ArrayList<Enemy>();
	public ArrayList<Enemy> blueEnemies= new ArrayList<Enemy>();

	public double maxSpeed=20;

	private Enemy initRedEnemy;

	private int count=0;
	private Enemy redEnemy;
	private Enemy blueEnemy;

	public static Thread myThread;

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

		//the initial red enemy guy
		initRedEnemy = new Enemy((int)(300), (int)(300), (int)(Math.random()*maxSpeed), true);
		redEnemies.add(initRedEnemy);
		
	}

	public void update(Graphics g){
		paint(g);
	}

	public void paint(Graphics g){
		g.clearRect(0, 0, 1000, 1000);

		//player's rectangle guy
		g.setColor(Color.magenta);
		g.fillRect(xPos, yPos, width,height);	


		//draws red horizontal enemies
		for (int i = 0; i<redEnemies.size(); i++){
			redEnemy = redEnemies.get(i);

			//clears past position
			g.setColor(Color.white);
			g.fillRect(redEnemy.getXPos(), redEnemy.getYPos(), width,height);
			
			//if it reaches a border it switches direction
			if(redEnemy.getXPos()>=930 || redEnemy.getXPos()<=0){
				redEnemy.setDirection(!redEnemy.getDirection());
			}
			if (redEnemy.getDirection()){
				redEnemy.setXPos(redEnemy.getXPos()+redEnemy.getSpeed()); //moves the box in the current direction
			}
			else {
				redEnemy.setXPos(redEnemy.getXPos()-redEnemy.getSpeed());
			}

			//draws the box at the new position
			g.setColor(Color.red);
			g.fillRect(redEnemy.getXPos(), redEnemy.getYPos(), width,height);	

		}

		
		//draws blue vertical enemies
		for (int j = 0; j<blueEnemies.size(); j++){
			blueEnemy = blueEnemies.get(j);

			g.setColor(Color.white);
			g.fillRect(blueEnemy.getXPos(), blueEnemy.getYPos(), width,height);
	
			if(blueEnemy.getYPos()>=930 || blueEnemy.getYPos()<=0){
				blueEnemy.setDirection(!blueEnemy.getDirection());
			}
			if (blueEnemy.getDirection()){
				blueEnemy.setYPos(blueEnemy.getYPos()+blueEnemy.getSpeed());
			}
			else {
				blueEnemy.setYPos(blueEnemy.getYPos()-blueEnemy.getSpeed());
			}
	
			g.setColor(Color.blue);
			g.fillRect(blueEnemy.getXPos(), blueEnemy.getYPos(), width,height);		

		}
		

		//creates new enemy every 50 iterations (alternates whether red or blue enemy is born)
		if((count+1)%50 ==0 && (count+1)%100 != 0){
			blueEnemies.add(new Enemy((int)(Math.random()*900), (int)(Math.random()*900), (int)(Math.random()*maxSpeed), true));
		}
		if((count+1)%100 ==0){
			redEnemies.add(new Enemy((int)(Math.random()*900), (int)(Math.random()*900), (int)(Math.random()*maxSpeed), true));
		}
		count ++;

		maxSpeed= maxSpeed+.01;
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





//OLD CODE -- Just in Case!
//g.setColor(Color.white);
//g.fillRect(blueEnemy.getXPos(), blueEnemy.getYPos(), width,height);
//
//if(blueEnemy.getYPos()>=930 || blueEnemy.getYPos()<=30){
//	direction = !direction;
//}
//if (direction){
//	blueEnemy.setYPos(blueEnemy.getYPos()+blueEnemy.getSpeed());
//}
//else {
//	blueEnemy.setYPos(blueEnemy.getYPos()-blueEnemy.getSpeed());
//}
//
//g.setColor(Color.blue);
//g.fillRect(blueEnemy.getXPos(), blueEnemy.getYPos(), width,height);	
