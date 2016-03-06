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
	 * @param args
	 *            the arguments
	 */
	
	//BEGIN - Main Method
	public static void main(String[] args) 
	{
		//Declare Variables
		//Default values set
		int isSnow =0; 
		int iDryBulbTemperature = 0; 
		int iWetBulbTemperature = 0; 
		int iHerbState = 0; 
		int iTimberSpreadIndex = 0;
		int iWindSpeed = 0; 
		int iGrassSpreadIndex = 0; 
		double dPrecipitation = 0; 
		double dbuildUpIndex = 0; 
		double dFineFuleMoisture = 99; 
		double dAdjusted10DayFuleMoisture = 99; 
		double dDryFactor = 0; 
		double dFireLoadRatio = 0; 
	
		Scanner userInput = new Scanner(System.in);
		
		//Prompt user to enter in Fire Danger Data from console
		System.out.println("Please indicate if there is snow:  [Enter eithere 1=Yes or 2=No]");
		isSnow = userInput.nextInt();
		
		System.out.println("Please enter in the Dry Bulb Reading value:");
		iDryBulbTemperature = userInput.nextInt();
		
		System.out.println("Please enter in the Wet Bulb Reading value");
		iWetBulbTemperature = userInput.nextInt();
		
		System.out.println("Please enter in the preceding 24-hour precipitation value: ");
		dPrecipitation = userInput.nextInt();
		
		System.out.println("Please enter in the current wind speed value: ");
		iWindSpeed = userInput.nextInt();
		
		System.out.println("Please enter in yesterday's build up index value:");
		dbuildUpIndex = userInput.nextInt();
		
		System.out.println("Please enter in the current hervaceous stage of vegetation value: [Enter either 1=Cured, 2=Transition, 3=Green");
		iHerbState = userInput.nextInt();
		
		//Print out value entered in by user
		System.out.println("*************************************************");
		System.out.println("The values you entered are listed below:");
		System.out.println("*************************************************");
		System.out.println("Snow = " + isSnow);
		System.out.println("Dry Bulb Temperature = " + iDryBulbTemperature);
		System.out.println("Wet Bulb Temperature = " + iWetBulbTemperature);
		System.out.println("Precipitation = " + dPrecipitation); 
		System.out.println("Wind Speed = " + iWindSpeed); 
		System.out.println("Build Up Index = " + dbuildUpIndex);
		System.out.println("Herb State = " + iHerbState);		

		
		//Values passed into Danger Method for calculation
		Danger(	iDryBulbTemperature, iWetBulbTemperature, 
				isSnow, dPrecipitation, iWindSpeed, 
				dbuildUpIndex, iHerbState, dDryFactor,
				dFineFuleMoisture, dAdjusted10DayFuleMoisture, 
				iGrassSpreadIndex, iTimberSpreadIndex,
				dFireLoadRatio);
		
						
	} // END - Main Method
	
	/**
	 * Danger.
	 *
	 * @param iDRY
	 *            the i dry
	 * @param iWET
	 *            the i wet
	 * @param iSNOW
	 *            the i snow
	 * @param dPRECIP
	 *            the d precip
	 * @param iWIND
	 *            the i wind
	 * @param dBUO
	 *            the d buo
	 * @param iHERB
	 *            the i herb
	 * @param dDF
	 *            the d df
	 * @param dFFM
	 *            the d ffm
	 * @param dADFM
	 *            the d adfm
	 * @param iGRASS
	 *            the i grass
	 * @param iTIMBER
	 *            the i timber
	 * @param dFLOAD
	 *            the d fload
	 */
	public static void Danger(	int iDRY, int iWET, int iSNOW, double dPRECIP, 
								int iWIND, double dBUO, int iHERB, double dDF, 
								double dFFM, double dADFM, int iGRASS,
								int iTIMBER, double dFLOAD)
	{
		//Routine variables declared
		double dDIF;
		
		/**
		 * Arrays have been used to contain the table values used in computing the Danger Ratings
		 * The names of the arrays are listed below:
		 * <ul>
		 * <li>myDouble_A_Array</li>
		 * <li>myDouble_B_Array</li>
		 * <li>myDouble_C_Array</li>
		 * <li>myDouble_D_Array</li>
		 * </ul> 
		 */
		double[] myDouble_A_Array = new double[]{0,-.1859,-.859,-.05966, -.077373};
		double[] myDouble_B_Array = new double[]{0,30.0,19.2,13.8,22.5};
		double[] myDouble_C_Array = new double[]{0,4.5,12.5,27.5};
		double[] myDouble_D_Array = new double[]{0,16,10,7,5,4,3};
				
		/**
		 * If the User has indicated that there is snow on the ground, all spread index values are set to zero.
		 * [Grass = 0, Timber = 0, Fire Load = 0, Build Up will be adjusted for Precipitation]
		 * If there is NO Snow on the ground, then the Spread Indexes, Fire Load, Build Up Index, and Precipitation will be calculated.
		 * This calculation takes place within the "if" conditional statement within the "Danger" method.
		 */
		
		//BEGIN - NO Snow on Ground
		if (iSNOW <= 0) //No Snow
		{
			
			//There is no Snow on the ground computing the Spread Indexes and Fire Load
			
			//BEGIN - Calculate Fine Fuel Moisture; Line 33
			dDIF = iDRY - iWET;
			for (int i = 1; i <= 4; i++) 
			{
			    if (((dDIF - myDouble_C_Array[i])<=0) ||  i == 4)
			    {
			    	dFFM=myDouble_B_Array[i]* Math.exp(myDouble_A_Array[i]*dDIF);
				    break;
			    }
			}
			//END - Calculate Fine Fuel Moisture; line 38
			
			/**
			 * The program will calculate the Drying Factor for the day.
			 */
			
			//Find the drying factor for the day
			//BEGIN -  Calculate Drying Factor; line 39
			for (int i = 1; i <= 6; i++)
			{
			    if ((dFFM - myDouble_D_Array[i])>0)
			    {
			    	dDF = i-1;
			    	break;
			    }
			    else
			    {
			    	dDF=7;
			    }
			}
			//End - Calculate Drying Factor; line 44
			
			/**
			 * A test will be conducted to see if the Fine Fuel Moisture has a value of one or less.
			 * If the Fine Fuel Moisture is equal to 1 or less, it will be reset to the value of 1.
			 */
			//Test to see if the Fine Fuel Moisture is one or less.
			//If Fine Fuel Moisture is 1 or less will set to 1.
			
			//BEGIN - Adjust Fine Fuel For Herb Stage; line 45
			if (dFFM-1.0 < 0)
			{
				dFFM=1;
			}

			dADFM = dADFM(dPRECIP, dBUO, iHERB, dDF, dFFM, dADFM);
			dFFM = dFFM + (iHERB - 1) * 5;
			//END - Adjust Fine Fuel For Herb Stage; line 47
			
			/**
			 * The precipitation value will be determined to see if it exceeds .1 inches.  
			 * If the precipitation value does exceed .1 inches, then the Build Up Index will be reduced.
			 */
			
			//BEGIN - Is raining; line 48
			if (isRaining(dPRECIP))
			{
				//BEGIN -  Increase BUI by Drying Factor; line 49
				//Precipitation exceeds .1 inches and the Build Up Index is reduced. 
				dBUO = -50 * Math.log(1.0 - (1.0 - Math.exp(-dBUO/50)) * Math.exp(-1.175*dBUO-.1));
				if (dBUO < 0)
					dBUO =0;
				else
					dBUO = dBUO + dDF;
				//END -  Increase BUI by Drying Factor; line 52
			}
			//END -  Is raining; line 52

			/**
			 * The Grass Index is adjusted for Heavy Fuel Lags.
			 * This will result in the Timber Spread Index, the Adjusted Fuel Moisture, the ADFM, the adjusted Heavy Fuels will not be computed. 
			 */
			
			//Adjust the Grass Index for Heavy Fuel Lags.
			//The result will be the Timber Spread Index, the Adjusted Fuel Moisture, ADFM, Adjusted for Heavy Fuels, Will not be computed
			
			

			/**
			 * The program tests to see if the fuel moisture is grater than 30%.  
			 * If so, then the Grass and Timber Spread Indexes will be set to 1 and the Spread Indexes, Build Up Index, and Fire Load Rating are printed and the program exits.
			 * 
			 */
			
			// Test to see if the fuel moisture is greater than 30 percent.
			// If so, then the Grass and Timber Spread Indexes will be set to 1.
			
			//BEGIN - Fine Fuel Greater than 30; line 54
			if (dADFM>=30)
			{
				iTIMBER = 1;
				if (dFFM >=30)
				{
					iGRASS = 1; 
					
					//Print out Grass and Timber Spread Indexes Calculated
					System.out.println("*************************************************");
					System.out.println("The Fire Danger Spread Indexes are printed below:");
					System.out.println("*************************************************");
					System.out.println("The Grass Spread Index = " + iGRASS);
					System.out.println("The Timber Spread Index = " + iTIMBER);
					System.out.println("The Build Up Index = " + dBUO);
					System.out.println ("Fire Load Rating = " + dFLOAD);
					return;
				}					
			}
			//END - Fine Fuel Greater than 30; line 60
			
			/**
			 * The program tests to see if the Wind Speed is greater then 14 miles per hour. 
			 */
			
			//Test to see if the Wind Speed is greater then 14 mph.
			 
			//BEGIN - WIND Greater than 14; Line 60
			//BEGIN - Calculate Grass and Timber Spread indexes inside of "if"
			if (iWIND < 14) // Wind Speed is less 14
			{
				if (iTIMBER != 1)
					iTIMBER = (int) (.01312 * (iWIND + 6) * (Math.pow((33 - dADFM),1.65) - 3));
				
				iGRASS = (int) ((.01312 * (iWIND + 6)) * (Math.pow((33 - dFFM),1.65) - 3));
				if (iTIMBER <=0)
					iTIMBER = 1;
				
				if (iGRASS < 0 )
					iGRASS =1;
			}
			else // Wind Speed is greater than 14
			{
				if (iTIMBER != 1)
					iTIMBER = (int) ((.00918 * (iWIND + 14)) * (Math.pow((33 - dADFM),1.65) - 3));
				
				iGRASS = (int)(.00918 * (iWIND + 14) * (Math.pow((33 - dFFM), 1.65) - 3));
				
				if (iGRASS > 0)
					iGRASS = 99;
				
				if (iTIMBER > 99)
					iTIMBER = 99;
			}
			//END - Calculate Grass and Timber Spread indexes inside of "if"
			//END - WIND Greater than 14; Line 74
			
			/**
			 * After the Grass and Timber Spread Indexes of the National Fire Danger Rating System have been calculated,
			 * the program will calculate the the Build Up Index.  If either are zero, then the Fire Load will also be zero.
			 * The program will print out the Spread Indexes, the Build Up Index, and the Fire Load and exit the program.
			 */
			
			//The Grass and Timber Spread Indexes of the National Fire Danger Rating System have been computed.
			//We  will Calculate the Build Up Index has been obtained.
			
			//If either are zero then the Fire Load will be zero.
			
			//BEGIN - Both BUI and TIMBER Spread index = 0; Line 75
			if ((iTIMBER <= 0) & (dBUO <= 0))
			{
				//Print out Spread Indexes and Build Up Index 
				System.out.println("*************************************************");
				System.out.println("The Fire Danger Spread Indexes are listed below:");
				System.out.println("*************************************************");
				System.out.println ("Timber Spread Index = " + iTIMBER);
				System.out.println ("Grass Spread Index = " + iGRASS);
				System.out.println ("Buid Up Index = " + dBUO);
				System.out.println ("Fire Load Rating = " + dFLOAD);
				return;
			}
			//End - Both BUI and TIMBER Spread index = 0; LINE 77
			
			/**
			 * Once the Build Up Indexes has been obtained. The Fire Load Rating will be calculated.
			 * It is necessary that neither the Timber Spread Index nor the Build Up Indexes are 0.
			 * If the Fire Load Rating calculated is less then 0, then the Fire Load Rating will be set to 0.
			 * After the Fire Load Rating has been calculated, the spread indexes, the Build Up Index, and the Fire rating will be printed out and the program will end.
			 */
			//The Build Up Index has been obtained.
			//It is necessary that neither the Timber Spread Nor the Build Up Indexes be zero.
						
			//We will calculate the Fire Load Rating
			
			//BEGIN - Calculate Fire Load Index; Line 78
			dFLOAD = (1.75 * Math.log10(iTIMBER)) + ((.32 * Math.log10(dBUO)) - 1.64);
	
			//Ensure that the FLOAD is greater then zero otherwise set it to zero
			if (dFLOAD <= 0)
			{
				dFLOAD = 0;
			}
			else
			{
				dFLOAD = Math.pow(10,dFLOAD);
			}
			//END - Calculate Fire Load Index; Line 84
			
			//Print out Spread Indexes and Build Up Index 
			System.out.println("*************************************************");
			System.out.println("The Fire Danger Spread Indexes are listed below:");
			System.out.println("*************************************************");
			System.out.println ("Timber Spread Index = " + iTIMBER);
			System.out.println ("Grass Spread Index = " + iGRASS);
			System.out.println ("Buid Up Index = " + dBUO);
			System.out.println ("Fire Load Rating = " + dFLOAD);
		}	
		//END - NO Snow on Ground
		
		/**
		 * If there is SNOW on the Ground and the precipitation exceeds .1 inches, then the Build Up Index will be reduced.
		 * All spread indexes, the Build Up Index, and the Fire Load Rating will be printed.
		 * <p>
		 * If there is SNOW on the ground and no precipitation then the spread indexes are set to zero.
		 * All spread indexes, the Build Up Index, and the Fire Load Rating will be printed and the program will end.
		 */
		
		//BEGIN - YES Snow on Ground
		else 
		{
			if (isRaining(dPRECIP))
			{
				//precipitation exceeds .1 inches and the build up index is reduced. 
				dBUO = -50 * Math.log(1.0 - (1.0 - Math.exp(-dBUO/50)) * Math.exp(-1.175 * dBUO - .1));
				if (dBUO < 0)
				{
					dBUO =0;
				}
			}
			//No Rain set all indexes to zero
			iTIMBER = 0;
			iGRASS = 0;
			
			//Print out Spread Indexes and Build Up Index 
			System.out.println("*************************************************");
			System.out.println("The Fire Danger Spread Indexes are listed below:");
			System.out.println("*************************************************");
			System.out.println ("Timber Spread Index = " + iTIMBER);
			System.out.println ("Grass Spread Index = " + iGRASS);
			System.out.println ("Buid Up Index = " + dBUO);
			System.out.println ("Fire Load Rating = " + dFLOAD);
		}
	}

	private static double dADFM(double dPRECIP, double dBUO, int iHERB,
			double dDF, double dFFM, double dADFM) {
		dFFM = dFFM + (iHERB - 1) * 5;
		if (isRaining(dPRECIP)) {
			dBUO = -50
					* Math.log(1.0 - (1.0 - Math.exp(-dBUO / 50))
							* Math.exp(-1.175 * dBUO - .1));
			if (dBUO < 0)
				dBUO = 0;
			else
				dBUO = dBUO + dDF;
		}
		dADFM = .9 * dFFM + .5 + 9.5 * Math.exp(-dBUO / 50);
		return dADFM;
	}
	
	/**
	 * Checks if is raining.
	 *
	 * @param dPrecipitation
	 *            the d precipitation
	 * @return true, if is raining
	 */
	public static boolean isRaining(double dPrecipitation) 
	{
		if ((dPrecipitation - .1)>0)
		{
			return true;
		}
		else
			return false;
	} 
}
