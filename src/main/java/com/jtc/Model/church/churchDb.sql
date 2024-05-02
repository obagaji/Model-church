
DROP TABLE IF EXISTS Member;
DROP TABLE IF EXISTS workers;
DROP TABLE IF EXISTS workers_attendance;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS nonworker;
DROP TABLE IF EXISTS maintenance;
DROP TABLE IF EXISTS attendance;

CREATE TABLE  Member
(
    id varchar(100),
    sex varchar(100),
    last_name varchar(100),
    first_name varchar(100),
    address varchar(255)NOT NULL,
    date_born varchar(100),
    phone varchar(100)NOT NULL,
    marry varchar(100),
    email_address varchar(100),
    attendance INT NOT NULL,
    login_date varchar(100) NOT NULL,
    in_church varchar(100),
    resent varchar(100),
    PRIMARY KEY(id)
);
CREATE TABLE  workers
(
    worker_Id varchar (100) NOT NULL,
    worker_depart varchar (100)NOT NULL,
    worker_position varchar(100),
    join_date varchar (100) NOT NULL
);
CREATE TABLE workers_attendance
(
    service_name varchar (100) NOT NULL,
    service_date DATE NOT NULL,
    workers_times TIME NOT NULL,
    attendance_id varchar(100) NOT NULL
);
CREATE TABLE  department
(
    depart_name varchar(100) NOT NULL,
    depart_total INT NOT NULL,
    depart_head varchar (100)
);
CREATE TABLE  nonworker
(
    nonWorkerId varchar(10),
    join_date varchar not null
);
CREATE TABLE  maintenance
(
    work_number INT NOT NULL,
    work_name varchar(100) NOT NULL,
    description varchar(255),
    activity_startdate DATE NOT NULL,
    completion_date DATE,
    cost DOUBLE NOT NULL,
    supervisorId varchar(100) NOT NULL,
    status varchar(100) NOT NULL,
    remark varchar(255)
);
CREATE TABLE  first_timer
(
    serial_number INT NOT NULL,
    id varchar(255) NOT NULL
);
CREATE TABLE  attendance
(
    service_date DATE NOT NULL,
    minister varchar,
    attendance_number INT NOT NULL,
    church_service varchar(255) NOT NULL,
    attendance_female INT,
    attendance_male INT,
    attendance_children INT
);

INSERT INTO Member
(id, sex,last_name ,first_name ,address,date_born,phone,marry,email_address,attendance,login_date,in_church ,resent)
VALUES("nbv1000","MALE","HGJB","BSFMS","DFAA","AFA","FGEEA","ETHTEHYER","  QE  QE","1","dddd","awfw","efrf");