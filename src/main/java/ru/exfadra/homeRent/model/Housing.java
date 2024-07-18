package ru.exfadra.homeRent.model;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Data
@Entity
public class Housing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private Long rentDays;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Long rating;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column()
    private Boolean tenantDone;

    @Column()
    private Boolean landlordDone;

    @Column()
    private byte[] image;

    @OneToOne (cascade = CascadeType.ALL)
    private User landLord;

    @OneToOne (cascade = CascadeType.ALL)
    private User tenant;


    public Housing() {
    }

    public Housing(String city, String country, Long rentDays, Long price,
                   Long rating, String name, String description, Boolean tenantDone, Boolean landlordDone, byte[] image, User landLord, User tenant) {
        this.city = city;
        this.country = country;
        this.rentDays = rentDays;
        this.price = price;
        this.rating = rating;
        this.name = name;
        this.description = description;
        this.tenantDone = tenantDone;
        this.landlordDone = landlordDone;
        this.image = image;
        this.landLord = landLord;
        this.tenant = tenant;
    }


    public String getDecodedImgPath(){
        return new String(Base64.getDecoder().decode(image));
    }
}
