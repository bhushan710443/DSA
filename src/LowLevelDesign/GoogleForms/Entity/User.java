package LowLevelDesign.GoogleForms.Entity;

public class User{

    private  String userId;
    private String name;
    private String email;
    private String roles;

    public User(String userId, String name, String email, String roles) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.roles = roles;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }


}
