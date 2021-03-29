package co.zoho.test;

import java.util.Scanner;

public class ImageRotation {

	
	public static void rotateBy90(int[][] mat,int n){
		for(int i=0;i<n/2;i++) {
			for(int j=i;j<n-i-1;j++) {
				int temp = mat[i][j];
				mat[i][j] = mat[j][n-i-1];
				mat[j][n-i-1] = mat[n-i-1][n-j-1];
				mat[n-i-1][n-j-1] = mat[n-j-1][i];
				mat[n-j-1][i] = temp;
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		
		
		int[][] mat = new int[n][n];
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				mat[i][j] = scanner.nextInt();
			}
		}
		rotateBy90(mat,n);
		System.out.println("Clockwise");
		for(int i=n-1;i>=0;i--) {
			for(int j=n-1;j>=0;j--) {
				System.out.print(mat[i][j]);
			}
			System.out.println();
		}
		System.out.println("Anti-Clockwise");
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				System.out.print(mat[i][j]);
			}
			System.out.println();
		}
		scanner.close();
		

	}

}
/*
 * 
 * Time Complexity O(n^2)
 * Space : O(1)
 * 
 * 
 */
