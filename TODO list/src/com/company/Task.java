package com.company;

public class Task implements Task1 {
    public Condition condition;
    public String content;
    Task(String content, Condition t){
        this.condition=Condition.NOTDONE;
        this.content=content;
    }
    public String toString(){
        return this.content + " - " + this.condition;
    }
    public boolean equals(String content) {
        return this.content.equals(content);
    }
}
