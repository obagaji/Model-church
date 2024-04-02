package com.jtc.Model.church.churchEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
//import org.springframework.data.annotation.Id;


@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
public class Member {

        @Id
        private String id;
        private String sex;
        private String lastName;
        private String firstName;
        private String address;
        private String dateBorn;
        private String phone;
        private String marry;
        private String emailAddress;
        private Integer attendance;
       // private int total = 0;
       private String loginDate;
       private String  inChurch;
       private String resent;

    public Member(String id, String sex, String lastName, String firstName, String address, String dateBorn, String phone,
                  String marry, String emailAddress, Integer attendance, String loginDate, String inChurch,
                  String resent) {
        this.id = id;
        this.sex = sex;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.dateBorn = dateBorn;
        this.phone = phone;
        this.marry = marry;
        this.emailAddress = emailAddress;
        this.attendance = attendance;
        this.loginDate = loginDate;
        this.inChurch = inChurch;
        this.resent = resent;
    }

    public Member() {
        }

        public Member(String i) {
            this.setId(i);
        }

    public void setAttendance(Integer attendance) {
        this.attendance = attendance;
    }

/*    public NonWorker getNonworker() {
        return nonworker;
    }

    public void setNonworker(NonWorker nonworker) {
        this.nonworker = nonworker;
    }*/

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getResent() {
        return resent;
    }

    public void setResent(String resent) {
        this.resent = resent;
    }

    public String getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    public String getInChurch() {
        return inChurch;
    }

    public void setInChurch(String inChurch) {
        this.inChurch = inChurch;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMarry() {
        return marry;
    }

    public void setMarry(String marry) {
        this.marry = marry;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDateBorn() {
        return dateBorn;
    }

    public void setDateBorn(String dateBorn) {
        this.dateBorn = dateBorn;
    }
/*
    public String getemailAddress() {
        return emailAddress;
    }

    public void setemailAddress(String eAddress) {
        this.emailAddress = emailAddress;
    }*/

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = 1 + attendance;
    }

}




