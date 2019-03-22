package com.example.demo.domain;


import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;

@Entity
public class User extends Admin{
    @Id
    private Integer uid;
    private String password;
    private String tel;
    private String email;
    private Integer history;
    @Transient
    private String currentAuthority="user";
    @Transient
    private String status;

    public User(){

    }
    public User(Integer id, String password, String tel, Integer history, String currentAuthority, String status){
        this.uid=id;
        this.password=password;
        this.tel=tel;
        this.history=history;
        this.currentAuthority=currentAuthority;
        this.status=status;

    }

    public void setId(Integer id) {
        this.uid = id;
    }
    public Integer getId(){
        return uid;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password){
        this.password=password;
    }

    public String getEmail() {
        return email;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getHistory() {
        return history;
    }

    public void setHistory(Integer history) {
        this.history = history;
    }

    @Transient
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTel() {
        return tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCurrentAuthority(String currentAuthority) {
        this.currentAuthority = currentAuthority;
    }
    @Transient
    public String getCurrentAuthority(){
        return currentAuthority;
    }

}
