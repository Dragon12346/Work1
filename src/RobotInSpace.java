	import java.awt.KeyEventDispatcher; 
	import java.awt.KeyboardFocusManager; 
	import java.awt.event.KeyEvent; 
	import java.io.File;
	import javax.sound.sampled.AudioInputStream; 
	import javax.sound.sampled.AudioSystem; 
	import javax.sound.sampled.Clip;

    import org.jointheleague.graphical.robot.Robot;
	
	public class RobotInSpace implements KeyEventDispatcher {
		
	Robot rob = new Robot();
	

	private void moveRobot(int keyPressed) {
		rob.setSpeed(30);
		System.out.println(keyPressed);

		if (keyPressed == 37){
			rob.setAngle(270);
			rob.move(5);
			
		} 
		if (keyPressed == 38){
			rob.setAngle(0);
			rob.move(5);
			
		} 
		if (keyPressed == 39){
			rob.setAngle(90);
			rob.move(5);
			
		} 
		if (keyPressed == 40){
			rob.setAngle(180);
			rob.move(5);
			
		} 
	}

	private void checkIfR2D2Found() throws Exception {
	    int robotLocationX = rob.getX();
	    int robotLocationY = rob.getY();

		if (robotLocationX <= 7300 && robotLocationX >= 720 && robotLocationY >= 150 && robotLocationY <= 160)
	        playEureka();
	}

	public static void main(String[] args) {
	    new RobotInSpace().controlTheRobot();
	}


	private void controlTheRobot() {
	    KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);
	    rob.setWindowImage("planet.jpg");
	    rob.penUp();
	    rob.setSpeed(10);
	}

	public boolean dispatchKeyEvent(KeyEvent e) {
	    if (e.getID() == KeyEvent.KEY_PRESSED) {
	        moveRobot(e.getKeyCode());
	        try {
	            checkIfR2D2Found();
	        } catch (Exception exception) {
	        }
	    }
	    return false;
	}

	public void playEureka() {
	    System.out.println("EUREKA!");
	    try {
	   		 Clip clip = AudioSystem.getClip();
	   		 clip.open(AudioSystem.getAudioInputStream(RobotInSpace.class.getResource("r2d2-eureka.wav")));
	   		 clip.start();
	   		 Thread.sleep(6000);
	   	} catch (Exception ex) {
	     	ex.printStackTrace();
	   	}
	}
	}
