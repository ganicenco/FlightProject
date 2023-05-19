package ro.itschool.startup;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ro.itschool.model.MyRole;
import ro.itschool.enums.RoleName;
import ro.itschool.repository.RoleRepository;

@Component
@RequiredArgsConstructor
public class RunAtStartup {

    private final RoleRepository roleRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        roleRepository.save(new MyRole(RoleName.ROLE_USER));
        roleRepository.save(new MyRole(RoleName.ROLE_ADMIN));
    }
}
