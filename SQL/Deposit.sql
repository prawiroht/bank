CREATE TABLE CURRENT_ACCOUNTS 
(GIRO_ID NUMBER CONSTRAINT GIRO_ID_PK PRIMARY KEY,
UNIVERSITY_ID NUMBER CONSTRAINT UNIVERSITY_ID_NN NOT NULL,
BANK_ID varchar(25) CONSTRAINT BANK_ID_NN NOT NULL,
ACCOUNT_NUMBER VARCHAR2(25) CONSTRAINT ACCOUNT_NUMBER_NN NOT NULL,
ACCOUNT_ID NUMBER CONSTRAINT ACCOUNT_ID_NN NOT NULL,
INITIAL_BALANCE_DATE DATE CONSTRAINT INITIAL_BALANCE_DATE_NN NOT NULL,
INITIAL_BALANCE_ACCOUNT NUMBER CONSTRAINT INITIAL_BALANCE_ACCOUNT NOT NULL,
CONSTRAINT UNIVERSITY_ID_FK FOREIGN KEY (UNIVERSITY_ID) REFERENCES UNIVERSITIES (UNIVERSITY_ID) ON DELETE CASCADE,
CONSTRAINT BANK_ID_FK FOREIGN KEY (BANK_ID) REFERENCES BANKS (BANK_ID) ON DELETE CASCADE,
CONSTRAINT ACCOUNT_ID_FK FOREIGN KEY (ACCOUNT_ID) REFERENCES ACCOUNT_TYPES (ACCOUNT_ID) ON DELETE CASCADE
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
BANK_ID VARCHAR2(5) CONSTRAINT DEPOSIT_BANK_ID_NN NOT NULL,
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