package com.jtc.Model.church.churchUi;

import com.jtc.Model.church.churchEntity.Member;
import com.jtc.Model.church.churchEntity.SerialMember;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class MemberList {
    private  ImageObjectResize imageObjectResize;
    private int size;
    private List<SerialMember> memberList;
    private static final Logger LOGGER = LoggerFactory.getLogger(MemberList.class);
    public MemberList(List<SerialMember>allMember)
    {

        this.memberList = allMember;
        setSizeMethod(allMember.size());
        LOGGER.info("Inside the MemberList constructor");

    }
    public void setSizeMethod(int size)
    {
        this.size = size;
    }
    public int getSizeMethod()
    {
        return size;
    }
    public CompletableFuture<Object[]> getObjectResized()
    {
        LOGGER.info("calling the CompletableFuture");

        return CompletableFuture.supplyAsync(imageObjectResize::getObject)
                .exceptionally(exec-> imageObjectResize.getObject());

    }
    public  Object[][] getResultingObject()
    {

        CompletableFuture<Object[]>[] list = new CompletableFuture[getSizeMethod()];
        for (int i=0; i <getSizeMethod(); i++)
        {
            LOGGER.info(" Object method to get object");
            imageObjectResize = new ImageObjectResize(memberList.get(i), new ImageConversion());


            list[i]=getObjectResized();

        }

       return  Stream.of(list).map(CompletableFuture::join).toArray( Object[][]::new);
    }
}
