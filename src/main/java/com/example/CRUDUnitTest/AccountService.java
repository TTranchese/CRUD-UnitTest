package com.example.CRUDUnitTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;
	
	public Account postUser(Account account) {
		if (account != null) {
			accountRepository.save(account);
		}
		return account;
	}
	
	public Account getUser(Long id) {
		Optional<Account> userFromDB = accountRepository.findById(id);
		return userFromDB.orElse(null);
	}
	
	public Account putUser(Long id, Account account) {
		account.setId(id);
		return accountRepository.save(account);
	}
	
	public void delUser(Long id) {
		accountRepository.deleteById(id);
	}
}
