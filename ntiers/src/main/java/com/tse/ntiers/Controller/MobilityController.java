package com.tse.ntiers.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tse.ntiers.business.Mobility;
import com.tse.ntiers.service.MobilityService;

/*Interface avec le front*/
@RestController
@CrossOrigin
public class MobilityController {
	
	@Autowired
	MobilityService mobilityService;
	
	@PostMapping("/mobility")
	/*Crée la mobilitée si elle n'existe pas déjà. Retourne les informations reçu*/
	public Mobility addMobility(@RequestBody Mobility mobility) {
		mobilityService.addMobility(mobility);
		return mobility;
	}
	
	@GetMapping("/mobility")
	/*Obtenir toute les mobilités*/
	public List<Mobility> getStudentMobility() {
		return mobilityService.getMobility();
	}
	
	@PostMapping("/mobility/filter")
	/*Obtenir les mobilités correspondant au filtre passé dans le body*/
	public List<Mobility> getStudentMobilityFilter(@RequestBody Mobility mobility) {
		return mobilityService.getMobilityFilter(mobility);
	}
	
	@GetMapping("/mobility/{eleveId}")
	/*Obtenir une mobilité par id*/
	public Mobility getStudentMobility(@PathVariable Long eleveId ) {
		return mobilityService.getMobility(eleveId);
	}
	
	@PostMapping("/mobility/delete")
	/*Suppression d'une mobilité ! Eleve uniquement !*/
	public Boolean delStudentMobilityFilter(@RequestBody Mobility mobility) {
		return mobilityService.delMobility(mobility);
	}	
}
