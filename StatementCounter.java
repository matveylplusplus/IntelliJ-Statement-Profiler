package StatementProfiler;

import someRecursiveMethods.SomeRecursiveMethods;
import someRecursiveMethods.UMCPList;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public interface StatementCounter {
    StatementRow testMethod(int n, ByteArrayOutputStream baos);
}

//**********************Proj4 TestMethods**************************************
class someRecursiveMethodsSumEltLocationsCounter implements StatementCounter {
    private final ArrayList<Integer> input = new ArrayList<>();

    @Override
    public StatementRow testMethod(int n, ByteArrayOutputStream baos) {
        int element = 3;

        InputFiller.inputFillAllSame(input, 3, n);
        UMCPList<Integer> list = new UMCPList<>(input);
        SomeRecursiveMethods.sumEltLocations(list, element);

        return new StatementRow(n, baos.size());
    }
}

class someRecursiveMethodslocOfOccurrenceCounter implements StatementCounter {
    private final ArrayList<Integer> input = new ArrayList<>();

    @Override
    public StatementRow testMethod(int n, ByteArrayOutputStream baos) {
        int element = 3;

        InputFiller.inputFillAllSame(input, 3, n);
        UMCPList<Integer> list = new UMCPList<>(input);
        SomeRecursiveMethods.locOfOccurrence(list, element, list.size());

        return new StatementRow(n, baos.size());
    }
}