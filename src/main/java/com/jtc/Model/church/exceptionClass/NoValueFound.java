package com.jtc.Model.church.exceptionClass;

import com.jtc.Model.church.churchEntity.Member;

import java.util.function.Supplier;

public class NoValueFound extends Exception implements Supplier<Member> {
    @Override
    public Member get() {
        return new Member("No Id","","No Name","No Name","","",
                "","",0,"","", "");
    }
}
