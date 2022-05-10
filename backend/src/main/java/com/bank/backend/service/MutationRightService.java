package com.bank.backend.service;

import java.util.ArrayList;
import java.util.List;

import com.bank.backend.entity.MutationRight;
import com.bank.backend.repository.BankRepository;
import com.bank.backend.repository.MutationRepository;
import com.bank.backend.repository.MutationRightRepository;
import com.bank.backend.util.PaginationList;
import com.bank.backend.wrapper.MutationRightWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class MutationRightService {
    @Autowired
    MutationRightRepository mutationRightRepository;
    @Autowired
    BankRepository bankRepository;
    @Autowired
    MutationRepository mutationRepository;

    private MutationRight toEntity (MutationRightWrapper wrapper){
        MutationRight entity = new MutationRight();
        if (wrapper.getMutationRightId() != null){
            entity = mutationRightRepository.getById(wrapper.getMutationRightId());
        }
        entity.setMutation(mutationRepository.getById(wrapper.getMutationId()));
        return entity;
    }

    private MutationRightWrapper toWrapper (MutationRight entity){
        MutationRightWrapper wrapper = new MutationRightWrapper();
        wrapper.setMutationRightId(entity.getMutationRightId());
        if (entity.getMutation() != null){
            wrapper.setMutationId(entity.getMutation().getMutationId());
            wrapper.setMutationName(entity.getMutation().getMutationName());
        }
        if (entity.getBank() != null){
            wrapper.setBankId(entity.getBank().getBankId());
            wrapper.setBankName(entity.getBank().getBankName());
        }
        return wrapper;
    }

    private List<MutationRightWrapper> toWrapperList(List<MutationRight> entityList){
        List<MutationRightWrapper> wrapperList = new ArrayList<MutationRightWrapper>();
        for (MutationRight entity : entityList) {
            wrapperList.add(toWrapper(entity));
        }
        return wrapperList;
    }

    private PaginationList<MutationRightWrapper,MutationRight> toPaginationList(Page<MutationRight> entityPage){
        List<MutationRight> entityList = entityPage.getContent();
        List<MutationRightWrapper> wrapperList = toWrapperList(entityList);
        PaginationList<MutationRightWrapper,MutationRight> paginationList = new PaginationList<>(wrapperList, entityPage);
        return paginationList;
    }

    public List<MutationRightWrapper> findAll() {
        return toWrapperList(mutationRightRepository.findAll());
    }

    public PaginationList<MutationRightWrapper, MutationRight> findAllPagination(int page, int size){
        Pageable paging = PageRequest.of(page, size);
        return toPaginationList(mutationRightRepository.findAll(paging));
    }

    public MutationRightWrapper save(MutationRightWrapper wrapper){
        return toWrapper(mutationRightRepository.save(toEntity(wrapper)));
    }

    public void delete(Long id){
        mutationRightRepository.deleteById(id);
    }

}
