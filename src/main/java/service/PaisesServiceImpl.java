package service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import model.Pais;

@Service
public class PaisesServiceImpl implements  PaisesService {

	@Autowired
	RestTemplate template;
	final String url = "https://restcountries.com/v2/all";

	@Override
	public List<Pais> obtenerPaises() {
		// Le indicamos que la respuesta va a ser un gran string.
		String resultado= template.getForObject(url, String.class);
		ObjectMapper maper = new ObjectMapper();
		List<Pais> paises = new ArrayList<>();
		ArrayNode array;
		try {
			// Obtenemos ArrayNode con los datos de la respuesta
			array= (ArrayNode) maper.readTree(resultado);
			for (Object  ob : array) {
				// Cada elemento del array es TODO el objeto país complejo con mil campos
				ObjectNode json= (ObjectNode) ob;
				//String nombre, String capital, int poblacion, String bandera
				
				String name = (json.get("name")!=null)?json.get("name").asText():"";
				String capital = (json.get("capital")!=null)?json.get("capital").asText():"";
				int population = (json.get("population")!=null)?json.get("population").asInt():0;
				String flag = (json.get("flag")!=null)?json.get("flag").asText():"";

				paises.add(new Pais(name,capital,population,flag));
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return paises;
	}
	
	@Override
	public List<Pais> buscarPaises(String name) {
		return obtenerPaises().stream().filter(p->p.getNombre().contains(name)).collect(Collectors.toList());
	}

}
