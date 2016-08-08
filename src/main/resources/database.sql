create table deal(
id int not null AUTO_INCREMENT,
title varchar(50) not null,
start_date datetime,
end_date datetime,
content text,
image varchar(128),
thum1 varchar(128),
thum2 varchar(128),
PRIMARY KEY (id)
);