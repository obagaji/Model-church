package com.jtc.Model.church.churchEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
//import org.springframework.data.annotation.Id;


@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
@Setter
@Getter
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

        @Column(name="member_photo")
        private String memberPhoto;

    public Member(String id, String sex, String lastName, String firstName, String address, String dateBorn, String phone,
                  String status,int attendance, String registerDate,
                  String resent)//, String memberPhoto)
     {
        this.id = id;
        this.sex = sex;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.dateBorn = dateBorn;
        this.phone = phone;
        this.status = status;
    //    this.memberPhoto = memberPhoto;
        this.attendance = attendance;
        this.registerDate = registerDate;
        this.resent = resent;
    }

}




