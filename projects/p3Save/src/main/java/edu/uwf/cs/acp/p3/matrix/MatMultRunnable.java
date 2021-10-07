package edu.uwf.cs.acp.p3.matrix;

import java.util.Date;

import edu.wuf.cs.acp.p3.primes.Primes;

public class MatMultRunnable implements Runnable {
	private int[][] a,b,c;
	private int firstRow, lastRow, firstCol, lastCol;
	private CountDown cd;
	

	public MatMultRunnable(CountDown cd, int[][] a, int[][] b, int[][] c, int firstRow, int lastRow, int firstCol, int lastCol) {
		super();
		this.cd = cd;
		this.a = a;
		this.b = b;
		this.c = c;
		this.firstRow = firstRow;
		this.lastRow = lastRow;
		this.firstCol = firstCol;
		this.lastCol = lastCol;
	}

	private static int dotProd(int[][] a, int row, int[][] b, int col, int len) {
		// System.out.println(row + " " + col);
		int product = 0;
		for (int i = 0; i < len; i++) {
			product += a[row][i]*b[i][col];
		}
		return product;
	}
	
	@Override
	public void run() {
		System.out.println("Thread: " + this + " started!");
		for (int i = firstRow; i < lastRow; i++) {
			for (int j = firstCol; j < lastCol; j++) {
				c[i][j] = dotProd(a,i,b,j,a.length);
			}
		}
		System.out.println("Thread: " + this + " terminated!");
		cd.decrement();
	}

	@Override
	public String toString() {
		return "Thread: " + firstCol + " - " + lastCol;
	}
}
