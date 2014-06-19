import java.text.DecimalFormat;


public class SensorData {
	private double Mx, My, Mz, Mtot, Gx, Gy, Gz, Gtot, inc, htf, azm;

	public SensorData(){
		Mx = Math.random();
		My = Math.random();
		Mz = Math.random();
		Mtot = Mx + My + Mz;
		Gx = Math.random();
		Gy = Math.random();
		Gz = Math.random();
		Gtot = Gx + Gy + Gz;
		inc = Math.random() * 360 - 180;
		htf = Math.random() * 360 - 180;
		azm = Math.random() * 360 - 180;
	}
	
	public String toString(){
		String data = "";
		DecimalFormat df = new DecimalFormat("000.000");
		data += df.format(Mx)+" ";
		data += df.format(My)+" ";
		data += df.format(Mz)+" ";
		data += df.format(Mtot)+" ";
		data += df.format(Gx)+" ";
		data += df.format(Gy)+" ";
		data += df.format(Gz)+" ";
		data += df.format(Gtot)+" ";
		data += df.format(inc)+" ";
		data += df.format(htf)+" ";
		data += df.format(azm);
		return data;
	}
	
	public double getMx() {
		return Mx;
	}

	public void setMx(double mx) {
		Mx = mx;
	}

	public double getMy() {
		return My;
	}

	public void setMy(double my) {
		My = my;
	}

	public double getMz() {
		return Mz;
	}

	public void setMz(double mz) {
		Mz = mz;
	}

	public double getMtot() {
		return Mtot;
	}

	public void setMtot(double mtot) {
		Mtot = mtot;
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

	public void setInc(double inc) {
		this.inc = inc;
	}

	public double getHtf() {
		return htf;
	}

	public void setHtf(double htf) {
		this.htf = htf;
	}

	public double getAzm() {
		return azm;
	}

	public void setAzm(double azm) {
		this.azm = azm;
	}
}
