package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin {
    @Id
    private Integer id;
    private String password;
    private String tel;
    private String email;
    private String currentAuthority="admin";
    private String status;

    public Admin(){

    }
    public Admin(Integer id, String password, String tel,  String currentAuthority, String status){
        this.id=id;
        this.password=password;
        this.tel=tel;
        this.currentAuthority=currentAuthority;
        this.status=status;

    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId(){
        return id;
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
    public String getCurrentAuthority(){
        return currentAuthority;
    }
}
