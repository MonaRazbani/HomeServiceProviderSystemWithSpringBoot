package ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.ServiceCategory;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.ServiceCategoryDto;

import java.util.List;

public interface ServiceCategoryService {

    void saveServiceCategory(ServiceCategoryDto serviceCategoryDto);

    void updateServiceCategory(ServiceCategoryDto serviceCategoryDto);

    ServiceCategory findByName(String name);

    List<ServiceCategoryDto> findAll();

}
