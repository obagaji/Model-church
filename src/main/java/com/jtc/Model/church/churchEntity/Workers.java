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



      public String getWorkerId() {
            return workerId;
      }

      public void setWorkerId(String workerId) {
            this.workerId = workerId;
      }

      public String getWorkerPosition() {
            return workerPosition;
      }

      public void setWorkerPosition(String workerPosition) {
            this.workerPosition = workerPosition;
      }

      public String getJoinDate() {
            return joinDate;
      }

      public void setJoinDate(String joinDate) {
            this.joinDate = joinDate;
      }

      public String getDepartNames() {
            return departNames;
      }

      public void setDepartNames(String departNames) {
            this.departNames = departNames;
      }
}


