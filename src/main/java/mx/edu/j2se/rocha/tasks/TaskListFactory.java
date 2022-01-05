package mx.edu.j2se.rocha.tasks;

public class TaskListFactory {
    public AbstractTaskList createTaskList(ListTypes.types type)
            throws IllegalArgumentException {
        if (type.toString() == "ARRAY") {
            ArrayTaskList array = new ArrayTaskList();
            return array;
        }

        else if (type.toString() == "LINKED") {
            LinkedTaskList linkList = new LinkedTaskList();
            return linkList;
        }

        else {
            throw new IllegalArgumentException();
        }

    }
}
