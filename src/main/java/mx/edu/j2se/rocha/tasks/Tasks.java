package mx.edu.j2se.rocha.tasks;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tasks {
    public static Iterable<Task> incoming(Iterable<Task> tasks,
                                       LocalDateTime start,
                            LocalDateTime end) {

        if (start.getYear() < 0 || end.getYear() < 0) {
            throw new IllegalArgumentException("Value must not be negative");
        }

        else if (end.isBefore(start)) {
            throw new IllegalArgumentException("Second parameter should end " +
                    "later than first one");
        }

        List<Task> auxList = new LinkedList<>();

        tasks.forEach((task)->{
            if (task.isActive()
                    && (task.nextTimeAfter(start).compareTo(end) <= 0)
                    && task.nextTimeAfter(start) != LocalDateTime.of(-1, 1, 1, 0, 0)
                    && (task.nextTimeAfter(start).compareTo(start)) >= 0) {
                auxList.add(task);
            }
        });
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
        Iterable<Task> iterator = auxList;
        return iterator;
    }


    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start,
                                                 LocalDateTime end) {

        SortedMap<LocalDateTime, Set<Task>>  sortedMap = new TreeMap();

        Set<LocalDateTime> dateSet = new TreeSet();
        Set taskSet = new TreeSet();

        int taskRepetition;
        long intervalMinutes;
        long startMinutes;
        long endMinutes;

        for (Task task : tasks) {
            if (task.isRepeated()) {
                taskRepetition = 0;
                intervalMinutes = task.toMinutes(task.getRepeatInterval());
                startMinutes = task.toMinutes(task.getStartTime());
                endMinutes = task.toMinutes(task.getEndTime());

                while ((startMinutes+(taskRepetition*intervalMinutes) <
                        endMinutes)) {
                    taskRepetition++;
                }


                for (int i = 0; i<taskRepetition; i++) {
                    dateSet.add(task.getStartTime().plusMinutes(i*intervalMinutes));
                }
            }

            else {
                dateSet.add(task.getTime());
            }
        }
        LocalDateTime [] dateArray= new LocalDateTime[dateSet.size()];
        dateSet.toArray(dateArray);
        List<LocalDateTime> dateList = Arrays.asList(dateArray);

        Set<Task> [] setArray = new HashSet [dateSet.size()];
        for (int i = 0; i<dateSet.size(); i++) {
            setArray[i] = new HashSet<>();
        }

        int aux = 0;
        for (Task task : tasks) {
            if (task.isRepeated()) {
                taskRepetition = 0;
                intervalMinutes = task.toMinutes(task.getRepeatInterval());
                startMinutes = task.toMinutes(task.getStartTime());
                endMinutes = task.toMinutes(task.getEndTime());

                while ((startMinutes+(taskRepetition*intervalMinutes) <
                        endMinutes)) {
                    taskRepetition++;
                }


                for (int i = 0; i<taskRepetition; i++) {
                    aux = dateList.indexOf(task.getStartTime().plusMinutes(i*intervalMinutes));
                    setArray[aux].add(task);
                }
            }

            else {
                aux = dateList.indexOf(task.getTime());
                setArray[aux].add(task);
            }
        }
        aux = 0;

        for (LocalDateTime item : dateSet) {
            sortedMap.put(item, setArray[aux]);
            aux++;
        }

        return sortedMap.subMap(start, end);
    }
}
