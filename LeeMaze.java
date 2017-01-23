

class MPoint {
	private int x;
	private int y;
	private int counter;
	private boolean isOpen;
}

public class LeeMaze {
	
	private static int [] startPoint = new int[]{1,1};
	private static int [] endPoint1 = new int[]{6,7};
	private static int [] endPoint2 = new int[]{2,5};
	private static int [] endPoint3 = new int[]{4,6};
    private static int [][] maze = new int[][]{{1, 1, 1, 1, 0, 1, 0, 1},
							                   {0, 1, 0, 0, 0, 1, 1, 1},
							                   {0, 1, 1, 0, 0, 1, 0, 1},
							                   {0, 1, 0, 1, 0, 1, 0, 1},
							                   {1, 1, 1, 0, 0, 1, 1, 0},
							                   {0, 0, 0, 1, 0, 1, 0, 1},
							                   {1, 1, 1, 1, 0, 1, 1, 1}};
    private static int [][] mazeClear = new int[][]{{0, 0, 0, 0, 0, 0, 0, 0},
										       {0, 0, 0, 0, 0, 0, 0, 0},
										       {0, 0, 0, 0, 0, 0, 0, 0},
										       {0, 0, 0, 0, 0, 0, 0, 0},
										       {0, 0, 0, 0, 0, 0, 0, 0},
										       {0, 0, 0, 0, 0, 0, 0, 0},
										       {0, 0, 0, 0, 0, 0, 0, 0}};
	
static void startWave(int [][] pointsArray, int [][] waveMap, int step)
{
	for (int i = 0; i < pointsArray[0].length; i++){
		int row = pointsArray[0][i];
		int col = pointsArray[1][i];
		if (waveMap[row][col] == 0) waveMap[row][col] = step + 1;
	}
}

static void showMaze(int [][] maze)
{
	for (int i = 0; i< maze.length; i++){
		System.out.println();
		for (int j=0; j < maze[0].length; j++){
			System.out.print(maze[i][j]);
		}
	}
}

static int[][] getNeighbors (int [][] pointsArray, int [][] maze)
{
	int [][] neighbors = new int [2][maze.length*maze[0].length];
	int numNeighbors = -1;
	System.out.println(" Amount of neighbors " + numNeighbors);
	System.out.println(" number of starting points " + pointsArray[0].length);
	for (int i = 0; i < pointsArray[0].length; i++){
		int row = pointsArray[0][i];
		int col = pointsArray[1][i];
		if (maze[row + 1][col] == 0) 
			{numNeighbors++;
			 neighbors[0][numNeighbors] = row + 1;
			 neighbors[1][numNeighbors] = col;
			}
		if (maze[row - 1][col] == 0)
			{numNeighbors++;
			neighbors[0][numNeighbors] = row - 1;
			neighbors[1][numNeighbors] = col;
			}
		if (maze[row][col + 1] == 0)
			{numNeighbors++;
			neighbors[0][numNeighbors] = row;
			neighbors[1][numNeighbors] = col + 1;
			}
		if (maze[row][col - 1] == 0)
			{numNeighbors++;
			neighbors[0][numNeighbors] = row;
			neighbors[1][numNeighbors] = col - 1;
			}
	}
	System.out.println(" Amount of neighbors " + (numNeighbors + 1));
	
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
		
		int [][] startPoint = new int[][]{{3},{3}};
		int [][] waveMap = new int[maze.length][maze[0].length];
		for (int i=0; i< maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++){
					waveMap[i][j] = 0;
				}
			}
	//	curNeighbors = getNeighbors(startPoint, maze);
/*		for (int i=0; i< curNeighbors[0].length; i++){
			System.out.println(" Coordinates are : " + curNeighbors[0][i] + "," + curNeighbors[1][i]);
		}*/
	//	startWave(curNeighbors, waveMap);
		showMaze(waveMap);

		for (int k = 0; k < 2; k++){
			int [][] curNeighbors;
			curNeighbors = getNeighbors(startPoint, mazeClear);
			startWave(curNeighbors, waveMap, k);
			showMaze(waveMap);
			startPoint = curNeighbors;	
		}		
	}	
}
