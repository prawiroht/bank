package com.bank.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bank.backend.entity.Group;
import com.bank.backend.entity.User;
import com.bank.backend.exception.BusinessException;
import com.bank.backend.repository.GroupRepository;
import com.bank.backend.repository.UserRepository;
import com.bank.backend.util.PaginationList;
import com.bank.backend.wrapper.UserWrapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    UserRepository userRepository;
    GroupRepository groupRepository;

    // get
    public List<UserWrapper> findAll(){
        return toWrapperList(userRepository.findAll(Sort.by("userId").ascending()));
    }
    
    public UserWrapper getById(Long id){
        if (id == null)
	        return null;
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent())
            return null;
        return toWrapper(user.get());
    }
    
    // post & put
    public UserWrapper save(UserWrapper wrapper){
        return toWrapper(userRepository.save(toEntity(wrapper)));
    }

    // delete
    public void delete(Long id){
        if (id == null)
	        throw new BusinessException("ID cannot be null.");
		Optional<User> entity = userRepository.findById(id);
		if (!entity.isPresent())
			throw new BusinessException("User not found: " + id + '.');
		userRepository.deleteById(id);
    }

    // util
    private User toEntity(UserWrapper wrapper){
        User entity = new User();
        if(wrapper.getUserId() != null){
            Optional<User> user = userRepository.findById(wrapper.getUserId());
            if (!user.isPresent())
                throw new BusinessException("User not found: " + wrapper.getUserId() + '.');
            entity=user.get();
        }
        entity.setUsername(wrapper.getUsername());
        entity.setPassword(wrapper.getPassword());
        entity.setName(wrapper.getName());
        entity.setAddress(wrapper.getAddress());
        entity.setEmail(wrapper.getEmail());
        entity.setPhone(wrapper.getPhone());
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
        List<Group> groupEntities = groupRepository.findGroupByUserId(entity.getUserId());
        List<String> groups = new ArrayList<String>();
        for (Group group : groupEntities){
            groups.add(group.getName());
        }
        wrapper.setGroups(groups);
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
