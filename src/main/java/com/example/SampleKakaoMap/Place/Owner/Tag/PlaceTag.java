package com.example.SampleKakaoMap.Place.Owner.Tag;

import com.example.SampleKakaoMap.Place.Owner.PlaceOwner;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PlaceTag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String tag;

    @ManyToOne
    private PlaceOwner placeOwner;
}
