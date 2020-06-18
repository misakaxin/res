package com.yc.bean;

public class Resuser {
    private Integer userid;
    private String username ;
    private String pwd  ;
    private String email;
    private String address;
    private String telephone;

    @Override
    public String toString() {
        return "Resuser{" +
               "userid=" + userid +
               ", username='" + username + '\'' +
               ", pwd='" + pwd + '\'' +
               ", email='" + email + '\'' +
               ", address='" + address + '\'' +
               ", telephone='" + telephone + '\'' +
               '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address=address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone=telephone;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid=userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username=username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd=pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email=email;
    }

}
