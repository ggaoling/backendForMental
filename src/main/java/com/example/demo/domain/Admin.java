package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    private Integer uid;
    private String name;
    private String password;
    private String tel;
    private String email;
    @Transient
    private String currentAuthority="admin";
    @Transient
    private String status;

    public Admin(){

    }
    public Admin(Integer id, String name,String password, String tel,  String currentAuthority, String status){
        this.uid=id;
        this.name=name;
        this.password=password;
        this.tel=tel;
        this.currentAuthority=currentAuthority;
        this.status=status;

    }

    public void setId(Integer id) {
        this.uid = id;
    }
    public Integer getId(){
        return uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
