package com.jtc.Model.church.exceptionClass;

import java.util.function.Supplier;

public class NoStringFound extends Exception implements Supplier<String> {
    @Override
    public String get() {
        return "No Value Found";
    }
}
