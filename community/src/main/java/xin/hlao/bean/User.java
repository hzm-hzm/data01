package xin.hlao.bean;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

//用户
public class User {
//	id
    private String uid;
//姓名
    @NotEmpty(message="姓名不能为空！")
    @Length(min=1,max=10,message="长度为1到10！")
    private String username;
//昵称
    @NotEmpty(message="昵称不能为空！")
    private String nickname;
//密码
    @NotEmpty(message="密码不能为空！")
    private String password;
//电话号码
    @NotEmpty(message="电话不能为空！")
    @Pattern(regexp="^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$",message="手机格式有错！")
    private String phone;
//账号（邮箱）
    @NotEmpty(message="账号不能为空！")
    @Pattern(regexp="^([a-z0-9_.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$",message="邮箱格式有错！")
    private String email;
//性别
    private Integer sex;
//年龄
    private Integer age;
//地址
    private String address;
//图片
    private String picture;
//激活状态
    private Boolean state;

    
    
    public User() {
	super();
}
    

	public User(String uid, String username, String nickname, String password, String phone, String email, Integer sex,
			Integer age, String address, String picture, Boolean state) {
		super();
		this.uid = uid;
		this.username = username;
		this.nickname = nickname;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.sex = sex;
		this.age = age;
		this.address = address;
		this.picture = picture;
		this.state = state;
	}


	public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }


	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", nickname=" + nickname + ", password=" + password
				+ ", phone=" + phone + ", email=" + email + ", sex=" + sex + ", age=" + age + ", address=" + address
				+ ", picture=" + picture + ", state=" + state + "]";
	}
    
    
}