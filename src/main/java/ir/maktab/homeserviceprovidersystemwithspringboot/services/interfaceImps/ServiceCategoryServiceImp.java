package ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaceImps;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.dao.ServiceCategoryDao;
import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.ServiceCategory;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.mapper.ServiceCategoryMapper;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.ServiceCategoryDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.exceptions.DuplicateServiceCategory;
import ir.maktab.homeserviceprovidersystemwithspringboot.exceptions.ServiceCategoryNotFound;
import ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces.ServiceCategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceCategoryServiceImp implements ServiceCategoryService {
    private final ServiceCategoryDao serviceCategoryDao;
    private final ModelMapper modelMapper;

   @Override
    public void saveServiceCategory(ServiceCategoryDto serviceCategoryDto) {
        if (serviceCategoryDao.findByName(serviceCategoryDto.getName()).isEmpty()) {
            ServiceCategory newServiceCategory = modelMapper.map(serviceCategoryDto, ServiceCategory.class);
            serviceCategoryDao.save(newServiceCategory);
        } else
            throw new DuplicateServiceCategory();
    }

    @Override
    public void updateServiceCategory(ServiceCategoryDto serviceCategoryDto) {
        Optional<ServiceCategory> oldServiceCategory = serviceCategoryDao.findByName(serviceCategoryDto.getName());
        if (oldServiceCategory.isPresent()) {
            ServiceCategory newServiceCategory = modelMapper.map(serviceCategoryDto, ServiceCategory.class);
            newServiceCategory.setId(oldServiceCategory.get().getId());
            serviceCategoryDao.save(newServiceCategory);
        } else
            throw new ServiceCategoryNotFound();
    }

    @Override
    public ServiceCategory findByName(String name) {
        Optional<ServiceCategory> serviceCategory = serviceCategoryDao.findByName(name);
        if (serviceCategory.isPresent()) {
            return serviceCategory.get();
        } else
            throw new ServiceCategoryNotFound();
    }
    @Override
    public List<ServiceCategoryDto> findAll() {
        return serviceCategoryDao.findAll()
                .stream()
                .map(serviceCategory -> ServiceCategoryMapper.toServiceCategoryDto(serviceCategory))
                .collect(Collectors.toList());
    }

}
