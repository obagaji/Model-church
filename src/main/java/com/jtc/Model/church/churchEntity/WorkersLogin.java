package com.jtc.Model.church.churchEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
public class WorkersLogin {
    @Id
   private String id;
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
    }
}
