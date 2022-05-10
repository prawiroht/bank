package com.bank.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import com.bank.backend.entity.Mutation;
import com.bank.backend.repository.MutationRepository;
import com.bank.backend.util.PaginationList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MutationService {
    @Autowired
    MutationRepository mutationRepository;

    public PaginationList<Mutation, Mutation> toPaginationList(Page<Mutation> entityPage){
        List<Mutation> entityList = entityPage.getContent();
        PaginationList<Mutation, Mutation> paginationList = new PaginationList<>(entityList, entityPage);
        return paginationList;
    }

    public List<Mutation> findAll(){
        return mutationRepository.findAll();
    } 

    public PaginationList<Mutation, Mutation> findAllPagination(int page, int size){
        Pageable paging = PageRequest.of(page, size);
        return toPaginationList(mutationRepository.findAll(paging));
    }

    public Mutation save(Mutation mutation){
        if (mutation.getMutationId() != null){
            Mutation existedMutation = mutationRepository.getById(mutation.getMutationId());
            existedMutation.setMutationId(mutation.getMutationId());
            existedMutation.setMutationName(mutation.getMutationName());
            return mutationRepository.save(existedMutation);
        } else {
            return mutationRepository.save(mutation);
        }
    }

    public void delete(String id){
        mutationRepository.deleteById(id);
    }
}
