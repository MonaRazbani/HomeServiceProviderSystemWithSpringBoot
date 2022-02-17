package ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaceImps;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.dao.SubServiceDao;
import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.ServiceCategory;
import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.SubService;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.mapper.SubServiceMapper;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.SubServiceDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.roles.ExpertDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.exceptions.DuplicateSubService;
import ir.maktab.homeserviceprovidersystemwithspringboot.exceptions.NoCategoryServiceForService;
import ir.maktab.homeserviceprovidersystemwithspringboot.exceptions.SubServiceNotFound;
import ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces.ServiceCategoryService;
import ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces.SubServiceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class SubServiceServiceImp implements SubServiceService {
    private final SubServiceDao subServiceDao;
    private final ModelMapper modelMapper;
    private final ServiceCategoryService serviceCategoryService;

    @Override
    public void saveSubService(SubServiceDto subServiceDto) {
        SubService newSubService = SubServiceMapper.toSubService(subServiceDto);
        ServiceCategory serviceCategory = serviceCategoryService.findByName(newSubService.getServiceCategory().getName());
        newSubService.setServiceCategory(serviceCategory);
        Optional<SubService> found = subServiceDao.findByName(newSubService.getName());
        if (!found.isEmpty()) {
            throw new DuplicateSubService();
        } else
            subServiceDao.save(newSubService);
    }

    @Override
    public void updateSubService(SubServiceDto subServiceDto) {
        SubService subService = modelMapper.map(subServiceDto, SubService.class);
        if (subServiceDao.findByName(subService.getName()).isPresent()) {
            if (subService.getServiceCategory() == null)
                throw new NoCategoryServiceForService();
            subServiceDao.save(subService);
        } else
            throw new SubServiceNotFound();
    }

    @Override
    public SubService findByName(String name) {
        Optional<SubService> subService = subServiceDao.findByName(name);
        if (subService.isPresent()) {
            return subService.get();
        } else
            throw new SubServiceNotFound();
    }

    @Override
    public List<SubServiceDto> findAll() {
        return subServiceDao.findAll()
                .stream()
                .map(SubServiceMapper::toSubServiceDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SubServiceDto> findSubServicesOfServiceCategory(String serviceCategoryName) {
        ServiceCategory serviceCategory = serviceCategoryService.findByName(serviceCategoryName);

        return subServiceDao.findByServiceCategory(serviceCategory).
                stream().
                map(SubServiceMapper::toSubServiceDto).
                collect(Collectors.toList());
    }

    @Override
    public List<SubService> findSubServiceByExpert(String expertEmail) {
        return subServiceDao.findByExpertList_Email(expertEmail);
    }
}
