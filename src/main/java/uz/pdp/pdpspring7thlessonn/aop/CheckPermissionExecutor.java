package uz.pdp.pdpspring7thlessonn.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.pdp.pdpspring7thlessonn.entity.User;
import uz.pdp.pdpspring7thlessonn.exceptions.ForbiddenException;

@Component
@Aspect

public class CheckPermissionExecutor {
    @Before(value = "@annotation(checkPermission)")
    public void checkUserPermissionMyMethod(CheckPermission checkPermission){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (user.getAuthorities().stream().filter())
//        String huquq = checkPermission.huquq();

        boolean exist = false;
        for (GrantedAuthority authority : user.getAuthorities()) {
            if (authority.getAuthority().equals(checkPermission.huquq())){
                exist=true;
                break;
            }
        }
        if (!exist){
            throw new ForbiddenException(checkPermission.huquq(),"Ruxsat yo'q");
        }

    }
}
