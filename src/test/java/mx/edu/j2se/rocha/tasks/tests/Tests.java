package mx.edu.j2se.rocha.tasks.tests;

import mx.edu.j2se.rocha.tasks.ArrayTaskList;
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
        repetitive.setActive(true);
        Assert.assertEquals(repetitive.nextTimeAfter(26), 26);
        Assert.assertEquals(repetitive.nextTimeAfter(27), 50);
        Assert.assertEquals(repetitive.nextTimeAfter(218), 218);
        Assert.assertEquals(repetitive.nextTimeAfter(219), -1);
        repetitive.setTime(14);
        Assert.assertFalse(repetitive.isRepeated());
    }

    @Test
    public void nonRepetitiveTask() {
        String title = "loquesea";
        int time = 18;
        Task nonRepetitive = new Task(title, time);
        Assert.assertFalse(nonRepetitive.isActive());
        Assert.assertEquals(nonRepetitive.getTime(), time);
        Assert.assertEquals(nonRepetitive.isRepeated(), false);
        Assert.assertEquals(nonRepetitive.getStartTime(), 18);
        Assert.assertEquals(nonRepetitive.getEndTime(), 18);
        Assert.assertEquals(nonRepetitive.getRepeatInterval(), 0);
        nonRepetitive.setActive(true);
        Assert.assertEquals(nonRepetitive.nextTimeAfter(18), 18);
        Assert.assertEquals(nonRepetitive.nextTimeAfter(19), 18);
        nonRepetitive.setActive(false);
        Assert.assertEquals(nonRepetitive.nextTimeAfter(20), -1);
        nonRepetitive.setTime(3, 150, 10);
        Assert.assertTrue(nonRepetitive.isRepeated());
    }

    @Test
    public void ArrayTaskList() {
        //incoming test

        ArrayTaskList taskSchedule = new ArrayTaskList();

        Task nonRepetitive1_5 = new Task("desayuno", 18);
        Task repetitive1 = new Task("desayuno diario", 2, 220, 24);
        Task nonRepetitive2 = new Task("comida", 58);
        Task repetitive2 = new Task("comida diario", 12, 200, 24);
        Task nonRepetitive3 = new Task("cena", 67);
        Task repetitive3 = new Task("cena diario", 78, 250, 12);
        Task nonRepetitive4 = new Task("medicina", 120);
        Task repetitive4 = new Task("medicina diario", 2, 58, 6);

        nonRepetitive1_5.setActive(true);
        repetitive1.setActive(true);
        nonRepetitive2.setActive(true);
        repetitive2.setActive(true);
        nonRepetitive3.setActive(true);
        repetitive3.setActive(true);
        nonRepetitive4.setActive(true);
        repetitive4.setActive(true);

        taskSchedule.add(nonRepetitive1_5);
        taskSchedule.add(repetitive1);
        taskSchedule.add(nonRepetitive2);
        taskSchedule.add(repetitive2);

        Assert.assertEquals(taskSchedule.size(), 4);
        Assert.assertTrue(taskSchedule.remove(nonRepetitive1_5));
        taskSchedule.remove(nonRepetitive1_5);
        Assert.assertEquals(taskSchedule.size(), 3);

        taskSchedule.add(nonRepetitive3);
        taskSchedule.add(repetitive3);
        taskSchedule.add(nonRepetitive4);
        taskSchedule.add(repetitive4);

        Assert.assertEquals(taskSchedule.getTask(0), repetitive1);
        Assert.assertEquals(taskSchedule.getTask(2), repetitive2);

        Assert.assertEquals(taskSchedule.incoming(2, 18).length, 3);
        Assert.assertEquals(taskSchedule.incoming(251, 260).length, 0);
        Assert.assertEquals(taskSchedule.incoming(3, 18).length, 2);
        Assert.assertEquals(taskSchedule.incoming(201, 250).length, 2);
    }

}
