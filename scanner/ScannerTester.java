package scanner;
import java.io.FileInputStream;
import java.io.File;
/**
 * Tests the Scanner class.
 * 
 * @author  Tarush Gupta
 * @version 9/3/25
 */
public class ScannerTester 
{
    /**
     * Tests the Scanner class.
     * @precondition the scanner will be tested
     * @postcondition the scanner is tested
     * @param args arguments from the command line
     */
    public static void main(String [ ] args)
    {
        try
        {
            String testfile = "/Users/tarushgupta/Compiler/scanner/ScannerTest.txt";
            String advtestfile = "/Users/tarushgupta/Compiler/scanner/scannerTestAdvanced.txt";
            FileInputStream inStream = new FileInputStream(new File(advtestfile));
            Scanner lex = new Scanner(inStream);
            while (lex.hasNext())
            {
                String nextToken = lex.nextToken();
                if (nextToken != null)
                {
                    System.out.println(nextToken);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e);
        }
    }
}
