public class Lab1p3 
{
	/* Chiar daca solutia optima se poate obtine folosind un greedy iterativ in
	 * O(N), pentru a exemplifica Divide et Impera vom folosi o abordare top-down.
	 * (Aceasta solutie are complexitatea O(3N) si este recursiva)
	 *
	 *   A) Impartim vectorul initial in doua jumatati [aproximativ] egale.
	 *
	 *   B) Aflam pentru fiecare dintre jumatati:
	 *      1) subsecventa de suma maxima lipita de capatul din drta al jumatatii
	 *      2) subsecventa de suma maxima lipita de capatul din stga al jumatatii
	 *      3) suma elementelor din respectiva jumatate
	 *
	 *   C) Dupa ce avem itemii 1)...3) calculati pentru fiecare jumatate, ii
	 *      folosim ca sa asamblam aceleasi statistici pentru pentru subsecventa
	 *      curenta. 
	 *
	 *   D) functia intoarce in acelasi timp si solutia pentru intervalul de vector
	 *      pe care calculeaza.
	 */
	private static int maximumSubsequenceSum(int[] v, int begin, int end, MSSData data)
	{
		if (begin == end)
		{
			/* 0) Cazul trivial: scanam o portiune de lungime 1. */
			data.sumSS = data.maxSSLeft = data.maxSSRight = v[begin];
			return data.sumSS;
		}
		else
		{

			/* A) Impartim vectorul initial in doua jumatati aproximativ egale. */
			int m = (begin + end) / 2;

			/* B) Calculam pentru jumatatea stanga, recursiv. */
			MSSData leftData = new MSSData();
			int bestLeft = maximumSubsequenceSum(v, begin, m, leftData);

			/* B) Calculam pentru jumatatea din dreapta, recursiv. */
			MSSData rightData = new MSSData();
			int bestRight = maximumSubsequenceSum(v, m + 1, end, rightData);

			/* C) Reasamblam din bucati valorile corecte la nivelul curent. */
			data.sumSS = leftData.sumSS + rightData.sumSS;
			data.maxSSRight = Math.max(rightData.maxSSRight, leftData.maxSSRight + rightData.sumSS);
			data.maxSSLeft  = Math.max(leftData.maxSSLeft, leftData.sumSS + rightData.maxSSLeft);

			/* D) Tot din ce intorc apelurile recursive, gasim si solutia cea mai buna
			 * pe subsecventa analizata la nivelul curent
			 */
			int return_value;

			/* 1) Presupunem ca subsecventa cea mai buna vine in intregime din jumatatea
			 * stanga sau dreapta :
			 *              ... (best_left) ... | ... (best_right) ...
			 */
			return_value = Math.max(bestLeft, bestRight);

			/* 2) Verificam daca nu cumva putem face o solutie si mai buna incalecand
			 * mijlocul cu secvente din stanga si din dreapta:
			 *          ... (max_ss_right_left] | [max_ss_left_right) ...
			 *
			 * Evident, secventele respective trebuie sa fie lipite de mijloc cu cate un capat.
			 */
			return_value = Math.max(return_value, leftData.maxSSRight + rightData.maxSSLeft);

			/* 3) Alte cazuri nu mai sunt: ori este in intregime intr-una din
			 * jumatati, ori incaleca mijlocul.
			 */
			return return_value;
		}

	}	
	private static int maximumSubsequenceSum(int[] v)
	{
		return maximumSubsequenceSum(v, 0, v.length - 1, new MSSData());
	}

	public static void main(String[] args)
	{
		int[] v = VectorUtil.readArrayOfIntegers();
		System.out.println("Subsecventa de suma maxima din vector are suma = " + 
				maximumSubsequenceSum(v));
	}
}

/* Vom folosi o clasa care retine urmatoarele informatii necesare algoritmului:
 *
 *   maxSSLeft = suma maxima a unei subsecvente care incepe din capatul din
 *                 stanga al portiunii analizate:
 *
 *		[max_ss_left) ..............
 *
 *   maxSSRight = suma maxima a unei subsecvente care incepe din capatul din
 *                  dreapta al portiunii analizate:
 *
 *    ............. (max_ss_right]
 *
 *   sumSS = suma elem din portiunea analizata (care atinge ambele capete)
 *
 *    [<<<<<<<<< sum_ss >>>>>>>>>]
 */
class MSSData
{
	public int maxSSLeft;
	public int maxSSRight;
	public int sumSS;
}
