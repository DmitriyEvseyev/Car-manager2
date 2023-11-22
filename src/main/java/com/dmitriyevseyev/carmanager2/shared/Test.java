package com.dmitriyevseyev.carmanager2.shared;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

public class Test {
    Integer a;
    String b;


    public Test() {
    }

    public Test(Integer a, String b) {
        this.a = a;
        this.b = b;
    }

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return Objects.equals(a, test.a) && Objects.equals(b, test.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    @Override
    public String toString() {
        return "Test{" +
                "a=" + a +
                ", b='" + b + '\'' +
                '}';
    }
}
