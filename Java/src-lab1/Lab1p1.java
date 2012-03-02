import java.util.Scanner;


public class Lab1p1 
{
	/* Vom imparti cele doua numere in formatul:
	 * n1 = x1 * (2^exp) + x2
	 * n2 = y1 * (2^exp) + y2
	 *
	 * Deci produsul final devine:
	 *
	 * (n1 * n2) = alpha * (2^(2*exp)) + beta * (2^exp) + gamma
	 *
	 * unde:
	 *  alpha = x1 * y1
	 *  beta = (x1 + x2) * (y1 + y2) - alpha - gamma
	 *  gamma = x2 * y2
	 */
	static long karatsuba(long x, long y, int exp)
	{
		/* Daca exponentul este 0, atunci inmulteste direct. */
		if (exp < 2) 
		{
			return x * y;
		}

		/* Cream o masca pentru a extrage bitii din a si din b. */
		long mask = (((long) 1) << exp) - 1;

		/* Calculam x1, x2, y1, y2. */
		long x1 = x >> exp;
		long x2 = x & mask;
		long y1 = y >> exp;
		long y2 = y & mask;

		/* Calculam alpha, beta si gamma. */
		long alpha = karatsuba(x1, y1, exp / 2);
		long gamma = karatsuba(x2, y2, exp / 2);
		long beta = karatsuba((x1 + x2), (y1 + y2), exp / 2) - alpha - gamma;

		/* Intoarcem rezultatul final. */
		return alpha * (((long) 1) << (2 * exp)) +
				beta * (((long) 1) << exp) +
				gamma;
	}

	public static void main(String[] args)
	{
		/* Citim doua numere int, de 32 biti, pe care le vom inmulti. */
		Scanner scanner = new Scanner(System.in);

		int n1 = scanner.nextInt();
		int n2 = scanner.nextInt();

		/* Afisam rezultatul inmultirii Karatasuba. */
		long prod_classic = ((long) n1) * ((long) n2);
		long prod_karatsuba = karatsuba(n1, n2, 16);

		if (prod_classic == prod_karatsuba) 
		{
			System.out.println("OK!");
			System.out.println("Produsul celor doua numere este: " + prod_classic);
		}
		else 
		{
			System.out.println("Fail!");

			System.out.println("Produsul celor doua numere este: " + prod_classic + 
					", iar Karatsuba a calculat: " + prod_karatsuba);
		}
	}
}
