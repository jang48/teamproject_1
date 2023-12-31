package com.example.SampleKakaoMap.Place.Menu;

import com.example.SampleKakaoMap.Place.Operate.OperateDto;
import com.example.SampleKakaoMap.Place.Operate.PlaceOperateService;
import com.example.SampleKakaoMap.Place.Owner.PlaceOwner;
import com.example.SampleKakaoMap.Place.Owner.PlaceOwnerDto;
import com.example.SampleKakaoMap.Place.Owner.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/place/map")
@Controller
public class PlaceMenuController {

    private final PlaceMenuService placeMenuService;
    private final PlaceService placeService;
    private final PlaceOperateService placeOperateService;
    @RequestMapping("/regist/menu")
    public String uploadMenu(MultipartHttpServletRequest mre, Model model, @RequestParam Long placeOwnerId, @RequestParam String name, @RequestParam String price) throws IOException {

        MultipartFile mf = mre.getFile("file");
        String uploadPath = "";
        PlaceOwner owner = this.placeService.findById(placeOwnerId);

        String path = "C:\\"+"place\\"+"menu\\";

        File Folder = new File(path);
        if (!Folder.exists()) {
            Folder.mkdirs();
        }

        Path directoryPath = Paths.get(path);
        Files.createDirectories(directoryPath);

        String origional = mf.getOriginalFilename();

        String timestamp = String.valueOf(System.currentTimeMillis());
        String uniqueFileName = timestamp + "_" + origional;

        uploadPath = path+uniqueFileName;

        try{
            mf.transferTo(new File(uploadPath)); // 파일 저장
            this.placeMenuService.savefile(uniqueFileName,uploadPath,owner,name,price);
        } catch (IllegalStateException | IOException e){
            e.printStackTrace();;
        }
        model.addAttribute("uploadPath",uploadPath);
        return "redirect:/place/map/regist/menu/detail/" + placeOwnerId;
    }

    @RequestMapping("/regist/menu/detail/{id}")
    public String detailMenu(Model model, @PathVariable Long id) {
        List<PlaceMenu> placeMenuList  =  this.placeMenuService.findByPlaceOwnerId(id);
        model.addAttribute("menus", placeMenuList);

        PlaceOwner placeOwner = this.placeService.findById(id);
        PlaceOwnerDto placeOwnerDto = placeOwner.convertDto();
        model.addAttribute("placeOwner", placeOwnerDto);

        Long ownerId = placeOwner.getId();
        List<OperateDto> operateDtoList = placeOperateService.getAllOperateDtoList(ownerId);
        model.addAttribute("placeOperateList", operateDtoList);

        return "MapRegist";
    }

    @RequestMapping("/regist/menu/delete")
    public String deleteMenu(Model model, @RequestParam Long fileId, @RequestParam Long ownerId) {

        String path = "C:\\"+"place\\"+"menu\\";
        String originFileName = this.placeMenuService.findFile(fileId);

        File file = new File(path+originFileName);
        System.out.println(file);
        file.delete();

        this.placeMenuService.deleteFile(fileId);

        PlaceOwner placeOwner = this.placeService.findById(ownerId);
        PlaceOwnerDto placeOwnerDto = placeOwner.convertDto();
        model.addAttribute("placeOwner", placeOwnerDto);

        Long OwnerId = placeOwner.getId();
        List<OperateDto> operateDtoList = placeOperateService.getAllOperateDtoList(OwnerId);
        model.addAttribute("placeOperateList", operateDtoList);

        return "redirect:/place/map/regist/menu/detail/" + ownerId;
    }

}
