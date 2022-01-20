package mx.edu.j2se.rocha.tasks;

import java.time.LocalDateTime;
import java.util.Objects;

public class Task {
    private String title;
    private boolean active;
    private LocalDateTime time;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime interval;
    private boolean repeated;

    public Task(String title, LocalDateTime time) throws IllegalArgumentException,
            NullPointerException {

        if (time.getYear()<0) {
            throw new IllegalArgumentException();
        }

        else if (time==null) {
            throw new NullPointerException();
        }

        else if (title == null) {
            throw new NullPointerException();
        }

        else {
            this.active = false;
            this.repeated = false;
            this.time = time;
            this.title = title;
        }

    }

    public Task(String title,
                LocalDateTime start,
                LocalDateTime end,
                LocalDateTime interval) throws IllegalArgumentException, NullPointerException {

        if (start.getYear()<0 || end.getYear()<0 || interval.getYear()<0) {
            throw new IllegalArgumentException();
        }

        else if (end.isBefore(start)) {
            throw new IllegalArgumentException("End time should be greater " +
                    "than start time");
        }

        else if ((end.getYear() - start.getYear()) < interval.getYear() &&
                (end.getDayOfYear() - start.getDayOfYear()) < interval.getDayOfYear() &&
                (end.getHour() - start.getHour()) < interval.getHour() &&
                (end.getMinute() - start.getMinute()) < interval.getMinute()) {
            throw new IllegalArgumentException("Interval should be smaller in" +
                    " order that task is repeated");
        }

        else if (title == null || start == null || end == null || interval == null) {
            throw new NullPointerException("Your element should not be empty");
        }


        else {
            this.active = false;
            this.repeated = true;
            this.title = title;
            this.start = start;
            this.end = end;
            this.interval = interval;
        }

    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) throws NullPointerException {
        try {
            this.title = title;
        }
        catch (NullPointerException ex) {
            throw ex;
        }
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getTime() {
        if (this.repeated == true) {
            return this.start;
        }
        return this.time;
    }

    public void setTime(LocalDateTime time) throws IllegalArgumentException {

        if (time.getYear()<0) {
            throw new IllegalArgumentException("Time units cannot be negative");
        }

        this.time = time;
        if (this.repeated == true) {
            this.repeated = false;
        }
    }

    public LocalDateTime getStartTime() {
        if (this.repeated == false) {
            return this.time;
        }
        return this.start;
    }

    public LocalDateTime getEndTime() {
        if (this.repeated == false) {
            return this.time;
        }
        return this.end;
    }

    public LocalDateTime getRepeatInterval() {
        if (this.repeated == false) {
            return LocalDateTime.of(0, 1, 1, 0, 0);
        }
        return this.interval;
    }

    public void setTime(LocalDateTime start, LocalDateTime end, LocalDateTime interval) {

        if (start.getYear() <0 || end.getYear() <0 || interval.getYear() <0) {
            throw new IllegalArgumentException("Time units cannot be negative");
        }

        if (end.isBefore(start)) {
            throw new IllegalArgumentException("End time should be greater " +
                    "than start time");
        }

        else if ((end.getYear() - start.getYear()) < interval.getYear() &&
                (end.getDayOfYear() - start.getDayOfYear()) < interval.getDayOfYear() &&
                (end.getHour() - start.getHour()) < interval.getHour() &&
                (end.getMinute() - start.getMinute()) < interval.getMinute()) {
            throw new IllegalArgumentException("Interval should be smaller in" +
                    " order that task is repeated");
        }

        this.start = start;
        this.end = end;
        this.interval = interval;
        if (this.repeated == false) {
            this.repeated = true;
        }
    }

    public boolean isRepeated() {
        return this.repeated;
    }

    public LocalDateTime nextTimeAfter (LocalDateTime current) throws IllegalArgumentException {

        int taskRepetition = 0;
        if (this.active == false) {
            return LocalDateTime.of(-1, 1, 1, 0, 0);
        }

        long intervalMinutes = toMinutes(getRepeatInterval());
        long startMinutes = toMinutes(getStartTime());
        long endMinutes = toMinutes(getEndTime());
        long currentMinutes = toMinutes(current);

        while ((startMinutes+(taskRepetition*intervalMinutes)
                <currentMinutes) && (startMinutes+(taskRepetition*
                intervalMinutes) < endMinutes)) {
            taskRepetition++;
        }


        if (startMinutes+(taskRepetition*intervalMinutes) > endMinutes) {
            return LocalDateTime.of(-1, 1, 1, 0, 0);
        }

        /*if(this.repeated == false) {
            if (current>=this.time) {
                throw new IllegalArgumentException("Current time cannot be " +
                        "greater than task time");
            }
            return this.time;
        }

        if (current>=this.getEndTime()) {
            throw new IllegalArgumentException("Current time cannot be " +
                    "greater than task time");
        }*/
        return this.getStartTime().plusMinutes(taskRepetition*intervalMinutes);
    }

    public long toMinutes (LocalDateTime time) {
        return time.getYear()*365*24*60 + (time.getDayOfYear()-1)*24*60 +
                time.getHour()*60 + time.getMinute();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return active == task.active && time == task.time && start == task.start && end == task.end && interval == task.interval && repeated == task.repeated && title.equals(task.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, active, time, start, end, interval, repeated);
    }
}
