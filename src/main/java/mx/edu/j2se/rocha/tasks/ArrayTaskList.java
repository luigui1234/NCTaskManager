package mx.edu.j2se.rocha.tasks;

import java.util.*;

public class ArrayTaskList extends AbstractTaskList <Task []> {
    private Task[] taskList = {};

    public void add (Task task) {

        if (task == null) {
            throw new NullPointerException();
        }

        super.taskList.add(task);

        this.taskList = super.taskList.toArray(taskList);
    }

    public boolean remove (Task task) throws NullPointerException {

        if (task == null) {
            throw new NullPointerException();
        }

        List<Task> arrList = new ArrayList<Task>(Arrays.asList(this.taskList));
        Task[] anotherArray = new Task[taskList.length - 1];
        int i;
        int aux = 0;
        for (i = 0; i < this.taskList.length; i++) {
            if (taskList[i] == task) {
                super.taskList.remove(task);
                taskList = super.taskList.toArray(anotherArray);
                anotherArray = new Task[taskList.length - 1];
                aux++;
            }
        }
        if (aux == 0) {
            return false;
        }
        else {
            return true;
        }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayTaskList that = (ArrayTaskList) o;
        int aux=0;
        if (taskList.length == that.taskList.length) {
            for(int i = 0; i<taskList.length; i++) {
                if (taskList[i].equals(that.taskList[i])) {
                    aux++;
                }
            }
        }
        else {
            return false;
        }

        if (aux==0) {
            return false;
        }
        else return true;
    }

    Task[] cloneList () {
        return taskList;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(taskList);
    }

    @Override
    public String toString() {
        return "ArrayTaskList{" +
                "taskList=" + Arrays.toString(taskList) +
                '}';
    }
}
