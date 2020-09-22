package my.application.com.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "goal")
@JsonIgnoreProperties(ignoreUnknown = false)

public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "comment")
    private String comment;
    @Transient
    private int taskCount;
    @Column(name = "days_for_goal")
    private int daysForGoal;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "task_id")
    private List<Task> tasks;

    public Goal(String name, String comment, int daysForGoal, List<Task> tasks) {
        this.name = name;
        this.comment = comment;
        this.daysForGoal = daysForGoal;
        this.tasks = tasks;
    }

    public Goal() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getDaysForGoal() {
        return daysForGoal;
    }

    public void setDaysForGoal(int daysForGoal) {
        this.daysForGoal = daysForGoal;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}