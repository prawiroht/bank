CREATE TABLE USERS(
    USER_ID NUMBER(10)
        CONSTRAINT USERS_USER_ID_PK PRIMARY KEY,
    USERNAME VARCHAR2(50)
        CONSTRAINT USERS_USERNAME_NN NOT NULL
        CONSTRAINT USERS_USERNAME_UK UNIQUE,
    PASSWORD VARCHAR2(50)
        CONSTRAINT USERS_PASSWORD_NN NOT NULL,
    NAME VARCHAR2(50)
        CONSTRAINT USERS_NAME_NN NOT NULL,
    ADDRESS VARCHAR2(255),
    EMAIL VARCHAR2(50)
        CONSTRAINT USERS_EMAIL_NN NOT NULL
        CONSTRAINT USERS_EMAIL_UK UNIQUE
        CONSTRAINT USERS_EMAIL_CK CHECK (EMAIL LIKE '%@%.%'),
    PHONE VARCHAR2(50)
)

CREATE SEQUENCE USER_SEQ
MINVALUE 1
MAXVALUE 1000000000
START WITH 1
INCREMENT BY 1
NOCYCLE
NOCACHE;

CREATE TABLE GROUPS(
    GROUP_ID NUMBER(10)
        CONSTRAINT GROUPS_GROUP_ID_PK PRIMARY KEY,
    NAME VARCHAR2(100)
        CONSTRAINT GROUPS_NAME_NN NOT NULL
)

CREATE SEQUENCE GROUP_SEQ
MINVALUE 1
MAXVALUE 1000000000
START WITH 1
INCREMENT BY 1
NOCYCLE
NOCACHE;

CREATE TABLE MENUS(
    MENU_ID NUMBER(10)
        CONSTRAINT MENUS_MENU_ID_PK PRIMARY KEY,
    NAME VARCHAR2(100)
        CONSTRAINT MENUS_NAME_NN NOT NULL,
    ICON VARCHAR2(255)
        CONSTRAINT MENUS_ICON_NN NOT NULL,
    URL VARCHAR2(255)
        CONSTRAINT MENUS_URL_NN NOT NULL
)

CREATE SEQUENCE MENU_SEQ
MINVALUE 1
MAXVALUE 1000000000
START WITH 1
INCREMENT BY 1
NOCYCLE
NOCACHE;

CREATE TABLE ACCESS_RIGHTS(
    ACCESS_ID NUMBER(10)
        CONSTRAINT ACCESS_ACCESS_ID_PK PRIMARY KEY,
    USER_ID NUMBER(10)
        CONSTRAINT ACCESS_USER_ID_NN NOT NULL,
    GROUP_ID NUMBER(10)
        CONSTRAINT ACCESS_GROUP_ID_NN NOT NULL,
    IS_ACTIVE CHAR(1)
        CONSTRAINT ACCESS_IS_ACTIVE_NN NOT NULL,
    CONSTRAINT ACCESS_USER_ID_FK FOREIGN KEY (USER_ID)
        REFERENCES USERS(USER_ID)
        ON DELETE CASCADE,
    CONSTRAINT ACCESS_GROUP_ID_FK FOREIGN KEY (GROUP_ID)
        REFERENCES GROUPS(GROUP_ID)
        ON DELETE CASCADE
)

CREATE SEQUENCE ACCESS_SEQ
MINVALUE 1
MAXVALUE 1000000000
START WITH 1
INCREMENT BY 1
NOCYCLE
NOCACHE;

CREATE TABLE GROUP_MENUS(
    GROUP_MENU_ID NUMBER(10)
        CONSTRAINT GM_GM_ID_PK PRIMARY KEY,
    GROUP_ID NUMBER(10)
        CONSTRAINT GM_GROUP_ID_NN NOT NULL,
    MENU_ID NUMBER(10)
        CONSTRAINT GM_MENU_ID_NN NOT NULL,
    IS_ACTIVE CHAR(1)
        CONSTRAINT GM_IS_ACTIVE_NN NOT NULL,
    CONSTRAINT GM_GROUP_ID_FK FOREIGN KEY (GROUP_ID)
        REFERENCES GROUPS(GROUP_ID)
        ON DELETE CASCADE,
    CONSTRAINT GM_MENU_ID_FK FOREIGN KEY (MENU_ID)
        REFERENCES MENUS(MENU_ID)
        ON DELETE CASCADE
)

CREATE SEQUENCE GROUP_MENU_SEQ
MINVALUE 1
MAXVALUE 1000000000
START WITH 1
INCREMENT BY 1
NOCYCLE
NOCACHE;

CREATE OR REPLACE TRIGGER USERS_AFTER_INSERT
AFTER INSERT
   ON USERS
   FOR EACH ROW
DECLARE
    GRUP GROUPS%ROWTYPE;
    CURSOR C_GRUP IS SELECT * FROM GROUPS;
BEGIN
    IF NOT C_GRUP%ISOPEN THEN 
        OPEN C_GRUP;
    END IF;
    LOOP
        FETCH C_GRUP INTO GRUP;
        EXIT WHEN C_GRUP%NOTFOUND;
        INSERT INTO ACCESS_RIGHTS VALUES(
            ACCESS_SEQ.NEXTVAL,
            :NEW.USER_ID,
            GRUP.GROUP_ID,
            'N'
        );
    END LOOP;
    CLOSE C_GRUP;
END;

CREATE OR REPLACE TRIGGER MENUS_AFTER_INSERT
AFTER INSERT
   ON MENUS
   FOR EACH ROW
DECLARE
    GRUP GROUPS%ROWTYPE;
    CURSOR C_GRUP IS SELECT * FROM GROUPS;
BEGIN
    IF NOT C_GRUP%ISOPEN THEN 
        OPEN C_GRUP;
    END IF;
    LOOP
        FETCH C_GRUP INTO GRUP;
        EXIT WHEN C_GRUP%NOTFOUND;
        INSERT INTO GROUP_MENUS VALUES(
            GROUP_MENU_SEQ.NEXTVAL,
            GRUP.GROUP_ID,
            :NEW.MENU_ID,
            'N'
        );
    END LOOP;
    CLOSE C_GRUP;
END;

CREATE OR REPLACE TRIGGER GROUPS_AFTER_INSERT
AFTER INSERT
   ON GROUPS
   FOR EACH ROW
DECLARE
    USER USERS%ROWTYPE;
    CURSOR C_USER IS SELECT * FROM USERS;
    MENU MENUS%ROWTYPE;
    CURSOR C_MENU IS SELECT * FROM MENUS;
BEGIN
    IF NOT C_USER%ISOPEN THEN 
        OPEN C_USER;
    END IF;
    LOOP
        FETCH C_USER INTO USER;
        EXIT WHEN C_USER%NOTFOUND;
        INSERT INTO ACCESS_RIGHTS VALUES(
            ACCESS_SEQ.NEXTVAL,
            USER.USER_ID,
            :NEW.GROUP_ID,
            'N'
        );
    END LOOP;
    CLOSE C_USER;

    IF NOT C_MENU%ISOPEN THEN 
        OPEN C_MENU;
    END IF;
    LOOP
        FETCH C_MENU INTO MENU;
        EXIT WHEN C_MENU%NOTFOUND;
        INSERT INTO GROUP_MENUS VALUES(
            GROUP_MENU_SEQ.NEXTVAL,
            :NEW.GROUP_ID,
            MENU.MENU_ID,
            'N'
        );
    END LOOP;
    CLOSE C_MENU;
END;