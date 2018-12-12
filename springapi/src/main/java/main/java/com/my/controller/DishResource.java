package main.java.com.my.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import main.java.com.my.dao.DishRepository;
import main.java.com.my.dao.IndishRepository;
import main.java.com.my.dao.IngrenameRepository;
import main.java.com.my.model.Dish;
import main.java.com.my.model.Indish;
import main.java.com.my.model.Ingrename;
@CrossOrigin
@RestController
public class DishResource {
	@Autowired
	private DishRepository dishRepository;
	@Autowired
	private IngrenameRepository ingrenameRepository;
	@Autowired
	private IndishRepository indishRepository;
	
	@GetMapping("/dishes")
	public List<Dish> retrieveAllStudents() {
		return dishRepository.findAll();
	}
	
	@GetMapping("/dishes/{id}")
	public Dish retrieveStudent(@PathVariable int id) throws Exception {
		Optional<Dish> student = dishRepository.findById(id);

		if (!student.isPresent())
			throw new Exception("dish not found id-" + id);

		return student.get();
	}
	
	@DeleteMapping("/dishes/{id}")
	public void deleteStudent(@PathVariable int id) {
		dishRepository.deleteById(id);
	}
	
	@PostMapping("/dishes")
	public ResponseEntity<Object> createStudent(@RequestBody Dish student) {
		Dish savedStudent = dishRepository.save(student);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedStudent.getIddish()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@GetMapping("/dishes/search")
	public List<Dish> findDish(@RequestParam("name") String name) {
		List<Dish> savedStudent = dishRepository.findBydishnameContainingIgnoreCase(name);


		return savedStudent;

	}
	
	@PutMapping("/dishes/{id}")
	public ResponseEntity<Object> updateStudent(@RequestBody Dish student, @PathVariable int id) {

		Optional<Dish> studentOptional = dishRepository.findById(id);

		if (!studentOptional.isPresent())
			return ResponseEntity.notFound().build();

		student.setIddish(id);
		
		dishRepository.save(student);

		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/dishes/{id}/ingreds")
	public List<Ingrename> retrieveIngredsByDish(@PathVariable int id) throws Exception {
		List<Ingrename> student = ingrenameRepository.findByIndishsDishIddish(id );
		
		
	/*	if (!student.isPresent())
			throw new Exception("dish not found id-" + id);
*/
		return student;
	}
	@Transactional
	@DeleteMapping("/dishes/{id}/ingreds/{id1}")
	public void deleteIngredByDish(@PathVariable int id,@PathVariable int id1) throws Exception {
	/*	List<Indish> student =*/ indishRepository.deleteByDishIddishAndIngrenameIdingrenames(id, id1);
		
		
	/*	if (!student.isPresent())
			throw new Exception("dish not found id-" + id);
*/
		//return student;
		//return student.get(0);
	}	
	
	
	@Transactional
	@PostMapping("/dishes/{id}/ingreds/{id1}")
	public void addIngredByDish(@PathVariable int id,@PathVariable int id1) throws Exception {
		Indish tmp =new Indish();
		Dish dish = dishRepository.getOne(id);
		Ingrename ingrename = ingrenameRepository.getOne(id1);
		tmp.setDish(dish);
		tmp.setIngrename(ingrename);
		/*List<Indish> student = */indishRepository.save(tmp);
		
		
	/*	if (!student.isPresent())
			throw new Exception("dish not found id-" + id);*/

		//return student;
		//return student.get(0);
	}	
	
	
	@Transactional
	@GetMapping("/dishes/ingreds/{id}")
	public List<Dish> getDishesByIngred(@PathVariable int id) throws Exception {
		return dishRepository.findByindishsIngrenameIdingrenamesIn(id);
		/*List<Indish> student = *///indishRepository.save(tmp);
		
		
	/*	if (!student.isPresent())
			throw new Exception("dish not found id-" + id);*/

		//return student;
		//return student.get(0);
	}	
	
}
