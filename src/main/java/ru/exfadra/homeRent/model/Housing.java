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

    @OneToOne (cascade = CascadeType.ALL)
    private User landLord;

    @OneToOne (cascade = CascadeType.ALL)
    private User tenant;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "housing")
    private List<HouseImage> images = new java.util.ArrayList<>();

    public Housing(String city, String country, Long rentDays, Long price, Long rating, User landLord, User tenant, List<HouseImage> images) {
        this.city = city;
        this.country = country;
        this.rentDays = rentDays;
        this.price = price;
        this.rating = rating;
        this.landLord = landLord;
        this.tenant = tenant;
        this.images = images;
    }

    public Housing() {
    }

    public List<String> imagesPaths(){
        List<String> imagePaths = new ArrayList<>();

        for(HouseImage houseImage: images){
            imagePaths.add(houseImage.getDecodedImgPath());
        }
        return imagePaths;
    }
}
