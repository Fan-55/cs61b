package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> buggyL = new BuggyAList<>();

        int N = 500;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                buggyL.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // get last
                if (L.size() > 0 && buggyL.size() > 0) {
                    System.out.println("get last item");
                    assertEquals(L.getLast(), buggyL.getLast());
                }
            } else if (operationNumber == 2) {
                // remove last
                if (L.size() > 0 && buggyL.size() > 0) {
                    System.out.println("remove last item");
                    assertEquals(L.removeLast(), buggyL.removeLast());
                }
            } else if (operationNumber == 3) {
                // size
                int size = L.size();
                int buggyLSize = buggyL.size();
                System.out.println("size: " + size);
                System.out.println("buggy size: " + buggyLSize);
            }
        }
    }
}
