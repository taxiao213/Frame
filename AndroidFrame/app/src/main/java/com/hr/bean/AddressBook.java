package com.hr.bean;

/**
 * Created by syx on 2017/3/31.
 */

public class AddressBook {
    private String mobile;
    private String name;
    private String phone;
    private String certificate;
    private String id;

    public String getMoblie() {
        return mobile;
    }

    public void setMoblie(String moblie) {
        this.mobile = moblie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getCurrent_id() {
        return id;
    }

    public void setCurrent_id(String current_id) {
        this.id = current_id;
    }

    @Override
    public String toString() {
        return "AddressBook{" +
                "moblie='" + mobile + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", certificate='" + certificate + '\'' +
                ", current_id='" + id + '\'' +
                '}';
    }

    public AddressBook(String moblie, String name, String phone, String certificate, String current_id) {
        this.mobile = moblie;
        this.name = name;
        this.phone = phone;
        this.certificate = certificate;
        this.id = current_id;
    }

    public AddressBook() {
    }
}
