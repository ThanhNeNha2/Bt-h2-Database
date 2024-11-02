package dev.danvega.h2_demo.controller;

import org.springframework.web.bind.annotation.*;

import dev.danvega.h2_demo.domain.Personnel;
import dev.danvega.h2_demo.service.CompanyService;
import dev.danvega.h2_demo.service.PersonnelService;

import java.util.List;

@RestController
@RequestMapping("/api/personnel")
public class RestPersonnelController {

    private final PersonnelService personnelService;
    private final CompanyService companyService;

    public RestPersonnelController(PersonnelService personnelService, CompanyService companyService) {
        this.personnelService = personnelService;
        this.companyService = companyService;
    }

    // Lấy tất cả nhân viên
    @GetMapping
    public List<Personnel> getAllPersonnel() {
        return personnelService.getAll(); // Trả về danh sách nhân viên
    }

    // Thêm nhân viên mới
    @PostMapping(consumes = "application/json")
    public Personnel addPersonnel(@RequestBody Personnel personnel) {
        return personnelService.saveOrUpdate(personnel); // Lưu nhân viên và trả về thông tin nhân viên đã lưu
    }

    // Cập nhật thông tin nhân viên
    @PutMapping("/{id}")
    public Personnel updatePersonnel(@PathVariable long id, @RequestBody Personnel personnel) {
        Personnel currentPersonnel = personnelService.getById(id); // Lấy nhân viên hiện tại
        if (currentPersonnel != null) {
            currentPersonnel.setName(personnel.getName());
            currentPersonnel.setEmail(personnel.getEmail());
            currentPersonnel.setCountry(personnel.getCountry());
            currentPersonnel.setPosition(personnel.getPosition());
            return personnelService.saveOrUpdate(currentPersonnel); // Lưu các thay đổi và trả về thông tin đã cập nhật
        }
        return null; // Trả về null nếu không tìm thấy nhân viên
    }

    // Xóa nhân viên
    @DeleteMapping("/{id}")
    public void deletePersonnel(@PathVariable long id) {
        personnelService.deleteById(id); // Xóa nhân viên theo ID
    }
}
