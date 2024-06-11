package com.jtc.Model.church.churchEntity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NonWorker {

    @Id
    private String nonWorkerId;

    private String joinDate;



}
