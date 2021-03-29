package co.zoho.test;
import java.util.*;
public class MazeShortestPathBFS {
	
	static class Cell{
		int i,j;
		public Cell(int i,int j) {
			this.i=i;
			this.j=j;
		}
	}
	
	
	public static int shortestPath(int[][] maze,int n,int i,int j, int k,int l) {
		Queue<Cell> q = new LinkedList<>();
		
		 
		
		q.add(new Cell(i,j));
		
		boolean[][] visited = new boolean[n][n];
		int[][] dis = new int[n][n];
		dis[i][j] = 1;
		visited[i][j] = true;
		while(!q.isEmpty()) {
			
			Cell cell = q.poll();
			//System.out.println(cell.i+", "+cell.j);
			if(cell.i == k && cell.j == l) {
				System.out.println("Path found");
			}
			else {
				add(cell.i+1,cell.j,q,n,visited,maze,dis,dis[cell.i][cell.j]);
				add(cell.i,cell.j+1,q,n,visited,maze,dis,dis[cell.i][cell.j]);
				add(cell.i-1,cell.j,q,n,visited,maze,dis,dis[cell.i][cell.j]);
				add(cell.i,cell.j-1,q,n,visited,maze,dis,dis[cell.i][cell.j]);
			}
			
		}
		return dis[k][l];
		
	}
	
	private static void add(int i,int j,Queue<Cell> q,int n,boolean[][] visited,int[][] maze,int[][] dis,int oldCost) {
		if(i>=n || j>=n || i<0 || j<0 || visited[i][j] || maze[i][j] == 0) {
			return;
		}
		q.add(new Cell(i,j));
		visited[i][j] = true;
		dis[i][j] = oldCost+1;
	}
	

	public static void main(String[] args) {
		int mat[][] = {{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
                { 1, 0, 1, 0, 1, 1, 1, 0, 1, 1 },
                { 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 },
                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
                { 1, 1, 1, 0, 1, 1, 1, 0, 1, 0 },
                { 1, 0, 1, 1, 1, 1, 0, 1, 0, 0 },
                { 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
                { 1, 1, 0, 0, 0, 0, 1, 0, 0, 1 }};
		int n=mat.length;
		int srci = 0;
		int srcj = 0;
		int dstk = 3;
		int dstl = 4;
		int cost = shortestPath(mat,n,srci,srcj,dstk,dstl);
		System.out.println(cost);

	}

}
