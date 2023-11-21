package com.example.SampleKakaoMap.Place.Owner;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PlaceOperate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String day;         // 요일

    private String openTime;    // 오픈시간

    private String closeTime;    // 마감시간

    @ManyToOne
    private PlaceOwner placeOwner;
}
