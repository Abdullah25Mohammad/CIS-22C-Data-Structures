public class test {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 1; i < 4; i++) {
           list.addLast(i);
        }

        // UNIT TESTING OffEnd
        System.out.println("list.offEnd(): " + list.offEnd());
        System.out.println("Positioning iterator");
        list.positionIterator();

        for (int i = 0; i < 4; i++) { // When i = 3, list.offEnd() should be true
            System.out.println("list.offEnd(): " + list.offEnd());
            System.out.println("list.getIterator(): " + list.getIterator());
            System.out.println("list.advanceIterator()\n");
            list.advanceIterator();
        }
        


    }
}
