package edu.uwf.cs.acp.p3.matrix;

public class TryMatrix {
	private static int MAX = 1536; // 10000;
	private static int NUM_THREADS = 2;
	private static Thread[] threads = new Thread[NUM_THREADS];

	@SuppressWarnings("unused")
	private static int[][] matmult(int[][] a, int[][] b) {
		int rowsA = a.length;
		int colsA = a[0].length; // ugly
		int rowsB = b.length;
		int colsB = b[0].length; // also ugly
		int[][] c = new int[rowsA][colsB];
		for (int i = 0; i < rowsA; i++) {
			for (int j = 0; j < colsB; j++) {
				c[i][j] = dotProd(a, i, b, j, rowsA);
			}
		}
		return c;
	}

	private static boolean noRunningThread() {
		for (Thread t : threads) {
			if (t.getState() != Thread.State.TERMINATED && t.getState() != Thread.State.NEW) {
				return false;
			}
		}
		return true;
	}
	
	@SuppressWarnings("unused")
	private static int[][] matmultThreaded(int[][] a, int[][] b) {
		int rowsA = a.length;
		int colsA = a[0].length; // ugly
		int rowsB = b.length;
		int colsB = b[0].length; // also ugly
		// assert(colsA == rowsB);
		int[][] c = new int[rowsA][colsB];

		double colLim = rowsA / NUM_THREADS;
		CountDown cd = new CountDown(NUM_THREADS);

		int firstCol = 0;
		for (int i = 0; i < NUM_THREADS - 1; i++) {
			int lastCol = (i + 1) * (int) Math.round(colLim);
			threads[i] = new Thread(new MatMultRunnable(cd, a, b, c, 0, rowsA - 1, firstCol, lastCol));
			firstCol = lastCol + 1;
		}
		threads[NUM_THREADS - 1] = new Thread(new MatMultRunnable(cd, a, b, c, 0, rowsA - 1, firstCol, colsB - 1));
		try {
			for (int i = 0; i < NUM_THREADS; i++) {
				threads[i].start();
			}
			threads[NUM_THREADS - 1].join();
			while (!noRunningThread()) {
				Thread.sleep(10);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

	private static int dotProd(int[][] a, int row, int[][] b, int col, int len) {
		// System.out.println(row + " " + col);
		int product = 0;
		for (int i = 0; i < len; i++) {
			product += a[row][i] * b[i][col];
		}
		return product;
	}

	public static void main(String[] args) {
		int[][] matrix = new int[MAX][MAX];
		for (int i = 0; i < MAX; i++) {
			for (int j = 0; j < MAX; j++) {
				matrix[i][j] = 1;
			}
		}
		System.out.println("Phase 1");
		long start = System.currentTimeMillis();
		int[][] squared = matmult(matrix, matrix);
		long time = System.currentTimeMillis() - start;
		System.out.println(time);
		System.out.println(squared[3][9]);
		start = System.currentTimeMillis();
		squared = matmultThreaded(matrix, matrix);
		time = System.currentTimeMillis() - start;
		System.out.println(time);
		System.out.println(squared[3][9]);
	}
}
