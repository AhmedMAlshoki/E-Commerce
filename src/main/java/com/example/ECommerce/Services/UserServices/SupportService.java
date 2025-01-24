package com.example.ECommerce.Services.UserServices;

import com.example.ECommerce.DTOs.RoleBasedDTO.SupportDTO;
import com.example.ECommerce.DTOs.UserRegisterationDTO;
import com.example.ECommerce.Entities.SubEntities.Support;
import com.example.ECommerce.Entities.User;
import com.example.ECommerce.Mappers.SupportMapper;
import com.example.ECommerce.Mappers.UserMapper;
import com.example.ECommerce.Repositories.RoleBasedRepositories.SupportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupportService {
    private final SupportRepository supportRepository;
    private final UserMapper userMapper;
    private final SupportMapper supportMapper;
    @Autowired
    public SupportService(SupportRepository supportRepository , UserMapper userMapper, SupportMapper supportMapper) {
        this.supportRepository = supportRepository;
        this.userMapper = userMapper;
        this.supportMapper = supportMapper;
    }

    public SupportDTO registerSupport(UserRegisterationDTO userRegisterationDTO) {
        Support support = userMapper.userRegisterationDTOToSupport(userRegisterationDTO);
        supportRepository.save(support);
        //support mapper support to supportDTO
        SupportDTO supportDTO = supportMapper.supportToSupportDTO(support);
        return null;
    }
}
