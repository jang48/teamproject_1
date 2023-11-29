package com.example.SampleKakaoMap.Place.Operate;

import com.example.SampleKakaoMap.Place.Owner.PlaceOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceOperateRepository extends JpaRepository<PlaceOperate, Integer> {
    List<PlaceOperate> findByPlaceOwner_Id(Long PlaceOwnerId);
    List<PlaceOperate>  findByPlaceOwner(PlaceOwner placeOwner);
}
