insert into member (email, name, password, created_date_time, updated_date_time)
values ('member1@gmail.com', '홍길동', 'test', now(), now()),
       ('member2@gmail.com', '고길동', 'test', now(), now()),
       ('member3@gmail.com', '홍두깨', 'test', now(), now());



insert into store (name, address, created_date_time, updated_date_time)
values ('잠실점', '서울시 송파구', now(), now()),
       ('일산점', '경기도 고양시', now(), now()),
       ('판교점', '경기도 성남시', now(), now());


INSERT INTO lesson (store_seq, name, limit_participant, start_date, end_date, start_time, end_time, created_date_time,
                    updated_date_time)
VALUES (1, '도시 농부', 20, '2023-12-24', '2023-12-24', '15:00:00', '15:50:00', NOW(), NOW()),
       (1, '드로잉', 20, '2023-12-24', '2023-12-24', '16:00:00', '16:50:00', NOW(), NOW()),
       (1, '오감 놀이', 20, '2023-12-24', '2023-12-24', '15:00:00', '15:50:00', NOW(), NOW()),
       (2, '도시 농부', 20, '2023-12-24', '2023-12-24', '16:00:00', '16:50:00', NOW(), NOW()),
       (2, '드로잉', 20, '2023-12-24', '2023-12-24', '15:00:00', '15:50:00', NOW(), NOW()),
       (3, '오감 놀이', 20, '2023-12-24', '2023-12-24', '15:00:00', '15:50:00', NOW(), NOW());



insert into reservation (member_seq, lesson_seq, reservation_status, participant, created_date_time, updated_date_time)
values (1, 1, 'RESERVATION', 2, '2023-12-01 15:00:00', '2023-12-01 15:00:00'),
       (1, 2, 'RESERVATION', 2, '2023-12-01 15:30:00', '2023-12-01 15:30:00'),
       (2, 1, 'RESERVATION', 1, '2023-12-02 11:00:00', '2023-12-02 11:00:00'),
       (2, 2, 'RESERVATION', 1, '2023-12-02 15:00:00', '2023-12-02 15:00:00'),
       (3, 1, 'RESERVATION', 3, '2023-12-01 09:00:00', '2023-12-01 09:00:00'),
       (3, 2, 'RESERVATION', 3, '2023-12-01 15:00:00', '2023-12-01 15:00:00');

