import java.text.DecimalFormat;

public class electricField implements Impl{

	public double calculateDistance(Particle p,Charge c)
	{
		double d=0.0;
		double x=p.getX()-c.getX();
		double y=p.getY()-c.getY();
		d=Math.sqrt(x*x+y*y);
		return d;
	}

	public double calculateSine(Particle p,Charge c)
	{

		double r=calculateDistance(p,c);
		double sine=0.0;
		if(c.getY()>p.getY())
		{
			double line=c.getY()-p.getY();
			sine=line/r;

		}
		else if(c.getY()<p.getY())
		{
			double line=c.getY()-p.getY();
			sine=line/r;

		}
		else if(c.getY()==p.getY())
		{
			sine=0.0;
		}

		return sine;
	}

	public double calculateCosine(Particle p,Charge c)
	{

		double r=calculateDistance(p,c);
		double cosine=0.0;
		if(c.getX()>p.getX())
		{
			double line=c.getX()-p.getX();
			cosine=line/r;

		}
		else if(c.getX()<p.getX())
		{
			double line=c.getY()-p.getY();
			cosine=line/r;

		}
		else if(c.getX()==p.getX())
		{
			cosine=0.0;
		}

		return cosine;
	}

	public double calculateEF(Particle p,Charge c) throws ForceNChargeNotMatch
	{
		double e=0;
		double k=8.99*Math.pow(10,9);
		double r=calculateDistance(p,c);
		e=k*c.getPointC()/Math.pow(r, 2);
		
		return e;

	}

	public String calculateDirection(Particle p,Charge c) 
	{
		double e=0.0;
		double cosine=0.0;
		double sine=0.0;
		try
		{
			e=calculateEF(p,c);
			if(e>0&&c.getPointC()<0||e<0&&c.getPointC()>0)
			{
				throw new ForceNChargeNotMatch();
			}
		}
		catch(ForceNChargeNotMatch fncn)
		{
			System.out.println(fncn.getMessage());
			System.out.println(fncn.toString());

		}
		cosine=calculateCosine(p,c);
		sine=calculateSine(p,c);
		String dir=" ";
		double ex=e*cosine;
		double ey=e*sine;
		String x=" ";
		String y=" ";

		if(ex>0)
		{
			x="Psitive";
		}
		else if(ex<0)
		{
			x="Negetive";
		}
		else if(ex==0)
		{
			x="No Direction";
		}
		if(ey>0)
		{
			y="Psitive";
		}
		else if(ey<0)
		{
			y="Negetive";
		}
		else if(ex==0)
		{
			y="No Direction";
		}
		DecimalFormat df = new DecimalFormat(".##");
		System.out.println("The magnitude of electric field at Particle: "+p.getName()+" due to Charge Particle #: "+c.getCurrentId()+" is "+df.format(e));
		System.out.println("the point coordinates are: "+p.toString());
		System.out.println("The charge is: "+c.getPointC());
		System.out.println("The charge coordinates are: "+c.toString());
		System.out.println("cosine of the angle= "+cosine);
		System.out.println("The value of the x component of the Electric field at Particle: "+p.getName()+" due to Charge Particle #: "+c.getCurrentId()+" is "+df.format(ex));
		System.out.println("sine of the angle= "+sine);
		System.out.println("The value of the y component of the Electric field at Particle: "+p.getName()+" due to Charge Particle #: "+c.getCurrentId()+" is "+df.format(ey));


		dir="The direction of the x-component is "+x+" and the direction of the y component is "+y;
		System.out.println(dir);
		return dir;
		
		
	}
	
	public double calculateEx(Particle p,Charge c)
	{
		double e=0.0;
		try
		{
			e=calculateEF(p,c);
			if(e>0&&c.getPointC()<0||e<0&&c.getPointC()>0)
			{
				throw new ForceNChargeNotMatch();
			}
		}
		catch(ForceNChargeNotMatch fncn)
		{
			System.out.println(fncn.getMessage());
			System.out.println(fncn.toString());

		}
		double cosine=calculateCosine(p,c);
		double ex=e*cosine;
		
		return ex;
		
	}
	
	public double calculateEy(Particle p,Charge c)
	{
		double e=0.0;
		try
		{
			e=calculateEF(p,c);
			if(e>0&&c.getPointC()<0||e<0&&c.getPointC()>0)
			{
				throw new ForceNChargeNotMatch();
			}
		}
		catch(ForceNChargeNotMatch fncn)
		{
			System.out.println(fncn.getMessage());
			System.out.println(fncn.toString());

		}
		double sine=calculateSine(p,c);
		double ey=e*sine;
		
		return ey;
		
	}

}
