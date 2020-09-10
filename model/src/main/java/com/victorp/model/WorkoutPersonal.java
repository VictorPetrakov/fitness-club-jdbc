package com.victorp.model;

public class WorkoutPersonal {

    private Trainer trainer;
    private Client client;
    private String trainingTime;

    public WorkoutPersonal() {
    }

    public WorkoutPersonal(Trainer trainer, Client client, String trainingTime) {
        this.trainer = trainer;
        this.client = client;
        this.trainingTime = trainingTime;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getTrainingTime() {
        return trainingTime;
    }

    public void setTrainingTime(String trainingTime) {
        this.trainingTime = trainingTime;
    }
}
