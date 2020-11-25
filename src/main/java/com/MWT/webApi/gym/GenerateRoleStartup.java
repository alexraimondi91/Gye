package com.MWT.webApi.gym;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.MWT.webApi.gym.model.Ruolo;
import com.MWT.webApi.gym.model.TipiRuoli;
import com.MWT.webApi.gym.repository.RuoliRepository;

@Component
public class GenerateRoleStartup implements CommandLineRunner{
	
	@Autowired
	private RuoliRepository ruoliRepository;

	@Override
	public void run(String... args) throws Exception {
		
		if(!ruoliRepository.findByNome(TipiRuoli.ROLE_USER).isPresent() && !ruoliRepository.findByNome(TipiRuoli.ROLE_ADMIN).isPresent()) {
			
			Ruolo ruoloAdmin = new Ruolo();
			ruoloAdmin.setNome(TipiRuoli.ROLE_ADMIN);
			Ruolo ruoloUser = new Ruolo();
			ruoloUser.setNome(TipiRuoli.ROLE_USER);
			
			ruoliRepository.save(ruoloAdmin);
			ruoliRepository.save(ruoloUser);
			
		}
	}

}
