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
public class Maintenance {

    @Id
   // @GeneratedValue
   private int workNumber;
   private String work_name;
   private String description;
   private String activityStartDate;
   private String completionDate;
   private double cost;
   private String supervisorId;
   private String status;
   private String remark;

}
