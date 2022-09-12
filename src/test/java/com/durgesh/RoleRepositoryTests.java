package com.durgesh;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.durgesh.model.Role;
import com.durgesh.model.User;
import com.durgesh.repo.RoleRepository;
import com.durgesh.repo.UserRepository;
 
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {
    @Autowired private RoleRepository repo;
    @Autowired private UserRepository userrepo;
     
    @Test
    public void testCreateRoles() {
        Role admin = new Role("ROLE_ADMIN");
        Role editor = new Role("ROLE_EDITOR");
        Role customer = new Role("ROLE_CUSTOMER");
         
        repo.saveAll(List.of(admin, editor, customer));
         
        long count = repo.count();
        assertEquals(3, count);
    }
    
    @Test
    public void testAssignRoleToUser() {
        Integer userId = 2;
        Integer roleId = 3;
        User user = userrepo.findById(userId).get();
        user.addRole(new Role(roleId));
         
        User updatedUser =userrepo.save(user);
        assertThat(updatedUser.getRoles()).hasSize(1);
         
    }
    
    @Test
    public void testAssignRoleToUserAdmin() {
        Integer userId = 2;
        Integer roleId = 1;
        User user = userrepo.findById(userId).get();
        user.addRole(new Role(roleId));
   
     
         
    User updatedUser =userrepo.save(user);
        assertThat(updatedUser.getRoles()).hasSize(2);
         
    }
    
    @Test
    public void testAssignRoleToUserCustomer() {
        Integer userId = 2;
        User user = userrepo.findById(userId).get();
        user.addRole(new Role(2));
    
     
         
    User updatedUser =userrepo.save(user);
        assertThat(updatedUser.getRoles()).hasSize(3);
         
    }
}