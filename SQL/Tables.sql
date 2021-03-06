CREATE TABLE GROUPS
	(GROUP_ID NUMBER, 
	GROUP_NAME VARCHAR2(20), 
	CONSTRAINT group_id_pk PRIMARY KEY (GROUP_ID),
	CONSTRAINT group_name_nn CHECK (GROUP_NAME IS NOT NULL) ENABLE
	);
	
CREATE TABLE MENUS
	(MENU_ID NUMBER,
	MENU_NAME VARCHAR(20),
	CONSTRAINT menu_id_pk PRIMARY KEY (MENU_ID),
	CONSTRAINT menu_name CHECK (MENU_NAME IS NOT NULL) ENABLE
	);
	
CREATE TABLE USERS
	(USER_ID NUMBER,
	PASSWORD VARCHAR2(30),
	NAME VARCHAR2(50),
	ADDRESS VARCHAR2(100),
	EMAIL VARCHAR2(30),
	PHONE VARCHAR(13),
	CONSTRAINT user_id_pk PRIMARY KEY (USER_ID),
	CONSTRAINT password_nn CHECK (PASSWORD IS NOT NULL) ENABLE,
	CONSTRAINT name_nn CHECK (NAME IS NOT NULL) ENABLE,
	CONSTRAINT address_nn CHECK (ADDRESS IS NOT NULL) ENABLE,
	CONSTRAINT email_uk UNIQUE (EMAIL),
	CONSTRAINT email_nn CHECK (EMAIL IS NOT NULL) ENABLE,
	CONSTRAINT phone_nn CHECK (PHONE IS NOT NULL) ENABLE
	);
	
CREATE TABLE ACCESS_RIGHTS
	(ACCESS_RIGHT_ID NUMBER NOT NULL,
	USER_ID NUMBER NOT NULL,
	GROUP_ID NUMBER NOT NULL,
	CONSTRAINT access_right_id_pk PRIMARY KEY (ACCESS_RIGHT_ID),
	CONSTRAINT access_right_user_id_fk FOREIGN KEY (USER_ID) REFERENCES USERS (USER_ID),
	CONSTRAINT access_right_group_id_fk FOREIGN KEY (GROUP_ID) REFERENCES GROUPS (GROUP_ID)
	);
	
CREATE TABLE GROUP_MENUS
	(GROUP_MENU_ID NUMBER NOT NULL,
	GROUP_ID NUMBER NOT NULL,
	MENU_ID NUMBER NOT NULL,
	CONSTRAINT group_menu_id_pk PRIMARY KEY (GROUP_MENU_ID),
	CONSTRAINT group_menu_group_id_fk FOREIGN KEY (GROUP_ID) REFERENCES GROUPS (GROUP_ID),
	CONSTRAINT group_menu_menu_id_fk FOREIGN KEY (MENU_ID) REFERENCES MENUS (MENU_ID)
	);
	
CREATE TABLE UNIVERSITIES
	(UNIVERSITY_ID NUMBER NOT NULL,
	UNIVERSITY_NAME VARCHAR2(50) NOT NULL,
	CONSTRAINT university_id_pk PRIMARY KEY (UNIVERSITY_ID)
	);
	
CREATE TABLE BANKS
	(BANK_ID NUMBER,
	CODE VARCHAR2(5),
	BANK_NAME VARCHAR2(30),
	CONSTRAINT bank_id_pk PRIMARY KEY (BANK_ID),
	CONSTRAINT code_uk UNIQUE (CODE),
	CONSTRAINT code_nn CHECK (CODE IS NOT NULL) ENABLE,
	CONSTRAINT bank_name_nn CHECK (BANK_NAME IS NOT NULL) ENABLE
	);
	
CREATE TABLE ACCOUNT_TYPES
	(ACCOUNT_ID NUMBER,
	ACCOUNT_NAME VARCHAR2(30),
	CONSTRAINT account_id_pk PRIMARY KEY (ACCOUNT_ID),
	CONSTRAINT account_name_nn CHECK (ACCOUNT_NAME IS NOT NULL) ENABLE
	);
	
CREATE TABLE PURCHASES
	(PURCHASE_ID NUMBER,
	ALIAS VARCHAR2(5),
	PURCHASE_NAME VARCHAR2(50),
	CONSTRAINT purchase_id_pk PRIMARY KEY (PURCHASE_ID),
	CONSTRAINT purchase_alias_uk UNIQUE (ALIAS),
	CONSTRAINT purchase_alias_nn CHECK (ALIAS IS NOT NULL) ENABLE,
	CONSTRAINT purchase_name_nn CHECK (PURCHASE_NAME IS NOT NULL) ENABLE
	);
	
CREATE TABLE RECEIVINGS
	(RECEIVING_ID NUMBER,
	RECEIVING_NAME VARCHAR2(50),
	CONSTRAINT receiving_id_pk PRIMARY KEY (RECEIVING_ID),
	CONSTRAINT receiving_name_nn CHECK (RECEIVING_NAME IS NOT NULL) ENABLE
	);
	
