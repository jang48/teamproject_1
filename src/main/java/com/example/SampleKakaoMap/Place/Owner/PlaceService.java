package com.example.SampleKakaoMap.Place.Owner;

import com.example.SampleKakaoMap.Place.Operate.PlaceOperate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;
}
