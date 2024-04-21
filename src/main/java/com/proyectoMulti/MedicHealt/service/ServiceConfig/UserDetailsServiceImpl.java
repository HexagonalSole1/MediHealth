package com.proyectoMulti.MedicHealt.service.ServiceConfig;


import com.proyectoMulti.MedicHealt.entity.Users;
import com.proyectoMulti.MedicHealt.repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PersonalRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{

       Users user = repository.findByEmail(email)
               .orElseThrow(()-> new UsernameNotFoundException("User not found"));

        return new UserDetailsImpl(user);
    }
}
