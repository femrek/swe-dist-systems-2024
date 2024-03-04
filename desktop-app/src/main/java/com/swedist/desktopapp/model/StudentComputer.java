package com.swedist.desktopapp.model;

import java.util.Date;

public class StudentComputer {
    private int id;
    private int studentId;
    private int computerId;
    private Date reservationDate;
    private Date endDate;
    private int durationInMinutes;

    public StudentComputer() {
    }

    public StudentComputer(int id, int studentId, int computerId, Date reservationDate, Date endDate, int durationInMinutes) {
        this.id = id;
        this.studentId = studentId;
        this.computerId = computerId;
        this.reservationDate = reservationDate;
        this.endDate = endDate;
        this.durationInMinutes = durationInMinutes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getComputerId() {
        return computerId;
    }

    public void setComputerId(int computerId) {
        this.computerId = computerId;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    @Override
    public String toString() {
        return "StudentComputer{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", computerId=" + computerId +
                ", reservationDate=" + reservationDate +
                ", endDate=" + endDate +
                ", durationInMinutes=" + durationInMinutes +
                '}';
    }
}
