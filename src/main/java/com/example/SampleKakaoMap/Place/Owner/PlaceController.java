package com.example.SampleKakaoMap.Place.Owner;

import com.example.SampleKakaoMap.Place.Customer.PlaceCustRepository;
import com.example.SampleKakaoMap.Place.Customer.PlaceCustService;
import com.example.SampleKakaoMap.Place.Operate.OperateDto;
import com.example.SampleKakaoMap.Place.Operate.PlaceOperate;
import com.example.SampleKakaoMap.Place.Operate.PlaceOperateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/place")
@Controller
public class PlaceController {

    private final PlaceOperateService placeOperateService;
    private final OperateDto operateDto2;

    @GetMapping("/map/regist")
    public String regist(Model model) {
        List<PlaceOperate> placeOperateList = this.placeOperateService.getAllOperateList(null);
        model.addAttribute("placeOperateList", placeOperateList);
        return "MapRegist";
    }
    @GetMapping("/map/regist/owner")
    public String regist2() {
        return "MapRegistOpentime";
    }

    @PostMapping("/map/regist/owner/time")
    public String operatetime(@ModelAttribute OperateDto operateDto){
        List<OperateDto> operateDtoList = operateDto.getOperateDtoList();
        this.placeOperateService.saveoperate(operateDtoList);

        return "redirect:/place/map/regist";
    }
}
