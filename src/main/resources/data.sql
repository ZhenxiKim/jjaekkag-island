insert into member (email, name, password, created_date_time, updated_date_time)
values ('memer1@gmail.com', 'member1', 'test', now(), now()),
       ('memer2@gmail.com', 'member2', 'test', now(), now()),
       ('memer3@gmail.com', 'member3', 'test', now(), now());



insert into store (name, address, created_date_time, updated_date_time)
values ('잠실점', '서울시 송파구', now(), now()),
       ('일산점', '경기도 고양시', now(), now()),
       ('판교점', '경기도 성남시', now(), now());


INSERT INTO lesson (store_seq, name, limit_participant, start_time, end_time, created_date_time, updated_date_time)
VALUES
    (1, '도시 농부', 20, '2023-12-24 15:00:00', '2023-12-24 15:00:00' + INTERVAL 50 MINUTE, NOW(), NOW()),
    (1, '드로잉', 20, '2023-12-24 16:00:00', '2023-12-24 16:00:00' + INTERVAL 50 MINUTE, NOW(), NOW()),
    (1, '오감 놀이', 20, '2023-12-24 15:00:00', '2023-12-24 15:00:00' + INTERVAL 50 MINUTE, NOW(), NOW()),
    (2, '도시 농부', 20, '2023-12-24 16:00:00', '2023-12-24 16:00:00' + INTERVAL 50 MINUTE, NOW(), NOW()),
    (2, '드로잉', 20, '2023-12-24 15:00:00', '2023-12-24 15:00:00' + INTERVAL 50 MINUTE, NOW(), NOW()),
    (3, '오감 놀이', 20, '2023-12-24 15:00:00', '2023-12-24 15:00:00' + INTERVAL 50 MINUTE, NOW(), NOW());



insert into reservation (member_seq, lesson_seq, reservation_status, participant, created_date_time, updated_date_time)
values (1, 1, 'SUCCESS', 2, now(), now()),
       (2, 1, 'SUCCESS', 1, now(), now()),
       (3, 1, 'SUCCESS', 3, now(), now());
