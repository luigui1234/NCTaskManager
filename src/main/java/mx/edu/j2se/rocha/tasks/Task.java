package mx.edu.j2se.rocha.tasks;

public class Task {
    private String title;
    private boolean active;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean repeated;

    public Task(String title, int time) {
        this.active = false;
        this.repeated = false;
        this.time = time;
        this.title = title;
    }

    public Task(String title, int start, int end, int interval) {
        this.active = false;
        this.repeated = true;
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public void setTime(int time) {
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

    public int nextTimeAfter (int current) {
        int taskRepetition = 0;
        if (this.active == false) {
            return -1;
        }

        if(this.repeated == false) {
            return this.time;
        }

        while ((this.getStartTime()+(taskRepetition*this.getRepeatInterval())
                <current) && (this.getStartTime()+(taskRepetition*
                this.getRepeatInterval()) < this.getEndTime())) {
            taskRepetition++;
        }
        System.out.println(taskRepetition);
        if (this.getStartTime()+(taskRepetition*
                this.getRepeatInterval()) > this.getEndTime()) {
            return -1;
        }

        return this.getStartTime()+(taskRepetition*this.getRepeatInterval());
    }
}
