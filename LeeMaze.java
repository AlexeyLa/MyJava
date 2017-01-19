

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
	
void startWave()
{
	
}

static void showMaze(int [][] maze)
{
	for (int i =0; i< maze.length; i++){
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
	for (int i = 0; i < pointsArray[0].length; i++){
		int y = pointsArray[0][i];
		int x = pointsArray[1][i];
		System.out.println(maze[x + 1][y]);
		System.out.println(maze[x - 1][y]);
		System.out.println(maze[x][y + 1]);
		System.out.println(maze[x][y - 1]);
		if (maze[x + 1][y] == 0) 
			{numNeighbors++;
			 neighbors[0][numNeighbors] = x + 1;
			 neighbors[1][numNeighbors] = y;
			}
		if (maze[x - 1][y] == 0)
			{numNeighbors++;
			neighbors[0][numNeighbors] = x - 1;
			neighbors[1][numNeighbors] = y;
			}
		if (maze[x][y + 1] == 0) numNeighbors++;
			{numNeighbors++;
			neighbors[0][numNeighbors] = x ;
			neighbors[1][numNeighbors] = y + 1;
			}
		if (maze[x][y - 1] == 0) numNeighbors++;
			{numNeighbors++;
			neighbors[0][numNeighbors] = x;
			neighbors[1][numNeighbors] = y - 1;
			}
	}
	return neighbors;
}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [][] curNeighbors;
		int [][] startPoint = new int[][]{{1},{1}};
		int [][] waveMap = new int[maze.length][maze[0].length];
		for (int i=0; i< maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++){
					waveMap[i][j] = 0;
				}
			}
		
		showMaze(waveMap);

		int [][] testArray = new int [][]{{3,4,3,2,1,5},
									      {5,5,6,7,2,4}};
		
		for (int i = 0; i< testArray.length; i++){
			System.out.println();
			for (int j = 0; j < testArray[0].length; j++){
				System.out.print(testArray[i][j]);
			}
		}
		curNeighbors = getNeighbors(startPoint, waveMap);
		for (int i=0; i< curNeighbors.length; i++){
			for (int j=0; j< curNeighbors[0].length; j++){
				System.out.println(curNeighbors[i][j]);
			}
		}
		
	}	
}
