import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.DecimalFormat;

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

	DecimalFormat df;
	
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
		df = new DecimalFormat(" 000.0;-000.0");
		setupTargets();
		setupTolerance();
		setupRSDDisplay();
		setupIncReading();
		setupHtfReading();
		setupAzmReading();
		
		TextUtil.getInstance().setAlignment(TextUtil.CENTER);
	}

	public void setupTolerance(){
		incTol = 0.2;
		htfTol = 2.0;
		azmTol = 0.5;
	}
	
	public void setupTargets(){
		incTarget = Math.random()*360-180;
		htfTarget = Math.random()*360-180;
		azmTarget = Math.random()*360-180;
	}
	
	public void setupIncReading(){
		incReading = new Menu();
		incReading.setColor(new Color(0.2,0.2,0.2));
		incReading.setDimensions(new Coord(windowDimensions.getX()/3,200));
		incReading.setVisible(true);
		
		Label incLabel0 = new Label("inclination",new Coord(1,1));
		incLabel0.setDimensions(new Coord(10,30));
		incLabel0.setSize(Size.MATCH_PARENT_WIDTH);

		incLabel = new Label(" 000.000",new Coord(1,1));
		incLabel.setDimensions(new Coord(10,70));
		incLabel.setSize(Size.MATCH_PARENT_WIDTH);

		incTolLabel = new Label("tol.: 0.0",new Coord(1,1));
		incTolLabel.setDimensions(new Coord(10,20));
		incTolLabel.setSize(Size.MATCH_PARENT_WIDTH);
		
		incTargetLabel = new Label(df.format(incTarget),new Coord(1,1));
		incTargetLabel.setDimensions(new Coord(10,40));
		incTargetLabel.setSize(Size.MATCH_PARENT_WIDTH);
		
		incReading.add(incLabel0);
		incReading.add(incLabel);
		incReading.add(incTargetLabel);
		incReading.add(incTolLabel);
	}
	public void setupHtfReading(){
		htfReading = new Menu();
		htfReading.setColor(new Color(0.2,0.2,0.2));
		htfReading.setDimensions(new Coord(windowDimensions.getX()/3,200));
		htfReading.setVisible(true);
		
		Label htfLabel0 = new Label("tool face",new Coord(1,1));
		htfLabel0.setDimensions(new Coord(10,30));
		htfLabel0.setSize(Size.MATCH_PARENT_WIDTH);

		htfLabel = new Label(" 000.000",new Coord(1,1));
		htfLabel.setDimensions(new Coord(10,70));
		htfLabel.setSize(Size.MATCH_PARENT_WIDTH);

		htfTolLabel = new Label("tol.: 0.0",new Coord(1,1));
		htfTolLabel.setDimensions(new Coord(10,20));
		htfTolLabel.setSize(Size.MATCH_PARENT_WIDTH);
		
		htfTargetLabel = new Label(df.format(htfTarget),new Coord(1,1));
		htfTargetLabel.setDimensions(new Coord(10,40));
		htfTargetLabel.setSize(Size.MATCH_PARENT_WIDTH);
		
		htfReading.add(htfLabel0);
		htfReading.add(htfLabel);
		htfReading.add(htfTargetLabel);
		htfReading.add(htfTolLabel);
	}
	public void setupAzmReading(){
		azmReading = new Menu();
		azmReading.setColor(new Color(0.2,0.2,0.2));
		azmReading.setDimensions(new Coord(windowDimensions.getX()/3,200));
		azmReading.setVisible(true);
		
		Label azmLabel0 = new Label("azimuth",new Coord(1,1));
		azmLabel0.setDimensions(new Coord(10,30));
		azmLabel0.setSize(Size.MATCH_PARENT_WIDTH);

		azmLabel = new Label(" 000.000",new Coord(1,1));
		azmLabel.setDimensions(new Coord(10,70));
		azmLabel.setSize(Size.MATCH_PARENT_WIDTH);

		azmTolLabel = new Label("tol.: 0.0",new Coord(1,1));
		azmTolLabel.setDimensions(new Coord(10,20));
		azmTolLabel.setSize(Size.MATCH_PARENT_WIDTH);
		
		azmTargetLabel = new Label(df.format(azmTarget),new Coord(1,1));
		azmTargetLabel.setDimensions(new Coord(10,40));
		azmTargetLabel.setSize(Size.MATCH_PARENT_WIDTH);
		
		azmReading.add(azmLabel0);
		azmReading.add(azmLabel);
		azmReading.add(azmTargetLabel);
		azmReading.add(azmTolLabel);
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
			
			double x = htfTarget;
			
			x -= currentSD.getHtf();
			
			currentSD.setHtf(x/10+currentSD.getHtf());
			
			if(currentSD.getInc()>180){
				currentSD.setInc(currentSD.getInc()-360);
			}if(currentSD.getHtf()>180){
				currentSD.setHtf(currentSD.getHtf()-360);
			}if(currentSD.getAzm()>180){
				currentSD.setAzm(currentSD.getAzm()-360);
			}
			
			updateToleranceLabel();
			updateIncLabel();
			updateHtfLabel();
			updateAzmLabel();
			updateRSD();
		}
	}
	
	public void updateToleranceLabel(){
		incTolLabel.setLabel("tol.: "+incTol);
		htfTolLabel.setLabel("tol.: "+htfTol);
		azmTolLabel.setLabel("tol.: "+azmTol);
	}
	
	public void updateIncLabel(){
		if(currentSD.getInc()>incTarget+incTol||currentSD.getInc()<incTarget-incTol){
			incLabel.setColor(Color.RED);
		}else{
			incLabel.setColor(Color.GREEN);
		}
		incLabel.setLabel(currentSD.getIncString());
	}
	public void updateHtfLabel(){
		if(currentSD.getHtf()>htfTarget+htfTol||currentSD.getHtf()<htfTarget-htfTol){
			htfLabel.setColor(Color.RED);
		}else{
			htfLabel.setColor(Color.GREEN);
		}
		htfLabel.setLabel(currentSD.getHtfString());
	}
	public void updateAzmLabel(){
		if(currentSD.getAzm()>azmTarget+azmTol||currentSD.getAzm()<azmTarget-azmTol){
			azmLabel.setColor(Color.RED);
		}else{
			azmLabel.setColor(Color.GREEN);
		}
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
		double x = windowDimensions.getX()*0.33333;
		glPushMatrix();
			glTranslated(-x,400,0);
			incReading.draw();
			glTranslated(x,0,0);
			htfReading.draw();
			glTranslated(x,0,0);
			azmReading.draw();
		glPopMatrix();
	}
	
	public void drawControls(){
		
	}
}
