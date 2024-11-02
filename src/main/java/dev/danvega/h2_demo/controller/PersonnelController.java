package dev.danvega.h2_demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

import dev.danvega.h2_demo.domain.Personnel;
import dev.danvega.h2_demo.service.CompanyService;
import dev.danvega.h2_demo.service.PersonnelService;

@Controller
public class PersonnelController {
    private final PersonnelService personnelService;
    private final CompanyService companyService;

    public PersonnelController(PersonnelService personnelService, CompanyService companyService) {
        this.personnelService = personnelService;
        this.companyService = companyService;
    }

    // GET ALL ( TABLE )
    @GetMapping("/personnelTable")
    public String getAllPersonnel(Model model) {
        List<Personnel> personnelList = personnelService.getAll();
        model.addAttribute("personnelList", personnelList);
        return "PersonnelTable"; // Trang hiển thị danh sách Personnel
    }

    // CREATE

    @GetMapping("/addPersonnel")
    public String addPersonnel(Model model) {
        model.addAttribute("personnel", new Personnel());
        model.addAttribute("companies", companyService.getAll()); // Truyền danh sách công ty cho dropdown
        System.out.println(model);
        return "PersonnelCreate"; // Tên trang Thymeleaf cho form thêm Personnel
    }

    @PostMapping("/addPersonnel")
    public String savePersonnel(@ModelAttribute("personnel") Personnel personnel) {
        // Lưu Personnel mới
        personnelService.saveOrUpdate(personnel);
        System.out.println("check nhap vao " + personnel);
        return "redirect:/personnelTable"; // Chuyển hướng về trang danh sách Personnel sau khi lưu
    }

    // UPDATE
    @RequestMapping("/personnel/update/{id}")
    public String getUpdatePersonnelPage(Model model, @PathVariable long id) {
        Personnel personnel = this.personnelService.getById(id); // Lấy thông tin Personnel theo ID
        model.addAttribute("updatePersonnel", personnel); // Truyền thông tin Personnel vào model
        return "PersonnelUpdate"; // Tên trang Thymeleaf cho form cập nhật Personnel
    }

    @PostMapping("/personnel/update")
    public String postUpdatePersonnel(Model model, @ModelAttribute("updatePersonnel") Personnel personnel) {
        Personnel currentPersonnel = this.personnelService.getById(personnel.getId()); // Lấy Personnel hiện tại theo ID
        if (currentPersonnel != null) {
            currentPersonnel.setName(personnel.getName()); // Cập nhật tên
            currentPersonnel.setEmail(personnel.getEmail()); // Cập nhật email
            currentPersonnel.setCountry(personnel.getCountry()); // Cập nhật quốc gia
            currentPersonnel.setPosition(personnel.getPosition()); // Cập nhật chức vụ

            this.personnelService.saveOrUpdate(currentPersonnel); // Lưu các thay đổi
        }
        return "redirect:/personnelTable"; // Chuyển hướng về trang danh sách Personnel sau khi cập nhật
    }

    // DELETE

    @RequestMapping("/personnel/delete/{id}")
    public String getDeletePersonnelPage(Model model, @PathVariable long id) {
        Personnel personnel = this.personnelService.getById(id); // Lấy thông tin Personnel theo ID
        model.addAttribute("deletePersonnel", personnel); // Truyền thông tin Personnel vào model để hiển thị trên trang
                                                          // xác nhận
        return "PersonnelDelete"; // Trả về trang xác nhận xóa
    }

    // Xử lý POST request để xóa Personnel
    @PostMapping("/personnel/delete")
    public String postDeletePersonnel(Model model, @ModelAttribute("deletePersonnel") Personnel personnel) {
        System.out.println("Xóa nhân viên với ID: " + personnel.getId()); // In ra ID của nhân viên cần xóa (chỉ để kiểm
                                                                          // tra)
        this.personnelService.deleteById(personnel.getId()); // Thực hiện hành động xóa trong service
        return "redirect:/personnelTable"; // Chuyển hướng về trang danh sách nhân viên sau khi xóa thành công
    }
}
