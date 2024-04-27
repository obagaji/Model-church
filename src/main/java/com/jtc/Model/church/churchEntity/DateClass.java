package com.jtc.Model.church.churchEntity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*@AllArgsConstructor
@NoArgsConstructor
@Data*/
@Table(name= "date_class")
@Entity
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

    public DateClass(String classDate) {
        this.classDate = classDate;
    }

    public DateClass() {
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

    public String getIdMember() {
        return idmember;
    }
    public void setIdMember(String idMember) {
        this.idmember = idmember;
    }
}