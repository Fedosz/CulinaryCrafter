package com.example.culinarycrafter.models.enums

enum class IngredientEnum(val value: String) {

    Potato("Картофель"),
    Onion("Лук"),
    Carrot("Морковь"),
    Tomatoes("Томаты"),
    Cucumbers("Огурцы"),
    Peppers("Перцы"),
    Beef("Говядина"),
    Chicken("Курица"),
    Pork("Свинина"),
    Fish("Рыба"),
    Rice("Рис"),
    Pasta("Паста"),
    Milk("Молоко"),
    Eggs("Яйца"),
    Flour("Мука"),
    VegetableOil("Масло растительное"),
    Salt("Соль"),
    Pepper("Перец"),
    Dill("Укроп"),
    Punch("Петрушка"),
    Garlic("Чеснок"),
    Lemon("Лимон"),
    SourCream("Сметана"),
    Cheese("Сыр"),
    Olives("Оливки"),
    Spinach("Шпинат"),
    Oranges("Апельсины"),
    Mushrooms("Грибы"),
    Pea("Горошек");

    override fun toString(): String {
        return value
    }


}