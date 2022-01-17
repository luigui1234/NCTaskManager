package mx.edu.j2se.rocha.tasks;

import java.util.*;

public class LinkedTaskList extends AbstractTaskList <LinkedList> {
    private LinkedList<Task> taskList = (LinkedList<Task>)super.taskList;

    public LinkedTaskList() {
        super.obj = new LinkedList<Task>();
    }

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
        return taskList.size();
    }

    public Task getTask(int index) throws IndexOutOfBoundsException {
        try {
            return this.taskList.get(index);
        }
        catch (IndexOutOfBoundsException ex) {
            throw ex;
        }
    }

    /*public LinkedList<Task> incoming (int from, int to)
            throws IllegalArgumentException {
        if (from < 0 || to < 0) {
            throw new IllegalArgumentException("Value must not be negative");
        }

        else if (to < from) {
            throw new IllegalArgumentException("Second parameter should end " +
                    "later than first one");
        }

        System.out.println(this.taskList);
        LinkedList<Task> linkList = new LinkedList<>();
        Iterator i = listIterator();

        for (Task aux; i.hasNext();) {
            aux = (Task) i.next();
            if (aux.isActive()
                    && aux.nextTimeAfter(from)<=to
                    && aux.nextTimeAfter(from) != -1
                    && (aux.nextTimeAfter(from)-from) >= 0) {
                System.out.println(aux.getTitle());
                linkList.add(aux);
            }
        }

        return linkList;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedTaskList that = (LinkedTaskList) o;
        int aux = 0;
        if (taskList.size() == that.taskList.size()) {
            for(int i = 0; i<taskList.size(); i++) {
                if (taskList.get(i).equals(that.taskList.get(i))) {
                    aux++;
                }
            }
        }

        if (aux==0) {
            return false;
        }
        else return true;
    }

    LinkedList cloneList () {
        return (LinkedList) taskList.clone();
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskList);
    }

    @Override
    public String toString() {
        return "LinkedTaskList{" +
                "taskList=" + taskList +
                '}';
    }
}
