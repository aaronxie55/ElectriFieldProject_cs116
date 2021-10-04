
public abstract class Point {
	double x=0.0;
	double y=0.0;
	
	public Point()
	{
		x=0.0;
		y=0.0;
	}
	
	public Point(double xx,double yy)
	{
		x=xx;
		y=yy;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public String toString()
	{
		String out=x+" "+y;
		return out;
	}
	
}
