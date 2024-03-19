package com.example.culinarycrafter.models.enums

enum class CategoryEnum(val value: String, var isChecked: Boolean = false) {

    Soup("Суп"),
    Garnish("Гарнир"),
    MeatDishes("Мясные блюда"),
    Salads("Салаты"),
    Snacks("Закуски"),
    Other("Другие");

}