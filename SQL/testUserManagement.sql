SELECT g.GROUP_ID,g.name
FROM GROUPS G
JOIN ACCESS_RIGHTS AR ON G.GROUP_ID=AR.GROUP_ID
JOIN USERS U ON AR.USER_ID=U.USER_ID
WHERE U.USER_ID=1 and ar.IS_ACTIVE='Y';

desc users

INSERT INTO MENUS VALUES(MENU_SEQ.NEXTVAL,'Penampungan');
INSERT into GROUPS values(GROUP_SEQ.NEXTVAL,'User');

insert into users values(USER_SEQ.NEXTVAL,'super','1','Super Admin Ganteng','Jl. Super', 'super@super.com','5017312')

select * from GROUP_MENUS

update ACCESS_RIGHTS
set IS_ACTIVE = 'Y'
where GROUP_ID=1;

update GROUP_MENUS
set IS_ACTIVE = 'Y'