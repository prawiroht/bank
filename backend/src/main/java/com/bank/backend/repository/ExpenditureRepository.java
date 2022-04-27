// package com.bank.backend.repository;

// import com.bank.backend.entity.Expenditure;

// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;

// public interface ExpenditureRepository extends JpaRepository<Expenditure, Long>{
//     Page<Expenditure> findAll(Pageable page);
    
//     @Query (value =
//     "SELECT e.EXPENDITURE_ID, e.BANK_ID, b.BANK_NAME, e.UNIVERSITY_ID, u.UNIVERSITY_NAME, e.ACCOUNT_NUMBER, "+ 
//     "e.MUTATION_ID, e.TRANSACTION_DATE, e.VALUE, e.PURCHASE_ID, p.PURCHASE_NAME, e.ACCOUNT_TYPE_ID, at.ACCOUNT_TYPE_NAME, "+
//     "e.FUND_ID, f.FUND_NAME, e.DESCRIPTION FROM EXPENDITURES e "+
//     "LEFT JOIN BANKS b ON e.BANK_ID = b.BANK_ID "+
//     "LEFT JOIN UNIVERSITIES u ON e.UNIVERSITY_ID = u.UNIVERSITY_ID "+
//     "LEFT JOIN PURCHASES p ON e.PURCHASE_ID = p.PURCHASE_ID "+
//     "LEFT JOIN ACCOUNT_TYPES at ON e.ACCOUNT_TYPE_ID = at.ACCOUNT_TYPE_ID "+
//     "LEFT JOIN FUNDS f ON e.FUND_ID = f.FUND_ID "+
//     "LOWER(b.BANK_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pBankName), '%')) OR "+
//     "LOWER(u.UNIVERSITY_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pUniversityName), '%')) OR "+
//     "LOWER(e.ACCOUNT_NUMBER) LIKE LOWER(CONCAT(CONCAT('%', :pAccountNumber), '%')) OR "+
//     "LOWER(e.MUTATION_ID) LIKE LOWER(CONCAT(CONCAT('%', :pMutationId), '%')) OR "+
//     "LOWER(p.PURCHASE_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pPurchaseName), '%')) OR "+
//     "LOWER(at.ACCOUNT_TYPE_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pAccountTypeName), '%')) OR "+
//     "LOWER(f.FUND_NAME) LIKE LOWER(CONCAT(CONCAT('%', :pFundName), '%')) OR "+
//     "LOWER(e.DESCRIPTION) LIKE LOWER(CONCAT(CONCAT('%', :pDescription), '%')) OR"
//     )
//     Page<Expenditure> findByAllCategoriesWithIndexedQueryParam(
//         String bankName,
//         String universityName,
//         String accountNumber,
//         String mutationId,
//         String purchaseName,
//         String accountTypeName,
//         String fundName,
//         String description, 
//         Pageable page);
//     default Page<Expenditure> findByAllCategories (String all, Pageable page){
//         return findByAllCategoriesWithIndexedQueryParam(
//             all, all, all, all, all, all, all, all, page);
//     }

// }
