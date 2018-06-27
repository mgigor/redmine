package net.resetarcade.redmine.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskadapter.redmineapi.RedmineException;

import net.resetarcade.redmine.redmine.SprintManager;

@CrossOrigin
@RestController
@RequestMapping("/controller")
public class Controller {

	@GetMapping("/close-sprint")
	public ResponseEntity<Void> closeSprint() throws RedmineException {
		
		SprintManager sprint = new SprintManager("Sprint 06", "Sprint 07", "115", "478");
		
		sprint.closeSprint();
		
		
		
		return ResponseEntity.ok().build();
	}
	
	
}
