package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import model.Pais;
import service.PaisesService;

@RestController
public class PaisesController {

	@Autowired
	PaisesService paisesService;

	@GetMapping(value = "/paises", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Pais> listaPaises() {
		return paisesService.obtenerPaises();
	}

	@GetMapping(value = "/paises/{name}")
	public List<Pais> listaPaises(@PathVariable("name") String name) {
		return paisesService.buscarPaises(name);
	}
}
