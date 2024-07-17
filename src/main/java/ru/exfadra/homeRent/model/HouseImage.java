package ru.exfadra.homeRent.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Base64;

@Data
@Entity
@Table(name = "house_image")
public class HouseImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private Housing housing;


    private byte[] imgPath;

    public HouseImage() {
    }

    public HouseImage(Housing housing, byte[] imgPath) {
        this.housing = housing;
        this.imgPath = imgPath;
    }


    public String getDecodedImgPath(){
        return new String(Base64.getDecoder().decode(imgPath));
    }
}
