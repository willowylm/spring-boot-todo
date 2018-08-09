create table todo(
	`id` int primary key auto_increment,
  `value` varchar(255),
  `is_complete` boolean default false,
  `date` timestamp default now(),
  `user_id` int,
  `deleted` int default 0
);