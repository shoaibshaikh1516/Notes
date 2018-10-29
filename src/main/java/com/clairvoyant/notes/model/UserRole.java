package com.clairvoyant.notes.model;

import javax.persistence.*;

@Entity
@Table(name = "tblUserRole")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userroleid")
    private int userRoleid;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "role_id")
    private int roleId;

    public UserRole() {
    }

    public UserRole(int userRoleid, int userId, int roleId) {
        this.userRoleid = userRoleid;
        this.userId = userId;
        this.roleId = roleId;
    }

    public int getUserRoleid() {
        return userRoleid;
    }

    public void setUserRoleid(int userRoleid) {
        this.userRoleid = userRoleid;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
