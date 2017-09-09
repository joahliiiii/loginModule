package cn.joah.login.entity;

/**
 * 实体类
 */
public class User {
    private String username;
    private String password;
    private String email;
    private String sex;
    private String profile;
    private String website;

    public User() {
        super();
    }

    public User(String username, String password, String email, String sex, String profile, String website) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.sex = sex;
        this.profile = profile;
        this.website = website;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User User = (User) o;

        if (username != null ? !username.equals(User.username) : User.username != null) return false;
        if (password != null ? !password.equals(User.password) : User.password != null) return false;
        if (email != null ? !email.equals(User.email) : User.email != null) return false;
        if (sex != null ? !sex.equals(User.sex) : User.sex != null) return false;
        if (profile != null ? !profile.equals(User.profile) : User.profile != null) return false;
        return website != null ? website.equals(User.website) : User.website == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (profile != null ? profile.hashCode() : 0);
        result = 31 * result + (website != null ? website.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                ", profile='" + profile + '\'' +
                ", website='" + website + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProfil() {
        return profile;
    }

    public void setProfil(String profile) {
        this.profile = profile;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}