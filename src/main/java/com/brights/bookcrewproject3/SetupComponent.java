package com.brights.bookcrewproject3;

import com.brights.bookcrewproject3.pagedata.model.UserInfo;
import com.brights.bookcrewproject3.pagedata.repository.UserInfoRepository;
import com.brights.bookcrewproject3.security.model.Role;
import com.brights.bookcrewproject3.security.model.User;
import com.brights.bookcrewproject3.security.model.UserRoles;
import com.brights.bookcrewproject3.security.repository.RoleRepository;
import com.brights.bookcrewproject3.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class SetupComponent {

    private PasswordEncoder encoder;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserInfoRepository userInfoRepository;

    public SetupComponent(PasswordEncoder encoder, UserRepository userRepository, RoleRepository roleRepository, UserInfoRepository userInfoRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userInfoRepository = userInfoRepository;
    }

    @EventListener
    @Transactional
    public void handleApplicationReady(ApplicationReadyEvent applicationReadyEvent) {
        Role adminRole = new Role(UserRoles.ROLE_ADMIN);
        Role userRole = new Role(UserRoles.ROLE_USER);
        adminRole = roleRepository.save(adminRole);
        userRole = roleRepository.save(userRole);

        User user = new User("Test123",
                "mc@gmail.com",
                encoder.encode("Test123"));

        user.setRoles(Set.of(adminRole, userRole));
        UserInfo userInfo = new UserInfo("Matija", "Cvetan", new Date(), LocalDate.now(), 123465798L);
        user.setUserInfo(userInfoRepository.save(userInfo));
        userRepository.save(user);
    }
}
