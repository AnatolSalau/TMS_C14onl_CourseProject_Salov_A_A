package by.salov.tms.courseproject.entities.roles;

/**Enums with roles */
public enum Role {
    ROLE_ADMIN("ADMIN"),
    ROLE_DOCTOR("DOCTOR"),
    ROLE_PATIENT("PATIENT"),
    ROLE_USER("USER");

    private String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return this.roleName;
    }
}
