package uk.ac.le.cs.CO3015.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.ac.le.cs.CO3015.domain.Algorithms;

@Repository
public interface AlgorithmsRepository extends CrudRepository<Algorithms,Integer> {
	 Optional <Algorithms> findByAlgorithm(String algorithm);
	 
}

