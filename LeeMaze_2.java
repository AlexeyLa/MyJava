class MPoint{
	int waveAmp;
	boolean isOpen;
	boolean isChecked;
	boolean isSpecial;
	boolean isStart;
}

public class LeeMaze {

    private static int [][] maze = new int[][]{{1, 1, 1, 1, 0, 1, 0, 0},
							                   {0, 1, 0, 0, 0, 1, 0, 1},
							                   {0, 1, 1, 1, 0, 1, 0, 1},
							                   {0, 1, 0, 1, 1, 1, 0, 0},
							                   {1, 1, 0, 0, 0, 1, 1, 0},
							                   {1, 0, 0, 1, 1, 1, 0, 0},
							                   {1, 1, 1, 1, 0, 0, 0, 1}};
	
static void showMaze(int [][] maze)
{
	for (int i = 0; i< maze.length; i++){
		System.out.println();
		for (int j=0; j < maze[0].length; j++){
			System.out.print(maze[i][j]);
		}
	}
}

static int[][] getNeighbors (MPoint [][] leemaze, int [][] pointsArray, int step)
{
	int [][] neighbors = new int [2][4*leemaze.length*leemaze[0].length];
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
			}
		if (((row - 1) >= 0) && (leemaze[row - 1][col].isOpen == true) && (leemaze[row - 1][col].waveAmp == 0))
			{
			numNeighbors++;
			neighbors[0][numNeighbors] = row - 1;
			neighbors[1][numNeighbors] = col;
			leemaze[row - 1][col].waveAmp = step + 1;
			}
		if (((col + 1) < leemaze[0].length) &&(leemaze[row][col + 1].isOpen == true) && (leemaze[row][col + 1].waveAmp == 0))
			{
			numNeighbors++;
			neighbors[0][numNeighbors] = row;
			neighbors[1][numNeighbors] = col + 1;
			leemaze[row][col + 1].waveAmp = step + 1;
			}
		if (((col - 1) >= 0) &&(leemaze[row][col - 1].isOpen == true) && (leemaze[row][col - 1].waveAmp == 0))
			{
			numNeighbors++;
			neighbors[0][numNeighbors] = row;
			neighbors[1][numNeighbors] = col - 1;
			leemaze[row][col - 1].waveAmp = step + 1;
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

		int [][] startPoint = new int[][]{{3},{3}};
		// create structure

		for (int i = 0; i< fullMaze.length; i++) {
			for (int j = 0; j < fullMaze[0].length; j++){
				fullMaze[i][j] = new MPoint();
				fullMaze[i][j].isOpen = (maze[i][j] == 1);
			}
		}
		// mark special points - end points
		fullMaze[0][0].isSpecial = true;
		fullMaze[6][0].isSpecial = true;
		fullMaze[0][5].isSpecial = true;
		// mark initial point
		fullMaze[3][3].isStart = true;
		fullMaze[3][3].waveAmp = -1;
		
		for (int k = 0; k < 5; k++){
			System.out.println();
			System.out.println("----------------");
			int [][] curNeighbors;
			curNeighbors = getNeighbors(fullMaze, startPoint, k);
			
			for (int i = 0; i< fullMaze.length; i++) {
				System.out.println();
				for (int j = 0; j < fullMaze[0].length; j++){
					System.out.print(fullMaze[i][j].waveAmp);
				}
			}
			startPoint = curNeighbors;	
		}		
	}	
}
