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

	public static double precipitation;
	public static double buildUpIndex;
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
		int currentWindSpeed;
		int herbaceousVegStage;
		int TIMBER;
		int GRASS;
		double diff;
		double FFM = 99; //Fine Fuel Moisture
		double ADFM = 99; //Adjusted (10 Day Lag) Fuel Moisture
		double DF = 0; //Drying Factor
		double FLOAD = 0; //Fire Load Rating (Man-Hour Base)
		
		double[] myDouble_A_Array = new double[]{0,-.185900,-.85900,-.059660, -.077373};
		double[] myDouble_B_Array = new double[]{0,30.0,19.2,13.8,22.5};
		double[] myDouble_C_Array = new double[]{0,4.5,12.5,27.5};
		double[] myDouble_D_Array = new double[]{0,16.0,10.0,7.0,5.0,4.0,3.0};
		
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
		if (isSnow <= 0) //No Snow
		{
			
			// Will refactor each one into a Method if I have the time
			//Begin Calculate Fine Fuel Moisture; Line 33
			diff = dryBulbReading - wetBulbReading;
			for (int i = 1; i <= 4; i++) 
			{
				if (i<4)
				{
				    if ((diff - myDouble_C_Array[i])<=0)
				    {
					    FFM=myDouble_B_Array[i]* Math.exp(myDouble_A_Array[i]*diff);
					    break;
				    }
				}
				else
					FFM=myDouble_B_Array[i]* Math.exp(myDouble_A_Array[i]*diff);
			}
			//End Calculate Fine Fuel Moisture; line 38
			
			//Begin Calculate Drying Factor; line 39
			for (int i = 1; i <= 6; i++)
			{
			    if ((FFM - myDouble_D_Array[i])>0)
			    {
			    	DF = i-1;
			    	break;
			    }
			    else
			    {
			    	DF=7;
			    }
			}
			//End Calculate Drying Factor; line 44
			
			//Begin Adjust Fine Fuel For Herb Stage; line 45
			if (FFM-1.0 <0)
				FFM=1;
			else
				FFM = FFM + (herbaceousVegStage-1) *5.0;
			//End Adjust Fine Fuel For Herb Stage; line 47
			
			//line 48
			if (isRaining())
			{
				AdjustBuildUpIndex();
			}
			//line 49
			
			//Start Increase BUI by Drying Factor; line 50
			if (buildUpIndex <0)
				buildUpIndex = 0.0;
			else
				buildUpIndex = buildUpIndex + DF;
			//End Increase BUI by Drying Factor; line 52
			
			//Start Calculate Adjusted Fuel Moisture; line 53
			ADFM = .9*FFM + .5 + 9.5 * Math.exp(-buildUpIndex/50);
			//End Calculate Adjusted Fuel Moisture; line 54

			//Start Fine Fuel Greater than 30%; line 55
			if (ADFM-30 >= 0)	
			{
				TIMBER = 1;
				if (FFM -30 >=0)
				{
					GRASS = 1;
					//Print all
					return;
				}
				
			}
			//End Fine Fuel Greater than 30%; line 55
			
			//Start Wind Greater Than 14; 
			
			//End Wind Greater Than 14;
			
		}	
		else //Yes on snow
		{
			if (isRaining())
			{
				AdjustBuildUpIndex();
			}
		}
	//Print all
	} // End of main
	
	public static boolean isRaining() 
	{
		if ((precipitation - .1)>0)
		{
			return true;
		}
		else
			return false;
	} 
	
	public static void AdjustBuildUpIndex() 
	{
		//precipitation exceeds .1 inches and the build up index is reduced. 
		buildUpIndex = -50 * Math.log(1.0 - (1.0 - Math.exp(-buildUpIndex/50)) * Math.exp(-1.175*precipitation-.1));
		if (buildUpIndex < 0)
		{
			buildUpIndex =0;
		}
	} 
}
