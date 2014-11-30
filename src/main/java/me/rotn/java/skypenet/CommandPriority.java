package me.rotn.java.skypenet;

public enum CommandPriority {

    LOWEST(0), LOW(1), NORMAL(2), HIGH(3), HIGHEST(4), OMGSOHIGHNOONEWILLDENYYOU(Integer.MAX_VALUE);

    private int priority;

    CommandPriority(int priority) {
        this.priority = priority;
    }

    public boolean moreImportantThan(CommandPriority other) {
        return this.priority > other.priority;
    }

}
