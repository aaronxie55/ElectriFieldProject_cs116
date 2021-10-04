
public class ForceNChargeNotMatch extends Exception{
	public ForceNChargeNotMatch()
	{
		super();
	}
	public String getMessage()
	{
		return "ForceNDirectionNotMatch exception was thrown.";
	}
	public String toString()
	{
		return "force and direction does not match.";
	}
}
