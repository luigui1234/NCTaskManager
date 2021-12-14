package mx.edu.j2se.rocha.tasks;

public class Task {
    private String title;
    private boolean active;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean repeated;

    public Task(String title, int time) throws IllegalArgumentException, NullPointerException {
        if (time<0) {
            throw new IllegalArgumentException();
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

    public Task(String title, int start, int end, int interval) throws IllegalArgumentException, NullPointerException {
        if (start<0 || end<0 || interval<0) {
            throw new IllegalArgumentException();
        }

        else if (end < start) {
            throw new IllegalArgumentException("End time should be greater " +
                    "than start time");
        }

        else if ((start-end)<interval) {
            throw new IllegalArgumentException("Interval should be smaller in" +
                    " order that task is repeated");
        }

        else if (title == null) {
            throw new NullPointerException("You must specify a title");
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

    public int getTime() {
        if (this.repeated == true) {
            return this.start;
        }
        return this.time;
    }

    public void setTime(int time) throws IllegalArgumentException {
        if (time<0) {
            throw new IllegalArgumentException("Time units cannot be negative");
        }

        this.time = time;
        if (this.repeated == true) {
            this.repeated = false;
        }
    }

    public int getStartTime() {
        if (this.repeated == false) {
            return this.time;
        }
        return this.start;
    }

    public int getEndTime() {
        if (this.repeated == false) {
            return this.time;
        }
        return this.end;
    }

    public int getRepeatInterval() {
        if (this.repeated == false) {
            return 0;
        }
        return this.interval;
    }

    public void setTime(int start, int end, int interval) {
        if (start<0 || end<0 || interval<0) {
            throw new IllegalArgumentException("Time units cannot be negative");
        }

        else if (end < start) {
            throw new IllegalArgumentException("End time should be greater " +
                    "than start time");
        }

        else if ((start-end)<interval) {
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

    public int nextTimeAfter (int current) throws IllegalArgumentException {

        if (current<0) {
            throw new IllegalArgumentException("Time units cannot be negative");
        }


        int taskRepetition = 0;
        if (this.active == false) {
            return -1;
        }

        if(this.repeated == false) {
            if (current>this.time) {
                throw new IllegalArgumentException("Current time cannot be " +
                        "greater than task time");
            }
            return this.time;
        }

        if (current>this.getEndTime()) {
            throw new IllegalArgumentException("Current time cannot be " +
                    "greater than task time");
        }

        while ((this.getStartTime()+(taskRepetition*this.getRepeatInterval())
                <current) && (this.getStartTime()+(taskRepetition*
                this.getRepeatInterval()) < this.getEndTime())) {
            taskRepetition++;
        }

        if (this.getStartTime()+(taskRepetition*
                this.getRepeatInterval()) > this.getEndTime()) {
            return -1;
        }

        return this.getStartTime()+(taskRepetition*this.getRepeatInterval());
    }
}
