package com.bank.backend.service;

import java.util.ArrayList;
import java.util.List;

import com.bank.backend.entity.GroupMenu;
import com.bank.backend.repository.GroupMenuRepository;
import com.bank.backend.repository.GroupRepository;
import com.bank.backend.repository.MenuRepository;
import com.bank.backend.util.PaginationList;
import com.bank.backend.wrapper.GroupMenuWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GroupMenuService {
    @Autowired
    GroupMenuRepository groupMenuRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    MenuRepository menuRepository;

    public List<GroupMenuWrapper> findAll(){
        return toWrapperList(groupMenuRepository.findAll());
    }

    public PaginationList<GroupMenuWrapper,GroupMenu> findAllPagination(int page,int size){
        Pageable paging = PageRequest.of(page, size);
        return toPaginationList(groupMenuRepository.findAll(paging));
    }

    public GroupMenuWrapper save(GroupMenuWrapper wrapper){
        return toWrapper(groupMenuRepository.save(toEntity(wrapper)));
    }

    public GroupMenuWrapper saveByGroupIdAndMenuId(Long groupId,Long menuId, Character isActive){
        GroupMenu access = groupMenuRepository.findByGroupIdAndMenuId(groupId, menuId);
        access.setIsActive(isActive);
        return toWrapper(groupMenuRepository.save(access));
    }

    public void delete(Long id){
        groupMenuRepository.deleteById(id);
    }

    private GroupMenu toEntity(GroupMenuWrapper wrapper){
        GroupMenu entity = new GroupMenu();
        if(wrapper.getGroupMenuId() != null){
            entity=groupMenuRepository.getById(wrapper.getGroupMenuId());
        }
        entity.setGroup(groupRepository.getById(wrapper.getGroupId()));
        entity.setMenu(menuRepository.getById(wrapper.getMenuId()));
        entity.setIsActive(wrapper.getIsActive());
        return entity;
    }

    private GroupMenuWrapper toWrapper(GroupMenu entity){
        GroupMenuWrapper wrapper = new GroupMenuWrapper();
        wrapper.setGroupMenuId(entity.getGroupMenuId());
        if (entity.getGroup() != null){
            wrapper.setGroupId(entity.getGroup().getGroupId());
            wrapper.setGroupName(entity.getGroup().getName());
        }
        if (entity.getMenu() != null){
            wrapper.setMenuId(entity.getMenu().getMenuId());
            wrapper.setMenuName(entity.getMenu().getName());
        }
        wrapper.setIsActive(entity.getIsActive());
        return wrapper;
    }

    private List<GroupMenuWrapper> toWrapperList(List<GroupMenu> entityList){
        List<GroupMenuWrapper> wrapperList = new ArrayList<GroupMenuWrapper>();
        for (GroupMenu entity : entityList) {
            wrapperList.add(toWrapper(entity));
        }
        return wrapperList;
    }

    private PaginationList<GroupMenuWrapper,GroupMenu> toPaginationList(Page<GroupMenu> entityPage){
        List<GroupMenu> entityList = entityPage.getContent();
        List<GroupMenuWrapper> wrapperList = toWrapperList(entityList);
        PaginationList<GroupMenuWrapper,GroupMenu> paginationList = new PaginationList<>(wrapperList, entityPage);
        return paginationList;
    }
}
