package com.bank.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bank.backend.entity.Group;
import com.bank.backend.entity.Menu;
import com.bank.backend.entity.User;
import com.bank.backend.exception.BusinessException;
import com.bank.backend.repository.GroupRepository;
import com.bank.backend.repository.MenuRepository;
import com.bank.backend.repository.UserRepository;
import com.bank.backend.util.PaginationList;
import com.bank.backend.wrapper.GroupWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GroupService {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    UserRepository userRepository;

    public PaginationList<GroupWrapper,Group> findAllPagination(int page,int size){
        Pageable paging = PageRequest.of(page, size);
        return toPaginationList(groupRepository.findAll(paging));
    }
    
    public List<GroupWrapper> findAll(){
        return toWrapperList(groupRepository.findAll());
    }

    public List<GroupWrapper> findGroupByUserId(Long userId){
        if(userId == null)
            return null;
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent())
            return null;
        return toWrapperList(groupRepository.findGroupByUserId(userId));
    }

    public GroupWrapper save(GroupWrapper wrapper){
        return toWrapper(groupRepository.save(toEntity(wrapper)));
    }

    public void delete(Long id){
        if (id == null)
	        throw new BusinessException("ID cannot be null.");
		Optional<Group> entity = groupRepository.findById(id);
		if (!entity.isPresent())
			throw new BusinessException("Group not found: " + id + '.');
		groupRepository.deleteById(id);
    }
    // util
    private Group toEntity(GroupWrapper wrapper){
        Group entity = new Group();
        if(wrapper.getGroupId() != null){
            Optional<Group> group = groupRepository.findById(wrapper.getGroupId());
            if (!group.isPresent())
                throw new BusinessException("Group not found: " + wrapper.getGroupId() + '.');
            entity=group.get();
        }
        entity.setName(wrapper.getName());
        return entity;
    }

    private GroupWrapper toWrapper(Group entity){
        GroupWrapper wrapper = new GroupWrapper();
        wrapper.setGroupId(entity.getGroupId());
        wrapper.setName(entity.getName());
        List<Menu> menuEntities = menuRepository.findMenuByGroupId(entity.getGroupId());
        List<String> menus = new ArrayList<String>();
        for (Menu menu : menuEntities){
            menus.add(menu.getName());
        }
        wrapper.setMenus(menus);
        return wrapper;
    }

    private List<GroupWrapper> toWrapperList(List<Group> entityList){
        List<GroupWrapper> wrapperList = new ArrayList<GroupWrapper>();
        for (Group entity : entityList) {
            wrapperList.add(toWrapper(entity));
        }
        return wrapperList;
    }

    private PaginationList<GroupWrapper,Group> toPaginationList(Page<Group> entityPage){
        List<Group> entityList = entityPage.getContent();
        List<GroupWrapper> wrapperList = toWrapperList(entityList);
        PaginationList<GroupWrapper,Group> paginationList = new PaginationList<>(wrapperList, entityPage);
        return paginationList;
    }
}
