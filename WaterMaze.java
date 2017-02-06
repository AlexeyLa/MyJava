
class WPoint{
	int type;
	int waveAmp;
	boolean isOpen;
	boolean isSpecial;
}

public class WaterMaze {

	private static int length = 6;
	private static int counterElem;
	private static int[] paths = new int[3];
    private static int [][] maze = new int[][]{{3, 0, 0, 0, 0, 3},
							                   {2, 0, 0, 0, 0, 6},
							                   {1, 3, 1, 1, 3, 1},
							                   {2, 0, 2, 0, 0, 2},
							                   {0, 0, 4, 3, 1, 1}};
	
    private static int [][] mapR = new int [][]{{1,0,1,0,0,1,1},
    											{0,0,0,0,0,0,0},
    											{1,0,1,0,0,1,1},
    											{1,0,1,0,0,1,1},
    											{1,0,1,0,0,1,1},
    											{0,0,0,0,0,0,0},
    											{0,0,0,0,0,0,0}};
    private static int [][] mapL = new int [][]{{1,0,1,1,1,0,0},
												{0,0,0,0,0,0,0},
												{1,0,1,1,1,0,0},
												{0,0,0,0,0,0,0},
												{0,0,0,0,0,0,0},
												{1,0,1,1,1,0,0},
												{1,0,1,1,1,0,0}};
    private static int [][] mapU = new int [][]{{1,1,0,0,1,1,0},
												{1,1,0,0,1,1,0},
												{0,0,0,0,0,0,0},
												{1,1,0,0,1,1,0},
												{0,0,0,0,0,0,0},
												{0,0,0,0,0,0,0},
												{1,1,0,0,1,1,0}};
    private static int [][] mapD = new int [][]{{1,1,0,1,0,0,1},
												{1,1,0,1,0,0,1},
												{0,0,0,0,0,0,0},
												{0,0,0,0,0,0,0},
												{1,1,0,1,0,0,1},
												{1,1,0,1,0,0,1},
												{0,0,0,0,0,0,0}};
    
    
    
    static int getMax(int [] array)
    {
    	int maxVal = array[0];
    	for (int i = 1; i < array.length; i++)
    	{
    		if (array[i] > maxVal)	maxVal = array[i];
    	}
    	return maxVal;
    }
       
	static int[][] getNeighbors (WPoint [][] leemaze, int [][] pointsArray, int step)
	{
		int [][] neighbors = new int [2][leemaze.length*leemaze[0].length];
		int numNeighbors = -1;
		for (int i = 0; i < pointsArray[0].length; i++){
			int row = pointsArray[0][i];
			int col = pointsArray[1][i];
			int curType = leemaze[row][col].type;		
			if (((row + 1) < leemaze.length) && (leemaze[row + 1][col].waveAmp == 0) && (leemaze[row + 1][col].type != 0) && (mapD[curType][(leemaze[row + 1][col].type)] == 1))// condition
				{
				 numNeighbors++;
				 neighbors[0][numNeighbors] = row + 1;
				 neighbors[1][numNeighbors] = col;
				 leemaze[row + 1][col].waveAmp = step + 1;
				 counterElem++;
				}
			if (((row - 1) >= 0) && (leemaze[row - 1][col].waveAmp == 0) && (leemaze[row - 1][col].type != 0) && (mapU[curType][(leemaze[row - 1][col].type)] == 1))
				{
				numNeighbors++;
				neighbors[0][numNeighbors] = row - 1;
				neighbors[1][numNeighbors] = col;
				leemaze[row - 1][col].waveAmp = step + 1;
				counterElem++;
				}
			if (((col + 1) < leemaze[0].length) && (leemaze[row][col + 1].waveAmp == 0) && (leemaze[row][col + 1].type != 0) && (mapR[curType][(leemaze[row][col + 1].type)] == 1))
				{
				numNeighbors++;
				neighbors[0][numNeighbors] = row;
				neighbors[1][numNeighbors] = col + 1;
				leemaze[row][col + 1].waveAmp = step + 1;
				counterElem++;
				}
			if (((col - 1) >= 0) && (leemaze[row][col - 1].waveAmp == 0) && (leemaze[row][col - 1].type != 0) && (mapL[curType][(leemaze[row][col - 1].type)] == 1)) 
				{
				numNeighbors++;
				neighbors[0][numNeighbors] = row;
				neighbors[1][numNeighbors] = col - 1;
				leemaze[row][col - 1].waveAmp = step + 1;
				counterElem++;
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
		WPoint[][] fullMaze = new WPoint[5][6];	
		// create structure
		for (int i = 0; i< fullMaze.length; i++) {
			for (int j = 0; j < fullMaze[0].length; j++){
				fullMaze[i][j] = new WPoint();
				fullMaze[i][j].type = (maze[i][j]);
			}
		}
		int [][] startPoint = new int[][]{{2},{2}};
		fullMaze[startPoint[0][0]][startPoint[1][0]].waveAmp = -1;
		int step = 0;
		while (step < length ) {
			int [][] curNeighbors;
			curNeighbors = getNeighbors(fullMaze, startPoint, step);
			startPoint = curNeighbors;
			step++;
		}
		System.out.println(" Total elements reached " + counterElem);
	}	
}
