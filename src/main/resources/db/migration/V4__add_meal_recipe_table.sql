create table recipes
(
    recipe_id int primary key auto_increment,
    grams float,
    meal_id int,
    food_id int
);

create table meals
(
    meal_id int primary key auto_increment,
    name varchar(100) not null
)