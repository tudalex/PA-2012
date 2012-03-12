import java.util.Scanner;

public class Lab3p1 
{
  public static void main(String[] args)
  {
    Scanner inputScanner = new Scanner(System.in);
    String inputString = inputScanner.nextLine();  
    BooleanExpression booleanExpression = new BooleanExpression(inputString);
    if(booleanExpression.isValid())
    {
      Lexem[] lexemArray = booleanExpression.getLexems();
      System.out.println("" + countModes(lexemArray));
    }
    else
    {
      System.out.println("Invalid expression, exiting...");
    }
  }

  private static int countModes(Lexem[] expr) 
  {
    /* TODO: Numarati modurile in care se pot pune paranteze paranteze in
     * expresie astfel incat sa se obtina rezultatul "true".
     *
     * OBS: Este obligatoriu sa puneti in expresie un numar de paranteze egal
     * cu numarul de operatori prezenti (adica parantezarea sa fie completa).
     *
     * OBS: Asa cum scrie si in documentatie, Lexem este un tip de enumerare ce
     * poate avea valorile: 
     *
     * BooleanExpression::True, 
     * BooleanExpression::False, 
     * BooleanExpression::Or, 
     * BooleanExpression::Xor, 
     * BooleanExpression::And 
     *
     * (sunt constante numerice, valoare reala nu este importanta).
     */
    return 0;
  }
}

