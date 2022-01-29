alter table recipes
add foreign key (meal_id)
references meals (meal_id);

alter table recipes
    add foreign key (food_id)
    references foods (food_id);