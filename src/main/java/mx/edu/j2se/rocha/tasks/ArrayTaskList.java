package mx.edu.j2se.rocha.tasks;

import java.util.*;

public class ArrayTaskList {
    private Task[] taskList = {};

    public void add (Task task) {

        List<Task> arrList
                = new ArrayList<Task>(
                Arrays.asList(this.taskList));

        arrList.add(task);

        this.taskList = arrList.toArray(taskList);
    }

    public boolean remove (Task task) {
        List<Task> arrList = new ArrayList<Task>(Arrays.asList(this.taskList));
        Task[] resl = {};
        int i = 0;
        for (i = 0; i < this.taskList.length; i++) {
            if (taskList[i] == task) {
                Task[] anotherArray = new Task[taskList.length - 1];

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

    public Task getTask(int index) {
        return this.taskList[index];
    }
}
