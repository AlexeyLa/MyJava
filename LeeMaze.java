
public class LeeMaze {
	
	private static int [] startPoint = new int[]{1,1};
	private static int [] endPoint1 = new int[]{6,7};
	private static int [] endPoint2 = new int[]{2,5};
	private static int [] endPoint3 = new int[]{4,6};
    private static int [][] maze = new int[][]{{1, 1, 1, 1, 0, 1, 0, 0},
							                   {0, 1, 0, 0, 0, 1, 0, 1},
							                   {0, 1, 1, 0, 0, 1, 0, 1},
							                   {0, 1, 0, 1, 0, 1, 0, 0},
							                   {1, 1, 0, 0, 0, 1, 1, 0},
							                   {0, 0, 0, 1, 0, 1, 0, 0},
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

static int[][] getNeighbors (int [][] pointsArray, int [][] maze, int [][] waveMap, int step)
{
	int [][] neighbors = new int [2][4*maze.length*maze[0].length];
	int numNeighbors = -1;
	for (int i = 0; i < pointsArray[0].length; i++){
		int row = pointsArray[0][i];
		int col = pointsArray[1][i];
		if (((row + 1) < maze.length) && (maze[row + 1][col] == 0) && (waveMap[row + 1][col] == 0)) 
			{
			 numNeighbors++;
			 neighbors[0][numNeighbors] = row + 1;
			 neighbors[1][numNeighbors] = col;
			 waveMap[row + 1][col] = step + 1;
			}
		if (((row - 1) >= 0) && (maze[row - 1][col] == 0) && (waveMap[row - 1][col] == 0))
			{
			numNeighbors++;
			neighbors[0][numNeighbors] = row - 1;
			neighbors[1][numNeighbors] = col;
			waveMap[row - 1][col] = step + 1;
			}
		if (((col + 1) < maze[0].length) &&(maze[row][col + 1] == 0) && (waveMap[row][col + 1] == 0))
			{
			numNeighbors++;
			neighbors[0][numNeighbors] = row;
			neighbors[1][numNeighbors] = col + 1;
			waveMap[row][col + 1] = step + 1;
			}
		if (((col - 1) >= 0) &&(maze[row][col - 1] == 0) && (waveMap[row][col - 1] == 0))
			{
			numNeighbors++;
			neighbors[0][numNeighbors] = row;
			neighbors[1][numNeighbors] = col - 1;
			waveMap[row][col - 1] = step + 1;
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
		int ar = -1;
		System.out.println(ar);
		int [][] startPoint = new int[][]{{5},{0}};
		int [][] waveMap = new int[maze.length][maze[0].length];
		for (int i=0; i< maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++){
					waveMap[i][j] = 0;
				}
			}
		waveMap[startPoint[0][0]][startPoint[1][0]] = -1;

		for (int k = 0; k < 15; k++){
			System.out.println();
			System.out.println("----------------");
			int [][] curNeighbors;
			curNeighbors = getNeighbors(startPoint, maze, waveMap, k);
			showMaze(waveMap);
			startPoint = curNeighbors;	
		}		
	}	
}
