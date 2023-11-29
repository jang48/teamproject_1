package com.example.SampleKakaoMap.Place.Owner;

import com.example.SampleKakaoMap.Place.Menu.PlaceMenu;
import com.example.SampleKakaoMap.Place.Menu.PlaceMenuService;
import com.example.SampleKakaoMap.Place.Operate.OperateDto;
import com.example.SampleKakaoMap.Place.Operate.PlaceOperate;
import com.example.SampleKakaoMap.Place.Operate.PlaceOperateService;
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
import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/place/map")
@Controller
public class PlaceController {

    private final PlaceOperateService placeOperateService;
    private final OperateDto operateDto2;
    private final PlaceMenuService placeMenuService;
    private final PlaceService placeService;

    @GetMapping("/regist")
    public String regist(Model model) {
        List<PlaceOperate> placeOperateList = this.placeOperateService.getAllOperateList(null);
        model.addAttribute("placeOperateList", placeOperateList);
        model.addAttribute("placeOwner", null);
        return "MapRegist";
    }
    @GetMapping("/regist/owner")
    public String regist2() {
        return "MapRegistOpentime";
    }

    @PostMapping("/regist/owner/time")
    public String saveOperateTime(@ModelAttribute OperateDto operateDto,@RequestParam Long placeOwnerId){
        List<OperateDto> operateDtoList = operateDto.getOperateDtoList();
        this.placeOperateService.saveOperate(operateDtoList, placeOwnerId);
        return "redirect:/place/map/regist/info/" + placeOwnerId;
    }

    @PostMapping("/regist/owner/time/update")
    public String updateOperateTime(@ModelAttribute OperateDto operateDto,@RequestParam Long placeOwnerId){
        List<OperateDto> operateDtoList = operateDto.getOperateDtoList();
        this.placeOperateService.updateOperate(operateDtoList,placeOwnerId);
        return "redirect:/place/map/regist/info/" + placeOwnerId;
    }

    @RequestMapping("/regist/action")
    public String uploadTest(MultipartHttpServletRequest mre,@RequestParam Long placeOwnerId, @RequestParam String name, @RequestParam Long price) throws IOException {
        MultipartFile mf = mre.getFile("file");
        String uploadPath = "";
        PlaceOwner owner = this.placeService.findById(placeOwnerId);

        String path = "C:\\"+"files\\"+"menu\\";
        File Folder = new File(path);

        if(!Folder.exists()) {
            try {
                Folder.mkdir();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }

        Path directoryPath = Paths.get(path);
        Files.createDirectories(directoryPath);

        String origional = mf.getOriginalFilename();

        uploadPath = path+origional;

        try{
            mf.transferTo(new File(uploadPath)); // 파일 저장
            this.placeMenuService.savefile(origional,uploadPath,owner,name,price);
        } catch (IllegalStateException | IOException e){
            e.printStackTrace();;
        }
        return "redirect:/place/map/regist";
    }
    @PostMapping("/regist/info/save")
    public String infoSave(Model model, @RequestParam String storeName, @RequestParam String address, @RequestParam String detailAddress, @RequestParam String phoneNum, @RequestParam String category,@RequestParam Double latitude, @RequestParam Double longitude ){
        PlaceOwner placeOwner = this.placeService.savePlace(storeName,address,detailAddress,phoneNum,category,latitude,longitude);
        model.addAttribute("placeOwner", placeOwner);

        List<PlaceOperate> placeOperateList = this.placeOperateService.getAllOperateList(placeOwner.getId());
        model.addAttribute("placeOperateList", placeOperateList);

        return "MapRegist";
    }

    @GetMapping("/regist/info/{id}")
    public String infosave2(Model model, @PathVariable Long id ){
        PlaceOwner placeOwner = this.placeService.findById(id);
        PlaceOwnerDto placeOwnerDto = placeOwner.convertDto();
        model.addAttribute("placeOwner", placeOwnerDto);


        Long ownerId = placeOwner.getId();
        List<OperateDto> operateDtoList = placeOperateService.getAllOperateDtoList(ownerId);
        model.addAttribute("placeOperateList", operateDtoList);


//        List<PlaceOperate> placeOperateList = this.placeOperateService.getAllOperateList(Math.toIntExact(placeOwner.getId()));
//        model.addAttribute("placeOperateList", placeOperateList);

        return "MapRegist";
    }
}

