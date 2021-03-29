package co.zoho.test;

import java.util.Scanner;

public class MazePath {

	public static int shortestPath(int[][] maze,int n,int i,int j,int k,int l,int dp[][],int[][] visited) {
		//System.out.println(i+" "+j);
		if(i>=n || i<0 || j>=n || j<0 || maze[i][j] == 0) {
			return n*n+1; // No path
		}
		if(i==k && j==l) {
			return 1;
		}
		if(visited[i][j] != 0) {
			return n*n+1;
		}
		visited[i][j] = 1;
		if(dp[i][j] != 0) {
			return dp[i][j];
		}
		int m1 = Math.min(shortestPath(maze,n,i+1,j,k,l,dp,visited), shortestPath(maze,n,i,j+1,k,l,dp,visited));
		int m2 = Math.min(shortestPath(maze,n,i-1,j,k,l,dp,visited), shortestPath(maze,n,i,j-1,k,l,dp,visited));
		dp[i][j] = Math.min(m1, m2)+1;
		visited[i][j] = 0;
		return dp[i][j];
		
	}
	
	/*public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int n = scanner.nextInt();
		int srci = scanner.nextInt();
		int srcj = scanner.nextInt();
		int dstk = scanner.nextInt();
		int dstl = scanner.nextInt();
		
		int[][] maze = new int[n][n];
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				maze[i][j] = scanner.nextInt();
			}
		}
		int[][] dp = new int[n][n];
		int[][] visited = new int[n][n];
		int cost = shortestPath(maze,n,srci-1,srcj-1,dstk-1,dstl-1,dp,visited);
		//System.out.println(cost);
		if(cost>n*n) {
			System.out.println("No valid path");
		}
		else {
			System.out.println(cost);
		}
		
		scanner.close();
	}*/
	
	
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
		int[][] dp = new int[n][n];
		int[][] visited = new int[n][n];
		int cost = shortestPath(mat,n,srci,srcj,dstk,dstl,dp,visited);
		System.out.println(cost);
		
	}

}
/*
Input 
4 1 1 4 4
1 0 0 0
1 1 0 0 
0 1 1 1
1 0 0 1
 
 
 Approach: 
 
 Using DFS on Grid , Using DP for reducing duplicate calls on cells.
 
 Time complexity : O(n*n)
 Space complexity : O(n)
 
 */
