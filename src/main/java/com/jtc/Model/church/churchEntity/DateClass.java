package com.jtc.Model.church.churchEntity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name= "date_class")
@Entity
@Getter
@Setter
public class DateClass
{
    @Id
    private String idmember;

    private String classDate;

    private String outChurch;

    public DateClass(String idmember, String classDate, String outChurch) {
        this.idmember = idmember;

        this.classDate = classDate;

        this.outChurch = outChurch;
    }

    public String getIdmember() {
        return idmember;
    }

    public void setIdmember(String idmember) {
        this.idmember = idmember;
    }

    public String getClassDate() {
        return classDate;
    }

    public void setClassDate(String classDate) {
        this.classDate = classDate;
    }

    public String getOutChurch() {
        return outChurch;
    }

    public void setOutChurch(String outChurch) {
        this.outChurch = outChurch;
    }
}