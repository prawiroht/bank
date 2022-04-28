package com.bank.backend.service;

import java.util.ArrayList;
import java.util.List;

import com.bank.backend.entity.Inbox;
import com.bank.backend.repository.InboxRepository;
import com.bank.backend.repository.TransactionRepository;
import com.bank.backend.repository.UserRepository;
import com.bank.backend.util.PaginationList;
import com.bank.backend.wrapper.InboxWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InboxService {
    @Autowired
    InboxRepository inboxRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TransactionRepository transactionRepository;
    public List<InboxWrapper> findAll(){
        return toWrapperList(inboxRepository.findAll());
    }

    public PaginationList<InboxWrapper,Inbox> findAllPagination(int page,int size){
        Pageable paging = PageRequest.of(page, size);
        return toPaginationList(inboxRepository.findAll(paging));
    }

    public List<InboxWrapper> findByUserId(Long userId){
        return toWrapperList(inboxRepository.findByUserId(userId));
    }

    public PaginationList<InboxWrapper,Inbox> findByUserIdPagination(Long userId, int page, int size){
        Pageable paging = PageRequest.of(page,size);
        return toPaginationList(inboxRepository.findAll(paging));
    }

    public InboxWrapper save(InboxWrapper wrapper){
        return toWrapper(inboxRepository.save(toEntity(wrapper)));
    }

    public void delete(Long id){
        inboxRepository.deleteById(id);
    }

    private Inbox toEntity(InboxWrapper wrapper){
        Inbox entity = new Inbox();
        if(wrapper.getInboxId() != null){
            entity=inboxRepository.getById(wrapper.getInboxId());
        }
        entity.setUser(userRepository.getById(wrapper.getUserId()));
        entity.setTransaction(transactionRepository.getById(wrapper.getTransactionId()));
        entity.setMessage(wrapper.getMessage());
        return entity;
    }

    private InboxWrapper toWrapper(Inbox entity){
        InboxWrapper wrapper = new InboxWrapper();
        wrapper.setInboxId(entity.getInboxId());
        if (entity.getUser() != null){
            wrapper.setUserId(entity.getUser().getUserId());
            wrapper.setUsername(entity.getUser().getUsername());
        }
        wrapper.setMessage(entity.getMessage());
        if (entity.getTransaction() != null){
            wrapper.setTransactionId(entity.getTransaction().getTransactionId());
            wrapper.setTransactionName(entity.getTransaction().getName());
        }
        return wrapper;
    }

    private List<InboxWrapper> toWrapperList(List<Inbox> entityList){
        List<InboxWrapper> wrapperList = new ArrayList<InboxWrapper>();
        for (Inbox entity : entityList) {
            wrapperList.add(toWrapper(entity));
        }
        return wrapperList;
    }

    private PaginationList<InboxWrapper,Inbox> toPaginationList(Page<Inbox> entityPage){
        List<Inbox> entityList = entityPage.getContent();
        List<InboxWrapper> wrapperList = toWrapperList(entityList);
        PaginationList<InboxWrapper,Inbox> paginationList = new PaginationList<>(wrapperList, entityPage);
        return paginationList;
    }
}
