package com.bank.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bank.backend.entity.Group;
import com.bank.backend.entity.GroupMenu;
import com.bank.backend.entity.Menu;
import com.bank.backend.exception.BusinessException;
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
        if (menuId == null || groupId == null || isActive == null)
            throw new BusinessException("Input tidak boleh kosong");
        Optional<GroupMenu> access = groupMenuRepository.findByGroupIdAndMenuId(groupId, menuId);
        if(!access.isPresent())
            throw new BusinessException("Akses tidak ditemukan");
        access.get().setIsActive(isActive);
        return toWrapper(groupMenuRepository.save(access.get()));
    }

    public void delete(Long id){
        if(id == null)
            throw new BusinessException("ID tidak boleh kosong");
        Optional<GroupMenu> access = groupMenuRepository.findById(id);
        if(!access.isPresent())
            throw new BusinessException("Hak akses tidak ditemukan: "+id+".");
        groupMenuRepository.deleteById(id);
    }

    private GroupMenu toEntity(GroupMenuWrapper wrapper){
        GroupMenu entity = new GroupMenu();
        if(wrapper.getGroupMenuId() != null){
            Optional<GroupMenu> gm = groupMenuRepository.findById(wrapper.getGroupMenuId());
            if (!gm.isPresent())
                throw new BusinessException("GroupMenu not found: " + wrapper.getGroupMenuId() + '.');
            entity=gm.get();
        }
        if(wrapper.getGroupId() == null || wrapper.getMenuId() == null)
            throw new BusinessException("ID grup atau menu tidak boleh null");
        Optional<Group> group = groupRepository.findById(wrapper.getGroupId());
        if(!group.isPresent())
            throw new BusinessException("Grup tidak ditemukan");
        entity.setGroup(group.get());
        Optional<Menu> menu = menuRepository.findById(wrapper.getMenuId());
        if(!menu.isPresent())
            throw new BusinessException("User tidak ditemukan");
        entity.setMenu(menu.get());
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
