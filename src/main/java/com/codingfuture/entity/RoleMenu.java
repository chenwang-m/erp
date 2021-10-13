package com.codingfuture.entity;

//@Data
//@AllArgsConstructor
public class RoleMenu {
    private Long roleuuid;
    private String rolename;

    public Long getRoleuuid() {
        return roleuuid;
    }

    public void setRoleuuid(Long roleuuid) {
        this.roleuuid = roleuuid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
