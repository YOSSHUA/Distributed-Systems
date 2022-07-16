package mx.itam.packages.rmi.serializableobjects;

import java.io.Serializable;

public class Task implements Serializable {
    int taskId;
    int requirementId;
    int length;
    int output;

    public Task(int taskId, int requirementId, int length) {
        this.taskId = taskId;
        this.requirementId = requirementId;
        this.length = length;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(int requirementId) {
        this.requirementId = requirementId;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getOutput() {
        return output;
    }

    public void setOutput(int output) {
        this.output = output;
    }
}
