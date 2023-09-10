package StatementProfiler;

public class StatementRow {
    private final int n;
    private final int steps;

    public StatementRow(int n, int s)
    {
        this.n = n;
        steps = s;
    }

    public static class StatementRowArray {
        private int largestN;
        private int largestSteps;
        private final StatementRow[] SRA;
        private boolean firstAdd = true;

        public StatementRowArray(int size)
        {
            SRA = new StatementRow[size];
        }

        public void add(int index, StatementRow SR)
        {
            SRA[index] = SR;
            if (!firstAdd)
            {
                if(SR.n > largestN)
                    largestN = SR.n;
                if (SR.steps > largestSteps)
                    largestSteps = SR.steps;
            }
            else
            {
                largestN = SR.n;
                largestSteps = SR.steps;
                firstAdd = false;
            }
        }

        @Override
        public String toString()
        {
            int nSpaces = (String.valueOf(largestN)).length();
            int stepSpaces = (String.valueOf(largestSteps)).length();
            StringBuilder table =
                    new StringBuilder("|n" + stringSum(" ", (nSpaces - 1)) + "|s" +
                            stringSum(" ", (stepSpaces - 1)) + "\n");

            for (StatementRow statementRow : SRA) {
                String phrase = String.valueOf(statementRow.n);
                String phrase2 = String.valueOf(statementRow.steps);

                table.append("|").append(phrase).append(stringSum(" ",
                        nSpaces - phrase.length()));

                table.append("|").append(phrase2).append(stringSum(" ",
                        stepSpaces - phrase2.length()));

                table.append("\n");
            }
            return table.toString();
        }

        private String stringSum(String str, int max)
        {
            return String.valueOf(str).repeat(Math.max(0, max));
        }
    }
}