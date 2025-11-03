package parser;
import scanner.*;
import java.io.*;
import environment.*;
import ast.*;
import java.util.ArrayList;
/**
 * Test the Parser class.
 * 
 * @author  Tarush Gupta
 * @version 10/25/25
 */
public class ParserTest 
{
    /* ParserTest methods: */

    /**
     * Tests the parser class.
     *
     * @param args arguments from the command line
     */
    public static void main(String [ ] args)
    {
        // Write your code here
        try
        {
            String test1 = "/Users/tarushgupta/Compiler/parserTest0.txt"; //3
            String test2 = "/Users/tarushgupta/Compiler/parserTest1.txt"; //4 9 1
            String test3 = "/Users/tarushgupta/Compiler/parserTest2.txt"; //14 10 20
            String test4 = "/Users/tarushgupta/Compiler/parserTest3.txt"; //1 2 3
            String test45= "/Users/tarushgupta/Compiler/parserTest4.5ForLoopReadln.txt"; //x changes
            String test5 = "/Users/tarushgupta/Compiler/parserTest4.txt"; //15
            String test6="/Users/tarushgupta/Compiler/parserTest6.txt"; //15 5 3 0 1 2 3 4 5 6 7 8 9
            String test65 = "/Users/tarushgupta/Compiler/parserTest6_5.txt"; //depends on x
            String test7 = "/Users/tarushgupta/Compiler/parserTest7.txt"; 
            //15 5 3 0 1 2 3 4 5 6 7 8 9 10 12
            String test8 = "/Users/tarushgupta/Compiler/parserTest8.txt"; 
            //15 5 3 0 1 2 3 4 5 6 7 8 9 3 4 10 3 14
            String test85 = "/Users/tarushgupta/Compiler/parserTest8_5.txt"; //9 3 0
            ArrayList<String> tests = new ArrayList<String>();
            tests.add(test1);
            tests.add(test2);
            tests.add(test3);
            tests.add(test4);
            tests.add(test45);
            tests.add(test5);
            tests.add(test6);
            tests.add(test65);
            tests.add(test7);
            tests.add(test8);
            tests.add(test85);
            
            for (String test : tests)
            {
                System.out.println("Testing: " + test);
                
                
                FileInputStream inStream = new FileInputStream(new File(test));
                
                Scanner scan = new Scanner(inStream);
                Parser parse = new Parser(scan);
                Environment env = new Environment();
                
                Program program = parse.parseProgram();
                program.exec(env);
                System.out.println("Moving on to the next test. \n\n");
            }
            System.out.println("All tests passed. Hooray!");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
