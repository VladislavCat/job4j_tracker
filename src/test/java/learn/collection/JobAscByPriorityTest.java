package learn.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class JobAscByPriorityTest {

    @Test
    public void whenCompareTwoNumPlusOne() {
        int res = new JobAscByPriority().compare(new Job("a", 3), new Job("b", 2));
        Assert.assertEquals(1, res);
    }

    @Test
    public void whenSortThisComparator() {
        List<Job> arr = Arrays.asList(
                new Job("b", 5),
                new Job("a", 12),
                new Job("c", 1),
                new Job("b", 8)
        );
        arr.sort(new JobAscByPriority());
        Assert.assertEquals(arr.get(0), new Job("c", 1));
    }
}
