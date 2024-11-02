package dev.danvega.h2_demo.controller;

import dev.danvega.h2_demo.domain.NV_CP;
import dev.danvega.h2_demo.service.CompanyService;
import dev.danvega.h2_demo.service.NV_CPService;
import dev.danvega.h2_demo.service.PersonnelService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/nvcp")
public class RestNV_CPController {

    private final NV_CPService nv_cpService;
    private final CompanyService companyService;
    private final PersonnelService personnelService;

    public RestNV_CPController(NV_CPService nv_cpService, CompanyService companyService,
            PersonnelService personnelService) {
        this.nv_cpService = nv_cpService;
        this.companyService = companyService;
        this.personnelService = personnelService;
    }

    // GET ALL
    @GetMapping
    public List<NV_CP> getAllNV_CP() {
        return nv_cpService.findAll();
    }

    // CREATE
    @PostMapping(consumes = "application/json")
    public NV_CP createNV_CP(@RequestBody NV_CP nv_cp) {
        if (nv_cp.getPersonnel() != null && nv_cp.getPersonnel().getId() == 0) {
            personnelService.saveOrUpdate(nv_cp.getPersonnel());
        }
        return nv_cpService.save(nv_cp);
    }

    // UPDATE
    @PutMapping("/{id}")
    public NV_CP updateNV_CP(@PathVariable long id, @RequestBody NV_CP nv_cp) {
        NV_CP currentNvcp = nv_cpService.findById(id);
        if (currentNvcp != null) {
            if (nv_cp.getPersonnel() != null && nv_cp.getPersonnel().getId() == 0) {
                personnelService.saveOrUpdate(nv_cp.getPersonnel());
            }
            currentNvcp.setPersonnel(nv_cp.getPersonnel());
            currentNvcp.setCompany(nv_cp.getCompany());
            currentNvcp.setRole(nv_cp.getRole());
            currentNvcp.setStatus(nv_cp.getStatus());
            return nv_cpService.save(currentNvcp);
        }
        return null; // Xử lý trường hợp không tìm thấy NV_CP
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteNV_CP(@PathVariable long id) {
        nv_cpService.deleteById(id);
    }
}
