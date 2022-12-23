package com.komsoft.shopspringmvc.model;

import com.komsoft.shopspringmvc.util.Encoding;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    protected String login;
    protected String encryptedPassword;
    protected String fullName;
    protected String region;
    protected String gender;
    protected String comment;

    public UserModel() {
    }

    public UserModel(long id, String login, String password, String fullName, String region, String gender, String comment) {
        this.id = id;
        this.login = login;
        this.encryptedPassword = UserModel.encryptPassword(password);
        this.fullName = fullName;
        this.region = region;
        this.gender = gender;
        this.comment = comment;
    }

    public UserModel(long id, String login, String fullName, String region, String gender, String comment) {
        this.id = id;
        this.login = login;
        this.encryptedPassword = null;
        this.fullName = fullName;
        this.region = region;
        this.gender = gender;
        this.comment = comment;
    }

    public long getId() {
        return this.id;
    }

    public String getLogin() {
        return this.login;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public String getRegion() {
        return region;
    }

    public String getGender() {
        return gender;
    }

    public String getComment() {
        return comment;
    }

    public static String encryptPassword(String password) {
//        return Encoding.md5EncryptionWithSalt(password);
        return Encoding.bCryptEncryption(password);
    }

}
