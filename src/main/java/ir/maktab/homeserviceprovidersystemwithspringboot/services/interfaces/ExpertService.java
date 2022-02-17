package ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.roles.Expert;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.SubServiceDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.roles.ExpertDto;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;

public interface ExpertService {
    ExpertDto saveExpert(ExpertDto expertDto, MultipartFile profilePhoto) throws IOException;

    ExpertDto updateExpert(ExpertDto expertDto);

    Expert updateExpert(Expert expert);

    ExpertDto loginExpert(ExpertDto expertDto);

    Expert findExpertByEmail(String email);

    ExpertDto findExpertDtoByEmail(String email);

    long findExpertId(String email);

    void changePasswordForExpert(ExpertDto expertDto, String currentPassword, String newPassword);

    void addSubServiceToExpertSubServices(String expertEmail , String SubServiceName);

    void deleteServiceFromExpertServices(ExpertDto expertDto, SubServiceDto subServiceDto);


}
