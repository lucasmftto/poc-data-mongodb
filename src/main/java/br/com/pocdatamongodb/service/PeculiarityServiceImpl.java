package br.com.pocdatamongodb.service;

import br.com.pocdatamongodb.dto.PeculiarityDTO;
import br.com.pocdatamongodb.entity.Peculiarity;
import br.com.pocdatamongodb.repository.PeculiarityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeculiarityServiceImpl implements PeculiarityService{

    @Autowired
    private PeculiarityRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void insertPeculiarity(PeculiarityDTO peculiarityDTO) {
        this.repository.save(mapper.map(peculiarityDTO, Peculiarity.class));
    }
}
