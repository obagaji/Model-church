package com.jtc.Model.church.churchEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import java.sql.Blob;
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
        private String status;
        private int attendance;
       private String registerDate;
       private String resent;
    @Column(name="member_photo",unique = true)
    private String memberPhoto;

    public Member(String id, String sex, String lastName, String firstName, String address, String dateBorn, String phone,
                  String status,int attendance, String registerDate,
                  String resent, String memberPhoto) {
        this.id = id;
        this.sex = sex;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.dateBorn = dateBorn;
        this.phone = phone;
        this.status = status;
        this.memberPhoto = memberPhoto;
        this.attendance = attendance;
        this.registerDate = registerDate;
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

    public String getMemberPhoto() {
        return memberPhoto;
    }

    public void setMemberPhoto(String memberPhoto) {
        this.memberPhoto = memberPhoto;
    }
    public String getResent() {
        return resent;
    }

    public void setResent(String resent) {
        this.resent = resent;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String loginDate) {
        this.registerDate = registerDate;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance =  attendance;
    }

}




