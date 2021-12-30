package mx.edu.j2se.rocha.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

public class LinkedTaskList {
    private LinkedList<Task> taskList = new LinkedList<>();

    public void add (Task task) {

        if (task == null) {
            throw new NullPointerException();
        }

        this.taskList.add(task);
    }

    public boolean remove (Task task) throws NullPointerException {

        if (task == null) {
            throw new NullPointerException();
        }

        if (!this.taskList.contains(task)) {
            return false;
        }

        while(this.taskList.contains(task)) {
            this.taskList.remove(task);
        }

        return true;

    }

    public int size () {
        return this.taskList.size();
    }

    public Task getTask(int index) throws IndexOutOfBoundsException {
        try {
            return this.taskList.get(index);
        }
        catch (IndexOutOfBoundsException ex) {
            throw ex;
        }
    }

    public LinkedList<Task> incoming (int from, int to) throws IllegalArgumentException {
        if (from < 0 || to < 0) {
            throw new IllegalArgumentException("Value must not be negative");
        }

        else if (to < from) {
            throw new IllegalArgumentException("Second parameter should end " +
                    "later than first one");
        }

        System.out.println(this.taskList);
        LinkedList<Task> linkList = new LinkedList<>();

        for (int i = 0; i < this.taskList.size(); i++) {
            if (this.taskList.get(i).isActive()
                    && this.taskList.get(i).nextTimeAfter(from)<=to
                    && this.taskList.get(i).nextTimeAfter(from) != -1
                    && (this.taskList.get(i).nextTimeAfter(from)-from) >= 0) {
                System.out.println(this.taskList.get(i).getTitle());
                linkList.add(taskList.get(i));
            }
        }

        return linkList;
    }
}
