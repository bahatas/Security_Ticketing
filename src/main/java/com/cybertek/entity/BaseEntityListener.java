package com.cybertek.entity;

import com.cybertek.entity.common.UserPrincipal;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;



@Component
public class BaseEntityListener  {



   /* @Override
    public void touchForCreate(Object baseEntity) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        ((BaseEntity) baseEntity).insertDateTime = LocalDateTime.now();
        ((BaseEntity) baseEntity).lastUpdateDateTime = LocalDateTime.now();
        ((BaseEntity) baseEntity).insertUserId = 1L;
        ((BaseEntity) baseEntity).lastUpdateUserId = 1L;

        if (authentication != null && !authentication.getName().equals("anonymousUser")) {

            Object principal = authentication.getPrincipal();

            ((BaseEntity) baseEntity).insertUserId=((UserPrincipal)principal).getId();
            ((BaseEntity) baseEntity).lastUpdateUserId=((UserPrincipal)principal).getId();
        }
    }

    @Override
    public void touchForUpdate(Object baseEntity) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        ((BaseEntity) baseEntity).lastUpdateDateTime = LocalDateTime.now();
        ((BaseEntity) baseEntity).lastUpdateUserId = 1L;

        if (authentication != null && !authentication.getName().equals("anonymousUser")) {

            Object principal = authentication.getPrincipal();

            ((BaseEntity) baseEntity).lastUpdateUserId=((UserPrincipal)principal).getId();

        }
    }*/

    @PrePersist
    public void touchForCreate(BaseEntity baseEntity) {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        baseEntity.insertDateTime = LocalDateTime.now();
        baseEntity.lastUpdateDateTime = LocalDateTime.now();
        baseEntity.insertUserId = 1L;
        baseEntity.lastUpdateUserId = 1L;

        if (authentication != null && !authentication.getName().equals("anonymousUser")) {

            Object principal = authentication.getPrincipal();

            baseEntity.insertUserId=((UserPrincipal)principal).getId();
            baseEntity.lastUpdateUserId=((UserPrincipal)principal).getId();
        }

    }


    @PreUpdate
    public void touchForUpdate(BaseEntity baseEntity) {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        baseEntity.lastUpdateDateTime = LocalDateTime.now();
        baseEntity.lastUpdateUserId = 1L;

        if (authentication != null && !authentication.getName().equals("anonymousUser")) {

            Object principal = authentication.getPrincipal();

            baseEntity.lastUpdateUserId=((UserPrincipal)principal).getId();

        }
    }


}
