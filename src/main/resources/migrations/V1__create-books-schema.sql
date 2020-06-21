create table book (
   isbn varchar(255) not null primary key,
   name varchar(255) not null,
   constraint id unique (isbn),
   constraint name unique (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
