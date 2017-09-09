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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!username.equals(user.username)) return false;
        if (!password.equals(user.password)) return false;
        if (!email.equals(user.email)) return false;
        if (!sex.equals(user.sex)) return false;
        if (!profile.equals(user.profile)) return false;
        return website.equals(user.website);
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + sex.hashCode();
        result = 31 * result + profile.hashCode();
        result = 31 * result + website.hashCode();
        return result;
    }

    public User(String username, String password, String email, String sex, String profile, String website) {

        this.username = username;
        this.password = password;
        this.email = email;
        this.sex = sex;
        this.profile = profile;
        this.website = website;
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

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
