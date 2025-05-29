package com.jtc.Model.church.churchEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SerialMember {


    @Id
    private int serial_number;
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
}
