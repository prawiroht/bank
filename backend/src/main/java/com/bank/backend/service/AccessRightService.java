package com.bank.backend.service;

import java.util.ArrayList;
import java.util.List;

import com.bank.backend.entity.AccessRight;
import com.bank.backend.entity.Group;
import com.bank.backend.entity.User;
import com.bank.backend.repository.AccessRightRepository;
import com.bank.backend.repository.GroupRepository;
import com.bank.backend.repository.UserRepository;
import com.bank.backend.util.PaginationList;
import com.bank.backend.wrapper.AccessRightWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccessRightService {
    @Autowired
    AccessRightRepository accessRightRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    UserRepository userRepository;

    public List<AccessRightWrapper> findAll(){
        return toWrapperList(accessRightRepository.findAll());
    }

    public PaginationList<AccessRightWrapper,AccessRight> findAllPagination(int page,int size){
        Pageable paging = PageRequest.of(page, size);
        return toPaginationList(accessRightRepository.findAll(paging));
    }

    public AccessRightWrapper save(AccessRightWrapper wrapper){
        return toWrapper(accessRightRepository.save(toEntity(wrapper)));
    }

    public AccessRightWrapper saveByUserIdAndGroupId(Long userId,Long groupId, Character isActive){
        AccessRight access = accessRightRepository.getByGroupIdAndUserId(groupId, userId);
        access.setIsActive(isActive);
        return toWrapper(accessRightRepository.save(access));
    }

    public void delete(Long id){
        accessRightRepository.deleteById(id);
    }
    private AccessRight toEntity(AccessRightWrapper wrapper){
        AccessRight entity = new AccessRight();
        if(wrapper.getAccessRightId() != null){
            entity = accessRightRepository.getById(wrapper.getAccessRightId());
        }
        Group group = groupRepository.getById(wrapper.getGroupId());
        System.out.println(group);
        entity.setGroup(group);
        User user = userRepository.getById(wrapper.getUserId());
        System.out.println(user);
        entity.setUser(user);
        entity.setIsActive(wrapper.getIsActive());
        return entity;
    }

    private AccessRightWrapper toWrapper(AccessRight entity){
        AccessRightWrapper wrapper = new AccessRightWrapper();
        wrapper.setAccessRightId(entity.getAccessRightId());
        if (entity.getGroup() != null){
            wrapper.setGroupId(entity.getGroup().getGroupId());
            wrapper.setGroupName(entity.getGroup().getName());
        }
        if (entity.getUser() != null){
            wrapper.setUserId(entity.getUser().getUserId());
            wrapper.setUsername(entity.getUser().getUsername());
        }
        wrapper.setIsActive(entity.getIsActive());
        return wrapper;
    }

    private List<AccessRightWrapper> toWrapperList(List<AccessRight> entityList){
        List<AccessRightWrapper> wrapperList = new ArrayList<AccessRightWrapper>();
        for (AccessRight entity : entityList) {
            wrapperList.add(toWrapper(entity));
        }
        return wrapperList;
    }

    private PaginationList<AccessRightWrapper,AccessRight> toPaginationList(Page<AccessRight> entityPage){
        List<AccessRight> entityList = entityPage.getContent();
        List<AccessRightWrapper> wrapperList = toWrapperList(entityList);
        PaginationList<AccessRightWrapper,AccessRight> paginationList = new PaginationList<>(wrapperList, entityPage);
        return paginationList;
    }
}
