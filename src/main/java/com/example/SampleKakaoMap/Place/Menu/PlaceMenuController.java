package com.example.SampleKakaoMap.Place.Menu;

import com.example.SampleKakaoMap.Place.Operate.OperateDto;
import com.example.SampleKakaoMap.Place.Operate.PlaceOperateService;
import com.example.SampleKakaoMap.Place.Owner.PlaceOwner;
import com.example.SampleKakaoMap.Place.Owner.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @RequestMapping("/regist/menu")
    public String uploadTest(MultipartHttpServletRequest mre, Model model, @RequestParam Long placeOwnerId, @RequestParam String name, @RequestParam String price) throws IOException {
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
        model.addAttribute("uploadPath",uploadPath);
        return "redirect:/place/map/regist/info/" + placeOwnerId;
    }

}
