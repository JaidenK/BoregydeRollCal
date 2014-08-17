import static org.lwjgl.opengl.GL11.*;
import king.jaiden.util.*;
public class PipeView {
	boolean ghost = false;
	RollCalMain m;
	Color[] pipeColors,pipeColors2;
	double previousHtf = 0, previousInc = 0, previousAzm = 0;
	double currentHtf = 0, currentInc = 0, currentAzm = 0;
	int ticksTilNext = 0;
	int currTick = 0;
	boolean easing = true;
	
	public PipeView(RollCalMain m,boolean x){
		this.m = m;
		ghost = x;
		pipeColors = new Color[8];
		if(!x){
			pipeColors[0] = new Color(0.4,0.4,0.4);
			pipeColors[1] = new Color(0.6,0.6,0.6);
			pipeColors[2] = new Color(0.3,0.3,0.3);
			pipeColors[3] = new Color(0.6,0.6,0.6);
			pipeColors[4] = new Color(0.4,0.4,0.4);
			pipeColors[5] = new Color(0.7,0.7,0.7);
			pipeColors[6] = new Color(0.5,0.5,0.5);
			pipeColors[7] = new Color(0.6,0.6,0.6);
		}else{
			pipeColors[0] = new Color(0.4,0.4,0.4,0.5);
			pipeColors[1] = new Color(0.6,0.6,0.6,0.5);
			pipeColors[2] = new Color(0.3,0.3,0.3,0.5);
			pipeColors[3] = new Color(0.6,0.6,0.6,0.5);
			pipeColors[4] = new Color(0.4,0.4,0.4,0.5);
			pipeColors[5] = new Color(0.7,0.7,0.7,0.5);
			pipeColors[6] = new Color(0.5,0.5,0.5,0.5);
			pipeColors[7] = new Color(0.6,0.6,0.6,0.5);
		}
	}
	public void update(double htf, double inc, double azm,int ticksTilNext){
		currTick = 0;
		previousHtf = currentHtf;
		currentHtf = htf;
		
		previousInc = currentInc;
		currentInc = inc;
		
		previousAzm = currentAzm;
		currentAzm = azm;
		
		this.ticksTilNext = ticksTilNext;
	}
	public double getValue(double t, double b, double c, double d){
		return c*t/d + b;
	}
	public void setEasing(boolean e){
		easing = e;
	}
	public void draw(){
		
		
		
		glPushMatrix();
			m.setup3DMatrix();
				glEnable(GL_DEPTH_TEST);
					glTranslated(0,0,-15);
					glRotated(10,1,0,0);
					glRotated(-10,0,1,0);
					glBegin(GL_LINES);
						DrawUtil.setColor(Color.RED);
						glVertex3d(-10,0,0);
						glVertex3d(10,0,0);
						DrawUtil.setColor(Color.GREEN);
						glVertex3d(0,-10,0);
						glVertex3d(0,10,0);
						DrawUtil.setColor(Color.BLUE);
						glVertex3d(0,0,-10);
						glVertex3d(0,0,10);
					glEnd();
					
					if(!ghost){
						glRotated(getValue(currTick,previousAzm,currentAzm-previousAzm,ticksTilNext),0,1,0);
						glRotated(getValue(currTick,previousInc,currentInc-previousInc,ticksTilNext),0,0,1);
						glRotated(getValue(currTick,previousHtf,currentHtf-previousHtf,ticksTilNext),1,0,0);
					}else{
						glRotated(currentAzm,0,1,0);
						glRotated(currentInc,0,0,1);
						glRotated(currentHtf,1,0,0);
					}
					
					DrawUtil.drawRectPrismAboutOrigin(new Coord3D(15,2,2), pipeColors);
					glTranslated(7.5,0,0);
					if(!ghost){
						DrawUtil.drawRectPrismAboutOrigin(new Coord3D(1,1.75,1.75), new Color(0.05,0.05,0.05));
					}else{
						DrawUtil.drawRectPrismAboutOrigin(new Coord3D(1,1.75,1.75), new Color(0.05,0.05,0.05,0.5));
					}
					glTranslated(0.25,0,0);
					glTranslated(0,0.5,0);
					DrawUtil.drawRectPrismAboutOrigin(new Coord3D(1,0.25,0.25), Color.COPPER_ORANGE);
					glTranslated(0,-0.5,0.5);
					DrawUtil.drawRectPrismAboutOrigin(new Coord3D(1,0.25,0.25), Color.COPPER_ORANGE);
					glTranslated(0,-0.5,-0.5);
					DrawUtil.drawRectPrismAboutOrigin(new Coord3D(1,0.25,0.25), Color.COPPER_ORANGE);
					glTranslated(0,0.5,-0.5);
					DrawUtil.drawRectPrismAboutOrigin(new Coord3D(1,0.25,0.25), Color.COPPER_ORANGE);
				glDisable(GL_DEPTH_TEST);
			m.setup2DMatrix();
		glPopMatrix();
		if(easing)
			currTick++;
	}
}
