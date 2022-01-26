drop table if exists foods;
create table foods(
    id int primary key auto_increment,
    name varchar(50) not null,
    producer varchar(50),
    price float,
    kcal int,
    protein float,
    fat float,
    carbs float
)