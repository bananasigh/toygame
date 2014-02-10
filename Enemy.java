
public class Enemy {
	public int xPos;
	public int yPos;
	public int speed;
	public boolean direction;
	
	//constructor
	public Enemy(int xPos, int yPos, int speed, boolean direction){
		setXPos(xPos);
		setYPos(yPos);
		setSpeed(speed);
		setDirection(direction);
	}

  //setters
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setYPos(int yPos) {
		this.yPos = yPos;
	}
	
	public void setXPos(int xPos) {
		this.xPos = xPos;
	}
	
	public void setDirection(boolean newDir) {
		this.direction = newDir;
	}
	
	
  //getters
	public int getSpeed() {
		return this.speed;
	}

	public int getYPos() {
		return this.yPos;
	}
	
	public int getXPos() {
		return this.xPos;
	}
	
	public boolean getDirection() {
		return this.direction;
	}

}
