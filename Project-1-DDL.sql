--tbl_users DDL
create table tbl_Users(
	userID serial primary key,
	userEmail varchar(50),
	userPassword varchar(30),
	userFName varchar(25),
	userMInit char(1),
	userLName varchar(25)
);

--tbl_accounts DDL
create table tbl_Accounts(
	accountID serial primary key,
	accountType varchar(25),
	accountNotes text,
	accountBalance decimal,
	userID integer references tbl_Users
);

--tbl_transactions DDL
create table tbl_Transactions(
	transID serial primary key,
	transDate date,
	transType varchar(25),
	transPreBalance decimal,
	transPostBalance decimal,
	transNotes text,
	accountID integer references tbl_Accounts
); 