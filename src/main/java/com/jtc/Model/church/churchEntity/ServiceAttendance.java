package com.jtc.Model.church.churchEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//import org.springframework.data.annotation.Id;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServiceAttendance
{
        @Id
       private String serviceDate;
       private  String minist;
       private int attendanceNumber;
       private  String church_service;
       private int attendanceFemale;
       private int attendanceMale;
       private int attendanceChildren;

}
