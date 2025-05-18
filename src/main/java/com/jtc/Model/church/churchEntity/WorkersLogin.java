package com.jtc.Model.church.churchEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
//@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter

public class WorkersLogin implements Serializable {
    @Id
   private String id;
 //   public static final long serialVersionUID = 1234L;
   private String loginTime;
   private String memberStatus;
   private String loginDate;

    public WorkersLogin() {
    }

    public WorkersLogin(String id, String loginTime,String memberStatus,String loginDate) {
        this.id = id;
        this.loginTime = loginTime;
        this.memberStatus = memberStatus;
        this.loginDate = loginDate;
    }
/*
    public String getWorkerTimeId() {
        return id;
    }

    public void setWorkerTimeId(String workerTimeId) {
        this.id = workerTimeId;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }

    public String getMemberStatus() {
        return memberStatus;
    }

    public String getLoginDate() {
     return loginDate;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }*/
}
