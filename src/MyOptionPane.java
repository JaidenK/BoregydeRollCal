import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import static org.lwjgl.input.Keyboard.*;
import static org.lwjgl.opengl.GL11.*;

import king.jaiden.util.*;


public class MyOptionPane {
	Coord pos;
	boolean ready = false; //Turns true when user hits enter
	RollCalMain m;
	
	static MyOptionPane instance = null;
	
	Menu dialog;
	Label prompt, responseLabel;
	
	String response = "";
	
	int insertPoint = 0;
	
	public MyOptionPane(){
		pos = new Coord();
		
		dialog = new Menu();
		dialog.setSize(Size.FIXED);
		dialog.setDimensions(new Coord(500,100));
		dialog.setPosition(pos);
		dialog.setVisible(true);
		
		prompt = new Label("Input:", new Coord(1,2));
		prompt.setSize(Size.FIXED);
		prompt.setDimensions(new Coord(500,50));
		prompt.setTextAlignment(TextUtil.LEFT);
		prompt.setPosition(new Coord(-230,20));
		prompt.setColor(Color.BLACK);

		responseLabel = new Label("", new Coord(1,2));
		responseLabel.setSize(Size.FIXED);
		responseLabel.setDimensions(new Coord(500,50));
		responseLabel.setTextAlignment(TextUtil.LEFT);
		responseLabel.setPosition(new Coord(-230,-20));
		responseLabel.setColor(Color.BLACK);
		
		dialog.add(prompt);
		dialog.add(responseLabel);
	}
	public static MyOptionPane getInstance(){
		if(instance==null)
			instance = new MyOptionPane();
		return instance;
	}
	public String showInputDialog(String prompt){
		this.prompt.setLabel(prompt);
		while(true){
			input();
			draw();
			if(ready)
				break;
			Display.update();
		}
		ready = false;
		String fence = response;
		response = "";
		responseLabel.setLabel("");
		insertPoint = 0;
		draw();
		Display.update();
		return fence;
	}
	public void backspace(){
		if(response.length()>0&&insertPoint>0){
			response = response.substring(0, insertPoint-1) + response.substring(insertPoint);
			insertPoint--;
			responseLabel.setLabel(response);
		}
	}
	public void moveInsertionPoint(int x){
		insertPoint += x;
		if(insertPoint<0)
			insertPoint = 0;
		else if(insertPoint>response.length())
			insertPoint = response.length();
	}
	public void addCharacter(char c){
		if(insertPoint == response.length()){
			response += c;
		}else{
			response = response.substring(0, insertPoint) + c + response.substring(insertPoint);
		}
		insertPoint++;
		responseLabel.setLabel(response);
	}
	public void input(){
		while(Keyboard.next()){
			if(Keyboard.isKeyDown(Keyboard.getEventKey())){	
				switch(Keyboard.getEventKey()){
				case KEY_RETURN:
					ready = true;
					break;
				case KEY_BACK:
					backspace();
					break;
				case KEY_LEFT:
					moveInsertionPoint(-1);
					break;
				case KEY_RIGHT:
					moveInsertionPoint(1);
					break;
				default:
					addCharacter(Keyboard.getEventCharacter());
				}
			}
		}
	}
	public void draw(){
		dialog.draw();
		glBegin(GL_LINES);
			glVertex2d(-250+10+25*insertPoint,0);
			glVertex2d(-250+10+25*insertPoint,-50);
		glEnd();
	}
	public double showDoubleInputDialog(String prompt){
		while(true){
			try{
				double y = Double.parseDouble(showInputDialog(prompt));
				if(!(Double.isNaN(y)||Double.isInfinite(y))){
					return y;
				}
			}catch(NumberFormatException e){
				System.out.println("Bad number input.");
			}
		}
	}
}
