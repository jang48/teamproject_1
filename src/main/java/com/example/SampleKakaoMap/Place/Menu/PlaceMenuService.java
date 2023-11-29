package com.example.SampleKakaoMap.Place.Menu;


import com.example.SampleKakaoMap.Place.Owner.PlaceOwner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PlaceMenuService {
    private final PlaceMenuRepository placeMenuRepository;

    public void savefile(String origFileName, String filePath, PlaceOwner placeOwner, String name, Long price){
        PlaceMenu placeMenu = new PlaceMenu();
        placeMenu.setOrigFileName(origFileName);
        placeMenu.setFilePath(filePath);
        placeMenu.setMenuName(name);
        placeMenu.setMenuPrice(price);
        placeMenu.setPlaceOwner(placeOwner);

        this.placeMenuRepository.save(placeMenu);
    }

}
