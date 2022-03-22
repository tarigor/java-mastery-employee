create table employee
(
    employee_id   serial       not null
        constraint employee_pkey
            primary key,
    first_name    varchar(100) not null,
    last_name     varchar(100) not null,
    department_id serial       not null,
    job_title     varchar(100) not null,
    gender        varchar(10)  not null,
    date_of_birth date         not null
);

INSERT INTO public.employee (employee_id, first_name, last_name, department_id, job_title, gender, date_of_birth)
VALUES (1, 'Vasya', 'Pupkin', 1, 'programmer', 'male', '2013-03-07');
INSERT INTO public.employee (employee_id, first_name, last_name, department_id, job_title, gender, date_of_birth)
VALUES (2, 'Dyadya', 'Stepa', 2, 'tester', 'male', '1995-07-20');
INSERT INTO public.employee (employee_id, first_name, last_name, department_id, job_title, gender, date_of_birth)
VALUES (3, 'Galina', 'Pugovkina', 3, 'resource manager', 'female', '1999-09-01');