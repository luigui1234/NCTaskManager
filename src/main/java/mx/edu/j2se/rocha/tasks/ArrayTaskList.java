package mx.edu.j2se.rocha.tasks;

import java.util.*;

public class ArrayTaskList {
    private Task[] taskList = {};

    public void add (Task task) {

        if (task == null) {
            throw new NullPointerException();
        }

        List<Task> arrList = new ArrayList<Task>(Arrays.asList(this.taskList));
        arrList.add(task);

        this.taskList = arrList.toArray(taskList);
    }

    public boolean remove (Task task) throws NullPointerException {

        if (task == null) {
            throw new NullPointerException();
        }

        List<Task> arrList = new ArrayList<Task>(Arrays.asList(this.taskList));
        Task[] anotherArray = new Task[taskList.length - 1];
        int i;
        for (i = 0; i < this.taskList.length; i++) {
            if (taskList[i] == task) {


                // Copy the elements from starting till index
                // from original array to the other array
                System.arraycopy(taskList, 0, anotherArray, 0, i);

                // Copy the elements from index + 1 till end
                // from original array to the other array
                System.arraycopy(taskList, i + 1,
                        anotherArray, i,
                        taskList.length - i - 1);
                taskList = anotherArray;

                return true;
            }
        }
        return false;

    }

    public int size () {
        return this.taskList.length;
    }

    public Task getTask(int index) throws IndexOutOfBoundsException {
        try {
            return this.taskList[index];
        }
        catch (IndexOutOfBoundsException ex) {
            throw ex;
        }
    }

    public Task[] incoming (int from, int to) throws IllegalArgumentException {
        if (from < 0 || to < 0) {
            throw new IllegalArgumentException("Value must not be negative");
        }

        else if (to < from) {
            throw new IllegalArgumentException("Second parameter should end " +
                    "later than first one");
        }

        System.out.println(this.taskList);
        Task[] subtask = {};
        List<Task> arrList
                = new ArrayList<Task>(Arrays.asList(subtask));

        for (int i = 0; i < this.taskList.length; i++) {
            if (this.taskList[i].isActive()
                    && this.taskList[i].nextTimeAfter(from)<=to
                    && this.taskList[i].nextTimeAfter(from) != -1
                    && (this.taskList[i].nextTimeAfter(from)-from) >= 0) {
                System.out.println(this.taskList[i].getTitle());
                arrList.add(taskList[i]);
            }
        }

        return arrList.toArray(subtask);
    }
}
