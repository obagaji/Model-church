package com.jtc.Model.church.churchEntity;

//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.Id;

//@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FirstTimer {

  //  @Id
    private int serialNo;
    private String loginId;
}
