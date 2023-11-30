package com.example.SampleKakaoMap.Place.Menu;


import com.example.SampleKakaoMap.Place.Owner.PlaceOwner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceMenuService {
    private final PlaceMenuRepository placeMenuRepository;

    public void savefile(String origFileName, String filePath, PlaceOwner placeOwner, String name, String price){
        PlaceMenu placeMenu = new PlaceMenu();
        placeMenu.setOrigFileName(origFileName);
        placeMenu.setFilePath(filePath);
        placeMenu.setMenuName(name);
        placeMenu.setMenuPrice(price);
        placeMenu.setPlaceOwner(placeOwner);

        this.placeMenuRepository.save(placeMenu);
    }

    public List<PlaceMenu> findByPlaceOwnerId(Long id){
        List<PlaceMenu> placeMenuList  =  this.placeMenuRepository.findByPlaceOwner_Id(id);
        return placeMenuList;
    }

    public void deletefile(Long id){
       PlaceMenu placeMenu = this.placeMenuRepository.findById(Math.toIntExact(id)).get();
       this.placeMenuRepository.delete(placeMenu);
    }
}
