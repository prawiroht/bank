package com.bank.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bank.backend.entity.Menu;
import com.bank.backend.exception.BusinessException;
import com.bank.backend.repository.MenuRepository;
import com.bank.backend.util.PaginationList;
import com.bank.backend.wrapper.MenuWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MenuService {
    @Autowired
    MenuRepository menuRepository;

    public List<MenuWrapper> findAll(){
        return toWrapperList(menuRepository.findAll());
    }
    public PaginationList<MenuWrapper,Menu> findAllPagination(int page,int size){
        Pageable paging = PageRequest.of(page, size);
        return toPaginationList(menuRepository.findAll(paging));
    }

    public MenuWrapper save(MenuWrapper wrapper){
        return toWrapper(menuRepository.save(toEntity(wrapper)));
    }

    public void delete(Long id){
        if(id == null)
            throw new BusinessException("ID tidak boleh kosong");
        Optional<Menu> menu = menuRepository.findById(id);
        if (!menu.isPresent())
            throw new BusinessException("Menu tidak ditemukan "+id+'.');
        menuRepository.deleteById(id);
    }

    // util
    private Menu toEntity(MenuWrapper wrapper){
        Menu entity = new Menu();
        if(wrapper.getMenuId() != null){
            Optional<Menu> menu = menuRepository.findById(wrapper.getMenuId());
            if (!menu.isPresent())
                throw new BusinessException("Menu not found: " + wrapper.getMenuId() + '.');
            entity=menu.get();
        }
        entity.setName(wrapper.getName());
        return entity;
    }

    private MenuWrapper toWrapper(Menu entity){
        MenuWrapper wrapper = new MenuWrapper();
        wrapper.setMenuId(entity.getMenuId());
        wrapper.setName(entity.getName());
        return wrapper;
    }

    private List<MenuWrapper> toWrapperList(List<Menu> entityList){
        List<MenuWrapper> wrapperList = new ArrayList<MenuWrapper>();
        for (Menu entity : entityList) {
            wrapperList.add(toWrapper(entity));
        }
        return wrapperList;
    }

    private PaginationList<MenuWrapper,Menu> toPaginationList(Page<Menu> entityPage){
        List<Menu> entityList = entityPage.getContent();
        List<MenuWrapper> wrapperList = toWrapperList(entityList);
        PaginationList<MenuWrapper,Menu> paginationList = new PaginationList<>(wrapperList, entityPage);
        return paginationList;
    }
}
