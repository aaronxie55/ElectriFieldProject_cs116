import java.io.Serializable;


public class Particle extends Point implements Serializable{
	String name=" ";
	
	public Particle()
	{
		super();
		name="NO_NAME";
	}
	
	public Particle(String n,double pxx,double pyy)
	{
		super(pxx,pyy);
		name=n;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString()
	{
		String out=super.toString();
		return out;
	}
	
}
