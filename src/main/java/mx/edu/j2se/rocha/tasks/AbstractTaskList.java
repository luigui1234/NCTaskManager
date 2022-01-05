package mx.edu.j2se.rocha.tasks;

import java.util.AbstractList;
import java.util.LinkedList;

abstract class AbstractTaskList <T> {
    private AbstractList<Task> taskList = new LinkedList<>();

    abstract void add (Task task);

    abstract boolean remove (Task task) throws NullPointerException;

    abstract int size ();

    abstract Task getTask(int index) throws IndexOutOfBoundsException;

    public T incoming (int from, int to) throws IllegalArgumentException {
        if (from < 0 || to < 0) {
            throw new IllegalArgumentException("Value must not be negative");
        }

        else if (to < from) {
            throw new IllegalArgumentException("Second parameter should end " +
                    "later than first one");
        }

        System.out.println(this.taskList);
        AbstractList<Task> absList = new LinkedList<>();

        for (int i = 0; i < this.taskList.size(); i++) {
            if (this.taskList.get(i).isActive()
                    && this.taskList.get(i).nextTimeAfter(from)<=to
                    && this.taskList.get(i).nextTimeAfter(from) != -1
                    && (this.taskList.get(i).nextTimeAfter(from)-from) >= 0) {
                System.out.println(this.taskList.get(i).getTitle());
                absList.add(taskList.get(i));
            }
        }

        return (T) absList;
    }



}
