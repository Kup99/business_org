package my.application.com.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
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
    @Column(name = "goal_data")
    private Date goalDate;
    @Transient
    private int daysForGoal;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "goal_id")
    private List<Task> tasks;
    private long user_id;

    public int getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
    }

    public Date getGoalDate() {
        return goalDate;
    }

    public void setGoalDate(Date goalDate) {
        this.goalDate = goalDate;
    }

    public Goal(String name, String comment, int taskCount, Date goalDate, int daysForGoal, List<Task> tasks, long user_id) {
        this.name = name;
        this.comment = comment;
        this.taskCount = taskCount;
        this.goalDate = goalDate;
        this.daysForGoal = daysForGoal;
        this.tasks = tasks;
        this.user_id = user_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
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