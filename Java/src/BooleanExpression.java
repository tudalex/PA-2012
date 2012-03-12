import java.util.StringTokenizer;
import java.util.Vector;

enum Lexem
{
  True, False, And, Or, Xor
}

class BooleanExpression
{
  private Vector<Lexem> expression = new Vector<Lexem>();
  private boolean valid;

  BooleanExpression(String stringRepresentation)
  {
    if(lexicalAnalysis(stringRepresentation) == false)
    {
      System.out.println("Lexical analysis failed!");
      valid = false;
      return;
    }

    parse(stringRepresentation);

    if(semanticAnalysis() == false)
    {
      System.out.println("Semantic analysis failed!");
      valid = false;
      return;      
    }

    valid = true;
  }

  private boolean validToken(String token)
  {
    return
        token.equals("true") ||
        token.equals("false") ||
        token.equals("or") ||
        token.equals("xor") ||
        token.equals("and");
  }

  private boolean lexicalAnalysis(String input)
  {
    StringTokenizer tokenizer = new StringTokenizer(input, " ", false);
    while(tokenizer.hasMoreTokens())
    {
      String token = tokenizer.nextToken();
      if(!validToken(token))
        return false;
    }
    return true;
  }

  private boolean isOperator(Lexem lexem)
  {
    return 
        lexem == Lexem.And ||
        lexem == Lexem.Or ||
        lexem == Lexem.Xor;
  }

  private boolean isOperand(Lexem lexem)
  {
    return
        lexem == Lexem.True ||
        lexem == Lexem.False;
  }

  private boolean semanticAnalysis()
  {
    /*
     * nextShouldBe:
     *     1 = operator
     *     -1 = operand
     * Initial nextShouldBe = -1 (incepem cu operand)
     * La sfarsit nextShouldBe = 1 (terminam cu un operand)
     */

    int nextShouldBe = -1;
    for(Lexem lexem : expression)
    {
      boolean check = 
          (nextShouldBe == 1 && isOperator(lexem)) ||
          (nextShouldBe == -1 && isOperand(lexem));
      if(!check)
      {
        return false;
      }
      nextShouldBe *= (-1);
    }

    if(nextShouldBe == -1)
    {
      return false;
    }

    return true;
  }

  private void parse(String input)
  {
    expression.clear();
    StringTokenizer tokenizer = new StringTokenizer(input, " ", false);
    while(tokenizer.hasMoreTokens())
    {
      String token = tokenizer.nextToken();
      if(token.equals("and")) expression.add(Lexem.And);
      if(token.equals("false")) expression.add(Lexem.False);
      if(token.equals("or")) expression.add(Lexem.Or);
      if(token.equals("true")) expression.add(Lexem.True);
      if(token.equals("xor")) expression.add(Lexem.Xor);
    }
  }

  public boolean isValid()
  {
    return valid;
  }

  public Lexem[] getLexems()
  {
    if(isValid())
      return expression.toArray(new Lexem[0]);
    else
      return new Lexem[0];
  }
}
