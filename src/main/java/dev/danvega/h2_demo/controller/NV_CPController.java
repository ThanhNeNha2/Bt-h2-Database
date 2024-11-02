package dev.danvega.h2_demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import dev.danvega.h2_demo.domain.Company;
import dev.danvega.h2_demo.domain.NV_CP;
import dev.danvega.h2_demo.domain.Personnel;
import dev.danvega.h2_demo.service.CompanyService;
import dev.danvega.h2_demo.service.NV_CPService;
import dev.danvega.h2_demo.service.PersonnelService;

@Controller
public class NV_CPController {

    private final NV_CPService nv_cpService;
    private final CompanyService companyService;
    private final PersonnelService personnelService;

    public NV_CPController(NV_CPService nv_cpService, CompanyService companyService,
            PersonnelService personnelService) {
        this.nv_cpService = nv_cpService;
        this.companyService = companyService;
        this.personnelService = personnelService;
    }

    // GET
    @GetMapping("/NV_CP")
    public String getAllNV_CP(Model model) {
        List<NV_CP> nvcpList = nv_cpService.findAll();
        System.out.println("nvcpList" + nvcpList);
        model.addAttribute("nvcpList", nvcpList);
        return "NV_CP_Table"; // Trang hiển thị danh sách NV_CP
    }

    // CREATE

    @GetMapping("/addNV_CP")
    public String addNvCp(Model model) {
        model.addAttribute("nvcp", new NV_CP());
        model.addAttribute("companies", companyService.getAll()); // Truyền danh sách công ty cho dropdown
        return "NV_CP_Create"; // Tên trang Thymeleaf cho form thêm NV_CP
    }

    @PostMapping("/addNV_CP")
    public String saveNV_CP(@ModelAttribute("nvcp") NV_CP nv_cp) {
        nv_cpService.save(nv_cp); // Lưu NV_CP mới

        return "redirect:/NV_CP"; // Chuyển hướng về trang danh sách NV_CP sau khi lưu
    }

    // UPDATE
    @RequestMapping("/nvcp/update/{id}")
    public String getUpdateNvcpPage(Model model, @PathVariable long id) {
        NV_CP nvcp = this.nv_cpService.findById(id); // Lấy thông tin NV_CP theo id
        model.addAttribute("nvcp", nvcp);
        model.addAttribute("companies", companyService.getAll()); // Truyền danh sách công ty để chọn
        return "NV_CP_Update"; // Trả về trang cập nhật
    }

    @PostMapping("/nvcp/update")
    public String postUpdateNvcp(Model model, @ModelAttribute("nvcp") NV_CP nvcp) {
        NV_CP currentNvcp = this.nv_cpService.findById(nvcp.getId()); // Lấy NV_CP hiện tại
        if (currentNvcp != null) {
            // Kiểm tra và lưu Personnel nếu chưa lưu
            if (nvcp.getPersonnel() != null && nvcp.getPersonnel().getId() == 0) {
                // Lưu Personnel nếu nó chưa có id (chưa tồn tại trong DB)
                this.personnelService.saveOrUpdate(nvcp.getPersonnel());
            }

            // Cập nhật thông tin Personnel và Company
            currentNvcp.setPersonnel(nvcp.getPersonnel()); // Cập nhật Personnel
            currentNvcp.setCompany(nvcp.getCompany()); // Cập nhật Company
            currentNvcp.setRole(nvcp.getRole()); // Cập nhật vai trò
            currentNvcp.setStatus(nvcp.getStatus()); // Cập nhật trạng thái

            // Lưu NV_CP sau khi đã lưu Personnel và Company
            this.nv_cpService.save(currentNvcp); // Lưu NV_CP
        }

        // In thông tin vừa nhập ra console (log)
        System.out.println("Cập nhật NV_CP thành công:");
        System.out.println("ID NV_CP: " + currentNvcp.getId());
        System.out.println("Tên Nhân Viên: " + currentNvcp.getPersonnel().getName());
        System.out.println("Công Ty: " + currentNvcp.getCompany().getCompanyName());
        System.out.println("Vị trí: " + currentNvcp.getRole());
        System.out.println("Trạng thái: " + currentNvcp.getStatus());

        // Thêm thông tin vào model để hiển thị cho người dùng
        model.addAttribute("nvcpInfo", currentNvcp);

        // Sau khi cập nhật chuyển hướng về trang hiển thị thông tin đã cập nhật
        return "redirect:/NV_CP";
    }

    // DELETE

    @RequestMapping("/nvcp/delete/{id}")
    public String getDeleteNvcpPage(Model model, @PathVariable long id) {
        NV_CP nvcp = this.nv_cpService.findById(id);
        if (nvcp == null) {
            // Xử lý trường hợp không tìm thấy NV_CP
            System.out.println("Không tìm thấy NV_CP với ID: " + id);
            return "redirect:/NV_CP"; // Điều hướng về trang danh sách nếu không tìm thấy
        }
        System.out.println("check dau vao " + nvcp);
        model.addAttribute("deleteNvcp", nvcp);
        return "NV_CP_Delete"; // Trang xác nhận xóa NV_CP
    }

    // Xóa NV_CP
    @PostMapping("/nvcp/delete")
    public String postDeleteNvcp(Model model, @ModelAttribute("deleteNvcp") NV_CP nvcp) {
        System.out.println("Xóa NV_CP với ID: " + nvcp.getId());
        System.out.println("Xóa NV_CP với ID: " + nvcp);

        this.nv_cpService.deleteById(nvcp.getId());
        return "redirect:/NV_CP"; // Sau khi xóa chuyển hướng về trang danh sách NV_CP
    }
}