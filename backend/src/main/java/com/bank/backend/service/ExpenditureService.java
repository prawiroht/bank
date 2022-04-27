// package com.bank.backend.service;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// import com.bank.backend.entity.Expenditure;
// import com.bank.backend.exception.BusinessException;
// import com.bank.backend.repository.ExpenditureRepository;
// import com.bank.backend.util.PaginationList;
// import com.bank.backend.wrapper.ExpenditureWrapper;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// @Service
// @Transactional
// public class ExpenditureService {
//     @Autowired
//     ExpenditureRepository expenditureRepository;

//     //util
//     private Expenditure toEntity(ExpenditureWrapper wrapper){
//         Expenditure entity = new Expenditure();
//         if(wrapper.getExpenditureId() != null){
//             Optional<Expenditure> expenditure = expenditureRepository.findById(wrapper.getExpenditureId());
//             if (!expenditure.isPresent())
//                 throw new BusinessException("Expenditure ID "+ wrapper.getExpenditureId() +" is not found.");
//             entity=expenditure.get();
//         }
//         entity.getBank().setBankId(wrapper.getBankId());
//         entity.getBank().setCode(wrapper.getCode());
//         entity.getBank().setBankName(wrapper.getBankName());
//         entity.getUniversity().setUniversityId(wrapper.getUniversityId());
//         entity.getUniversity().setUniversityName(wrapper.getUniversityName());
//         entity.setAccountNumber(wrapper.getAccountNumber());
//         entity.setMutationId(wrapper.getMutationId());
//         entity.setTransactionDate(wrapper.getTransactionDate());
//         entity.setValue(wrapper.getValue());
//         entity.getPurchase().setPurchaseId(wrapper.getPurchaseId());
//         entity.getPurchase().setAlias(wrapper.getPurchaseAlias());
//         entity.getPurchase().setPurchaseName(wrapper.getPurchaseName());
//         entity.getAccountType().setAccountTypeId(wrapper.getAccountTypeId());
//         entity.getAccountType().setAccountTypeName(wrapper.getAccountTypeName());
//         entity.getFund().setFundId(wrapper.getFundId());
//         entity.getFund().setAlias(wrapper.getFundAlias());
//         entity.getFund().setFundName(wrapper.getFundName());
//         entity.setDescription(wrapper.getDescription());
//         return entity;
//     }

//     private ExpenditureWrapper toWrapper(Expenditure entity){
//         ExpenditureWrapper wrapper = new ExpenditureWrapper();
//         wrapper.setExpenditureId(entity.getExpenditureId());
//         wrapper.setBankId(entity.getBank() != null ? entity.getBank().getBankId() : null);
//         wrapper.setCode(entity.getBank() != null ? entity.getBank().getCode() : null);
//         wrapper.setBankName(entity.getBank() != null ? entity.getBank().getBankName() : null);
//         wrapper.setUniversityId(entity.getUniversity() != null ? entity.getUniversity().getUniversityId() : null);
//         wrapper.setUniversityName(entity.getUniversity() != null ? entity.getUniversity().getUniversityName() : null);
//         wrapper.setAccountNumber(entity.getAccountNumber());
//         wrapper.setMutationId(entity.getMutationId());
//         wrapper.setTransactionDate(entity.getTransactionDate());
//         wrapper.setValue(entity.getValue());
//         wrapper.setPurchaseId(entity.getPurchase() != null ? entity.getPurchase().getPurchaseId() : null);
//         wrapper.setPurchaseAlias(entity.getPurchase() != null ? entity.getPurchase().getAlias() : null);
//         wrapper.setPurchaseName(entity.getPurchase() != null ? entity.getPurchase().getPurchaseName() : null);
//         wrapper.setAccountTypeId(entity.getAccountType() != null ? entity.getAccountType().getAccountTypeId() : null);
//         wrapper.setAccountTypeName(entity.getAccountType() != null ? entity.getAccountType().getAccountTypeName() : null);
//         wrapper.setFundId(entity.getFund() != null ? entity.getFund().getFundId() : null);
//         wrapper.setFundAlias(entity.getFund() != null ? entity.getFund().getAlias() : null);
//         wrapper.setFundName(entity.getFund() != null ? entity.getFund().getFundName() : null);
//         wrapper.setDescription(entity.getDescription());
//         return wrapper;
//     }

