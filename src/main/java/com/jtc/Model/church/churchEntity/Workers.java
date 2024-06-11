package com.jtc.Model.church.churchEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Workers
{
      @Id
      private String workerId;

      private String  workerPosition;

      private String joinDate;

      private String departNames;


}


