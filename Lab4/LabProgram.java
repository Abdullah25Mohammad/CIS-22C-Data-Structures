public class LabProgram {
    public static void main(String[] args) {
        LabProgram lab = new LabProgram();

        msg("Calling testClear()");
        if (lab.testClear() == 0) {
            msg("-No errors found");
        }

        msg("Calling testLinkedListArray()");
        if (lab.testLinkedListArray() == 0) {
            msg("-No errors found");
        }

        msg("Calling testLinkedListLinkedListOfT()");
        if (lab.testLinkedListLinkedListOfT() == 0) {
            msg("-No errors found");
        }

        msg("Calling testEqualsObject()");
        if (lab.testEqualsObject() == 0) {
            msg("-No errors found");
        }

        msg("Calling testSpinList()");
        if (lab.testSpinList() == 0) {
            msg("-No errors found");
        }

        msg("Calling testAltList()");
        if (lab.testAltList() == 0) {
            msg("-No errors found");
        }
    }

    private static void msg(String message) {
        System.out.println(message);
    }

    private int testClear() {
        int cntErr = 0;

        LinkedList<String> strList = new LinkedList<>();
        for (String strg : new String[] {"A", "B", "C"}) {
            strList.addLast(strg);
        }
        strList.clear();
        int length = strList.getLength();
        if (length != 0) {
            msg("After clear(), method getLength() returned " + length
                + ", should be: 0\n");
            cntErr++;
        }
        boolean test = strList.isEmpty();
        if (!test) {
            msg("After clear(), method isEmpty() returned " + test
                + ", should be true\n");
            cntErr++;
        }
        return cntErr;
    }

    private int testLinkedListArray() {
        int cntErr = 0;
        int length = 0;

        Integer[] array = null;
        LinkedList<Integer> intList = new LinkedList<>(array);
        length = intList.getLength();
        if (length != 0) {
            msg("After T[] constructor when array = null, getLength() returned " + length
                + ", should be: 0\n");
            cntErr++;
        }
        array = new Integer[] {1, 2, 3};
        intList = new LinkedList<>(array);
        int item = intList.getFirst();
        if (intList.getFirst() != array[0]) {
            msg("After T[] constructor when array = {1, 2, 3}, getFirst() returned "
            + item + ", should be " + array[0] + "\n");
            cntErr++;
        }
        if (intList.getLast() != array[array.length - 1]) {
            msg("After T[] constructor when array = {1, 2, 3}, getLast() returned "
            + item + ", should be " + array[array.length - 1] + "\n");
            cntErr++;
        }
        length = intList.getLength();
        if (length != array.length) {
            msg("After T[] constructor when array = {1, 2, 3}, getLength() returned " + length
                + ", should be " + array.length + "\n");
            cntErr++;
        }
        return cntErr;
    }

    private int testLinkedListLinkedListOfT() {
        int cntErr = 0;
        int length = 0;

        LinkedList<String> strList = new LinkedList<>(new String[] {"A", "B", "C"});
        LinkedList<String> nullList = null;
        LinkedList<String> copyList = new LinkedList<>(nullList);
        length = copyList.getLength();
        if (length != 0) {
            msg("After copy constructor when original list = null, getLength() returned " + length
                + ", should be 0\n");
            cntErr++;
        }
        copyList = new LinkedList<>(strList);
        String str = copyList.getFirst();
        if (!str.equals("A")) {
            msg("After copy constructor when original list = {\"A\", \"B\", \"C\"}, method getFirst() returns "
                + str + ", should be A\n");
            cntErr++;
        }
        str = copyList.toString();
        if (!str.equals(strList.toString())) {
            msg("After copy constructor when original list = {\"A\", \"B\", \"C\"}, method toString() returns "
                + str.trim() + ", should be: " + strList.toString() + "\n");
            cntErr++;
        }
        //checking for deep copy
        strList.addLast("D");
        length = copyList.getLength();
        if (length == strList.getLength()) {
            msg("Copy constructor must make a deep copy\n");
            cntErr++;
        }
        return cntErr;
    }

    private int testEqualsObject() {
        int cntErr = 0;

        LinkedList<String> emptyList = new LinkedList<>();
        LinkedList<Integer> iList = new LinkedList<>();
        LinkedList<String> sList = new LinkedList<>(new String[] {"A", "B", "C"});
        LinkedList<String> sList1 = new LinkedList<>(new String[] {"A", "B", "C"});
        LinkedList<String> sList2 = new LinkedList<>(new String[] {"A", "B", "C", "D"});
        LinkedList<String> sList3 = new LinkedList<>(new String[] {"A", "B", "Z"});
        LinkedList<String> sList4 = new LinkedList<>(new String[] {"A", null, "C"});
        LinkedList<String> sList5 = new LinkedList<>(new String[] {"A", null, "C"});

        if (sList1.equals(emptyList)) {
            msg("Testing equals(): list {" + sList1.toString().trim()
                + "} must not equal an empty list\n");
            cntErr++;
        }
        if (sList1.equals(null)) {
            msg("Testing equals(): list {" + sList1.toString().trim()
                + "} must not equal null\n");
            cntErr++;
        }
        if (sList1.equals(iList)) {
            msg("Testing equals(): list {" + sList1.toString().trim()
                + "} must not equal list {" + iList.toString().trim() + "}\n");
            cntErr++;
        }
        if (sList1.equals(sList2)) {
            msg("Testing equals(): list {" + sList1.toString().trim()
                + "} must not equal list {" + sList2.toString().trim() + "}\n");
            cntErr++;
        }
        if (sList1.equals(sList3)) {
            msg("Testing equals(): list {" + sList1.toString().trim()
                + "} must not equal list {" + sList3.toString().trim() + "}\n");
            cntErr++;
        }
        if (sList1.equals(sList3)) {
            msg("Testing equals(): list {" + sList1.toString().trim()
                + "} must not equal list {" + sList3.toString().trim() + "}\n");
            cntErr++;
        }
        if (!sList1.equals(sList)) {
            msg("Testing equals(): list {" + sList1.toString().trim()
                + "} must equal list {" + sList.toString().trim() + "}\n");
            cntErr++;
        }
        if (!sList4.equals(sList5)) {
            msg("Testing equals(): list {" + sList4.toString().trim()
                + "} must equal list {" + sList5.toString().trim() + "}\n");
            cntErr++;
        }
        if (!sList5.equals(sList4)) {
            msg("Testing equals(): list {" + sList5.toString().trim()
                + "} must equal list {" + sList4.toString().trim() + "}\n");
            cntErr++;
        }
        if (sList.equals(sList4)) {
            msg("Testing equals(): list {" + sList.toString().trim()
                + "} must not equal list {" + sList4.toString().trim() + "}\n");
            cntErr++;
        }
        if (sList4.equals(sList)) {
            msg("Testing equals(): list {" + sList4.toString().trim()
                + "} must not equal list {" + sList.toString().trim() + "}\n");
            cntErr++;
        }
        return cntErr;
    }

    private int testSpinList() {
        int cntErr = 0;

        LinkedList<String> strList = new LinkedList<>(new String[] {"H", "i"});
        try {
            strList.spinList(-1);
            msg("Method spinList() must throw IllegalArgumentException when numMoves < 0\n");
            cntErr++;
        } catch (IllegalArgumentException e) {
            // Good
        }
        strList = new LinkedList<>();
        strList.spinList(5);
        String str = strList.toString();
        if (!str.equals("\n")) {
            msg("Testing spinList(): list {" + strList.toString().trim()
                + "}.spinList(5) must equal {} not \"" + str + "\"\n");
            cntErr++;
        }
        LinkedList<Integer> iList = new LinkedList<>(new Integer[] {1, 2, 3, 4, 5});
        String before = iList.toString().trim();
        iList.positionIterator();
        iList.spinList(2);
        str = iList.toString();
        if (!str.equals("4 5 1 2 3 \n")) {
            msg("Testing spinList(): list {" + before
                + "}.spinList(2) must equal {4 5 1 2 3 \\n} not " + str + "\n");
            cntErr++;
        }
        int iterValue = iList.getIterator();
        if (iterValue != 1) {
            msg("Testing spinList(): list {" + str.trim()
                + "}.spinList(2), getIterator() must equal 1 not " + iterValue + "\n");
            cntErr++;
        }
        iList = new LinkedList<>(new Integer[] {1, 2, 3, 4, 5});
        before = iList.toString().trim();
        iList.positionIterator();
        iList.spinList(4);
        str = iList.toString();
        if (!str.equals("2 3 4 5 1 \n")) {
            msg("Testing spinList(): list {" + before
                + "}.spinList(4) must equal {2 3 4 5 1 \\n} not " + str + "\n");
            cntErr++;
        }
        iList = new LinkedList<>(new Integer[] {1, 2, 3, 4, 5});
        before = iList.toString().trim();
        iList.positionIterator();
        iList.spinList(7);
        str = iList.toString();
        if (!str.equals("4 5 1 2 3 \n")) {
            msg("Testing spinList(): list {" + before
                + "}.spinList(7) must equal {4 5 1 2 3 \\n} not " + str + "\n");
            cntErr++;
        }

        return cntErr;
    }

    private int testAltList() {
        int cntErr = 0;

        LinkedList<String> nullList = null;
        LinkedList<String> emptyList = new LinkedList<>();
        LinkedList<String> sList1 = new LinkedList<>(new String[] {"A", "B", "C"});
        LinkedList<String> sList2 = new LinkedList<>(new String[] {"A", "B", "C", "D"});
        if (!sList1.altLists(nullList).toString().equals(sList1.toString())) {
            msg("Testing altList(): list {" + sList1.toString().trim()
                + "}.altList({" + nullList + "}) must equal {" + sList1.toString().trim() + "}\n");
            cntErr++;
        }
        if (!emptyList.altLists(sList1).toString().equals(sList1.toString())) {
            msg("Testing altList(): list {" + emptyList.toString().trim()
                + "}.altList({" + sList1.toString().trim() + "}) must equal {"
                + sList1.toString().trim() + "}\n");
            cntErr++;
        }
        LinkedList<String> temp = sList1.altLists(sList2);
        if (!temp.toString().equals("A A B B C C D \n")) {
            msg("Testing altList(): list {" + sList1.toString().trim()
                + "}.altList({" + sList2.toString().trim() + "}) must equal {"
                + "A A B B C C D" + "}\n");
            cntErr++;
        }
        temp = sList2.altLists(sList1);
        if (!temp.toString().equals("A A B B C C D \n")) {
            msg("Testing altList(): list {" + sList2.toString().trim()
                + "}.altList({" + sList1.toString().trim() + "}) must equal {"
                + "A A B B C C D" + "}\n");
            cntErr++;
        }

        return cntErr;
    }
}
