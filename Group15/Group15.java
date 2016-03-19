import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import com.opencsv.CSVReader;
import java.io.FileWriter;
 
import com.opencsv.CSVWriter;

class Group15{
	public static void ParseCSV () throws IOException {
		
	String csvFile = "C:/Users/Aoife107/Documents/CASE4/CA4010/Report/split/Merged/merged_output10_13.csv";
	String csvFile2 = "C:/Users/Aoife107/Documents/CASE4/CA4010/Report/split/Merged/merged_output_14.csv";
	CSVReader reader = new CSVReader(new FileReader(csvFile));
	CSVReader reader2 = new CSVReader(new FileReader(csvFile2));
	ArrayList<String> accident_index 	= new ArrayList<String>();
	ArrayList<String> date 	= new ArrayList<String>();
	ArrayList<String> weather_cond = new ArrayList<String>();
	ArrayList<String> sex	= new ArrayList<String>();
	ArrayList<String> age	= new ArrayList<String>();
	ArrayList<String> age_band 	= new ArrayList<String>();
	ArrayList<String> severity 	= new ArrayList<String>();
	
	ArrayList<Double> severityList 	= new ArrayList<Double>();
	ArrayList<Double> agebandList 	= new ArrayList<Double>();
	ArrayList<Double> ageList 	= new ArrayList<Double>();
		
		
	String[] nextLine;
	boolean firstLine = true;
	
	try 
	{
		while ((nextLine = reader.readNext()) != null) 
		{
			if (firstLine) 
			{
				firstLine = false;
				continue;
			}
			if(nextLine != null)
			{
				accident_index.add(nextLine[0]);
			}
			if (nextLine != null)
			{
				date.add(nextLine[9]);
			}
			/*
			if(nextLine !=null)
			{
				day_of_week.add(nextLine[10]);
			}
			*/
			if (nextLine != null)
			{
				weather_cond.add(nextLine[8]);
			}
			if(nextLine != null){
				sex.add(nextLine[35]);
			}
			if(nextLine != null) 
			{
				age.add(nextLine[36]);		
			}
			if(nextLine != null) 
			{
				age_band.add(nextLine[37]);
			}
			if(nextLine != null) 
			{
				severity.add(nextLine[38]);
			}	
		}
		reader.close();
			
		ArrayList<Integer> sexList = new ArrayList<Integer>();
		ArrayList<String> dates = new ArrayList<String>();
		ArrayList<Double> ageband_listM = new ArrayList<Double>();
		ArrayList<Double> ageband_listF = new ArrayList<Double>();
		
		/*************************2014**************************/
		/*****DECLARE LISTS FOR 2014*****/
		ArrayList<String> accident_index2 = new ArrayList<String>();
		ArrayList<String> date2 	= new ArrayList<String>();
		ArrayList<String> weather_cond2 = new ArrayList<String>();
		ArrayList<String> sex2	= new ArrayList<String>();
		ArrayList<String> age2	= new ArrayList<String>();
		ArrayList<String> age_band2 	= new ArrayList<String>();
		ArrayList<String> severity2 	= new ArrayList<String>();
		
		ArrayList<Double> severityList2 	= new ArrayList<Double>();
		ArrayList<Double> agebandList2 	= new ArrayList<Double>();
		ArrayList<Double> ageband_listM14 = new ArrayList<Double>();
		ArrayList<Double> ageband_listF14 = new ArrayList<Double>();
		ArrayList<Double> ageList2 	= new ArrayList<Double>();
		ArrayList<Integer> sexList2 = new ArrayList<Integer>();
		
		/******************************/
		
		String[] nextLine2;
		boolean firstLine2 = true;
		while ((nextLine2 = reader2.readNext()) != null) 
		{
			if (firstLine2) 
			{
				firstLine2 = false;
				continue;
			}
			if(nextLine2 != null) 
			{
				accident_index2.add(nextLine2[0]);
			}
			if (nextLine2 != null)
			{
				date2.add(nextLine2[9]);
			}
			/*
			if(nextLine !=null)
			{
				day_of_week.add(nextLine[10]);
			}
			*/
			if (nextLine2 != null)
			{
				weather_cond2.add(nextLine2[8]);
			}
			if(nextLine2 != null) 
			{
				sex2.add(nextLine2[35]);	
			}
			if(nextLine2 != null) 
			{
				age2.add(nextLine2[36]);
			}
			if(nextLine2 != null) 
			{
				age_band2.add(nextLine2[37]);
			}
			if(nextLine2 != null) 
			{
				severity2.add(nextLine2[38]);
			}		
		}
		reader2.close();
		
		/*******storage for sample dataset results *******/
		float totalVals=0;
		float overall_sample_fatalities=0;
		float totalSampleMales =0, totalSampleFemales =0,totalSampleFatalM=0, totalSampleFatalF=0; 
		float fat_withAgeBandM=0, fat_withAgeBandF=0;
					
		double agemodeFatalF = 0;
		double agemodeFatalM = 0;
		/**************************************/
					
		/**************************************/
		/*******storage for 2014 results *******/
		float total2014=0;
		float overall_fatalities14=0;
		float totalMales14 =0, totalFemales14 =0,totalMaleFatal14=0, totalFemaleFatal14=0; 
		float fat_withAgeBandM14=0, fat_withAgeBandF14=0;
				
		/**************************************/
		double freq_gender = 0;
		double freq_ageband = 0;
		
		double agemodeFatalM14 = 0;
		double agemodeFatalF14 = 0;
		double agemodeFatal14 = 0;
		float accuracy = 0;
		float correct_samples= 0;
	
		float correct_age = 0;
		float gender_percent_fatal14 = 0;
		float correct_gender = 0;
			
		double prediction_gender = 0;
		double prediction_age = 0;
		/*We know value in dataset for male is 1 and female is 2*/
		double female = 2;
		double male = 1;
		
		double gender_percent_fatal = 0;
		double age_percent_fatal = 0;
		
		/******************************************************/
		for (int i = 0; i < accident_index.size(); i ++)
		{		
			//get all stuff for sample dataset and store into variables
			//get total of all values from sample dataset
			totalVals++;
			//GET total Genders
			int getSex = Integer.parseInt(sex.get(i));
			sexList.add(getSex);
			
			if(sexList.get(i)==1)
			{
				totalSampleMales ++;
			}else
			{
				totalSampleFemales ++;
			}
					
			//Get amount with fatal accident
			double severity_list = Double.parseDouble(severity.get(i));
			severityList.add(severity_list);
			if(severityList.get(i)==1)
			{
				overall_sample_fatalities ++;
			
				//check are they male and increment maleFatal counter
				if (sexList.get(i)==1)
				{
					totalSampleFatalM++;
					//get ageband mode for males
					//fat_withAgeBandM.add(getageband);
					double getageband = Double.parseDouble(age_band.get(i));
					ageband_listM.add(getageband);
					agemodeFatalM = getMode(ageband_listM);
					if(getageband == agemodeFatalM)
					{
						fat_withAgeBandM ++; 
					}
				}
				else if(sexList.get(i)==2)
				{
					//check are they female and increment femaleFatal counter
					totalSampleFatalF++;
					//get ageband mode for females
					double getageband = Double.parseDouble(age_band.get(i));
					ageband_listF.add(getageband);
					agemodeFatalF = getMode(ageband_listF);
					if(getageband == agemodeFatalF)
					{
						fat_withAgeBandF ++; 
					}
				}				
			}				
		}
		/**************************************************/
		/*	GET TEST FILES DATA */
		/**************************************************/
		agemodeFatalF = Math.round(agemodeFatalF);
		agemodeFatalM = Math.round(agemodeFatalM);
		
		
		/*****/
		if (totalSampleFatalF > totalSampleFatalM)
		{
			prediction_gender = female;
			prediction_age = agemodeFatalF;
			gender_percent_fatal = (totalSampleFatalF / overall_sample_fatalities) *100;	
			age_percent_fatal = (fat_withAgeBandF / overall_sample_fatalities)*100;
		}else
		{
			prediction_gender = male;
			prediction_age = agemodeFatalM;	
			gender_percent_fatal = (totalSampleFatalM / overall_sample_fatalities) *100;	
			age_percent_fatal = (fat_withAgeBandM/overall_sample_fatalities)*100;			
		}
		/*****/
		
		for (int i = 0; i < accident_index2.size(); i ++)
		{
			//get all stuff for 2014 and store into variables
			//get total of 2014
			total2014++;
			//Get ageband
			double getAgeBand = Double.parseDouble(age_band2.get(i));
			ageList2.add(getAgeBand);
			//GET total Genders
			int getSex = Integer.parseInt(sex2.get(i));
			sexList2.add(getSex);
			if(sexList2.get(i)==1)
			{
				totalMales14 ++;
			}else
			{
				totalFemales14 ++;
			}	
			//Get amount with fatal accident
			double severity_list = Double.parseDouble(severity2.get(i));
			severityList2.add(severity_list);
			if(severityList2.get(i)==1)
			{
				overall_fatalities14 ++;
				if(sexList2.get(i)==1)
				{
					totalMaleFatal14 ++;
				}
				else if(sexList2.get(i)==2)
				{
					totalFemaleFatal14 ++;
				}
				if(sexList2.get(i)==prediction_gender)
				{
					correct_gender ++;
					if(ageList2.get(i)==prediction_age)
					{
						agemodeFatal14 ++;
						correct_age ++;
					}
				}
				/*********************************/
				
				if(sexList2.get(i)==1)
				{
					double getageband = Double.parseDouble(age_band2.get(i));
					ageband_listM14.add(getageband);
					agemodeFatalM14 = getMode(ageband_listM14);
					if(getageband == agemodeFatalM14)
					{
						fat_withAgeBandM14 ++; 
					}
				}else if(sexList2.get(i)==2)
				{
					double getageband = Double.parseDouble(age_band2.get(i));
					ageband_listF14.add(getageband);
					agemodeFatalF14 = getMode(ageband_listF14);
					if(getageband == agemodeFatalF14)
					{
						fat_withAgeBandF14 ++; 
					}
				}					
			}			
		}
		
		if (totalMaleFatal14 > totalFemaleFatal14)
		{
			freq_gender = male;
			freq_ageband = agemodeFatalM14;
		}else 
		{
			freq_gender = female;
			freq_ageband = agemodeFatalF14;
		}	
		//Check gender prediction accuracy
		gender_percent_fatal14 = (correct_gender/overall_fatalities14)*100;	
		double the_accuracy_value = 0;
		if (gender_percent_fatal14 > gender_percent_fatal)
		{
			the_accuracy_value = 100-(((gender_percent_fatal14 - gender_percent_fatal)/gender_percent_fatal)*100);
		}else if (gender_percent_fatal14 < gender_percent_fatal)
		{
			the_accuracy_value = 100-(((gender_percent_fatal - gender_percent_fatal14)/gender_percent_fatal)*100);
		}
		//Check gender with correct ageband prediction accuracy
		double age_percent_fatal14 = (correct_age/correct_gender)*100;
		double age_accuracy = 0;
		if (age_percent_fatal14 > age_percent_fatal)
		{
			age_accuracy = 100-(((age_percent_fatal14 - age_percent_fatal)/age_percent_fatal)*100);
		}else if (age_percent_fatal14 < age_percent_fatal)
		{
			age_accuracy = 100-(((age_percent_fatal - age_percent_fatal14)/age_percent_fatal)*100);
		}
		double percent_fem_fat_and_ageband = (fat_withAgeBandF14/totalFemaleFatal14)*100;
		/**PRINT STATEMENTS**/
		/**Sample Set**/
		System.out.println("\nSample Set: \n----------\n\nNumber of accidents overall: " + totalVals + "\nNumber of fatalities: " + overall_sample_fatalities + 
		"\nCount of female Accidents: " + totalSampleFemales + "\nCount of male Accidents: " + totalSampleMales + "\nCount of fatal females: " + totalSampleFatalF +
		"\nCount of fatal males: " + totalSampleFatalM + "\n\nprediction gender: " + prediction_gender  +"\nPercent of most common gender in a fatality: " + 
		gender_percent_fatal +"\nTotal Female Fatalities With AgeBand=Mode: " +fat_withAgeBandF +
		"\nTotal male Fatalities With AgeBand=Mode: " + fat_withAgeBandM +"\n most popular AgeBand for Females: " + agemodeFatalF +
		"\nMost popular AgeBand for Males: " + agemodeFatalM+"\nCount of people in sever accident of predicted gender WITH predicted ageband = " + agemodeFatal14);
		/****/
		/**2014 Test Set**/		

		System.out.println("\n\n2014 Test Set: \n----------\n\nNumber of accidents overall: " + total2014 + "\nNumber of fatal accidents: "+ overall_fatalities14 
		+"\nMost common gender in fatality: " + freq_gender +
		"\nNumber of fatalities: " +  overall_fatalities14 + "\nCount of fatal males: " +totalMaleFatal14+ "\nCount of fatal females: " + totalFemaleFatal14 +
		"\nCount of gender matching predicted Gender: " + correct_gender+ "\nPercent Fatal Females with age band = 11 " + percent_fem_fat_and_ageband +
		"\nPercent of that gender in fatality: " + gender_percent_fatal14 + "%" + "\nMode of gender: " + freq_gender 
		+ "\nFatality Percent For that Gender: " + 	"\n\nAccuracy of prediction:\nThat gender type " + freq_gender+ 
		" (1 = male, 2 = female) will be in a fatality in 2014 is: \n" + the_accuracy_value);
		/****/
		
		System.out.println("Percentage of FATALITIES with most frequently occurring gender who also are within the ageband mode (for fatalities in that gender): \nsample set: \n" +
		age_percent_fatal + "\n\n2014: \n"+age_percent_fatal14 +"\n\nAccuracy of prediction for fatality to occur to Gender of " + freq_gender + 
		" and age band to be " + freq_ageband + " is: \n" + age_accuracy);
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	

	public static double getMode(ArrayList<Double> temp_list) {
		
		double[] array = toArray(temp_list); 
		
	    int maxValue = 0;
	    int maxCount = 0;

	    for (int i = 0; i < array.length; i++) {
	        
	    	int count = 0;
	        for (int j = 0; j < array.length; j++) {
	            if (array[j] == array[i]) {
	            	 count++;
	            }
	           
	        }
	        if (count > maxCount) {
	            maxCount = count;
	            maxValue = (int) array[i];
	        }
	    }

	    return maxValue;
	}
	
	public static double[] toArray(ArrayList<Double> agebandList ) {
		
		double[] arr = new double[agebandList.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = agebandList.get(i);
		}
		return arr;
	}
	
	public static void main (String[] args) throws IOException {
		
		ParseCSV();
		
	}
}
