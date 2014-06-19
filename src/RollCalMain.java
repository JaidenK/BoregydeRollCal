import java.awt.Dimension;
import java.awt.Toolkit;

import king.jaiden.util.*;
import static org.lwjgl.opengl.GL11.*;

public class RollCalMain extends ApplicationWindow {
	
	Menu RawSensorDataDisplay;
	Label[] label;
	Label RSDLabel,incLabel,htfLabel,azmLabel,
				  incTargetLabel,htfTargetLabel,azmTargetLabel,
				  incTolLabel,htfTolLabel,azmTolLabel;
	SensorData[] RSD;
	SensorData currentSD;
	Menu incReading, htfReading, azmReading;
	
	double incTol, htfTol, azmTol, incTarget, htfTarget, azmTarget;
	
	
	public RollCalMain(IntCoord intCoord, int i, String string, boolean b,
			int twoDimensional) {
		super(intCoord,i,string,b,twoDimensional);
	}

	public static void main(String[] args) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		new RollCalMain(new IntCoord((int)screenSize.getWidth(),(int)screenSize.getHeight()), 90, "Roll and Calibration", true, ApplicationWindow.TWO_DIMENSIONAL);
	}

	@Override
	public void init() {
		setupTolerance();
		setupRSDDisplay();
		setupIncReading();
		setupHtfReading();
		setupAzmReading();
		
		TextUtil.getInstance().setAlignment(TextUtil.CENTER);
	}

	public void setupTolerance(){
		incTol = 2.0;
		htfTol = 0.2;
		azmTol = 0.5;
	}
	
	public void setupTargets(){
		incTarget = Math.random()*360-180;
		htfTarget = Math.random()*360-180;
		azmTarget = Math.random()*360-180;
	}
	
	public void setupIncReading(){
		incReading = new Menu();
		incReading.setColor(Color.CLEAR);
		incReading.setDimensions(new Coord(windowDimensions.getX()/3,100));
		incReading.setVisible(true);
		
		Label incLabel0 = new Label("inclination",new Coord(1,1));
		incLabel0.setDimensions(new Coord(10,30));
		incLabel0.setSize(Size.MATCH_PARENT_WIDTH);

		incLabel = new Label(" 000.000",new Coord(1,1));
		incLabel.setDimensions(new Coord(10,70));
		incLabel.setSize(Size.MATCH_PARENT_WIDTH);
		
		incTargetLabel = new Label("tol.: 0.0",new Coord(1,1));
		
		incReading.add(incLabel0);
		incReading.add(incLabel);
	}
	public void setupHtfReading(){
		htfReading = new Menu();
		htfReading.setColor(Color.CLEAR);
		htfReading.setDimensions(new Coord(windowDimensions.getX()/3,100));
		htfReading.setVisible(true);
		
		Label htfLabel0 = new Label("tool face",new Coord(1,1));
		htfLabel0.setDimensions(new Coord(10,30));
		htfLabel0.setSize(Size.MATCH_PARENT_WIDTH);

		htfLabel = new Label(" 000.000",new Coord(1,1));
		htfLabel.setDimensions(new Coord(10,70));
		htfLabel.setSize(Size.MATCH_PARENT_WIDTH);
		
		htfReading.add(htfLabel0);
		htfReading.add(htfLabel);
	}
	public void setupAzmReading(){
		azmReading = new Menu();
		azmReading.setColor(Color.CLEAR);
		azmReading.setDimensions(new Coord(windowDimensions.getX()/3,100));
		azmReading.setVisible(true);
		
		Label azmLabel0 = new Label("azimuth",new Coord(1,1));
		azmLabel0.setDimensions(new Coord(10,30));
		azmLabel0.setSize(Size.MATCH_PARENT_WIDTH);

		azmLabel = new Label(" 000.000",new Coord(1,1));
		azmLabel.setDimensions(new Coord(10,70));
		azmLabel.setSize(Size.MATCH_PARENT_WIDTH);
		
		azmReading.add(azmLabel0);
		azmReading.add(azmLabel);
	}
	
	public void setupRSDDisplay(){
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
		
		RawSensorDataDisplay = new Menu();
		RawSensorDataDisplay.setColor(Color.CLEAR);
		RawSensorDataDisplay.setDimensions(new Coord(windowDimensions.getX(),windowDimensions.getY()));
		RawSensorDataDisplay.setVisible(true);

		RSDLabel = new Label(" Bx      By      Bz      Btot    Gx     Gy     Gz     Gtot   inc    htf    azm    temp ",new Coord(1,1));
//							   000.86  000.95  000.09  001.89  0.485  0.940  0.600  2.026  146.0  086.8 -144.4  024.0
		RSDLabel.setDimensions(new Coord(1000,30));
		RSDLabel.setSize(Size.MATCH_PARENT);
//		RawSensorDataDisplay.add(RSDLabel);
		
		Menu m = getTableMenu(2);
		RawSensorDataDisplay.add(m);
		m.add(RSDLabel);
		
		label = new Label[RSD.length];
		for(int i = 0; i < label.length; i++){
			label[i] = new Label(RSD[i].toString(),new Coord(1,1));
			label[i].setDimensions(new Coord(10,30));
			label[i].setSize(Size.MATCH_PARENT_WIDTH);
		}
		for(int i = label.length -1; i > -1; i--){
			Menu m2 = getTableMenu(i%2);
			RawSensorDataDisplay.add(m2);
			m2.add(label[i]);
		}
	}
	
	public Menu getTableMenu(int x){ //0-dark 1-light 2-Header
		Menu m = new Menu();
		switch(x){
		case 0:
			m.setColor(new Color(0.15,0.3,0.45));
			break;
		case 1:
			m.setColor(new Color(0.2,0.4,0.6));
			break;
		case 2:
			m.setColor(new Color(0.2,0.2,0.6));
			break;
		default:
			System.out.println("Cannot get menu for "+x);
		}
		m.setDimensions(new Coord(10,30));
		m.setSize(Size.MATCH_PARENT_WIDTH);
		m.setVisible(true);
		return m;
	}
	
	public void tick(){
		super.tick();
		if(currentTick%30==0){
			currentSD.setHtf(currentSD.getHtf()+Math.random()*5);
			updateIncLabel();
			updateHtfLabel();
			updateAzmLabel();
			updateRSD();
		}
	}
	
	public void updateIncLabel(){
		incLabel.setLabel(currentSD.getIncString());
	}
	public void updateHtfLabel(){
		htfLabel.setLabel(currentSD.getHtfString());
	}
	public void updateAzmLabel(){
		azmLabel.setLabel(currentSD.getAzmString());
	}
	
	public void updateRSD(){
		for(int i = RSD.length-1; i > 0; i--){
			RSD[i] = RSD[i-1].clone();
		}
		RSD[0] = currentSD.clone();
		for(int i = 0; i < label.length; i++){
			label[i].setLabel(RSD[i].toString());
		}
	}
	
	public void input(){
		
	}
	
	public void draw() {
		super.draw();
		drawRawSensorData();
		drawTargets();
		drawControls();
	}
	
	public void drawRawSensorData(){
		RawSensorDataDisplay.draw();
	}
	
	public void drawTargets(){
		glPushMatrix();
			glTranslated(-500,400,0);
			incReading.draw();
			glTranslated(500,0,0);
			htfReading.draw();
			glTranslated(500,0,0);
			azmReading.draw();
		glPopMatrix();
	}
	
	public void drawControls(){
		
	}
}
