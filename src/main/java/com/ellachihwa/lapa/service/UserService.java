package com.ellachihwa.lapa.service;

import com.ellachihwa.lapa.dto.UserDto;
import com.ellachihwa.lapa.model.Client;
import com.ellachihwa.lapa.model.NotificationEntity;
import com.ellachihwa.lapa.model.Role;
import com.ellachihwa.lapa.model.User;
import com.ellachihwa.lapa.repository.NotificationRepository;
import com.ellachihwa.lapa.repository.RoleRepository;
import com.ellachihwa.lapa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    public UserService(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }


    public User save(UserDto userDto) {
        NotificationEntity notificationEntity = new NotificationEntity("New user created",new Date(),"user",false,true);
        if(userRepository.findByEmail(userDto.getEmail()) != null){

            return null;
        }else {

            Role role = roleRepository.getOne(userDto.getRole());
            User user = new User(userDto.getFirstName(),
                    userDto.getLastName(), userDto.getEmail(),
                    passwordEncoder.encode(userDto.getPassword()), Arrays.asList(role),true,userDto.getPhoto());


            notificationRepository.save(notificationEntity);
            return userRepository.save(user);
        }

    }


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

        User user = userRepository.findByEmail(username);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public User getUser(Long id){
        User user = userRepository.findById(id).orElse(null);

        if (user==null) {

            return null;

        }

        else return user;
    }

    public List<User> getUsers(){
        System.out.println(userRepository.findAll(Sort.by(Sort.Direction.DESC, "id")));
        return userRepository.findAll();
    }

    public User updateUSer(User updatedUser){
        NotificationEntity notificationEntity = new NotificationEntity("User" + updatedUser.getFirstName() + " " + updatedUser.getLastName() + "updated",new Date(),"user",false,true);

        return userRepository.findById(updatedUser.getId())
                .map(user -> {
                    user.setFirstName(updatedUser.getFirstName());
                    user.setLastName(updatedUser.getLastName());
                    user.setEmail(updatedUser.getEmail());
                    user.setPassword(updatedUser.getPassword());
                    notificationRepository.save(notificationEntity);
                    return userRepository.save(user);
                })
                .orElseGet(() -> null);

    }

    public void deleteUser(Long id){
        NotificationEntity notificationEntity = new NotificationEntity("User:" + id + "deleted",new Date(),"user",false,true);
        notificationRepository.save(notificationEntity);
        userRepository.deleteById(id);
    }

    public User loginUser(String username,String password){
        User user = userRepository.findByEmail(username);
        if(user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {

                return user;
            }
            else
                return null;
        }
        return null;

    }

}
