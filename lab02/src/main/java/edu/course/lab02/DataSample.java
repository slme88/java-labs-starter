package edu.course.lab02;

import java.util.Arrays;

public class DataSample {
    private final String id;
    private final String label;
    private SampleStatus status;
    private final double[] features;

    public DataSample(String id, String label, SampleStatus status, double[] features) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Id не может быть пустым или null");
        }
        if (label == null || label.trim().isEmpty()) {
            throw new IllegalArgumentException(" label не может быть пустой или null");
        }
        if (status == null) {
            throw new IllegalArgumentException("Статус не может быть null");
        }
        if (features == null) {
            throw new IllegalArgumentException("Массив не может быть null");
        }

        this.id = id;
        this.label = label;
        this.status = status;
        this.features = Arrays.copyOf(features, features.length);
    }

    public void changeStatus(SampleStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("Новый статус не может быть null");
        }
        this.status = newStatus;
    }

    public boolean isReady() {
        return this.status == SampleStatus.PROCESSED && this.features.length > 0;
    }

    public double calculateMean() {
        if (this.features.length == 0) {
            return 0.0;
        }
        double sum = 0.0;
        for (double val : this.features) {
            sum += val;
        }
        return sum / this.features.length;
    }

    public double[] getNormalizedFeatures() {
        double[] copy = getFeatures();
        double mean = calculateMean();
        for (int i = 0; i < copy.length; i++) {
            copy[i] -= mean; 
        }
        return copy;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public SampleStatus getStatus() {
        return status;
    }

    public double[] getFeatures() {
        return Arrays.copyOf(features, features.length);
    }
}