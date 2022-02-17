package ir.maktab.homeserviceprovidersystemwithspringboot.dto.mapper;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.ServiceCategory;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.ServiceCategoryDto;

public class ServiceCategoryMapper {
    public static ServiceCategory toServiceCategory(ServiceCategoryDto serviceCategoryDto) {
        return ServiceCategory.builder()
                .name(serviceCategoryDto.getName())
                .build();
    }
    public static ServiceCategoryDto toServiceCategoryDto(ServiceCategory serviceCategory) {
        ServiceCategoryDto serviceCategoryDto = new ServiceCategoryDto();
        serviceCategoryDto.setName(serviceCategory.getName());
        return serviceCategoryDto;
    }
}
