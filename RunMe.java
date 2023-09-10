package StatementProfiler;//Change imports here!
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class RunMe
{
    public static void main(String[] args)
    {
        boolean inMenu = true;

        System.out.println("Welcome to the Java Statement Counter!");
        Scanner scan = new Scanner(System.in);

        while(inMenu) // will always be true; the exit protocol is literally
            // doing System.exit(0) lol
        {
            printMainMenu();
            boolean invalidInput = true;

            while(invalidInput)
            {
                String menuInput = scan.next();
                if (menuInput.equals("profile"))
                {
                    invalidInput = false;
                    profile(scan);
                }
                else if (menuInput.equals("help"))
                {
                    invalidInput = false;
                    printHelp();
                }
                else if (menuInput.equals("info"))
                {
                    invalidInput = false;
                    printInfo();
                }
                else if (menuInput.equals("exit"))
                {
                    scan.close();
                    System.exit(0);
                }
                else {
                    System.out.println("Invalid, try agane");
                }
            }
        }
    }

    /**
     * Prints the main menu that the user interacts with in the profiler.
     */
    private static void printMainMenu()
    {
        System.out.println("Here are your available commands:");
        System.out.println("[info] - Information about the Java Statement " +
                "Counter and its purpose.");
        System.out.println("[help] - Instructions on how to use the " +
                "Java " +
                "Statement " +
                "Counter.");
        System.out.println("[profile] - Enter the run menu to select a " +
                "program to" +
                " profile.");
        System.out.println("[exit] - Terminate the Java Statement Counter.");
    }

    /**
     * Prints information about what the profiler is intended to do.
     */
    private static void printInfo()
    {
        System.out.println("This is a program designed to count the number of" +
                " statements executed " +
                "by any Java algorithm, so as to gage its algorithmic " +
                "complexity in a way that strikes a medium between " +
                "benchmarking (where the results are machine-dependent, as " +
                "time is being measured) and " +
                "Big-O (where the results are machine-independent but too " +
                "inaccurate and provide no " +
                "room for comparison between two algorithms that are in the " +
                "same Big-O classification).");
        System.out.println("For every algorithm you indicate to be profiled, " +
                "the profiler will shit out a Statement Table that shows you " +
                "the number of statements executed based on a given input " +
                "size (dubbed simply \"n\"). You can indicate multiple " +
                "algorithms to be profiled in one profiling session and their" +
                " " +
                "Statement" +
                " " +
                "Tables" +
                " will be shat out in the order you input the algorithms to " +
                "be profiled.");
        System.out.println("This program is NOT designed to  " +
                "determine T(n), AKA the precise algorithmic complexity as a " +
                "function " +
                "of the input size n. That is for you to deduce by graphing " +
                "the statement table (as in, each individual point as an (x," +
                "y) coordinate) " +
                "in something like Desmos and " +
                "coming up " +
                "with the function that maps it based on your vast knowledge " +
                "of mathematical functions, as well as knowledge of the " +
                "source code and understanding which bits loop and which bits" +
                " don't.");
        System.out.println();
    }

    /**
     * Prints instructions for using the profiler.
     */
    private static void printHelp()
    {
        System.out.println("Step 1: Set breakpoints. Open " +
                "IntelliJ " +
                "IDEA " +
                "and go to" +
                " the method whose statements you are trying to measure -- " +
                "AKA the " +
                "test method -- and shift-click the gutter (the empty column " +
                "next to the line numbers) to designate where your " +
                "breakpoints should go. These breakpoints will be counted as " +
                "steps/statements, and should be yellow upon shift-click to " +
                "indicate that they will not suspend the program when ran in " +
                "debug mode. Additionally, for every breakpoint you want to " +
                "enable the [evaluate and log] feature and simply type \"x\" " +
                        "in the box. This will print \"x\" every time a " +
                        "breakpoint is hit into a secret input stream, which " +
                "is " +
                "then read to count the total number of statement " +
                        "hits. This option can be found when shift-clicking " +
                "the breakpoint, " +
                "but it can also be found by going to Run > View Breakpoints " +
                "in the IDEA toolbar.");

        System.out.println("Step 2: Add module dependency. Add the module " +
                "containing the test method" +
                " to the awfulAwfulthings module dependencies if not " +
                "already done so. File > Project Structure > Modules");

        System.out.println("Step 3: Go to StatementCounter.java and " +
                "write a new Tester Class that implements StatementCounter. " +
                "This class should create a valid input to be fed into the " +
                "algorithm that guarantees the algorithm's worst case runtime" +
                ". That's what we're comparing, after all.");

        System.out.println("Step 4: Go to printNChoices or " +
                "printConstantChoices, depending on whether the method you " +
                "just implemented runs in n-dependent or constant time, and " +
                "follow the examples to add the test method to the set of " +
                "choices the user gets in the GUI.");

        System.out.println("Step 5: Go to the if chain on line 234 and add a " +
                "new if block for your new Tester Class. ");

        System.out.println("Step 6: Hit Alt-Shift-D to debug THIS class " +
                "(RunMe)" +
                ". This" +
                " ensures the breakpoints are logged. ");

        System.out.println("Step 7: Have fun! :)");
        System.out.println();
    }

    private static void profile(Scanner scan)
    {
        int numberOfCChoices = printConstantChoices();
        System.out.println();
        int numberOfNChoices = printNChoices();
        System.out.println();

        System.out.println("Input the ID of each method you would like to" +
                " profile, on a separate line (hit enter each time!). Invalid" +
                " " +
                "inputs will be ignored. When you are done, enter 0.");

        int base, offset, jumpLength, jumps;
        // so the compiler doesn't ree
        base = 0;
        offset = 0;
        jumpLength = 0;
        jumps = 0;

        boolean done = false;
        boolean nPresent = false;
        ArrayList<Integer> IDlist = new ArrayList<>();
        while(!done)
        {
            int input = scan.nextInt();
            if(input >= 1 && input <= numberOfNChoices)
            {
                nPresent = true;
                IDlist.add(input);
            }
            else if (input <= -1 && Math.abs(input) <= numberOfCChoices)
                IDlist.add(input);

            done = (input == 0);
        }

        if(nPresent)
        {
            System.out.println("You indicated that you want to profile at " +
                    "least one n-dependent " +
                    "test method. So " +
                    "now you must provide details for n!");
            base = paramInit("Provide a base for n: ", false, scan);
            offset = paramInit("Provide n's starting exponent: ", true, scan);
            jumps = paramInit("Provide the number of jumps n's exponent will " +
                    "make: ", false, scan);
            jumpLength = paramInit("Provide the jump distance " +
                    "between each exponent of n: ", false, scan);
        }

        if(IDlist.size() > 0)
        {
            // Rewiring system.out to feed into baos
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            PrintStream old = System.out;
            System.setOut(ps);

            ArrayList<StatementRow.StatementRowArray> SRAL = new ArrayList<>();

            for(int ID : IDlist)
            {
                StatementRow.StatementRowArray SRA;
                if(ID >= 1) // test method is n-dependent
                    SRA = new StatementRow.StatementRowArray(jumps+1);
                else // test method is constant
                    SRA = new StatementRow.StatementRowArray(1);

                int SRAIndex = 0;
                StatementCounter testerClass;

                // if chain should start here, with one if for every class
                // that implements StatementCounter.
                // End
                // with an else for the last number so the
                // compiler doesn't shit itself because you didn't guarantee
                // initialization of testerClass
                if (ID == 1)
                    testerClass = new
                            someRecursiveMethodsSumEltLocationsCounter();
                else
                    testerClass = new
                            someRecursiveMethodslocOfOccurrenceCounter();
                //if chain ends here

                // for loop starts here
                for (int i = offset; i <= (jumps*jumpLength)+offset; i +=
                        jumpLength)
                {
                    StatementRow SR = testerClass.testMethod((int)(Math.pow
                            (base, i)), baos);
                    SRA.add(SRAIndex, SR);
                    baos.reset();
                    SRAIndex++;
                }
                // for loop ends here
                SRAL.add(SRA);
            }
            // reset System.out printStream to original value
            System.setOut(old);

            //printing results!
            for (StatementRow.StatementRowArray SRALelement : SRAL)
                System.out.println(SRALelement);
        }
    }

    private static int printConstantChoices()
    {
        int count = 0;
        System.out.println("Implemented constant time test methods:");

        System.out.println("none :(");
        // Have the IDs for constant time test methods be negative if and when
        // you implement them. The first is -1, second is -2, etc. This just
        // makes it easier for me to parse the validity of the input down the
        // line

        return count;
    }

    private static int printNChoices()
    {
        int count = 0;
        System.out.println("Implemented n-dependent test methods: ");

        System.out.println("[1] - proj4.SomeRecursiveMethods.sumEltLocations" +
                "()");
        count++;
        System.out.println("[2] - proj4.SomeRecursiveMethods.sumEltLocations" +
                "()");
        count++;

        return count;
    }

    private static int paramInit(String msg, boolean zeroOk, Scanner scan)
    {
        int toReturn = 0;
        boolean invalid = true;
        while(invalid)
        {
            System.out.println(msg);
            int temp = scan.nextInt();
            if( (temp > 0) || (zeroOk && temp == 0))
            {
                toReturn = temp;
                invalid = false;
            }
            else
                System.out.println("Invalid, try agane");
        }
        return toReturn;
    }
}