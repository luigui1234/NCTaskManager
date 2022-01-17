package mx.edu.j2se.rocha.tasks;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

abstract class AbstractTaskList <T> {

    public AbstractList<Task> taskList = new LinkedList<>();
    Iterator i = taskList.iterator();

    public T obj;

    private boolean instance (T obj) {
        if (obj instanceof LinkedList) {
            return true;
        }
        else {
            return false;
        }
    }
    abstract void add (Task task);

    abstract boolean remove (Task task) throws NullPointerException;

    abstract int size ();

    abstract Task getTask(int index) throws IndexOutOfBoundsException;

    /*public T incoming (int from, int to) throws IllegalArgumentException {
        if (from < 0 || to < 0) {
            throw new IllegalArgumentException("Value must not be negative");
        }

        else if (to < from) {
            throw new IllegalArgumentException("Second parameter should end " +
                    "later than first one");
        }

        AbstractList<Task> absList = new LinkedList<>();
        System.out.println(this.taskList);
        Task aux = (Task) i.next();

        for (i.next(); i.hasNext();) {
            if (aux.isActive()
                    && aux.nextTimeAfter(from)<=to
                    && aux.nextTimeAfter(from) != -1
                    && (aux.nextTimeAfter(from)-from) >= 0) {
                System.out.println(aux.getTitle());
                absList.add(aux);
            }
        }

        return (T) absList;
    }*/

    public final T incoming (int from, int to) throws IllegalArgumentException {
        if (from < 0 || to < 0) {
            throw new IllegalArgumentException("Value must not be negative");
        }

        else if (to < from) {
            throw new IllegalArgumentException("Second parameter should end " +
                    "later than first one");
        }

        /*AbstractList<Task> absList = new LinkedList<>();
        System.out.println(this.taskList);
        Task aux = (Task) i.next();

        for (i.next(); i.hasNext();) {
            if (aux.isActive()
                    && aux.nextTimeAfter(from)<=to
                    && aux.nextTimeAfter(from) != -1
                    && (aux.nextTimeAfter(from)-from) >= 0) {
                System.out.println(aux.getTitle());
                absList.add(aux);
            }
        }*/

        boolean ins = instance(this.obj);

        Stream<Task> listStream = getStream();
        AbstractList absList = listStream
                .filter(item -> item.isActive()
                && item.nextTimeAfter(from)<=to
                && item.nextTimeAfter(from) != -1
                && (item.nextTimeAfter(from)-from) >= 0
                )
                .collect(Collectors.toCollection(LinkedList::new));
        Task[] subtask = {};
        if (ins) {
            return (T) absList;
        }
        else {
            return (T) absList.toArray(subtask);
        }

    }


    abstract T cloneList ();

    public Iterator <T> listIterator () {
        return (Iterator <T>) taskList.iterator();
    }

    Stream<Task> getStream() {
       return taskList.stream();
    }

    @Override
    public String toString() {
        return "AbstractTaskList{" +
                "taskList=" + taskList +
                '}';
    }
}
