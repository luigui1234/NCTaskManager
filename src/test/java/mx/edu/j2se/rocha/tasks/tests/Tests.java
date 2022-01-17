package mx.edu.j2se.rocha.tasks.tests;

import mx.edu.j2se.rocha.tasks.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;

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

    @Test
    public void LinkedTaskList() {

        LinkedTaskList taskSchedule = new LinkedTaskList();

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
        taskSchedule.add(nonRepetitive1_5);


        Assert.assertEquals(taskSchedule.size(), 5);
        Assert.assertTrue(taskSchedule.remove(nonRepetitive1_5));
        taskSchedule.remove(nonRepetitive1_5);
        Assert.assertEquals(taskSchedule.size(), 3);

        System.out.println(taskSchedule.size());
        taskSchedule.add(nonRepetitive3);
        taskSchedule.add(repetitive3);
        taskSchedule.add(nonRepetitive4);
        taskSchedule.add(repetitive4);

        System.out.println(taskSchedule.size());

        Assert.assertEquals(taskSchedule.getTask(0), repetitive1);
        Assert.assertEquals(taskSchedule.getTask(2), repetitive2);
        System.out.println(taskSchedule.incoming(2, 18));
        Assert.assertEquals(taskSchedule.incoming(2, 18).size(), 3);
        Assert.assertEquals(taskSchedule.incoming(251, 260).size(), 0);
        Assert.assertEquals(taskSchedule.incoming(3, 18).size(), 2);
        Assert.assertEquals(taskSchedule.incoming(201, 250).size(), 2);
    }

    @Test
    public void AbstractFactory() {

        TaskListFactory listFactory = new TaskListFactory();

        ArrayTaskList array = (ArrayTaskList)
                listFactory.createTaskList(ListTypes.types.ARRAY);

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

        array.add(nonRepetitive1_5);
        array.add(repetitive1);
        array.add(nonRepetitive2);
        array.add(repetitive2);

        Assert.assertEquals(array.size(), 4);
        Assert.assertTrue(array.remove(nonRepetitive1_5));
        array.remove(nonRepetitive1_5);
        Assert.assertEquals(array.size(), 3);

        array.add(nonRepetitive3);
        array.add(repetitive3);
        array.add(nonRepetitive4);
        array.add(repetitive4);

        Assert.assertEquals(array.getTask(0), repetitive1);
        Assert.assertEquals(array.getTask(2), repetitive2);

        Assert.assertEquals(array.incoming(2, 18).length, 3);
        Assert.assertEquals(array.incoming(251, 260).length, 0);
        Assert.assertEquals(array.incoming(3, 18).length, 2);
        Assert.assertEquals(array.incoming(201, 250).length, 2);

        LinkedTaskList taskSchedule = (LinkedTaskList)
                listFactory.createTaskList(ListTypes.types.LINKED);

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
        taskSchedule.add(nonRepetitive1_5);

        Assert.assertEquals(taskSchedule.size(), 5);
        taskSchedule.remove(nonRepetitive1_5);
        Assert.assertEquals(taskSchedule.size(), 3);

        taskSchedule.add(nonRepetitive3);
        taskSchedule.add(repetitive3);
        taskSchedule.add(nonRepetitive4);
        taskSchedule.add(repetitive4);

        Assert.assertEquals(taskSchedule.getTask(0), repetitive1);
        Assert.assertEquals(taskSchedule.getTask(2), repetitive2);

        Assert.assertEquals(taskSchedule.incoming(2, 18).size(), 3);
        Assert.assertEquals(taskSchedule.incoming(251, 260).size(), 0);
        Assert.assertEquals(taskSchedule.incoming(3, 18).size(), 2);
        Assert.assertEquals(taskSchedule.incoming(201, 250).size(), 2);
    }

    @Test
    public void IteratorsServiceMethods() {
        TaskListFactory listFactory = new TaskListFactory();

        ArrayTaskList array = (ArrayTaskList)
                listFactory.createTaskList(ListTypes.types.ARRAY);

        ArrayTaskList arrayCpy = (ArrayTaskList)
                listFactory.createTaskList(ListTypes.types.ARRAY);

        Task nonRepetitive1_5 = new Task("desayuno", 18);
        Task repetitive1 = new Task("desayuno diario", 2, 220, 24);
        Task nonRepetitive2 = new Task("comida", 58);
        Task repetitive2 = new Task("comida diario", 12, 200, 24);
        Task nonRepetitive3 = new Task("cena", 67);
        Task repetitive3 = new Task("cena diario", 78, 250, 12);
        Task nonRepetitive4 = new Task("medicina", 120);
        Task repetitive4 = new Task("medicina diario", 2, 58, 6);

        Task nonRepetitive1_5Cpy = new Task("desayuno", 18);
        Task repetitive1Cpy = new Task("desayuno diario", 2, 220, 24);

        Assert.assertTrue(nonRepetitive1_5.equals(nonRepetitive1_5Cpy));
        Assert.assertFalse(nonRepetitive1_5.equals(nonRepetitive2));

        Assert.assertTrue(repetitive1.equals(repetitive1Cpy));
        Assert.assertFalse(repetitive1.equals(repetitive2));

        nonRepetitive1_5.setActive(true);
        repetitive1.setActive(true);
        nonRepetitive2.setActive(true);
        repetitive2.setActive(true);
        nonRepetitive3.setActive(true);
        repetitive3.setActive(true);
        nonRepetitive4.setActive(true);
        repetitive4.setActive(true);

        array.add(nonRepetitive1_5);
        array.add(repetitive1);
        array.add(nonRepetitive2);
        array.add(repetitive2);
        array.add(nonRepetitive3);
        array.add(repetitive3);
        array.add(nonRepetitive4);
        array.add(repetitive4);

        arrayCpy.add(nonRepetitive1_5);
        arrayCpy.add(repetitive1);
        arrayCpy.add(nonRepetitive2);
        arrayCpy.add(repetitive2);
        arrayCpy.add(nonRepetitive3);
        arrayCpy.add(repetitive3);
        arrayCpy.add(nonRepetitive4);
        arrayCpy.add(repetitive4);

        Assert.assertTrue(array.equals(arrayCpy));
        Assert.assertEquals(array.hashCode(), arrayCpy.hashCode());
        arrayCpy.remove(nonRepetitive1_5);
        Assert.assertFalse(array.equals(arrayCpy));
        Assert.assertNotEquals(array.hashCode(), arrayCpy.hashCode());

        System.out.println(array.toString());

        int count = 0;


        for(Iterator i = array.listIterator(); i.hasNext();) {
            Task task = (Task) i.next();
            //System.out.println(task.getTitle());
            count++;
        }

        Assert.assertEquals(count, array.size());

        LinkedTaskList taskSchedule = (LinkedTaskList)
                listFactory.createTaskList(ListTypes.types.LINKED);

        LinkedTaskList taskScheduleCpy = (LinkedTaskList)
                listFactory.createTaskList(ListTypes.types.LINKED);

        taskSchedule.add(nonRepetitive1_5);
        taskSchedule.add(repetitive1);
        taskSchedule.add(nonRepetitive2);
        taskSchedule.add(repetitive2);
        taskSchedule.add(nonRepetitive1_5);
        taskSchedule.add(nonRepetitive3);
        taskSchedule.add(repetitive3);
        taskSchedule.add(nonRepetitive4);
        taskSchedule.add(repetitive4);

        taskScheduleCpy.add(nonRepetitive1_5);
        taskScheduleCpy.add(repetitive1);
        taskScheduleCpy.add(nonRepetitive2);
        taskScheduleCpy.add(repetitive2);
        taskScheduleCpy.add(nonRepetitive1_5);
        taskScheduleCpy.add(nonRepetitive3);
        taskScheduleCpy.add(repetitive3);
        taskScheduleCpy.add(nonRepetitive4);
        taskScheduleCpy.add(repetitive4);

        Assert.assertTrue(taskSchedule.equals(taskScheduleCpy));
        Assert.assertEquals(taskSchedule.hashCode(),
                taskScheduleCpy.hashCode());
        taskScheduleCpy.remove(nonRepetitive1_5);
        Assert.assertFalse(taskSchedule.equals(taskScheduleCpy));
        Assert.assertNotEquals(taskSchedule.hashCode(),
                taskScheduleCpy.hashCode());

        System.out.println(taskSchedule.toString());

        count = 0;


        for(Iterator i = taskSchedule.listIterator(); i.hasNext();) {
            Task task = (Task) i.next();
            //System.out.println(task.getTitle());
            count++;
        }

        Assert.assertEquals(count, taskSchedule.size());
    }
}
