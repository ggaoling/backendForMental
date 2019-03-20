package com.example.demo.domain;

public class User {
    private int id;
    private String password;
    private String tel;
    private String email;
    private int history;
    private String currentAuthority;
    private String status;

    public User(){

    }
    public User(int id, String password, String tel, int history, String currentAuthority, String status){
        this.id=id;
        this.password=password;
        this.tel=tel;
        this.history=history;
        this.currentAuthority=currentAuthority;
        this.status=status;

    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId(){
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

    public int getHistory() {
        return history;
    }

    public void setHistory(int history) {
        this.history = history;
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
