package edu.uwf.cs.acp.p3.threads;

import edu.wuf.cs.acp.p3.primes.Primes;

public class RandomStuff {
	private static final int numThreads = 4;
	private static Thread[] threads = new Thread[numThreads];
	
	private static void divisorBetween(long N, int first, int last, Primes p) {
		for (int index = first; index <= last; index++) {
			if (N % p.primeAt(index) == 0)
				System.out.println(index + " " + p.primeAt(index));
		}
	}
	
	public static void main(String[] args) {
		System.out.println(Long.MAX_VALUE);
		System.out.println(Math.sqrt(Long.MAX_VALUE));
		Primes p = new Primes();
		long p1 = (long)p.randomPrime();
		long p2 = (long)p.randomPrime();
		long N = p1*p2;
		System.out.println(p1 + " " + p2 + " " + N);
		int lim = (int)(Math.sqrt(N)) + 1;
		p = new Primes(lim);
		int numPrimes = p.primesFound();
		System.out.println(lim + " " + numPrimes);
		double size = numPrimes/numThreads;
		int firstIndex = 0;
		for (int count = 0; count < numThreads; count++) {
			int lastIndex = Math.min((int)Math.round(size*(count + 1)),numPrimes-1);
			Runnable r = new PrimeCheckRunnable(p,N,firstIndex,lastIndex);
			threads[count] = new Thread(r);
			System.out.println(count + " " + p.primeAt(firstIndex) + " - " + p.primeAt(lastIndex));
			divisorBetween(N,firstIndex,lastIndex,p);
			firstIndex = lastIndex + 1;
		}
		for (int count = 0; count < numThreads; count++) {
			threads[count].start();
		}
	}
}
