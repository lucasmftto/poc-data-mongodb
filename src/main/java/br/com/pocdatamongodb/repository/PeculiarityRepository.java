package br.com.pocdatamongodb.repository;

import br.com.pocdatamongodb.entity.Peculiarity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeculiarityRepository  extends MongoRepository<Peculiarity, String> {
}
