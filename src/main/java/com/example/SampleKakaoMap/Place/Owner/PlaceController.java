package com.example.SampleKakaoMap.Place.Owner;

import com.example.SampleKakaoMap.Place.Customer.PlaceCustRepository;
import com.example.SampleKakaoMap.Place.Customer.PlaceCustService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequiredArgsConstructor
@RequestMapping("/place")
@Controller
public class PlaceController {


    @GetMapping("/map/regist/owner")
    public String regist() {
        return "MapRegistOpentime";
    }

}
