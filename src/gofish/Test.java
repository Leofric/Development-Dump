package gofish;

import java.util.Random;

public class Test {
	public static void main(String[] args) {

		Random r = new Random();
		
		System.out.println("POSITIVE 0 MIN 1 MAX");
		for(int i = 0; i<10; i++) {
			double randomValue = 0 + (1 - 0) * r.nextDouble();
			System.out.println(randomValue);
		}
		
		
		
	}
}
