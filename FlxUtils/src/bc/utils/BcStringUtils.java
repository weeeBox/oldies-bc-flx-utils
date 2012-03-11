package bc.utils;

public class BcStringUtils
{
	public static boolean equals(String str, String...strings)
	{
		for (String string : strings)
		{
			if (str.equals(string))
			{
				return true;
			}
		}
		return false;
	}
}
