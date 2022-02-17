package ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.SubService;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.SubServiceDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.roles.ExpertDto;

import java.util.List;

public interface SubServiceService {

    void saveSubService(SubServiceDto subServiceDto) ;

    void updateSubService(SubServiceDto subServiceDto);

    SubService findByName(String name);

    List<SubServiceDto> findAll();

    List<SubServiceDto> findSubServicesOfServiceCategory(String serviceCategoryName);

    List<SubService> findSubServiceByExpert (String expertEmail);

}
