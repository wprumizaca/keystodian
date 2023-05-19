
insert into users (id, email, password, username) values (default , 'will.01@jwt.io', '$2a$10$w60bMNFwKTWIQSd.ORKY5eBHN6Y2D0Ky1bZiR6pUT0jnxUPjnu0Wu', 'will.01');
insert into users (id, email, password, username) values (default , 'guille.01@jwt.io', '$2a$10$DP9sLMjO.zHg0Yb9wRnZ4.KxdZfWoEVl3Ub0/6fxzwwUpOBqe3usS', 'guille.01');
insert into users (id, email, password, username) values (default , 'raul.01@jwt.io', '$2a$10$XCy7PM.Usz5yvhtanpu.pOyJEbCuZT/HdC3Z4yYUF1laCNHB7FSF.', 'raul.01');


insert into passwords (id,app, password, creation_date) values (1, 'Twitter', 'd8821defebf48a5f4d31b8c57c391d0b', '2023-03-12 20:05:00.000');
insert into passwords (id,app, password, creation_date) values (1, 'Instagram', 'f7d26b13aa97e1f4f61b3274ca1b9fd6', '2012-05-03 10:23:00.000');
insert into passwords (id,app, password, creation_date) values (2, 'TikTok', 'd8821defebf48a5f4d31b8c57c391d0b', '2023-03-12 20:05:00.000');
insert into passwords (id,app, password, creation_date) values (3, 'Gmail', 'd8821defebf48a5f4d31b8c57c391d0b','2012-05-03 10:23:00.000');
insert into passwords (id,app, password, creation_date) values (2, 'Twitter', 'f7d26b13aa97e1f4f61b3274ca1b9fd6','2023-03-12 20:05:00.000');
insert into passwords (id,app, password, creation_date) values (2, 'GitHub', 'd8821defebf48a5f4d31b8c57c391d0b','2012-05-03 10:23:00.000');


--INSERT INTO public.logs (user_id, log_date, logged_ip) VALUES('', CURRENT_TIMESTAMP, inet_client_addr());
