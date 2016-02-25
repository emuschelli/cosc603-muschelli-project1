/**
 * Computer calculation of Fire Danger
 * <p>
 * Used by NORTH CENTERAL FOREST EXPERIMENT STATION, FOREST SERVICES - U.S. DEPARTMENT OF AGRICULTURE
 * <P>
 * This application calculates National Fire Danger Rating Indexes. The following indexes are also available:
 * <li>Fuel Moisture,</li>
 * <li>Build up index,</li>
 * <li>and, drying factors.</li>o
 * <p>
 * The original program was written in FORTRAN 77 and has been re-engineered using Java programming language.
 */


import java.io.Console;

/**
 * Calculation of National Fire Danger Rating Indexes used by the North Central Forest Experiment Station, Forest Services
 * <P>
 * U.S. Department of Agriculture
 */

/**
 * @author Erica L. Muschelli
 * @version 1.0
 * @since 22 FEB 2016
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Class FireDangerMain.
 */
public class FireDangerMain {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) 
	{
		//Declare Variables
		int isSnow;
		int dryBulbReading;
		int wetBulbReading;
		double precipitation;
		int currentWindSpeed;
		double buildUpIndex;
		int herbaceousVegStage;
		double diff;
		
		double[] myDouble_A_Array = new double[]{-.185900,-.85900,-.059660, -.077373};
//		double[] myDouble_B_Array = new double[]{1,2,3};
		double[] myDouble_C_Array = new double[]{4.5,12.5,27.5};
		double[] myDouble_D_Array = new double[]{16.0,10.0,7.0,5.0,4.0,3.0};
		
		Scanner userInput = new Scanner(System.in);
		
		//Prompt user to enter in Fire Danger Data
		System.out.println("Please indicate if there is snow:  Enter the value 1 for YES or 0 for NO");
		isSnow = userInput.nextInt();
		
		System.out.println("Please enter in the Dry Bulb Reading value:");
		dryBulbReading = userInput.nextInt();
		
		System.out.println("Please enter in the Wet Bulb Reading value");
		wetBulbReading = userInput.nextInt();
		
		System.out.println("Please enter in the preceding 24-hour precipitation value: ");
		precipitation = userInput.nextInt();
		
		System.out.println("Please enter in the current wind speed value: ");
		currentWindSpeed = userInput.nextInt();
		
		System.out.println("Please enter in yesterday's build up index value:");
		buildUpIndex = userInput.nextInt();
		
		System.out.println("Please enter in the current hervaceous stage of vegetation value:");
		herbaceousVegStage = userInput.nextInt();
		
		//IF THERE IS SNOW ON THE GROUND, all spread index values are zero.
		//grass = 0, timber = 0, fire load = 0, build up will be adjusted for precipitation
		if (isSnow == 1)
		{
//			precipitation = precipitation - .1;
//			if (precipitation < 0 || precipitation == 0)
			if ((precipitation - .1)>0)
			{
				
				//precipitation exceeds .1 inches and the build up index is reduced. 
				buildUpIndex = -50 * Math.log(1.0 - (1.0 - Math.exp(-buildUpIndex/50)) * Math.exp(-1.175*precipitation-.1));
				if (buildUpIndex < 0)
				{
					buildUpIndex =0;
				}
			}
			else
			{
				
			}
		}	

		diff = dryBulbReading - wetBulbReading;
		
		//int count = 1;
		
		for (int i = 0; i <= 3; i++) 
		{
		    if (diff - myDouble_C_Array[i]>0) //C
		    {
		    	i=3;
		    }
		    //label 7: FFM=B[i]*exp (A[i]*diff)
		}
		
		for (int i = 0; i <= 5; i++) 
		{
		    if (diff - myDouble_D_Array[i]>0) //C
		    {
		    	//DF = i -1;
		    }
		    else
		    {
		    	//DF=6;
		    	break;
		    }
		}
	}
	
	/**
	 * Method calculates spread index based on if snow is on the ground or not.
	 * @param isSnow 
	 */
	public static void Snow(int isSnow)
	{
				
		//If there is no snow, the fuel moisture and drying factor is calculated.
				
	}
	
	public static void calculateFineFuelMoisture(int fuelMoisture)
	{
		
	}
	
	public static void calculateAdustedFuelMoisture(int fuelMoisture)
	{
		
	}
	public static void calculateBuildUpIndex(int buildUp)
	{
		
	}
	public static void calculateFineFuelSpread(int fuelSpread)
	{
		
	}
	public static void calculateTimberSpreadIndex(int timberSpread)
	{
		
	}
	
	public static void calculateFireLoadIndex(int fireLoad)
	{
		
	}
}
