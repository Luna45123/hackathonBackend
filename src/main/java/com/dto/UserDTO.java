package com.dto;

public class UserDTO {
    private int id;
    private String ename;
    private String email;
    private String password;
    private int membership;
    private int canWrite;
    public UserDTO(int id, String ename, String email, String password, int membership, int canWrite) {
        this.id = id;
        this.ename = ename;
        this.email = email;
        this.password = password;
        this.membership = membership;
        this.canWrite = canWrite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMembership() {
        return membership;
    }

    public void setMembership(int membership) {
        this.membership = membership;
    }
    

    @Override
    public String toString() {
        return "UserDTO [id=" + id + ", ename=" + ename + ", email=" + email + ", password=" + password
                + ", membership=" + membership + "]";
    }

    public int getCanWrite() {
        return canWrite;
    }

    public void setCanWrite(int canWrite) {
        this.canWrite = canWrite;
    }

}
