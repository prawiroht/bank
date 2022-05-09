package com.bank.backend.service;

import java.util.ArrayList;
import java.util.List;

import com.bank.backend.entity.User;
import com.bank.backend.repository.GroupRepository;
import com.bank.backend.repository.UniversityRepository;
import com.bank.backend.repository.UserRepository;
import com.bank.backend.util.PaginationList;
import com.bank.backend.wrapper.GroupWrapper;
import com.bank.backend.wrapper.UserWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    GroupService groupService;
    @Autowired
    UniversityRepository universityRepository;
    // get
    public List<UserWrapper> findAll(){
        return toWrapperList(userRepository.findAll(Sort.by("userId").ascending()));
    }

    public PaginationList<UserWrapper,User> findAll(int page,int size){
        Pageable paging = PageRequest.of(page, size);
        return toPaginationList(userRepository.findAll(paging));
    }

    public UserWrapper getById(Long id){
        User user = userRepository.getById(id);
        return toWrapper(user);
    }

    public UserWrapper getByUsername(String username){
        User user = userRepository.getByUsername(username);
        return toWrapper(user);
    }
    public UserWrapper getByEmail(String email){
        User user = userRepository.getByEmail(email);
        return toWrapper(user);
    }
    // post & put
    public UserWrapper save(UserWrapper wrapper){
        return toWrapper(userRepository.save(toEntity(wrapper)));
    }

    // delete
    public void delete(Long id){
		userRepository.deleteById(id);
    }

    // util
    private User toEntity(UserWrapper wrapper){
        User entity = new User();
        if(wrapper.getUserId() != null){
            entity = userRepository.getById(wrapper.getUserId());
        }
        entity.setUsername(wrapper.getUsername());
        entity.setPassword(wrapper.getPassword());
        entity.setName(wrapper.getName());
        entity.setAddress(wrapper.getAddress());
        entity.setEmail(wrapper.getEmail());
        entity.setPhone(wrapper.getPhone());
        entity.setIsActive(wrapper.getIsActive());
        entity.setLastLogin(wrapper.getLastLogin());
        entity.setUniversity(universityRepository.getById(wrapper.getUniversityId()));
        return entity;
    }

    private UserWrapper toWrapper(User entity){
        UserWrapper wrapper = new UserWrapper();
        wrapper.setUserId(entity.getUserId());
        wrapper.setUsername(entity.getUsername());
        wrapper.setPassword(entity.getPassword());
        wrapper.setName(entity.getName());
        wrapper.setAddress(entity.getAddress());
        wrapper.setEmail(entity.getEmail());
        wrapper.setPhone(entity.getPhone());
        wrapper.setIsActive(entity.getIsActive());
        wrapper.setLastLogin(entity.getLastLogin());
        if(entity.getUniversity() != null){
            wrapper.setUniversityId(entity.getUniversity().getUniversityId());
            wrapper.setUniversityName(entity.getUniversity().getUniversityName());
        }
        List<GroupWrapper> groupEntities = groupService.findGroupByUserId(entity.getUserId());
        wrapper.setGroups(groupEntities);
        return wrapper;
    }

    private List<UserWrapper> toWrapperList(List<User> entityList){
        List<UserWrapper> wrapperList = new ArrayList<UserWrapper>();
        for (User entity : entityList) {
            wrapperList.add(toWrapper(entity));
        }
        return wrapperList;
    }

    private PaginationList<UserWrapper,User> toPaginationList(Page<User> entityPage){
        List<User> entityList = entityPage.getContent();
        List<UserWrapper> wrapperList = toWrapperList(entityList);
        PaginationList<UserWrapper,User> paginationList = new PaginationList<>(wrapperList, entityPage);
        return paginationList;
    }
}
