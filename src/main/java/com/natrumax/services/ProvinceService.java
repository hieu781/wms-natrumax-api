package com.natrumax.services;

import com.natrumax.dto.request.CreateProvinceRequest;
import com.natrumax.dto.request.UpdateProvinceRequest;
import com.natrumax.models.Province;
import com.natrumax.repository.ProvinceRepository;
import com.natrumax.services.interfaces.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProvinceService implements IProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;

    public List<Province> getAllProvinces() {
        return provinceRepository.findAll();
    }

    public Optional<Province> getProvinceById(Long id) {
        return provinceRepository.findById(id);
    }

    public Province createProvince(CreateProvinceRequest request) {
        Province province = new Province();
        province.setName(request.getName());
        province.setProvinceCode(request.getProvinceCode());
        province.setRegion(request.getProvinceSide());
        province.setCreateDate(LocalDate.now());

        return provinceRepository.save(province);
    }

    public Optional<Province> updateProvince(Long id, UpdateProvinceRequest request) {
        return provinceRepository.findById(id).map(province -> {
            province.setName(request.getName());
            province.setProvinceCode(request.getProvinceCode());
            province.setRegion(request.getProvinceSide());
            province.setModifyDate(LocalDate.now());
            return provinceRepository.save(province);
        });
    }
}
