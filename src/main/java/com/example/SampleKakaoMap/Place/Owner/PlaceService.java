package com.example.SampleKakaoMap.Place.Owner;

import com.example.SampleKakaoMap.Place.Operate.PlaceOperate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;
    public PlaceOwner savePlace(String storeName, String address, String detailAddress, String phoneNum, String category, Double latitude, Double longitude ){
        PlaceOwner placeOwner = new PlaceOwner();
        placeOwner.setStore(storeName);
        placeOwner.setStoreAddress(address + '/' + detailAddress);
        placeOwner.setCallNum(phoneNum);
        placeOwner.setStoreCategory(category);
        placeOwner.setLatitude(latitude);
        placeOwner.setLongitude(longitude);
        return this.placeRepository.save(placeOwner);
    }
    public PlaceOwner findById(Long id){
       return this.placeRepository.findById(id).get();
    }

    public void saveSubInfo(String webSite, String storeMemo, Long id){
        PlaceOwner placeOwner = this.placeRepository.findById(id).get();

        placeOwner.setLink(webSite);
        placeOwner.setStoreMemo(storeMemo);

        this.placeRepository.save(placeOwner);
    }


}
