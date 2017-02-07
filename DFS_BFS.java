package graph_algorithms;

class StackX {
	private final int SIZE = 20;
	private int[] st;
	private int top;
	// -----------------------------------------------------------
	public StackX() {
	st = new int[SIZE];
	top = -1;}
	// -----------------------------------------------------------
	public void push (int j) {
		st[++top] = j; }
	// -----------------------------------------------------------
	public int pop() {
		return st[top--]; }
	// ------------------------------------------------------------
	public int peek() {
		return st[top]; }
	// ------------------------------------------------------------
	public boolean isEmpty(){ 
		return (top == -1); 
	}
} 

class Queue {
	private final int SIZE = 20;
	private int[] queArray;
	private int front;
	private int rear;
	// -------------------------------------------------------------
	public Queue() {
		queArray = new int[SIZE];
		front = 0;
		rear = -1;
	}
	// -------------------------------------------------------------
	public void insert(int j) {
		if(rear == SIZE-1)
		rear = -1;
		queArray[++rear] = j;
	}
	// -------------------------------------------------------------
	public int remove() {
		int temp = queArray[front++];
		if(front == SIZE)
		front = 0;
		return temp;
	}
	// -------------------------------------------------------------
	public boolean isEmpty() {
	return ( rear+1==front || (front+SIZE-1==rear) );
	}	
}


class Vertex {
	public char label; 
	public boolean wasVisited;
	// ------------------------------------------------------------
	public Vertex(char lab) {
		label = lab;
		wasVisited = false;
	}
} 

class Graph {
	private final int MAX_VERTS = 20;
	private Vertex vertexList[];       // Список вершин
	private int adjMat[][];            // Матрица смежности
	private int nVerts;                // Текущее количество вершин
	private StackX theStack;
	private Queue theQueue;
	// -----------------------------------------------------------
	public Graph() {
		vertexList = new Vertex[MAX_VERTS];
		adjMat = new int[MAX_VERTS][MAX_VERTS];
		nVerts = 0;
		for(int j=0; j<MAX_VERTS; j++) 
			for(int k=0; k<MAX_VERTS; k++) 
				adjMat[j][k] = 0;
		theStack = new StackX();
		theQueue = new Queue();
	}
	public void addVertex(char lab)	{
		vertexList[nVerts++] = new Vertex(lab);
	}
	// -----------------------------------------------------------
	public void addEdge(int start, int end) {
	adjMat[start][end] = 1;
	adjMat[end][start] = 1;
	}
	// ------------------------------------------------------------
	public void displayVertex(int v){
	System.out.print(vertexList[v].label);
	}
	// ------------------------------------------------------------
	public void dfs(){
		vertexList[0].wasVisited = true;
		displayVertex(0);
		theStack.push(0);
		while( !theStack.isEmpty())	{
			// Получение непосещенной вершины, смежной к текущей
			int v = getAdjUnvisitedVertex( theStack.peek() );
			if(v == -1) 
				theStack.pop();
			else {
				vertexList[v].wasVisited = true; // Пометка
			displayVertex(v); // Вывод
			theStack.push(v); // Занесение в стек
			}
		}
	// Стек пуст, работа закончена
		for(int j=0; j<nVerts; j++) 
			vertexList[j].wasVisited = false;
	}
	public void bfs() { 
		vertexList[0].wasVisited = true; // Пометка
		displayVertex(0); // Вывод
		theQueue.insert(0); // Вставка в конец очереди
		int v2;
		while( !theQueue.isEmpty()) {
			int v1 = theQueue.remove();
			// Пока остаются непосещенные соседи
			while( (v2=getAdjUnvisitedVertex(v1)) != -1 ){
				// Получение вершины
				vertexList[v2].wasVisited = true; // Пометка
				displayVertex(v2); // Вывод
				theQueue.insert(v2); // Вставка
			}
		}
	// Очередь пуста, обход закончен
		for(int j=0; j<nVerts; j++) // Сброс флагов
			vertexList[j].wasVisited = false;
	}
	
	// Метод возвращает непосещенную вершину, смежную по отношению к v
	public int getAdjUnvisitedVertex(int v)	{
		for(int j=0; j<nVerts; j++)
			if(adjMat[v][j]==1 && vertexList[j].wasVisited==false)
				return j; // Возвращает первую найденную вершину
	return -1; // Таких вершин нет
	}
}

public class DFS_BFS {
	public static void main(String[] args) {
		Graph theGraph = new Graph();
		theGraph.addVertex('A'); // 0 (исходная вершина)
		theGraph.addVertex('B'); // 1
		theGraph.addVertex('C'); // 2
		theGraph.addVertex('D'); // 3
		theGraph.addVertex('E'); // 4
		theGraph.addEdge(0, 1); // AB
		theGraph.addEdge(1, 2); // BC
		theGraph.addEdge(0, 3); // AD
		theGraph.addEdge(3, 4); // DE
		System.out.print("DFS Visits: ");
		theGraph.dfs(); // Обход в глубину
		System.out.println();
		System.out.print("BFS Visits: ");
		theGraph.bfs(); // Обход в глубину
	}
}
