package zjut.xiaofeng.myapplication1.bean;

public class UserBean {
    // 属性声明
    private String username;
    private String password;

    //构造方法
    public UserBean() {
    }

    // get方法
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // set方法
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
