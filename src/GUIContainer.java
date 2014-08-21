import static org.lwjgl.opengl.GL11.*;

import java.text.DecimalFormat;

import king.jaiden.util.*;


public class GUIContainer {
	RollCalMain main;
	Menu RawSensorDataDisplay, controlMenu, incReading, htfReading, azmReading;
	Label[] label;
	Label RSDLabel,incLabel,htfLabel,azmLabel,
				  incTargetLabel,htfTargetLabel,azmTargetLabel,
				  incTolLabel,htfTolLabel,azmTolLabel,
				  incDeltaLabel,htfDeltaLabel,azmDeltaLabel;
	Image target, delta;
	DecimalFormat df;
	PipeView pipeView,pipeView2;
	Coord wd;
	public GUIContainer(RollCalMain main){
		this.main = main;
		df = new DecimalFormat(" 000.00;-000.00");
		target = new Image("res/images/target.png");
		delta = new Image("res/images/delta.png");
		pipeView = new PipeView(main,false);
		pipeView2 = new PipeView(main,true);
		wd = main.getWindowDimensions();
		setupRSDDisplay();
		setupIncReading();
		setupHtfReading();
		setupAzmReading();
		setupControlMenu();
	}
	public void setupControlMenu(){
		controlMenu = new Menu();
		controlMenu.setColor(Color.CLEAR);
		controlMenu.setDimensions(new Coord(wd.getX(),100));
		controlMenu.setVisible(true);
		
		Label buttons = new Label("F1       F2      F3      F4      F5     F6     F7     F8     F9     F10                ",new Coord(1,1));
		buttons.setDimensions(new Coord(10,50));
		buttons.setSize(Size.MATCH_PARENT_WIDTH);
		
		Label controls =new Label("Log      Read    Loop    Rate    Auto          Sim    Calibrate     Term               ",new Coord(1,1));
		controls.setDimensions(new Coord(10,50));
		controls.setSize(Size.MATCH_PARENT_WIDTH);

		controlMenu.add(buttons);
		controlMenu.add(controls);
	}
	public void setupIncReading(){
		incReading = new Menu();
		incReading.setColor(new Color(0.4,0.4,0.4));
		incReading.setDimensions(new Coord(400,160));
		incReading.setVisible(true);
		
		Label incLabel0 = new Label("INC",new Coord(1,2));
		incLabel0.setDimensions(new Coord(10,30));
		incLabel0.setSize(Size.MATCH_PARENT_WIDTH);
		incLabel0.setPosition(new Coord(-175,50));
		

		incLabel = new Label(" 000.000",new Coord(1,2));
		incLabel.setDimensions(new Coord(10,60));
		incLabel.setSize(Size.MATCH_PARENT_WIDTH);
		incLabel.setPosition(new Coord(100,45));
		
		Label l = new Label("",new Coord(1,1));
		l.setDimensions(new Coord(30,30));
		l.setSize(Size.FIXED);
		l.setImage(target);
		l.setPosition(new Coord(-180,-10));
		incReading.add(l);
		
		l = new Label("",new Coord(1,1));
		l.setDimensions(new Coord(30,30));
		l.setSize(Size.FIXED);
		l.setImage(delta);
		l.setPosition(new Coord(180,-10));
		incReading.add(l);

//		incTolLabel = new Label("tol.: 0.0",new Coord(1,1));
//		incTolLabel.setDimensions(new Coord(10,20));
//		incTolLabel.setSize(Size.MATCH_PARENT_WIDTH);
		
		incTargetLabel = new Label(df.format(main.incTarget),new Coord(1,2));
		incTargetLabel.setDimensions(new Coord(10,50));
		incTargetLabel.setSize(Size.MATCH_PARENT_WIDTH);
		incTargetLabel.setPosition(new Coord(-120,-55));
		
		incDeltaLabel = new Label(" 000.000",new Coord(1,2));
		incDeltaLabel.setDimensions(new Coord(10,50));
		incDeltaLabel.setSize(Size.MATCH_PARENT_WIDTH);
		incDeltaLabel.setPosition(new Coord(110,-55));
		
		incReading.add(incLabel0);
		incReading.add(incLabel);
		incReading.add(incTargetLabel);
		incReading.add(incDeltaLabel);
//		incReading.add(incTolLabel);
	}
	public void setupHtfReading(){
		htfReading = new Menu();
		htfReading.setColor(new Color(0.4,0.4,0.4));
		htfReading.setDimensions(new Coord(400,160));
		htfReading.setVisible(true);
		
		Label htfLabel0 = new Label("HTF",new Coord(1,2));
		htfLabel0.setDimensions(new Coord(10,30));
		htfLabel0.setSize(Size.MATCH_PARENT_WIDTH);
		htfLabel0.setPosition(new Coord(-175,50));
		

		htfLabel = new Label(" 000.000",new Coord(1,2));
		htfLabel.setDimensions(new Coord(10,60));
		htfLabel.setSize(Size.MATCH_PARENT_WIDTH);
		htfLabel.setPosition(new Coord(100,45));
		
		Label l = new Label("",new Coord(1,1));
		l.setDimensions(new Coord(30,30));
		l.setSize(Size.FIXED);
		l.setImage(target);
		l.setPosition(new Coord(-180,-10));
		htfReading.add(l);
		
		l = new Label("",new Coord(1,1));
		l.setDimensions(new Coord(30,30));
		l.setSize(Size.FIXED);
		l.setImage(delta);
		l.setPosition(new Coord(180,-10));
		htfReading.add(l);

//		htfTolLabel = new Label("tol.: 0.0",new Coord(1,1));
//		htfTolLabel.setDimensions(new Coord(10,20));
//		htfTolLabel.setSize(Size.MATCH_PARENT_WIDTH);
		
		htfTargetLabel = new Label(df.format(main.htfTarget),new Coord(1,2));
		htfTargetLabel.setDimensions(new Coord(10,50));
		htfTargetLabel.setSize(Size.MATCH_PARENT_WIDTH);
		htfTargetLabel.setPosition(new Coord(-120,-55));
		
		htfDeltaLabel = new Label(" 000.000",new Coord(1,2));
		htfDeltaLabel.setDimensions(new Coord(10,50));
		htfDeltaLabel.setSize(Size.MATCH_PARENT_WIDTH);
		htfDeltaLabel.setPosition(new Coord(110,-55));
		
		htfReading.add(htfLabel0);
		htfReading.add(htfLabel);
		htfReading.add(htfTargetLabel);
		htfReading.add(htfDeltaLabel);
//		htfReading.add(htfTolLabel);
	}
	public void setupAzmReading(){
		azmReading = new Menu();
		azmReading.setColor(new Color(0.4,0.4,0.4));
		azmReading.setDimensions(new Coord(400,160));
		azmReading.setVisible(true);
		
		Label azmLabel0 = new Label("AZI",new Coord(1,2));
		azmLabel0.setDimensions(new Coord(10,30));
		azmLabel0.setSize(Size.MATCH_PARENT_WIDTH);
		azmLabel0.setPosition(new Coord(-175,50));
		

		azmLabel = new Label(" 000.000",new Coord(1,2));
		azmLabel.setDimensions(new Coord(10,60));
		azmLabel.setSize(Size.MATCH_PARENT_WIDTH);
		azmLabel.setPosition(new Coord(100,45));
		
		Label l = new Label("",new Coord(1,1));
		l.setDimensions(new Coord(30,30));
		l.setSize(Size.FIXED);
		l.setImage(target);
		l.setPosition(new Coord(-180,-10));
		azmReading.add(l);
		
		l = new Label("",new Coord(1,1));
		l.setDimensions(new Coord(30,30));
		l.setSize(Size.FIXED);
		l.setImage(delta);
		l.setPosition(new Coord(180,-10));
		azmReading.add(l);

//		azmTolLabel = new Label("tol.: 0.0",new Coord(1,1));
//		azmTolLabel.setDimensions(new Coord(10,20));
//		azmTolLabel.setSize(Size.MATCH_PARENT_WIDTH);
		
		azmTargetLabel = new Label(df.format(main.azmTarget),new Coord(1,2));
		azmTargetLabel.setDimensions(new Coord(10,50));
		azmTargetLabel.setSize(Size.MATCH_PARENT_WIDTH);
		azmTargetLabel.setPosition(new Coord(-120,-55));
		
		azmDeltaLabel = new Label(" 000.000",new Coord(1,2));
		azmDeltaLabel.setDimensions(new Coord(10,50));
		azmDeltaLabel.setSize(Size.MATCH_PARENT_WIDTH);
		azmDeltaLabel.setPosition(new Coord(110,-55));
		
		azmReading.add(azmLabel0);
		azmReading.add(azmLabel);
		azmReading.add(azmTargetLabel);
		azmReading.add(azmDeltaLabel);
//		azmReading.add(azmTolLabel);
	}
	public void setupRSDDisplay(){
			
		
		RawSensorDataDisplay = new Menu();
		RawSensorDataDisplay.setColor(Color.CLEAR);
		RawSensorDataDisplay.setDimensions(new Coord(wd.getX(),wd.getY()));
		RawSensorDataDisplay.setVisible(true);

		RSDLabel = new Label(" Bx      By      Bz      Btot    Gx     Gy     Gz     Gtot   inc    htf    azm    temp ",new Coord(1,1));
//							   000.86  000.95  000.09  001.89  0.485  0.940  0.600  2.026  146.0  086.8 -144.4  024.0
		RSDLabel.setDimensions(new Coord(1000,30));
		RSDLabel.setSize(Size.MATCH_PARENT);
//		RawSensorDataDisplay.add(RSDLabel);
		
		Menu m = getTableMenu(2);
		RawSensorDataDisplay.add(m);
		m.add(RSDLabel);
		
		label = new Label[main.RSD.length];
		for(int i = 0; i < label.length; i++){
			label[i] = new Label(main.RSD[i].toString(),new Coord(1,1));
			label[i].setDimensions(new Coord(10,30));
			label[i].setSize(Size.MATCH_PARENT_WIDTH);
		}
		for(int i = label.length -1; i > -1; i--){
			Menu m2 = getTableMenu(i%2);
			RawSensorDataDisplay.add(m2);
			m2.add(label[i]);
		}
	}
	public Menu getTableMenu(int x){
		//0-dark 1-light 2-Header
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
	public void updateIncLabel(){
		if(main.currentSD.getInc()>main.incTarget+main.incTol||main.currentSD.getInc()<main.incTarget-main.incTol){
			incLabel.setColor(Color.RED);
		}else{
			incLabel.setColor(Color.GREEN);
		}
		incLabel.setLabel(main.currentSD.getIncString());
		incDeltaLabel.setLabel(df.format(main.currentSD.getInc()-main.incTarget));
	}
	public void updateHtfLabel(){
		if(main.currentSD.getHtf()>main.htfTarget+main.htfTol||main.currentSD.getHtf()<main.htfTarget-main.htfTol){
			htfLabel.setColor(Color.RED);
		}else{
			htfLabel.setColor(Color.GREEN);
		}
		htfLabel.setLabel(main.currentSD.getHtfString());
		htfDeltaLabel.setLabel(df.format(main.currentSD.getHtf()-main.htfTarget));
	}
	public void updateAzmLabel(){
		if(main.currentSD.getAzm()>main.azmTarget+main.azmTol||main.currentSD.getAzm()<main.azmTarget-main.azmTol){
			azmLabel.setColor(Color.RED);
		}else{
			azmLabel.setColor(Color.GREEN);
		}
		azmLabel.setLabel(main.currentSD.getAzmString());
		azmDeltaLabel.setLabel(df.format(main.currentSD.getAzm()-main.azmTarget));
	}
	public void updateToleranceLabel(){
//		incTolLabel.setLabel("tol.: "+incTol);
//		htfTolLabel.setLabel("tol.: "+htfTol);
//		azmTolLabel.setLabel("tol.: "+azmTol);
	}
	public void update(){
		updateToleranceLabel();
		updateIncLabel();
		updateHtfLabel();
		updateAzmLabel();
		updateRSD();
		pipeView.update(main.currentSD.getHtf(), main.currentSD.getInc(), main.currentSD.getAzm(), 30);
	}
	public void updateRSD(){
		for(int i = main.RSD.length-1; i > 0; i--){
			main.RSD[i] = main.RSD[i-1].clone();
		}
		main.RSD[0] = main.currentSD.clone();
		for(int i = 0; i < label.length; i++){
			label[i].setLabel(main.RSD[i].toString());
		}
	}
	public void updateTargets(){
		pipeView2.update(main.htfTarget, main.incTarget, main.azmTarget, 0);
		htfTargetLabel.setLabel(df.format(main.htfTarget));
		incTargetLabel.setLabel(df.format(main.incTarget));
		azmTargetLabel.setLabel(df.format(main.azmTarget));
	}
	
	public void draw() {
		drawHeader1();
		drawHeader2();
		drawHeader3();
//		drawRawSensorData();
		drawTargets();
		drawControls();
		pipeView.draw();
		pipeView2.draw();
	}
	public void drawHeader1(){	
	    // display time and date using toString()
	    String str = String.format("%td-%<tb-%<tY %<tT", main.date ); 
		glPushMatrix();
			glColor3f(1,1,0);
			glTranslated(0,wd.getY()/2-15,0); // Top Center
			DrawUtil.drawRectAboutOrigin(new Coord(wd.getX(),30));
			glTranslated(-wd.getX()/2+10,0,0); // Top Left
			DrawUtil.setColor(Color.BLACK);
			TextUtil.getInstance().setTextSize(new Coord(15,25));
			TextUtil.getInstance().setAlignment(TextUtil.LEFT);
			TextUtil.getInstance().write("Boregyde RolCal V0.0.1 -- PLACEHOLDER", new Coord());
			glTranslated(wd.getX()-20,0,0); // Top Right
			TextUtil.getInstance().setAlignment(TextUtil.RIGHT);
			TextUtil.getInstance().write(str, new Coord());
		glPopMatrix();
	}
	public void drawHeader2(){
		String sensorName = "APS750";
		glPushMatrix();
			glColor3f(1,0.8f,0);
			glTranslated(-wd.getX()/2+110,wd.getY()/2-50,0); // Top Left
			DrawUtil.drawRectAboutOrigin(new Coord(220,30));
			glTranslated(-100,0,0); // Top Left
			DrawUtil.setColor(Color.BLACK);
			TextUtil.getInstance().setAlignment(TextUtil.LEFT);
			TextUtil.getInstance().write("Sensor: "+sensorName, new Coord());
		glPopMatrix();
	}
	public void drawHeader3(){
		glPushMatrix();
			glColor3f(0.4f,1,1);
			glTranslated(230,wd.getY()/2-50,0);
			DrawUtil.drawRectAboutOrigin(new Coord(wd.getX(),30));
			DrawUtil.setColor(Color.BLACK);
			glTranslated(-wd.getX()/2+10,0,0);
			TextUtil.getInstance().write("Log:   Mode: Looping  Count:   1 Next:   0:01.0  ISSUE SENSOR COMMAND", new Coord());
		glPopMatrix();
	}
	public void drawRawSensorData(){
		RawSensorDataDisplay.draw();
	}
	public void drawTargets(){
		glPushMatrix();
			glTranslated(-360,wd.getY()/2-175,0);
			htfReading.draw();
			glTranslated(410,0,0);
			incReading.draw();
			glTranslated(410,0,0);
			azmReading.draw();
		glPopMatrix();
	}
	public void drawControls(){
		glPushMatrix();
			glTranslated(0,-wd.getY()/2+100,0);
			controlMenu.draw();
		glPopMatrix();
	}
}
