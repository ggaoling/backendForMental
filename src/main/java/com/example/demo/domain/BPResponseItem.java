package com.example.demo.domain;

public class BPResponseItem {
    private String name;
    private Integer O;
    private  Integer A;
    private Integer E;
    private Integer N;
    private Integer C;

    public BPResponseItem(String name,Integer O,Integer A,Integer E,Integer N,Integer C){
    this.name=name;
    this.A=A;
    this.C=C;
    this.E=E;
    this.O=O;
    this.N=N;
    }
    public BPResponseItem(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getA() {
        return A;
    }

    public Integer getC() {
        return C;
    }

    public Integer getE() {
        return E;
    }

    public Integer getN() {
        return N;
    }

    public Integer getO() {
        return O;
    }

    public void setA(Integer a) {
        A = a;
    }

    public void setC(Integer c) {
        C = c;
    }

    public void setE(Integer e) {
        E = e;
    }

    public void setN(Integer n) {
        N = n;
    }

    public void setO(Integer o) {
        O = o;
    }
}
