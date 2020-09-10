package com.victorp.model;

import java.util.List;

public class WorkoutGroup {

    private Trainer trainer;
    private List<Client> clientList;
    private String trainingTime;

    public WorkoutGroup() {
    }

    public WorkoutGroup(Trainer trainer, List<Client> clientList, String trainingTime) {
        this.trainer = trainer;
        this.clientList = clientList;
        this.trainingTime = trainingTime;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }
    public void setClientList(Client client){
        this.clientList.add(client);
    }

    public String getTrainingTime() {
        return trainingTime;
    }

    public void setTrainingTime(String trainingTime) {
        this.trainingTime = trainingTime;
    }
}
