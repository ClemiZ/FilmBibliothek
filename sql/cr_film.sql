create table film (
    f_id bigint auto_increment constraint product_pk primary key,
    f_name varchar(255) not null,
    f_length number(3) not null,
    f_producer varchar(255) not null,
    f_releaseDate Date,
    p_category varchar(20),
    p_available boolean
);