CREATE TABLE FUNDS
	(FUND_ID NUMBER,
	ALIAS VARCHAR2(5),
	FUND_NAME VARCHAR2(50),
	CONSTRAINT fund_id_pk PRIMARY KEY (FUND_ID),
	CONSTRAINT alias_uk UNIQUE (ALIAS),
	CONSTRAINT alias_nn CHECK (ALIAS IS NOT NULL) ENABLE,
	CONSTRAINT fund_name_nn CHECK (FUND_NAME IS NOT NULL) ENABLE
	);

	
CREATE TABLE PERIODS
	(PERIOD_ID NUMBER,
	PERIOD VARCHAR2(10),
	CONSTRAINT period_id_pk PRIMARY KEY (PERIOD_ID),
	CONSTRAINT period_nn CHECK (PERIOD IS NOT NULL) ENABLE
	);

CREATE TABLE EXPENDITURES
	(EXPENDITURE_ID NUMBER,
	BANK_ID NUMBER,
	UNIVERSITY_ID NUMBER,
	ACCOUNT_NUMBER VARCHAR2(25),
	MUTATION_ID VARCHAR2(20),
	TRANSACTION_DATE DATE,
	VALUE NUMBER,
	PURCHASE_ID NUMBER,
	ACCOUNT_TYPE_ID NUMBER,
	FUND_ID NUMBER,
	DESCRIPTION VARCHAR2(255),
	CONSTRAINT expenditure_id_pk PRIMARY KEY (EXPENDITURE_ID),
	CONSTRAINT expenditure_bank_id_fk FOREIGN KEY (BANK_ID) REFERENCES BANKS (BANK_ID) ON DELETE CASCADE,
	CONSTRAINT expenditure_bank_id_nn CHECK (BANK_ID IS NOT NULL) ENABLE,
	CONSTRAINT expenditure_university_id_fk FOREIGN KEY (UNIVERSITY_ID) REFERENCES UNIVERSITIES (UNIVERSITY_ID) ON DELETE CASCADE,
	CONSTRAINT expenditure_university_nn CHECK (UNIVERSITY_ID IS NOT NULL) ENABLE,
	CONSTRAINT expenditure_account_number_nn CHECK (ACCOUNT_NUMBER IS NOT NULL) ENABLE,
	CONSTRAINT expenditure_mutation_id_nn CHECK (MUTATION_ID IS NOT NULL) ENABLE,
	CONSTRAINT expenditure_transaction_date_nn CHECK (TRANSACTION_DATE IS NOT NULL) ENABLE,
	CONSTRAINT expenditure_value_nn CHECK (VALUE IS NOT NULL) ENABLE,
	CONSTRAINT expenditure_purchase_id_fk FOREIGN KEY (PURCHASE_ID) REFERENCES PURCHASES (PURCHASE_ID) ON DELETE CASCADE,
	CONSTRAINT expenditure_purchase_id_nn CHECK (PURCHASE_ID IS NOT NULL) ENABLE,
	CONSTRAINT expenditure_account_id_fk FOREIGN KEY (ACCOUNT_TYPE_ID) REFERENCES ACCOUNT_TYPES (ACCOUNT_TYPE_ID) ON DELETE CASCADE,
	CONSTRAINT expenditure_account_id_nn CHECK (ACCOUNT_TYPE_ID IS NOT NULL) ENABLE,
	CONSTRAINT expenditure_fund_id_fk FOREIGN KEY (FUND_ID) REFERENCES FUNDS (FUND_ID) ON DELETE CASCADE, 
	CONSTRAINT expenditure_fund_id_nn CHECK (FUND_ID IS NOT NULL) ENABLE
	);
	
CREATE SEQUENCE expenditure_seq MINVALUE 1 START WITH 1 INCREMENT BY 1 nocache nocycle;

CREATE SEQUENCE receiving_seq MINVALUE 1 START WITH 1 INCREMENT BY 1 nocache nocycle;

CREATE SEQUENCE purchase_seq MINVALUE 1 START WITH 1 INCREMENT BY 1 nocache nocycle;

INSERT INTO BANKS VALUES (BANK_SEQ.nextval, '008', '');

