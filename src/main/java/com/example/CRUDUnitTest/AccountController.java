package com.example.CRUDUnitTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	@PostMapping
	public ResponseEntity<Account> createUser(@RequestBody Account account) {
		Account createdAccount = accountService.postUser(account);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Account> getUserById(@PathVariable("id") Long id) {
		Account account = accountService.getUser(id);
		return ResponseEntity.status(HttpStatus.OK).body(account);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Account> updateUser(@PathVariable("id") Long id, @RequestBody Account updatedAccount) {
		Account account = accountService.putUser(id, updatedAccount);
		return ResponseEntity.status(HttpStatus.OK).body(account);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
		accountService.delUser(id);
		return ResponseEntity.ok().build();
	}
}
