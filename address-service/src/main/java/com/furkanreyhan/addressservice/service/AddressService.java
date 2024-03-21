package com.furkanreyhan.addressservice.service;

import com.furkanreyhan.addressservice.entity.Address;
import com.furkanreyhan.addressservice.repository.AddressRepository;
import com.furkanreyhan.addressservice.request.CreateAddressRequest;
import com.furkanreyhan.addressservice.response.AddressResponse;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class AddressService {

    AddressRepository addressRepository;
    public AddressResponse createAddress(CreateAddressRequest createAddressRequest) {
        Address address = new Address();
        address.setStreet(createAddressRequest.getStreet());
        address.setCity(createAddressRequest.getCity());

        addressRepository.save(address);

        return new AddressResponse(address);
    }

    public AddressResponse getById(long id) {

        Optional<Address> address = addressRepository.findById(id);

        return new AddressResponse(address.get());
    }
}
