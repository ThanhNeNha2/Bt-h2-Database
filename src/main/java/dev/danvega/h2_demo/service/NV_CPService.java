package dev.danvega.h2_demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.danvega.h2_demo.domain.NV_CP;
import dev.danvega.h2_demo.repository.NV_CPRepository;

@Service
public class NV_CPService {

    private final NV_CPRepository nv_cpRepository;

    public NV_CPService(NV_CPRepository nv_cpRepository) {
        this.nv_cpRepository = nv_cpRepository;
    }

    public List<NV_CP> findAll() {
        return nv_cpRepository.findAll();
    }

    public NV_CP findById(Long id) {
        return nv_cpRepository.findById(id).orElse(null);
    }

    public NV_CP save(NV_CP nv_cp) {
        return nv_cpRepository.save(nv_cp);
    }

    public void deleteById(Long id) {
        nv_cpRepository.deleteById(id);
    }
}