CREATE TABLE CURRENT_ACCOUNTS 
(CURRENT_ACCOUNT_ID NUMBER CONSTRAINT CURRENT_ACCOUNT_ID_PK PRIMARY KEY,
UNIVERSITY_ID NUMBER CONSTRAINT UNIVERSITY_ID_NN NOT NULL,
BANK_ID NUMBER CONSTRAINT BANK_ID_NN NOT NULL,
ACCOUNT_NUMBER VARCHAR2(25) CONSTRAINT ACCOUNT_NUMBER_NN NOT NULL,
ACCOUNT_TYPE_ID NUMBER CONSTRAINT ACCOUNT_ID_NN NOT NULL,
INITIAL_BALANCE_DATE DATE CONSTRAINT INITIAL_BALANCE_DATE_NN NOT NULL,
INITIAL_BALANCE_ACCOUNT NUMBER CONSTRAINT INITIAL_BALANCE_ACCOUNT NOT NULL,
CONSTRAINT UNIVERSITY_ID_FK FOREIGN KEY (UNIVERSITY_ID) REFERENCES UNIVERSITIES (UNIVERSITY_ID) ON DELETE CASCADE,
CONSTRAINT BANK_ID_FK FOREIGN KEY (BANK_ID) REFERENCES BANKS (BANK_ID) ON DELETE CASCADE,
CONSTRAINT ACCOUNT_TYPE_ID_FK FOREIGN KEY (ACCOUNT_TYPE_ID) REFERENCES ACCOUNT_TYPES (ACCOUNT_TYPE_ID) ON DELETE CASCADE
);

CREATE TABLE INVESTATIONS
(INVESTATION_ID NUMBER CONSTRAINT INVESTATION_ID_PK PRIMARY KEY,
UNIVERSITY_ID NUMBER CONSTRAINT INVESTATION_UNIVERSITY_ID_NN NOT NULL,
INVESTATION_NAME VARCHAR2(25) CONSTRAINT INVESTATION_NAME_NN NOT NULL,
INITIAL_SAVING NUMBER CONSTRAINT INITIAL_SAVING_NN NOT NULL,
INITIAL_UNIT NUMBER CONSTRAINT INITIAL_UNIT_NN NOT NULL,
INITIAL_VALUE NUMBER CONSTRAINT INITIAL_VALUE_NN NOT NULL,
MARKET_SAVING NUMBER CONSTRAINT MARKET_SAVING_NN NOT NULL,
MARKET_UNIT NUMBER CONSTRAINT MARKET_UNIT_NN NOT NULL,
MARKET_VALUE NUMBER CONSTRAINT MARKET_VALUE_NN NOT NULL,
START_DATE DATE CONSTRAINT START_DATE_NN NOT NULL,
CONSTRAINT INVESTATION_UNIVERSITY_ID_FK FOREIGN KEY (UNIVERSITY_ID) REFERENCES UNIVERSITIES (UNIVERSITY_ID) ON DELETE CASCADE);

CREATE TABLE  DEPOSITS
(DEPOSIT_ID NUMBER CONSTRAINT DEPOSIT_ID_PK PRIMARY KEY,
UNIVERSITY_ID NUMBER CONSTRAINT DEPOSIT_UNIVERSITY_ID_NN NOT NULL,
DEPOSIT_NAME VARCHAR2(25) CONSTRAINT DEPOSIT_NAME_NN NOT NULL,
BANK_ID NUMBER CONSTRAINT DEPOSIT_BANK_ID_NN NOT NULL,
ACCOUNT_NUMBER NUMBER CONSTRAINT DEPOSIT_ACCOUNT_NUMBER_NN NOT NULL,
PERIOD_ID NUMBER CONSTRAINT PERIOD_ID_NN NOT NULL,
NOMINAL NUMBER CONSTRAINT NOMINAL_NN NOT NULL,
INTEREST NUMBER CONSTRAINT INTEREST_NN NOT NULL,
EARNING_INTEREST NUMBER CONSTRAINT EARNING_INTEREST_NN NOT NULL,
START_DATE NUMBER CONSTRAINT DEPOSIT_START_DATE_NN NOT NULL,
DUE_DATE NUMBER CONSTRAINT DUE_DATE_NN NOT NULL,
CONSTRAINT DEPOSIT_UNIVERSITY_ID_FK FOREIGN KEY (UNIVERSITY_ID) REFERENCES UNIVERSITIES (UNIVERSITY_ID) ON DELETE CASCADE,
CONSTRAINT DEPOSIT_BANK_ID_FK FOREIGN KEY (BANK_ID) REFERENCES BANKS (BANK_ID) ON DELETE CASCADE,
CONSTRAINT PERIOD_ID_FK FOREIGN KEY (PERIOD_ID) REFERENCES PERIODS (PERIOD_ID) ON DELETE CASCADE
);

DROP TABLE FUNDS ;

SELECT * FROM BANKS b WHERE LOWER(b.BANK_NAME) LIKE LOWER(CONCAT(CONCAT('%','m'),'%'));

CREATE TABLE TRANSACTIONS(
	TRANSACTION_ID NUMBER(10)
	CONSTRAINT TRANSACTIONS_TRANSACTION_ID_PK PRIMARY KEY,
	NAME VARCHAR2(50)
	CONSTRAINT TRANSACTIONS_NAME_NN NOT NULL
)

CREATE SEQUENCE TRANSACTION_SEQ
MINVALUE 1
MAXVALUE 1000000000
START WITH 1
INCREMENT BY 1
NOCYCLE
NOCACHE;