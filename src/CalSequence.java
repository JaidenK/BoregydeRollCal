import java.util.ArrayList;


public class CalSequence {
	ArrayList<CalStep> calsteps = new ArrayList<CalStep>();
	int currStep = 0;
	public CalSequence(){
		calsteps.add(new CalStep(0,90,0));
		calsteps.add(new CalStep(45,90,0));
		calsteps.add(new CalStep(90,90,0));
		calsteps.add(new CalStep(135,90,0));
		calsteps.add(new CalStep(180,90,0));
		calsteps.add(new CalStep(-135,90,0));
		calsteps.add(new CalStep(-90,90,0));
		calsteps.add(new CalStep(-45,90,0));
		calsteps.add(new CalStep(0,0,0));
		calsteps.add(new CalStep(0,45,0));
		calsteps.add(new CalStep(0,90,0));
		calsteps.add(new CalStep(0,135,0));
		calsteps.add(new CalStep(0,180,0));
	}
	public CalStep next(){
//		if(currStep==calsteps.size()){
//			
//		}
		return calsteps.get(currStep++);
	}
}
