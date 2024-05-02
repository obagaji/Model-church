package com.jtc.Model.church.churchEntity;

//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.Id;


//@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
//@Table(name = "church_attendance")
public class ChurchAttendance {

  //  @Id
    String serviceDate;
    String serviceTopic;
    String ministerName;
    int maleTotal;
     int femaleTotal;
     int ServiceTotal;
    int childrenNumber;

}
