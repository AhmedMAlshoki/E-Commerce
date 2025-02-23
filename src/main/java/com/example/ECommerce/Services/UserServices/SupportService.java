package com.example.ECommerce.Services.UserServices;

import com.example.ECommerce.DTOs.RoleBasedDTO.SupportDTO;
import com.example.ECommerce.DTOs.RoleBasedDTO.UserDTO;
import com.example.ECommerce.DTOs.UserRegisterationDTO;
import com.example.ECommerce.Entities.SubEntities.Support;
import com.example.ECommerce.Entities.User;
import com.example.ECommerce.Enums.Roles;
import com.example.ECommerce.Mappers.ProfileMapper;
import com.example.ECommerce.Mappers.SupportMapper;
import com.example.ECommerce.Mappers.UserMapper;
import com.example.ECommerce.Repositories.RoleBasedRepositories.SupportRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupportService {
    private final SupportRepository supportRepository;
    private final UserMapper userMapper;
    private final SupportMapper supportMapper;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public SupportService(SupportRepository supportRepository , UserMapper userMapper,
                          SupportMapper supportMapper, PasswordEncoder passwordEncoder) {
        this.supportRepository = supportRepository;
        this.userMapper = userMapper;
        this.supportMapper = supportMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public SupportDTO registerSupport(UserRegisterationDTO userRegisterationDTO) {
        Support support = userMapper.userRegisterationDTOToSupport(userRegisterationDTO);
        support.setRole(Roles.SUPPORT);
        support.setPassword(passwordEncoder.encode(userRegisterationDTO.password()));
        supportRepository.save(support);
        //support mapper support to supportDTO
        return supportMapper.supportToSupportDTO(support);
    }

    public UserDTO getSupport(Long id) {
        return supportMapper.supportToSupportDTO(supportRepository.findById(id).orElseThrow());
    }

    public void deleteSupport(Long id) {
        supportRepository.deleteById(id);
    }

    public List<SupportDTO> getAllSupports() {
        List<Support> supports = supportRepository.findAll();
        return supports.stream().map(supportMapper::supportToSupportDTO).collect(Collectors.toList());
    }

    public Support getSupportByIdToPromote(Long id) {
        return supportRepository.findById(id).orElseThrow();
    }

    public SupportDTO updateSupport(Long id, JsonNode jsonNode) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper() .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);;
        Support existingSupport = supportRepository.findById(id).orElseThrow();
        existingSupport = objectMapper.readerForUpdating(existingSupport).readValue(jsonNode);
        supportRepository.save(existingSupport);
        //To make sure the updated Address will be returned within the entity
        Support theUpdatedSupport = supportRepository.findById(id).orElseThrow();
        return supportMapper.supportToSupportDTO(theUpdatedSupport);
    }


    /*
    * Get Support Profile
    * SupportProfileDTO getTheProfile(Long id){
    *     Support support = supportRepository.findById(id).orElseThrow();
    *     return supportMapper.supportToSupportProfileDTO(support);
    * }
    * */


}
