package mx.edu.j2se.rocha.tasks.tests;

import mx.edu.j2se.rocha.tasks.Task;
import org.junit.Assert;
import org.junit.Test;

public class Tests {

    @Test
    public void repetitiveTask() {
        String title = "loquesea";
        int start = 2;
        int end = 220;
        int interval = 24;
        Task repetitive = new Task(title, start, end, interval);
        Assert.assertFalse(repetitive.isActive());
        Assert.assertEquals(repetitive.getStartTime(), 2);
        Assert.assertEquals(repetitive.getEndTime(), end);
        Assert.assertEquals(repetitive.getRepeatInterval(), interval);
        Assert.assertEquals(repetitive.isRepeated(), true);
        Assert.assertEquals(repetitive.getTime(), start);
        Assert.assertTrue(repetitive.isRepeated());
        Assert.assertEquals(repetitive.nextTimeAfter(26), 26);
        Assert.assertEquals(repetitive.nextTimeAfter(27), 50);
        Assert.assertEquals(repetitive.nextTimeAfter(218), 218);
        Assert.assertEquals(repetitive.nextTimeAfter(217), -1);
        repetitive.setTime(14);
        Assert.assertFalse(repetitive.isRepeated());
    }

    @Test
    public void nonRepetitiveTask() {
        String title = "loquesea";
        int time = 2;
        int end = 220;
        int interval = 24;
        Task nonRepetitive = new Task(title, time);
        Assert.assertFalse(nonRepetitive.isActive());
        Assert.assertEquals(nonRepetitive.getTime(), time);
        Assert.assertEquals(nonRepetitive.isRepeated(), false);
        Assert.assertEquals(nonRepetitive.getStartTime(), 18);
        Assert.assertEquals(nonRepetitive.getEndTime(), 18);
        Assert.assertEquals(nonRepetitive.getRepeatInterval(), 0);
        Assert.assertEquals(nonRepetitive.nextTimeAfter(18), 18);
        Assert.assertEquals(nonRepetitive.nextTimeAfter(19), -1);
        nonRepetitive.setTime(3, 150, 10);
        Assert.assertTrue(nonRepetitive.isRepeated());    }
}
