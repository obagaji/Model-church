package com.jtc.Model.church.churchEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class ChurchAttendance {

    private String serviceDate;

    private String serviceTopic;

    private String ministerName;

    private int maleTotal;

    private int femaleTotal;

    private int ServiceTotal;

    private int childrenNumber;

}