//     private List<ExpenditureWrapper> toWrapperList(List<Expenditure> entityList){
//         List<ExpenditureWrapper> wrapperList = new ArrayList<ExpenditureWrapper>();
//         for (Expenditure entity : entityList) {
//             wrapperList.add(toWrapper(entity));
//         }
//         return wrapperList;
//     }

//     private PaginationList<ExpenditureWrapper, Expenditure> toPaginationList(Page<Expenditure> entityPage){
//         List<Expenditure> entityList = entityPage.getContent();
//         List<ExpenditureWrapper> wrapperList = toWrapperList(entityList);
//         PaginationList<ExpenditureWrapper, Expenditure> paginationList = new PaginationList<>(wrapperList, entityPage);
//         return paginationList;
//     }

//     //get
//     public List<Expenditure> findAll() {
//     return expenditureRepository.findAll();
//     }

//     public Expenditure findByExpenditureId(Long id){
//         return expenditureRepository.findById(id).get();
//     }

//     public PaginationList<ExpenditureWrapper, Expenditure> findAllPagination(int page, int size){
//         Pageable paging = PageRequest.of(page, size);
//         Page<Expenditure> expenditurePage = expenditureRepository.findAll(paging);
//         List<Expenditure> expenditureList = expenditurePage.getContent();
//         List<ExpenditureWrapper> expenditureWrapperList = toWrapperList(expenditureList);
//         return new PaginationList<ExpenditureWrapper, Expenditure>(expenditureWrapperList, expenditurePage);
//     }

//     public Expenditure findById(Long id){
//         if (id == null)
//             throw new BusinessException("Please insert ID");
//         Optional<Expenditure> expenditure = expenditureRepository.findById(id);
//         if (!expenditure.isPresent())
//             throw new BusinessException("ID "+ id +" is not found.");
//         return expenditure.get();
//     }

//     public PaginationList<ExpenditureWrapper, Expenditure> findAllCategories(String all, int page, int size){
//         Pageable paging  = PageRequest.of(page, size);
//         Page<Expenditure> expenditurePage = expenditureRepository.findByAllCategories(all, paging);
//         List<Expenditure> expenditureList = expenditurePage.getContent();
//         List<ExpenditureWrapper> expenditureWrapperList = toWrapperList(expenditureList);
//         return new PaginationList<ExpenditureWrapper, Expenditure>(expenditureWrapperList, expenditurePage);
//     }

//     //post and put
//     public ExpenditureWrapper save(ExpenditureWrapper wrapper) {
//         Expenditure expenditure = new Expenditure();
//         if (wrapper.getExpenditureId() == null) {
//             if (wrapper.getAccountTypeId() == null){
//                 throw new BusinessException("Account Type ID cannot be null");
//             if (wrapper.getAccountNumber() == null) {
//                 throw new BusinessException("Account Number cannot be null");
//             if (wrapper.getBankId() == null) {
//                 throw new BusinessException("Bank ID cannot be null");
//             if (wrapper.getFundId() == null){
//                 throw new BusinessException("Fund ID cannot be null");
//             if (wrapper.getMutationId() == null) {
//                 throw new BusinessException("Mutation ID cannot be null");
//             if (wrapper.getPurchaseId() == null) {
//                 throw new BusinessException("Purchase ID cannot be null");
//             if (wrapper.getUniversityId() == null) {
//                 throw new BusinessException("University ID cannot be null");
//             } expenditure = expenditureRepository.save(toEntity(wrapper));  
//         }
//         expenditure = expenditureRepository.save(toEntity(wrapper)); 
//     }
//         return toWrapper(expenditure);
// }

//     //delete
//     public void delete(Long id){
//         if (id == null)
//             throw new BusinessException("Please insert ID.");
//         Optional<Expenditure> expenditure = expenditureRepository.findById(id);
//         if (!expenditure.isPresent())
//             throw new BusinessException("Expenditure ID "+ id +" is not found.");
//         expenditureRepository.deleteById(id);
//     }
// }
