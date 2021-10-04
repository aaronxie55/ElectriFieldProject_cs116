import java.io.Serializable;


public class Charge extends Point implements Serializable{
	double pointC=0.0;
	static int id;
	int currentId=0;
	
	public Charge()
	{
		super();
		pointC=0.0;
		id++;
		currentId=id;
	}
	
	public Charge(double pxx,double pyy,double pcc)
	{
		super(pxx,pyy);
		pointC=pcc;
		id++;
		currentId=id;
	}

	public double getPointC() {
		return pointC;
	}

	public void setPointC(double pointC) {
		this.pointC = pointC;
	}

	public int getCurrentId() {
		return currentId;
	}
	
	public String toString()
	{
		String out=super.toString();
		return out;
	}
}
