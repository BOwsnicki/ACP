package edu.uwf.cs.acp.p3.threads;

import java.util.Date;

import edu.wuf.cs.acp.p3.primes.Primes;

public class PrimeCheckRunnable implements Runnable {
	private Primes primes;
	private long N;
	private int firstIndex, lastIndex;
	

	public PrimeCheckRunnable(Primes primes, long N, int firstIndex, int lastIndex) {
		super();
		this.primes = primes;
		this.N = N;
		this.firstIndex = firstIndex;
		this.lastIndex = lastIndex;
	}

	private int divisors() {
		for (int index = firstIndex; index <= lastIndex; index++) {
			if (N % primes.primeAt(index) == 0)
				return primes.primeAt(index);
		}
		return -1;
	}

	@Override
	public void run() {
		int d;
		if ((d = divisors()) != -1) {
			System.out.println(this + " " + d);
		}
		System.out.println("Thread: " + this + " terminated!");
	}

	@Override
	public String toString() {
		return "Thread: " + firstIndex + " - " + lastIndex;
	}
}
