package learn.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class JobDescByNameTest {

    @Test
    public void whenCompareTwoNumPlusOne() {
        int res = new JobDescByName().compare(new Job("b", 3), new Job("a", 2));
        Assert.assertEquals(-1, res);
    }

    @Test
    public void whenSortThisComparator() {
        List<Job> arr = Arrays.asList(
                new Job("b", 5),
                new Job("a", 12),
                new Job("c", 1),
                new Job("d", 8)
        );
        arr.sort(new JobDescByName());
        Assert.assertEquals(arr.get(0), new Job("d", 8));
    }
}
