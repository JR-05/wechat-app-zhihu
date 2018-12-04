package com.jr.zhihu.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Table(name = "user")
@Entity
public class UserEntity {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "user_name", length = 6)
    @Size(min = 3, max = 6, message = "账户名长度必须在{min}~{max}之间")
    private String userName;

    @Column(length = 12)
    private Integer password;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "brith_day")
    @DateTimeFormat(pattern = "yyyy-MM-dd") //时间格式转换
    private Date brithDay;

    @Column(name = "image_path")
    private String imagePath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public Date getBrithDay() {
        return brithDay;
    }

    public void setBrithDay(Date brithDay) {
        this.brithDay = brithDay;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public UserEntity() {
    }

    public UserEntity(Integer id, String userName, Integer password, Date brithDay, String imagePath) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.brithDay = brithDay;
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password=" + password +
                ", brithDay=" + brithDay +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
