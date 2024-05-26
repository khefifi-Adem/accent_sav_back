package com.accent.sav.service.serviceImpl;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.transaction.Transactional;

import com.accent.sav.dto.ResetPasswordDto;
import com.accent.sav.dto.ResetPasswordLinkDto;
import com.accent.sav.entities.ConfirmationToken;
import com.accent.sav.entities.ResetToken;
import com.accent.sav.repository.ConfirmationTokenRepository;
import com.accent.sav.repository.ResetTokenRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.accent.sav.dto.UserInfoDto;
import com.accent.sav.entities.User;
import com.accent.sav.repository.UserRepository;
import com.accent.sav.security.exception.ResourceAlreadyExistException;
import com.accent.sav.security.exception.ResourceNotFoundException;
import com.accent.sav.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    ResetTokenRepository resetTokenRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    protected ModelMapper mapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(User.class);

        @Override
        @Transactional
        public UserInfoDto addUser(UserInfoDto userInfoDto) {
            // TODO Auto-generated method stub
            if (userRepository.existsByUsername(userInfoDto.getUsername())) {
                // throw exception
                throw new ResourceAlreadyExistException("Existing pseudo ");
            }
            logger.info("Retrieved user with ID {} successfully.", userInfoDto);
            User user = this.mapper.map(userInfoDto, User.class);
            user.setPassword(bCryptPasswordEncoder.encode(user.getRawPassword()));
            user.setEmail(user.getEmail());
            User userAdded = this.userRepository.save(user);
            //this.userRepository.save(userAdded);
            UserInfoDto returnedUser =  this.mapper.map(userAdded, UserInfoDto.class);
//            ConfirmationToken confirmationToken = new ConfirmationToken();
//            confirmationToken.setCreatedDate(userInfoDto.getCreatedDate());
//            confirmationToken.setConfirmationToken(UUID.randomUUID().toString());
//            confirmationToken.setUser(userAdded);
//            confirmationTokenRepository.save(confirmationToken);
//            SimpleMailMessage mailMessage = new SimpleMailMessage();
//            mailMessage.setTo(user.getEmail());
//            mailMessage.setSubject("Complete Registration!");
//            mailMessage.setText("To confirm your account, please click here : "
//                    +"http://mahdia.ttn.tn/confirmation/"+confirmationToken.getConfirmationToken());
//            emailService.sendEmail(mailMessage);
//
//            System.out.println("Confirmation Token: " + confirmationToken.getConfirmationToken());

            return returnedUser;
        }
        


    @Override
    public UserInfoDto updateUser(UserInfoDto userInfoDto) {
        // TODO Auto-generated method stub
        if (userInfoDto != null) {
            User user = this.userRepository.findById(userInfoDto.getId()).orElse(null);
            if (user != null) {
                User userUpdate = this.mapper.map(userInfoDto, User.class);
                    userUpdate.setPassword(bCryptPasswordEncoder.encode(userUpdate.getRawPassword()));
                this.userRepository.saveAndFlush(userUpdate);
                UserInfoDto result = this.mapper.map(userUpdate, UserInfoDto.class);
                return result;
            }
        }
        return null;
    }

    @Override
    public Boolean confirmUser (String token){
            ConfirmationToken confirmationToken = confirmationTokenRepository.findByconfirmationToken(token);
            if ( confirmationToken == null) {
                throw new ResourceNotFoundException("Token n'existe pas");
            }
            confirmationToken.getUser().setIsConfirmed(1);
        User userUpdated = this.userRepository.saveAndFlush(confirmationToken.getUser());
        if (confirmationToken.getUser().getIsConfirmed() == new Integer(0)) {
            return false;
        } else {
            return true;
        }

    }

    @Override
    public Boolean deleteUser(int id) {
        User userOld = this.userRepository.getOne(id);
        if (userOld == null) {
            throw new ResourceNotFoundException("User" + id + "n'existe pas");
        }
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<User> findAllUsers() {
        // TODO Auto-generated method stub
        return userRepository.findAll();
    }

    @Override
    public UserInfoDto getUserById(Integer idUser) {
        User user = userRepository.findByIdUser(idUser);
        if (user == null) {
            logger.error("User with ID {} does not exist.", idUser);
            throw new ResourceNotFoundException("ce user n'existe pas !");
        } else {
            logger.info("Test user with ID {} successfully.", idUser);
        }
        return this.mapper.map(user, UserInfoDto.class);
    }

    @Override
    public List<Map<String, Long>> userAgeStatus() {
        return userRepository.userAgeStats();
    }

    @Override
    public String getImgByIdUser(Integer idUser) {
        String img = userRepository.findImgByIdUser(idUser);
        ;
        if (img == null) {
            throw new org.springframework.data.rest.webmvc.ResourceNotFoundException("ce asset n'existe pas !");
        }
        logger.info("Retrieved user with ID {} successfully.", idUser, img);
        return img;
    }

    @Override
    public UserInfoDto ResetPasswordUser(ResetPasswordDto resetPasswordDto) {
        ResetToken resetToken = resetTokenRepository.findByresetToken(resetPasswordDto.getToken());
        if ( resetToken == null) {
            throw new ResourceNotFoundException("Token n'existe pas");
        }
        User user = this.userRepository.findById(resetToken.getUser().getId()).orElse(null);
        User userUpdate = user;

        if (userUpdate != null) {
            userUpdate.setRawPassword(resetPasswordDto.getPassword());
            userUpdate.setPassword(bCryptPasswordEncoder.encode(resetPasswordDto.getPassword()));
            this.userRepository.saveAndFlush(userUpdate);
            UserInfoDto result = this.mapper.map(userUpdate, UserInfoDto.class);
            return result;
        }
        return null;
        }

    @Override
    public Boolean RequestResetLinkUser(ResetPasswordLinkDto email) {
            logger.info(email.getEmail());
        User user = userRepository.findByEmail(email.getEmail());
        if (user == null) {
            logger.error("User with email {} does not exist.", email);
            throw new ResourceNotFoundException("ce user n'existe pas !");
        } else {
            logger.info("Get user with email {} successfully.", email);
        }
        ResetToken resetToken = new ResetToken();
        resetToken.setCreatedDate(user.getCreatedDate());
        resetToken.setResetToken(UUID.randomUUID().toString());
        resetToken.setUser(user);
        resetTokenRepository.save(resetToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Reset Password!");
        mailMessage.setText("To Reset your Password, please click here : "
                +"http://mahdia.ttn.tn/signin/reset-password/"+resetToken.getResetToken());
        emailService.sendEmail(mailMessage);

        System.out.println("Confirmation Token: " + resetToken.getResetToken());
        return true;
    }


}
