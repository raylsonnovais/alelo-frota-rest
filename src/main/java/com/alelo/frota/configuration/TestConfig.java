package com.alelo.frota.configuration;


import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.alelo.frota.entidades.Vehicle;
import com.alelo.frota.repositories.VehicleRepository;


@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private VehicleRepository repository;
	
	private static Logger logger = LoggerFactory.getLogger(Vehicle.class);
	
	Random r = new Random();
	List<String> alphabet = Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");
	List<String> numbers = Arrays.asList("1", "2","3","4","5","6","7","8","9");
	List<String> colors = Arrays.asList("black", "withe", "purple", "blue", "green", "grey");
	
	@Override
	public void run(String... args) throws Exception {
		
		logger.info("Iniciado cadastro!");

		for(int i = 0; i < 30 ; i++) {
			Vehicle v = new Vehicle();
			
			v.setPlate(generatePlate());
			v.setColor(colors.get(r.nextInt(colors.size())));
			v.setStatus((i % 2) == 0);
			v.setManufacturer("Mercedes-Benz");
			v.setModel("Class C 1.1 Avantgarde Turbo Flex ");
			
			repository.save(v);
		}
		
		
		logger.info("Finalizado com sucesso!");
	}
	
	public String generatePlate() {
		String plate = "";

		for(int a = 0; a < 3 ; a++) {
			if(plate.isEmpty() ) {
				plate = alphabet.get(r.nextInt(alphabet.size()));
			} else {
				plate = plate.concat(alphabet.get(r.nextInt(alphabet.size())));
			}
		}
		
		for(int n = 0; n < 4; n++) {
			plate = plate.concat(numbers.get(r.nextInt(numbers.size())));
		}
		
		return plate;
	}
}
