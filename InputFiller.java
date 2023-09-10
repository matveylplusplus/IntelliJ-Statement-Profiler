package StatementProfiler;

import java.util.ArrayList;

public class InputFiller {
    public static <T> void inputFillAllSame(ArrayList<T> input, T element,
                                            int upTo)
    {
        while (input.size() < upTo)
            input.add(element);
    }
}
