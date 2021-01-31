package uk.ac.le.cs.CO3015.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.le.cs.CO3015.domain.Algorithms;
import uk.ac.le.cs.CO3015.repository.AlgorithmsRepository;

@Service
public class AlgorithmsService {
	
	@Autowired
	private AlgorithmsRepository ar;

	public Optional<Algorithms> findById(Integer id){
		return ar.findById(id);
	}
	
	public void deleteById(Integer id){
		ar.deleteById(id);;
	}
	
	public void save(Algorithms algorithm){
		ar.save(algorithm);
	}
	
	public Optional <Algorithms> findByName(String algorithm){
		return ar.findByAlgorithm(algorithm);
	}
	
}