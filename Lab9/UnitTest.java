import java.util.Comparator;

public class UnitTest {
    public static void main(String[] args) {
        BST<String> bst = new BST<>();

        class StrComparator implements Comparator<String> {
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        }

        StrComparator strCmp = new StrComparator();

        bst.insert("A", strCmp);
        bst.insert("C", strCmp);
        bst.insert("P", strCmp);
        bst.insert("Q", strCmp);
        bst.insert("Z", strCmp);
        bst.insert("L", strCmp);



        System.out.println(bst.getRoot());
        System.out.println(bst.getSize());
        System.out.println(bst.isEmpty());

        bst.remove("L", strCmp);
        bst.remove("Q", strCmp);

        System.out.println(bst.getSize());
    }
}
