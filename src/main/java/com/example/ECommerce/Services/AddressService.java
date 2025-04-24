package com.example.ECommerce.Services;

import com.example.ECommerce.DTOs.AddressDTO;
import com.example.ECommerce.Entities.Address;
import com.example.ECommerce.Mappers.AddressMapper;
import com.example.ECommerce.Repositories.JPA.RoleBasedRepositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    @Autowired
    public AddressService(AddressRepository addressRepository,AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
        this.addressRepository = addressRepository;
    }


    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

    public void updateAddress(Address address) {
        addressRepository.save(address);
    }

    public void updateAddress(AddressDTO addressDTO) {
        Address address = addressMapper.addressDTOToAddress(addressDTO);
        addressRepository.save(address);
    }

    public void addAddress(Address address) {
        addressRepository.save(address);
    }

    public AddressDTO getAddress(Long id) {
        Address address = addressRepository.findById(id).orElseThrow();
        return addressMapper.addressToAddressDTO(address);
    }

    public void addAddress(AddressDTO address) {
        addressRepository.save(addressMapper.addressDTOToAddress(address));
    }

}
