import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.util.Date;

import org.lwjgl.input.Keyboard;

import king.jaiden.util.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.input.Keyboard.*;

public class RollCalMain extends ApplicationWindow {
	SensorData[] RSD;
	SensorData currentSD;
	double incTol, htfTol, azmTol, incTarget, htfTarget, azmTarget,rate;
	Date date;
	GUIContainer gui;
	boolean looping;
	CalSequence calseq;
	
	public RollCalMain(IntCoord intCoord, int i, String string, boolean b,
			int twoDimensional) {
		super(intCoord,i,string,b,twoDimensional);
	}
	public static void main(String[] args) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		new RollCalMain(new IntCoord((int)screenSize.getWidth(),(int)screenSize.getHeight()), 90, "Roll and Calibration", true, ApplicationWindow.TWO_DIMENSIONAL);
	}
	public void init() {
		date = new Date();
		setupTolerance();
		setupRawSensorData();
		gui = new GUIContainer(this);
		calseq = new CalSequence();
		setupTargets();
		TextUtil.getInstance().setAlignment(TextUtil.CENTER);
		looping = true;
		rate = 0.5;
	}
	public void setupTolerance(){
		incTol = 0.2;
		htfTol = 2.0;
		azmTol = 0.5;
	}
	public void setupTargets(){
		CalStep cs = calseq.next();
		incTarget = cs.inc;
		htfTarget = cs.htf;
		azmTarget = cs.azi;
		gui.updateTargets();
	}
	public void setupRawSensorData(){
		RSD = new SensorData[5];
		for(int i = 0; i < RSD.length; i++){
			RSD[i] = new SensorData();
		}
		
		currentSD = new SensorData();
		currentSD.setGx(0);
		currentSD.setGy(1);
		currentSD.setGz(0);
		currentSD.setGtot(1);
		RSD[0] = currentSD;	
	}
	public void tick(){
		date = new Date();
		if(looping){
			currentTick++;
			if(currentTick%(60*rate)==0){
				read();			
			}
		}
		
	}
	public boolean allWithinTolerance(){
		if(!(currentSD.getInc()>incTarget+incTol||currentSD.getInc()<incTarget-incTol)){
			if(!(currentSD.getHtf()>htfTarget+htfTol||currentSD.getHtf()<htfTarget-htfTol)){
				if(!(currentSD.getAzm()>azmTarget+azmTol||currentSD.getAzm()<azmTarget-azmTol)){
					return true;
				}
			}
		}
		return false;
	}
	public void log(){
		if(allWithinTolerance())
			setupTargets();
	}
	public void read(){
		double z = azmTarget;
		z -= currentSD.getAzm();
		
		if(!(currentSD.getAzm()>azmTarget+azmTol||currentSD.getAzm()<azmTarget-azmTol)){

			double y = incTarget;
			y -= currentSD.getInc();
			
			if(!(currentSD.getInc()>incTarget+incTol||currentSD.getInc()<incTarget-incTol)){

				double x = htfTarget;
				x -= currentSD.getHtf();
				
				currentSD.setHtf(x/5+currentSD.getHtf());
				
			}
			currentSD.setInc(y/5+currentSD.getInc());				
		}
		currentSD.setAzm(z/5+currentSD.getAzm());
		
		if(currentSD.getInc()>180){
			currentSD.setInc(currentSD.getInc()-360);
		}if(currentSD.getHtf()>180){
			currentSD.setHtf(currentSD.getHtf()-360);
		}if(currentSD.getAzm()>180){
			currentSD.setAzm(currentSD.getAzm()-360);
		}
		
		gui.update();
	}
	public void loop(){
		if(looping){
			looping = false;
			gui.pipeView.setEasing(false);
		}else{
			looping = true;
			gui.pipeView.setEasing(true);
		}
	}
	public void changeRate(){
		rate = MyOptionPane.getInstance().showDoubleInputDialog("Rate: ");
	}
	public void modelRotate(){
		if(Keyboard.isKeyDown(KEY_LEFT)){
			gui.pipeView.yrot += 1;
			gui.pipeView2.yrot += 1;
		}
		if(Keyboard.isKeyDown(KEY_RIGHT)){
			gui.pipeView.yrot -= 1;
			gui.pipeView2.yrot -= 1;
		}
	}
	public void input(){
		modelRotate();
		while(Keyboard.next()){
			if(Keyboard.getEventKeyState()){
				switch(Keyboard.getEventKey()){
				case KEY_F1:
					log();
					break;
				case KEY_F2:
					read();
					break;
				case KEY_F3:
					loop();
					break;
				case KEY_F4:
					changeRate();
					break;
				}
			}
		}
	}
	
	public void draw(){
		super.draw();
		gui.draw();
	}
}
