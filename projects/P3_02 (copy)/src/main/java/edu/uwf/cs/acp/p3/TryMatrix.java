package edu.uwf.cs.acp.p3;

public class TryMatrix {
	private static int MAX = 1200; // 10000;
	private static int NUM_THREADS;
	private static final int MAX_THREADS = 4;
	private static Thread[] threads;

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

		int firstCol = 0;
		for (int i = 0; i < NUM_THREADS - 1; i++) {
			int lastCol = (i + 1) * (int) Math.round(colLim);
			threads[i] = new Thread(new MatMultRunnable(a, b, c, 0, rowsA - 1, firstCol, lastCol));
			firstCol = lastCol + 1;
		}
		threads[NUM_THREADS - 1] = new Thread(new MatMultRunnable(a, b, c, 0, rowsA - 1, firstCol, colsB - 1));
		try {
			for (int i = 0; i < NUM_THREADS; i++) {
				threads[i].start();
			}
			// threads[NUM_THREADS - 1].join();
			// while (!noRunningThread()) {
				// Thread.sleep(10);
			// }
			for (int i = 0; i < NUM_THREADS; i++) {
				threads[i].join();
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

	public static double[] results() {
		int[][] matrix = new int[MAX][MAX];
		for (int i = 0; i < MAX; i++) {
			for (int j = 0; j < MAX; j++) {
				matrix[i][j] = 1;
			}
		}
	
		double[] hourly = new double[MAX_THREADS];
		for (NUM_THREADS = 1; NUM_THREADS <= MAX_THREADS; NUM_THREADS++) {
			threads = new Thread[NUM_THREADS];
			System.out.println("Multithreaded: " + NUM_THREADS);
			long start = System.currentTimeMillis();
			int [][] squared = matmultThreaded(matrix, matrix);
			long time = System.currentTimeMillis() - start;
			double multPerHour = 3600000.0/time;
			hourly[NUM_THREADS-1] = multPerHour;
			System.out.println(time/1000.0 + " " + multPerHour);
		}
		for (int i = MAX_THREADS-1; i >= 0; i--) {
			hourly[i] = hourly[i]/hourly[0];
			System.out.println(hourly[i]);
		}
		return hourly;
	}
	
	public static void main(String[] args) {
		results();
	}
}
