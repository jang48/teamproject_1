package com.example.SampleKakaoMap.Place.Owner;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Setter
@Getter
public class PlaceOwnerTagDto {

    @Autowired
    private PlaceOwner placeOwner;


}
