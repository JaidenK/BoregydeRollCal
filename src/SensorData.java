import java.text.DecimalFormat;


public class SensorData {
	private double Bx, By, Bz, Btot, Gx, Gy, Gz, Gtot, inc, htf, azm, temp;
	DecimalFormat B = new DecimalFormat(" 000.00;-000.00");
	DecimalFormat G = new DecimalFormat(" 0.000;-0.000");
	DecimalFormat df = new DecimalFormat(" 000.00;-000.00");
	
	public SensorData(){
		Bx = Math.random();
		By = Math.random();
		Bz = Math.random();
		Btot = Bx + By + Bz;
		Gx = Math.random();
		Gy = Math.random();
		Gz = Math.random();
		Gtot = Gx + Gy + Gz;
		inc = Math.random() * 360 - 180;
		htf = Math.random() * 360 - 180;
		azm = Math.random() * 360 - 180;
		temp = Math.random()*10+15;
	}
	
	public SensorData(double Bx, double By, double Bz, double Btot, double Gx, double Gz, double Gy, double Gtot, double inc, double htf, double azm, double temp){
		this.Bx = Bx;
		this.By = By;
		this.Bz = Bz;
		this.Btot = Btot;
		this.Gx = Gx;
		this.Gy = Gy;
		this.Gz = Gz;
		this.Gtot = Gtot;
		this.inc = inc;
		this.htf = htf;
		this.azm = azm;
		this.temp = temp;
	}
	
	public SensorData clone(){
		return new SensorData(Bx, By, Bz, Btot, Gx, Gz, Gy, Gtot, inc, htf, azm, temp);
	}
	
	public String toString(){
		String data = "";
		data += B.format(Bx)+" ";
		data += B.format(By)+" ";
		data += B.format(Bz)+" ";
		data += B.format(Btot)+" ";
		data += G.format(Gx)+" ";
		data += G.format(Gy)+" ";
		data += G.format(Gz)+" ";
		data += G.format(Gtot)+" ";
		data += df.format(inc)+" ";
		data += df.format(htf)+" ";
		data += df.format(azm)+" ";
		data += df.format(temp);
		return data;
	}
	
	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public double getBx() {
		return Bx;
	}

	public void setBx(double mx) {
		Bx = mx;
	}

	public double getBy() {
		return By;
	}

	public void setBy(double my) {
		By = my;
	}

	public double getBz() {
		return Bz;
	}

	public void setBz(double mz) {
		Bz = mz;
	}

	public double getBtot() {
		return Btot;
	}

	public void setBtot(double mtot) {
		Btot = mtot;
	}

	public double getGx() {
		return Gx;
	}

	public void setGx(double gx) {
		Gx = gx;
	}

	public double getGy() {
		return Gy;
	}

	public void setGy(double gy) {
		Gy = gy;
	}

	public double getGz() {
		return Gz;
	}

	public void setGz(double gz) {
		Gz = gz;
	}

	public double getGtot() {
		return Gtot;
	}

	public void setGtot(double gtot) {
		Gtot = gtot;
	}

	public double getInc() {
		return inc;
	}
	
	public String getIncString() {
		return df.format(inc);
	}

	public void setInc(double inc) {
		this.inc = inc;
	}

	public double getHtf() {
		return htf;
	}
	
	public String getHtfString() {
		return df.format(htf);
	}

	public void setHtf(double htf) {
		this.htf = htf;
	}

	public double getAzm() {
		return azm;
	}

	public String getAzmString() {
		return df.format(azm);
	}
	
	public void setAzm(double azm) {
		this.azm = azm;
	}
}
