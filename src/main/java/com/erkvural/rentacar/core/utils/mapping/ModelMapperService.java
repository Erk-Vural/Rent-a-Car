package com.erkvural.rentacar.core.utils.mapping;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public interface ModelMapperService {
    ModelMapper forDto();

    ModelMapper forRequest();
}
