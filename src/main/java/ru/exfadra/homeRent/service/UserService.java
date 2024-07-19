package ru.exfadra.homeRent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.exfadra.homeRent.model.User;
import ru.exfadra.homeRent.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUser(String nickName){
        return userRepository.findByNickname(nickName);
    }


    public User getUser(Long id){
        return userRepository.findUserById(id);
    }


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void saveUser(User user){
        userRepository.save(user);
    }
    public String checkUser(User user)
    {
        User checkedUsernameByUsername = userRepository.findByNickname(user.getNickname());
        User checkedUsernameByEmail = userRepository.findUserByEmail(user.getEmail());


        if(checkedUsernameByUsername != null && checkedUsernameByEmail != null){
            return "notUniqueUsernameAndEmail";
        }

        if(checkedUsernameByUsername != null){
            return "notUniqueUsername";
        }

        if(checkedUsernameByEmail != null){
            return "notUniqueEmail";
        }
        return "";
    }
    public String registerUser(User user){

        String res=checkUser(user);
        if(res.equals("")) {
            user.setEnable(true);
            user.setLandLordRating(2L);
            user.setTenantRating(2L);
            user.setLandlordMarkCount(1L);
            user.setTenantMarkCount(1L);
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodedPassword = encoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            user.setRoles("USER");

            userRepository.save(user);

            return "success";
        }
        else {
            return res;
        }

    }
}
