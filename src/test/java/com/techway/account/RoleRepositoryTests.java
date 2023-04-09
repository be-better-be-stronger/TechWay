package com.techway.account;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.techway.entity.Account;
import com.techway.entity.Role;
import com.techway.repository.AccountRepository;
import com.techway.repository.RoleRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {
	
	@Autowired
	private RoleRepository roleRepository;
	@Autowired AccountRepository accountRepository;
    
    @Test
    public void testCreateRoles() {
        Role admin = new Role("ROLE_ADMIN");
        Role editor = new Role("ROLE_EDITOR");
        Role customer = new Role("ROLE_CUSTOMER");
         
        roleRepository.saveAll(List.of(admin, editor, customer));
         
        long count = roleRepository.count();
        assertEquals(3, count);
    }
   
    @Test
    public void testAssignRoleToUser() {
        Long accountId = 4L;
        Integer roleId = 3;
        Account user = accountRepository.findById(accountId).get();
        user.addRole(new Role(roleId));
         
        Account updatedUser = accountRepository.save(user);
        assertThat(updatedUser.getRoles()).hasSize(1);         
    }
}
