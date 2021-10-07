package edu.wuf.cs.acp.p3.primes;

import java.util.ArrayList;

public class Primes {
	public static final int MAX_PRIME = 100000000;

	private int n; // 16 bit
	private int lim;
	private boolean[] isPrime;
	private int[] primes;

	public Primes() {
		this(MAX_PRIME);
	}

	public Primes(int n) {
		super();
		setN(n);
	}

	public int getN() {
		return n;
	}

	public int[] getPrimes() {
		return primes;
	}

	public void setN(int n) {
		if (n < 3 || n > MAX_PRIME)
			throw new IllegalArgumentException();
		this.n = n;
		lim = (int) Math.sqrt(n) + 1;
		// Set up sieve
		isPrime = new boolean[n + 1];
		isPrime[0] = isPrime[1] = false;
		for (int i = 2; i <= n; i++)
			isPrime[i] = true;
		shake();

		// Pull out the prime array;
		extractPrimes();
	}

	// Shake the sieve!
	private void shake() {
		int nextP = 2;
		while (nextP <= lim) {
			for (int i = nextP * 2; i <= n; i += nextP) {
				isPrime[i] = false;
			}
			nextP++;
			while (nextP <= lim && !isPrime[nextP]) {
				nextP++;
			}
		}
	}

	private void extractPrimes() {
		ArrayList<Integer> pAL = getPrimesAL();
		primes = new int[pAL.size()];
		int count = 0;
		for (int i : pAL)
			primes[count++] = i;
	}

	private ArrayList<Integer> getPrimesAL() {
		ArrayList<Integer> result = new ArrayList<>();
		// For our purposes we have to omit the 2!
		for (int i = 3; i <= n; i++) {
			if (isPrime[i])
				result.add(i);
		}
		return result;
	}

	public int randomPrime(int k) {
		if (k < 2 || k > n)
			throw new IllegalArgumentException();
		int index = (int) (Math.random() * (k + 1));
		while (!isPrime[index])
			index--;
		return index;
	}
	
	public int nextPrime(int p) {
		int index = p + 1;
		while (index < n && !isPrime[index])
			index++;
		if (index >= n) {
			throw new IllegalArgumentException("Out of primes");
		}
		return index;
	}

	public int randomPrime() {
		return randomPrime(n);
	}

	public int primeAt(int index) {
		return primes[index];
	}
	
	public int primesFound() {
		return primes.length;
	}
	
	public static void main(String[] args) {
		Primes p = new Primes();
		int current = 1;
		for (int i = 1; i <= 100; i++) {
			System.out.println(current);
			current = p.nextPrime(current);
		}	
	}
}
