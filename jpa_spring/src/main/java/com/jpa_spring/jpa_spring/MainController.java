package com.jpa_spring.jpa_spring;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class MainController {
	
	@Autowired
	private UserRepository userRepository;

	
	@PostMapping("/add")
	public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String email ){
		User n = new User();
		n.setNome(name);
		n.setEmail(email);
		userRepository.save(n);		
		return "User Saved";		
	}
	
	@GetMapping("/all")
	public @ResponseBody Iterable<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/listar/{id}")
	public @ResponseBody User getUserById(@PathVariable Integer id){
		return userRepository.findById(id).get();
	}
	
	@PutMapping("/update/{id}")
	public @ResponseBody String updateUser(@PathVariable Integer id, @RequestParam String name, @RequestParam String email) {
	    Optional<User> optionalUser = userRepository.findById(id);
	    if (optionalUser.isPresent()) {
	        User user = optionalUser.get();
	        user.setNome(name);
	        user.setEmail(email);
	        userRepository.save(user);
	        return "User Updated";
	    } else {
	        return "User not found";
	    }
	}
	
	
	@GetMapping("/delete/{id}")
	public @ResponseBody String deleteUser(@PathVariable Integer id) {
		userRepository.deleteById(id);
		return "User Deleted";
		
	}
}
	




