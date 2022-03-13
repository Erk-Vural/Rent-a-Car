package com.erkvural.rentacar.core.utils.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;


public class ModelMapperImpl implements ModelMapperService {
    private ModelMapper modelMapper;

    public ModelMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ModelMapper forDto() {
        this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.LOOSE);
        return this.modelMapper;
    }


    @Override
    public ModelMapper forRequest() {
        this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.STANDARD);
        return this.modelMapper;
    }
}
