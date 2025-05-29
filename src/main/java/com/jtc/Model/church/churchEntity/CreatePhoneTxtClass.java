package com.jtc.Model.church.churchEntity;

public class CreatePhoneTxtClass {

    public void createPhoneTxtFile(String[] phone) {
        Filephone writer = new Filephone();
        writer.writeUsingBufferedWriter(phone);
    }

}
