package com.jtc.Model.church.churchEntity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
public class NonWorker {

    @Id
    private String nonWorkerId;

    private String joinDate;

    public NonWorker() {
    }

    public NonWorker(String nonWorkerId, String joinDate) {
        this.nonWorkerId = nonWorkerId;
        this.joinDate = joinDate;
    }


    public String getNonWorkerId() {
        return nonWorkerId;
    }

    public void setNonWorkerId(String nonWorkerId) {
        this.nonWorkerId = nonWorkerId;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }
}
