create table stockdetails
(
   id integer not null,
   stockDate date,
   open numeric,
   high numeric,
   low numeric,
   close numeric,
   adjClose numeric,
   volume numeric,
   primary key(id)
);