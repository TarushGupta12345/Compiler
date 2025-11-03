package scanner;
import java.io.*;

/**
 * Scans.
 * @author Tarush Gupta
 * @version 9/5/25
 */
public class Scanner
{
    private BufferedReader in;
    private char currentChar;
    private boolean eof;
    /**
     * Constructs a scanner.
     * FileInputStream inStream = new FileInputStream(new File());
     * Scanner lex = new Scanner(inStream);
     * @precondition there is no scanner 
     * @postcondition a new scanner with an inStream is created
     * @param inStream the input stream to use
     */
    public Scanner(InputStream inStream)
    {
        in = new BufferedReader(new InputStreamReader(inStream));
        eof = false;
        getNextChar();
    }
    /**
     * Constructs a scanner with an inString.
     * @precondition: there is no scanner
     * @postcondition: a new scanner with a string is created
     * @param inString the string to scan
     */
    public Scanner(String inString)
    {
        in = new BufferedReader(new StringReader(inString));
        eof = false;
        getNextChar();
    }
    /**
     * Gets the next character.
     * @precondition: the eat method wants the next character
     * @postcondition: the next character is returned
     */
    private void getNextChar()
    {
        try
        {
            int read = in.read();
            if (read == -1)
            {
                eof = true;
                currentChar = '\0';
                return;
            }
            currentChar = (char)read;
        }
        catch (IOException exception)
        {
            System.out.println("IO Exception occured.");
        }
    }
    /**
     * Progresses to the next character.
     * @precondition: currentChar is outdated or saved
     * @postcondition: currentChar is set to the next character
     * @param expected the expected character
     */
    private void eat(char expected) throws ScanErrorException
    {
        if (expected == currentChar)
        {
            getNextChar();
        }
        else
        {
            throw new ScanErrorException("Illegal character. expected " + expected + 
                                        " but found " + currentChar);
        }
    }
    /**
     * Checks if there is a next 
     * @precondition: unclear if the file has a next
     * @postcondition: identifies if the file has further tokens 
     * @return if the file has further tokens
     */
    public boolean hasNext()
    {
        return !eof;
    }
    
    /**
     * Checks if the character is a digit.
     * @precondition: unclear if the character is a digit
     * @postcondition: identifies if the character is a digit
     * @param val the value 
     * @return if the character is a digit
     */
    public static boolean isDigit(char val)
    {
        return val >= '0' && val <= '9';
    }
    
    /**
     * Checks if the character is a letter.
     * @precondition: unclear if the character is a letter
     * @postcondition: identifies if the character is a letter
     * @param val the value
     * @return if the character is a letter.
     */
    public static boolean isLetter(char val)
    {
        return (val >= 'a' && val <= 'z') || (val >= 'A' && val <= 'Z');
    }
    
    /**
     * Checks if the character is a whitespace.
     * @precondition: unclear if the character is a whitespace
     * @postcondition: identifies if the character is a whitespace
     * @param val the value
     * @return if the character is a whitespace.
     */
    public static boolean isWhiteSpace(char val)
    {
        return val == ' ' || val == '\t' || val == '\r' || val == '\n';
    }
    
    /**
     * Scans the next number lexeme.
     * @precondition: there is a next character
     * @postcondition: scans currentChar
     * @return the lexeme representation.
     */
    private String scanNumber() throws ScanErrorException
    {
        String lexeme = "";
        if (!isDigit(currentChar))
        {
            throw new ScanErrorException("Not a digit: " + currentChar);
        }
        while (isDigit(currentChar))
        {
            lexeme += currentChar;
            eat(currentChar);
        }
        return lexeme;
    }
    
    /**
     * Scans the next identifier lexeme.
     * @precondition: there is a next character
     * @postcondition: scans currentChar
     * @return the lexeme representation.
     */
    private String scanIdentifier() throws ScanErrorException
    {
        String lexeme = "";
        if (!isLetter(currentChar))
        {
            throw new ScanErrorException("Not a letter: " + currentChar);
        }
        while (isDigit(currentChar) || isLetter(currentChar))
        {
            lexeme += currentChar;
            eat(currentChar);
        }
        return lexeme;
    }
    
    /**
     * Scans the next operand lexeme.
     * @precondition: there is a next operand
     * @postcondition: scans currentChar
     * @return the lexeme representation.
     */
    private String scanOperand() throws ScanErrorException
    {
        String lexeme = "";
        
        boolean bool1 = currentChar == '=' || currentChar == '+' || currentChar == '-';
        boolean bool2 = currentChar == '/' || currentChar == '%' || currentChar == '(';
        boolean bool3 = currentChar == ';' || currentChar == '*' || currentChar == ')';
        boolean bool4 = currentChar == ':' || currentChar == '>';
        boolean bool5 = currentChar == '<';
        boolean bool6 = currentChar == ',';
        boolean comment = false;
        
        if (currentChar == '.')
        {
            eof = true;
            return "EOF";
        }
        
        if (!(bool1 || bool2 || bool3 || bool4 || bool5 || bool6))
        {
            throw new ScanErrorException("Not an operand: " + currentChar);
        }
        comment = currentChar == '/';
        lexeme += currentChar;
        eat(currentChar);
        
        if (comment && currentChar == '/')
        {
            return "comment";
        }
        
        if ((bool4 || bool5) && currentChar == '=' || (bool5 && currentChar == '>'))
        {
            lexeme += currentChar;
            eat(currentChar);
        }
        
        return lexeme;
    }
    /**
     * Scans the next token.
     * @precondition: the next token is needed
     * @postcondition: the next token is returned
     * @return the lexeme representation
     */
    public String nextToken() throws ScanErrorException
    {
        if (currentChar == '.' || eof)
        {
            int check = 1;
            check += 1;
        }
        else
        {
            while (!eof && isWhiteSpace(currentChar))
            {
                eat(currentChar);
            }
            if (currentChar == '/')
            {
                String check = scanOperand();
                if (check.equals("comment"))
                {
                    while (currentChar != '\n')
                    {
                        eat(currentChar);
                    }
                    eat(currentChar);
                    return nextToken(); 
                }
                else
                {
                    return check;
                }
            }
            if (isDigit(currentChar))
            {
                return scanNumber();
            }
            else if (isLetter(currentChar))
            {
                return scanIdentifier();
            }
            try
            {
                return scanOperand();
            }
            catch (ScanErrorException e)
            {
                System.out.println(e);
                eat(currentChar);
            }
        }
        return null;
    }    
}
