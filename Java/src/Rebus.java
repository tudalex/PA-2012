import java.util.Scanner;

public class Rebus implements Readable 
{
  public int rows;
  public int columns;

  char[][] m;
  char[][] ref;

  public Rebus()
  {
    rows = 0;
    columns = 0;
    m = null;
    ref = null;
  }

  public char get(int row, int column)
  {
    if(row >= 0 && row < rows && column >= 0 && column < columns)
    {
      return m[row][column];
    }
    else
    {
      return '\0';
    }
  }

  public void put(int row, int column, char c)
  {
    if (m != null && row < rows && row >= 0 && column < columns && column >= 0) 
    {
      if (m[row][column] != '*' && c >= 'A' && c <= 'Z') 
      {
        m[row][column] = c;
      }
    }
  }

  void putString(int row, int column, String s) 
  {
    int free = 0;
    for (int j = column; j < columns && is_empty(row, j); ++j) 
    {
      free++;
    }
    if (free == s.length()) 
    {
      for (int i = 0; i < s.length(); ++i) 
      {
        put(row, column + i, s.charAt(i));
      }
    }
  }

  void eraseString(int row, int column) 
  {
    for (int j = column; j < columns && m[row][j] != '*'; ++j) 
    {
      erase(row, j);
    }
  }

  void erase(int row, int column) 
  {
    if (m != null && row < rows && row >= 0 && column < columns && column >= 0) 
    {
      if (m[row][column] != '*') 
      {
        m[row][column] = '_';
      }
    }
  }

  boolean is_empty(int row, int column) 
  {
    if (m != null && row < rows && row >= 0 && column < columns && column >= 0) 
    {
      return m[row][column] == '_';
    }
    else 
    {
      return false;
    }
  }
  boolean is_done() 
  {
    if (m == null) 
    {
      return false;
    }

    for (int i = 0; i < rows; ++i) 
    {
      for (int j = 0; j < columns; ++j) 
      {
        if (ref[i][j] != m[i][j]) 
        {
          return false;
        }
      }
    }

    return true;
  }

  @Override
      public void read(Scanner scanner) 
      {
        rows = scanner.nextInt();
        columns = scanner.nextInt();

        m = new char[rows][columns];
        ref = new char[rows][columns];

        for(int i = 0; i < rows; i++)
        {
          String[] currentLineChars = scanner.nextLine().split(" ");
          while(currentLineChars.length != columns)
          {
            currentLineChars = scanner.nextLine().split(" ");
          }

          for(int j = 0; j < columns; j++)
          {
            ref[i][j] = currentLineChars[j].charAt(0);
            m[i][j] = (ref[i][j] == '*') ? '*' : '_';
          }
        }
      }

  @Override
      public String toString()
      {
        String representation = "";
        for(int i = 0; i < 2 * columns - 1; i++)
          representation += "-";
        representation += "\n";
        for(int i = 0; i < rows; i++)
        {
          for(int j = 0; j < columns; j++)
          {
            representation += ((j == 0 ? "" : " ") + m[i][j]);
          }
          representation += "\n";
        }
        for(int i = 0; i < 2 * columns - 1; i++)
          representation += "-";
        representation += "\n";
        return representation;
      }
}
