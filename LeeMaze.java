
class MPoint{
	int waveAmp;
	boolean isOpen;
	boolean isSpecial;
}

public class LeeMaze {

	private static int counterPaths;
	private static int[] paths = new int[3];
    private static int [][] maze = new int[][]{{1, 1, 1, 1, 0, 1, 0, 0},
							                   {0, 1, 0, 0, 0, 1, 1, 1},
							                   {0, 1, 1, 1, 0, 1, 0, 1},
							                   {0, 1, 0, 1, 1, 1, 0, 0},
							                   {1, 1, 0, 0, 0, 1, 1, 1},
							                   {1, 0, 0, 1, 1, 1, 0, 1},
							                   {1, 1, 1, 1, 0, 0, 0, 1}};
	
    static int getMax(int [] array)
    {
    	int maxVal = array[0];
    	for (int i = 1; i < array.length; i++)
    	{
    		if (array[i] > maxVal)	maxVal = array[i];
    	}
    	return maxVal;
    }
       
	static int[][] getNeighbors (MPoint [][] leemaze, int [][] pointsArray, int step)
	{
		int [][] neighbors = new int [2][leemaze.length*leemaze[0].length];
		int numNeighbors = -1;
		for (int i = 0; i < pointsArray[0].length; i++){
			int row = pointsArray[0][i];
			int col = pointsArray[1][i];
			if (((row + 1) < leemaze.length) && (leemaze[row + 1][col].isOpen == true) && (leemaze[row + 1][col].waveAmp == 0)) 
				{
				 numNeighbors++;
				 neighbors[0][numNeighbors] = row + 1;
				 neighbors[1][numNeighbors] = col;
				 leemaze[row + 1][col].waveAmp = step + 1;
				 if (leemaze[row + 1][col].isSpecial == true) {
					 paths[counterPaths] = step + 1;
					 counterPaths++;
					 leemaze[row + 1][col].isSpecial = false;
				 }
				}
			if (((row - 1) >= 0) && (leemaze[row - 1][col].isOpen == true) && (leemaze[row - 1][col].waveAmp == 0))
				{
				numNeighbors++;
				neighbors[0][numNeighbors] = row - 1;
				neighbors[1][numNeighbors] = col;
				leemaze[row - 1][col].waveAmp = step + 1;
				 if (leemaze[row - 1][col].isSpecial == true) {
					 paths[counterPaths] = step + 1;
					 counterPaths++;
					 leemaze[row - 1][col].isSpecial = false;
				 }
				}
			if (((col + 1) < leemaze[0].length) &&(leemaze[row][col + 1].isOpen == true) && (leemaze[row][col + 1].waveAmp == 0))
				{
				numNeighbors++;
				neighbors[0][numNeighbors] = row;
				neighbors[1][numNeighbors] = col + 1;
				leemaze[row][col + 1].waveAmp = step + 1;
				 if (leemaze[row][col + 1].isSpecial == true) {
					 paths[counterPaths] = step + 1;
					 counterPaths++;
					 leemaze[row][col + 1].isSpecial = false;
				 }
				}
			if (((col - 1) >= 0) && (leemaze[row][col - 1].isOpen == true) && (leemaze[row][col - 1].waveAmp == 0))
				{
				numNeighbors++;
				neighbors[0][numNeighbors] = row;
				neighbors[1][numNeighbors] = col - 1;
				leemaze[row][col - 1].waveAmp = step + 1;
				 if (leemaze[row][col - 1].isSpecial == true) {
					 paths[counterPaths] = step + 1;
					 counterPaths++;
					 leemaze[row][col - 1].isSpecial = false;
				 }
				}
		}
		int [][] cutNhbrs = new int [2][numNeighbors + 1];
		for(int i = 0; i < 2; i++){
			for(int j=0; j < numNeighbors + 1; j++){
				cutNhbrs[i][j] = neighbors[i][j];
			}
		}	
	return cutNhbrs;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MPoint[][] fullMaze = new MPoint[7][8];	
		int totalSites = 0;
		int [][] max_paths = new int[3][fullMaze.length*fullMaze[0].length];
		// create structure
		for (int i = 0; i< fullMaze.length; i++) {
			for (int j = 0; j < fullMaze[0].length; j++){
				fullMaze[i][j] = new MPoint();
				fullMaze[i][j].isOpen = (maze[i][j] == 1);
			}
		}
		// mark special points - end points
		fullMaze[0][2].isSpecial = true;
		fullMaze[2][1].isSpecial = true;
		fullMaze[6][7].isSpecial = true;

		for (int m1 = 0; m1 < fullMaze.length; m1++){
			for (int n1 = 0; n1 < fullMaze[0].length; n1++){
		
				if ((fullMaze[m1][n1].isOpen == true) && (fullMaze[m1][n1].isSpecial == false)){
					
					counterPaths = 0;
					int [][] startPoint = new int[][]{{m1},{n1}};
					// mark initial point
					fullMaze[startPoint[0][0]][startPoint[1][0]].waveAmp = -1;
		
					int step = 0;
					while (counterPaths < 3){
						int [][] curNeighbors;
						curNeighbors = getNeighbors(fullMaze, startPoint, step);
					/*	for (int i = 0; i< fullMaze.length; i++) {
							System.out.println();
							for (int j = 0; j < fullMaze[0].length; j++){
								System.out.print(fullMaze[i][j].waveAmp);
							}
						}*/
						startPoint = curNeighbors;
						step++;
					}
				fullMaze[0][2].isSpecial = true;
				fullMaze[2][1].isSpecial = true;
				fullMaze[6][7].isSpecial = true;
				max_paths[0][totalSites] = m1;
				max_paths[1][totalSites] = n1;
				max_paths[2][totalSites] = getMax(paths);
				System.out.println(max_paths[2][totalSites]);
				totalSites++;
				for (int i = 0; i< fullMaze.length; i++) {
					for (int j = 0; j < fullMaze[0].length; j++){
						fullMaze[i][j].waveAmp = 0;
						}
					}
				}
			}	
		}
		
 	    int index = 0;
		int firstVal = max_paths[2][0];
		for (int i=0; i < max_paths[0].length; i++)
		{
			if ((max_paths[2][i] <= firstVal) && (max_paths[2][i]!=0))
			{
				index = i;
				firstVal = max_paths[2][i];
			}
		}
		System.out.println(" Construction position is: " + max_paths[0][index] + " - " + max_paths[1][index]);
		System.out.println(" max path is: " + firstVal);
	}	
}
