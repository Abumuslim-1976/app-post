package uz.pdp.apppost.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.pdp.apppost.entity.User;
import uz.pdp.apppost.repository.UserRepository;

import java.util.Optional;

public class JpaAuditing implements AuditorAware<Long> {


    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() &&
                !authentication.getPrincipal().equals("anonymousUser")) {

            User user = (User)authentication.getPrincipal();
            return Optional.of(user.getId());
        }
        return Optional.empty();
    }


}
