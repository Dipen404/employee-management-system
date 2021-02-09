package com.ipen.ems.security.auth;
import java.io.IOException;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
   
    void createUser(User user, Set<UserRole> userRoles) throws IOException;
   
}