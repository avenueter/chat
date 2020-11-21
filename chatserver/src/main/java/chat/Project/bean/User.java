package chat.Project.bean;

/**
 * User类和数据库对应
 */
public class User {
    private int id;
    private String name;
    private String pwd;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"pwd\":\"")
                .append(pwd).append('\"');
        sb.append(",\"email\":\"")
                .append(email).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
