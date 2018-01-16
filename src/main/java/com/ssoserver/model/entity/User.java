package com.ssoserver.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "USER")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    @Column(name = "ID", nullable = false, length = 32)
    private String id;

    @Column(name = "USER_NAME", nullable = false, unique = true, length = 45)
    private String userName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "PASSWORD_SALT", nullable = false, length = 32)
    private String passwordSalt;

    @Column(name = "FIRST_NAME", nullable = false, length = 45)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false, length = 45)
    private String lastName;

    @Column(name = "EMAIL_ADDRESS", nullable = false, unique = true, length = 45)
    private String email;

    @Column(name = "PHONE_NUMBER", nullable = false, length = 16)
    private String phone;

    @Column(name = "STATUS")
    private int status;

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.userName);
        hash = 29 * hash + Objects.hashCode(this.passwordSalt);
        hash = 29 * hash + Objects.hashCode(this.firstName);
        hash = 29 * hash + Objects.hashCode(this.lastName);
        hash = 29 * hash + Objects.hashCode(this.email);
        hash = 29 * hash + Objects.hashCode(this.phone);
        hash = 29 * hash + this.status;
//        hash = 29 * hash + Objects.hashCode(this.createdDate);
//        hash = 29 * hash + Objects.hashCode(this.updatedDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.passwordSalt, other.passwordSalt)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
//        if (!Objects.equals(this.createdDate, other.createdDate)) {
//            return false;
//        }
//        if (!Objects.equals(this.updatedDate, other.updatedDate)) {
//            return false;
//        }
        return true;
    }

}
