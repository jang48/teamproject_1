package com.example.SampleKakaoMap.Place.Owner;

import com.example.SampleKakaoMap.Place.Menu.PlaceMenu;
import com.example.SampleKakaoMap.Place.Menu.PlaceMenuService;
import com.example.SampleKakaoMap.Place.Operate.OperateDto;
import com.example.SampleKakaoMap.Place.Operate.PlaceOperate;
import com.example.SampleKakaoMap.Place.Operate.PlaceOperateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.function.IOConsumer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@RequestMapping("/place/map")
@Controller
public class PlaceController {

    private final PlaceOperateService placeOperateService;
    private final PlaceService placeService;

    @GetMapping("/regist")
    public String regist(Model model) {
        List<PlaceOperate> placeOperateList = this.placeOperateService.getAllOperateList(null);
        model.addAttribute("placeOperateList", placeOperateList);
        model.addAttribute("placeOwner", null);
        return "MapRegist";
    }
    @PostMapping("/regist/info/save")
    public String infoSave(Model model, @RequestParam String storeName, @RequestParam String address, @RequestParam String detailAddress, @RequestParam String phoneNum, @RequestParam String category,@RequestParam Double latitude, @RequestParam Double longitude ){
        PlaceOwner placeOwner = this.placeService.savePlace(storeName,address,detailAddress,phoneNum,category,latitude,longitude);
        model.addAttribute("placeOwner", placeOwner);

        List<PlaceOperate> placeOperateList = this.placeOperateService.getAllOperateList(placeOwner.getId());
        model.addAttribute("placeOperateList", placeOperateList);

        return "redirect:/place/map/regist/info/" + placeOwner.getId();
    }

    @PostMapping("/regist/info/tag")
    @ResponseBody   // json형태를 java객체로 쓰겠다는 의미
    public String saveTag(@RequestBody Map<String, Object> requestMap){

        System.out.println(requestMap);
        List<String> tagList = (List<String>) requestMap.get("tagsArray");
        String placeOwnerId = (String) requestMap.get("placeOwnerId");

        this.placeService.addTag(tagList.get(0),(Long.valueOf(placeOwnerId)));
        return "{}";
    }
}

