import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
public class main {

	public static void main(String [] args) throws ForceNChargeNotMatch
	{
		String readingtxt="data.txt";

		ArrayList <Charge> carr=new ArrayList();
		ArrayList <Particle> parr=new ArrayList();
		ArrayList <Double> rarr=new ArrayList();
		ArrayList <Double> ecarr1=new ArrayList();
		ArrayList <Double> ecarr2=new ArrayList();
		try
		{
			FileInputStream fis=new FileInputStream(readingtxt);
			BufferedReader filereader=new BufferedReader(new InputStreamReader(fis));

			String reading=" ";
			while((reading=filereader.readLine())!=null)
			{
				StringTokenizer strtok=new StringTokenizer(reading,":");
				String tok1=strtok.nextToken();
				if(tok1.equals("Q"))
				{
					String line=strtok.nextToken();
					StringTokenizer strtok1=new StringTokenizer(line,",");
					double cx=Double.parseDouble(strtok1.nextToken());
					double cy=Double.parseDouble(strtok1.nextToken());
					double pc=Double.parseDouble(strtok1.nextToken());

					Charge c=new Charge(cx,cy,pc);

					carr.add(c);
				}
				else
				{
					String line=strtok.nextToken();
					StringTokenizer strtok2=new StringTokenizer(line,",");
					String name=strtok2.nextToken();
					double px=Double.parseDouble(strtok2.nextToken());
					double py=Double.parseDouble(strtok2.nextToken());

					Particle p=new Particle(name,px,py);

					parr.add(p);

				}
			}
			fis.close();
			filereader.close();
		}
		catch(FileNotFoundException fnf)
		{
			System.out.println("The file was not fund");
		}
		catch(IOException ioe)
		{
			System.out.println(ioe.toString()); 
		}


		for(int i=0;i<=parr.size()-1;i++)
		{
			double tex=0.0;
			double tey=0.0;
			double ex=0.0;
			double ey=0.0;
			double ec=0.0;
			for(int j=0;j<=carr.size()-1;j++)
			{
				electricField ef=new electricField();
				String dir=ef.calculateDirection(parr.get(i), carr.get(j));	
				if(j>=1)
				{
					tex=ef.calculateEx(parr.get(i), carr.get(j));
					tey=ef.calculateEy(parr.get(i), carr.get(j));
					ec=ef.calculateEF(parr.get(i), carr.get(j));
					ecarr2.add(ec);
				}
				else
				{
					ex=ef.calculateEx(parr.get(i), carr.get(j));
					ey=ef.calculateEy(parr.get(i), carr.get(j));
					ec=ef.calculateEF(parr.get(i), carr.get(j));
					ecarr1.add(ec);
				}
			}
			DecimalFormat df = new DecimalFormat(".##");
			double rf=Math.sqrt(Math.pow((ex+tex),2)+Math.pow((tey+ey), 2));
			rarr.add(rf);
			System.out.println("The magnitude of the resultant field is: "+df.format(rf));
			System.out.println("--------------NEXT POINT------------------------------");
		}


		System.out.println("-------------VALUES BEFORE WRITTEN IN .SER FILE-----------------------------------------------");
		for(int k=0;k<=parr.size()-1;k++)
		{
			DecimalFormat df = new DecimalFormat(".##");
			System.out.println("The x coordinate is x= "+parr.get(k).getX() +" The y coordinate is y= "+parr.get(k).getY()+" The point is: "+parr.get(k).getName());
			System.out.println("The individual fields from each charge are:");
			System.out.println("\t"+df.format(ecarr1.get(k))+" "+df.format(ecarr2.get(k)));
			System.out.println("The ResultantField is: "+df.format(rarr.get(k)));
		}

		String filename="mydata.ser";
		FileOutputStream fos=null;
		ObjectOutputStream oos=null;
		try
		{
			fos=new FileOutputStream(filename,false);
			oos=new ObjectOutputStream(fos);

			for(int k=0;k<=parr.size()-1;k++)
			{
				
				DecimalFormat df = new DecimalFormat(".##");
				oos.writeObject("The x coordinate is x= "+parr.get(k).getX() +" The y coordinate is y= "+parr.get(k).getY()+" The point is: "+parr.get(k).getName());				
				oos.writeObject("The individual fields from each charge are:");				
				oos.writeObject("\t"+df.format(ecarr1.get(k))+" "+df.format(ecarr2.get(k)));			
				oos.writeObject("The ResultantField is: "+df.format(rarr.get(k)));
				
				
			}
			oos.close();
		
		}
		catch(IOException ioe)
		{
			System.out.println(ioe.getMessage());
		}

		System.out.println("Do you want to have the .ser binary coded file read?");
		Scanner scan=new Scanner(System.in);
		String str=scan.next();

		if(str.equals("yes"))
		{
			try
			{
				System.out.println("------------------------DATA READ FROM .ser FILE");
				FileInputStream fis=new FileInputStream(filename);
				ObjectInputStream ois=new ObjectInputStream(fis);

				while(true)
				{
					try
					{
						String line=(String)ois.readObject();
						System.out.println(line);
						
					}
					catch(EOFException eofe)
					{
						System.out.println(eofe.getMessage());
						break;
					}
					
				}
			
				System.out.println("The End");
			}
			catch(ClassNotFoundException cnfe)
			{
				System.out.println(cnfe.getMessage());
			}
			catch(IOException ioe)
			{
				System.out.println(ioe.getMessage());
			}


		}
		else
		{
			System.out.println("Program is terminating");
			System.exit(0);
		}

	}
}
