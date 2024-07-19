package ru.exfadra.homeRent.model;



import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String patronymic;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private Date birthday;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Long tenantRating;

    @Column(nullable = false)
    private Long landLordRating;

    @Column()
    private Long tenantMarkCount;

    @Column()
    private Long landlordMarkCount;

    @Column(name="enabled",nullable = false)
    private boolean isEnable;

    @Column
    private String roles;

    public User() {
    }

    public User(String firstName, String lastName, String patronymic, String city,
                String country, String nickname, String password, Date birthday, String email,
                Long tenantRating, Long landLordRating, Long tenantMarkCount, Long landlordMarkCount, boolean isEnable, String roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.city = city;
        this.country = country;
        this.nickname = nickname;
        this.password = password;
        this.birthday = birthday;
        this.email = email;
        this.tenantRating = tenantRating;
        this.landLordRating = landLordRating;
        this.tenantMarkCount = tenantMarkCount;
        this.landlordMarkCount = landlordMarkCount;
        this.isEnable = isEnable;
        this.roles = roles;
    }


    public List<String> getRoleList(){
        if(this.roles.length() > 0){
            System.out.println("User has roles");
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }
}
