package dev.danvega.h2_demo.service;

import java.util.List;
import org.springframework.stereotype.Service;

import dev.danvega.h2_demo.domain.NV_CP;
import dev.danvega.h2_demo.domain.Personnel;
import dev.danvega.h2_demo.repository.PersonnelRepository;

@Service
public class PersonnelService {
    private final PersonnelRepository personnelRepository;

    public PersonnelService(PersonnelRepository personnelRepository) {
        this.personnelRepository = personnelRepository;
    }

    public Personnel saveOrUpdate(Personnel personnel) {
        return this.personnelRepository.save(personnel);
    }

    public List<Personnel> getAll() {
        return this.personnelRepository.findAll();
    }

    // Lấy Personnel theo ID
    public Personnel getById(long id) {
        return this.personnelRepository.findById(id).orElse(null);
    }

    // Xóa Personnel theo ID
    public void deleteById(long id) {
        this.personnelRepository.deleteById(id);
    }
}
