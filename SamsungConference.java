
class Program {
	int start_m;
	int stop_m;
	int length;
}

public class SamsungConference {

	static int totalP = 8;
	static int programs[][] = new int [][] {{10,40,12,20},
									        {9,10,15,10},
									        {11,0,16,30},
									        {11,50,15,50},
									        {8,30,13,50},
									        {15,0,18,10},
											{9,30,10,10},
										    {13,10,18,30}};

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Program [] myConf = new Program[totalP];
		for (int i=0; i< totalP; i++){
			myConf[i] = new Program();
			myConf[i].start_m = programs[i][0]*60 + programs[i][1];
			myConf[i].stop_m = programs[i][2]*60 + programs[i][3];
			myConf[i].length = myConf[i].stop_m - myConf[i].start_m;
			System.out.println(myConf[i].length);
		}
		
		int [][] adjMat = new int [totalP][totalP];
		for (int i = 0; i < totalP; i++){
			System.out.println();
			for (int j = 0; j < totalP; j++){
				if (myConf[i].stop_m <= myConf[j].start_m) 
					adjMat[i][j] = 1;
				else adjMat[i][j] = 0;
				System.out.print(adjMat[i][j]);
			}
		}
		
	}

}
