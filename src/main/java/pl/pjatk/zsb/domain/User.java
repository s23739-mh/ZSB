package pl.pjatk.zsb.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="users")
@ApiModel(value = "User", description = "User from DB")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value="User ID", required = true)
    private Integer id;
    @ApiModelProperty(value="User First Name", required = true)
    private String fname;
    @ApiModelProperty(value="User Second Name", required = true)
    private String sname;
    @ApiModelProperty(value="User Birth Date", required = true)
    private Date birthdate;
    @ApiModelProperty(value="User Place of Living", required = true)
    private String city;
    @ApiModelProperty(value="User Type of Permissions", required = true)
    @Enumerated(EnumType.STRING)
    private Type type;


    public User(Integer id, String fname, String sname, Date birthdate, String city, Type type) {
        this.id = id;
        this.fname = fname;
        this.sname = sname;
        this.birthdate = birthdate;
        this.city = city;
        this.type = type;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
