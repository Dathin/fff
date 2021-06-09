package me.pedrocaires.fff.environment.model;

public class CreateEnvironmentRequest {

    private String name;

    private int projectId;

    private boolean forMe;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public boolean isForMe() {
        return forMe;
    }

    public void setForMe(boolean forMe) {
        this.forMe = forMe;
    }
}
