import java.awt.Dimension;
import java.awt.Toolkit;

import king.jaiden.util.*;


public class RollCalMain extends ApplicationWindow {
	
	Menu RawSensorDataDisplay;
	Label[] label;
	SensorData[] RSD;
	
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
		RSD = new SensorData[5];
		for(int i = 0; i < RSD.length; i++){
			RSD[i] = new SensorData();
		}
		
		
		RawSensorDataDisplay = new Menu();
		RawSensorDataDisplay.setColor(Color.CLEAR);
		RawSensorDataDisplay.setDimensions(new Coord(windowDimensions.getX(),windowDimensions.getY()));
		RawSensorDataDisplay.setVisible(true);

		label = new Label[RSD.length];
		for(int i = 0; i < label.length; i++){
			label[i] = new Label(RSD[i].toString(),new Coord(1,1));
			label[i].setDimensions(new Coord(10,30));
			label[i].setSize(Size.MATCH_PARENT_WIDTH);
			RawSensorDataDisplay.add(label[i]);
		}
		
		TextUtil.getInstance().setAlignment(TextUtil.CENTER);
	}
	
	public void tick(){
		super.tick();
		updateRSD();
	}
	
	public void updateRSD(){
		for(int i = 0; i < RSD.length-1; i++){
			
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
		
	}
	
	public void drawControls(){
		
	}
}
