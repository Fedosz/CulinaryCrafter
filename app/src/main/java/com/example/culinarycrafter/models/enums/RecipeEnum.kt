package com.example.culinarycrafter.models.enums

import com.example.culinarycrafter.R

enum class RecipeEnum(
    val id: String,
    val value: String,
    val category: CategoryEnum,
    val description: String,
    val process: String?,
    val photo: Int,
    val ingredients: List<IngredientEnum>
) {

    CHICKEN_WITH_VEGETABLES(
        "ChickenWithVegetables",
        "Курица с овощами",
        CategoryEnum.MeatDishes,
        "Нежные кусочки куриного мяса, обжаренные с сочными овощами на сковороде до золотистой корочки, подается с ароматным соусом.",
        "Нарежьте куриные кусочки и обжарьте их на сковороде до золотистой корочки.\n" +
                "Добавьте нарезанные овощи (морковь, лук, перец, грибы) и тушите вместе с курицей.\n" +
                "Подсолите и добавьте специи по вкусу (перец, паприка, базилик).\n" +
                "Подавайте курицу с овощами гарниром из отварного картофеля или риса.",
        R.drawable.chicken_with_vegetables,
        listOf(
            IngredientEnum.Chicken,
            IngredientEnum.Onion,
            IngredientEnum.Carrot,
            IngredientEnum.Peppers,
            IngredientEnum.VegetableOil,
            IngredientEnum.Salt,
            IngredientEnum.Pepper
        )
    ),
    PASTA_WITH_TOMATO_SAUCE(
        "PastaWithTomatoSauce",
        "Паста с томатным соусом",
        CategoryEnum.Garnish,
        "Ароматная паста, приправленная насыщенным томатным соусом и посыпанная тертым сыром. Простое, но вкусное итальянское блюдо.",
        "Сварите пасту в подсоленной воде до готовности.\n" +
                "Подготовьте томатный соус, обжаривая лук и чеснок, добавляя томатную пасту и специи (базилик, орегано, соль, перец).\n" +
                "Перемешайте пасту с томатным соусом и подавайте горячей, посыпав тертым сыром.",
        R.drawable.pasta_with_tomato_sauce,
        listOf(
            IngredientEnum.Pasta,
            IngredientEnum.Tomatoes,
            IngredientEnum.Garlic,
            IngredientEnum.Onion,
            IngredientEnum.VegetableOil,
            IngredientEnum.Salt,
            IngredientEnum.Pepper
        )
    ),
    FISH_SUP(
        "FishSup",
        "Рыбный суп",
        CategoryEnum.Soup,
        "Нежный бульон, в котором купается сочный кусочек рыбы, с добавлением овощей и зелени. Питательное и легкое блюдо.",
        "Варите рыбный бульон из костей рыбы, лука, моркови и сельдерея.\n" +
                "Добавьте к бульону нарезанные морковь, картофель, лук, красный перец и кусочки рыбы.\n" +
                "Варите суп до готовности овощей и рыбы, подсолите и добавьте зелень по вкусу.",
        R.drawable.fish_sup,
        listOf(
            IngredientEnum.Fish,
            IngredientEnum.Onion,
            IngredientEnum.Carrot,
            IngredientEnum.Salt,
            IngredientEnum.Pepper,
            IngredientEnum.Dill,
            IngredientEnum.Lemon
        )
    ),
    BEEF_IN_FRENCH(
        "BeefInFrench",
        "Говядина по-французски",
        CategoryEnum.MeatDishes,
        "Тонко нарезанное говяжье мясо, тушенное в сочном соусе с луком, морковью и специями, создающее неповторимый французский вкус.",
        "Обжарьте говядину на сковороде до образования корки.\n" +
                "Приготовьте соус из лука, моркови, вина, бульона, томатной пасты и специй (перец, лавровый лист).\n" +
                "Подавайте говядину, полив соусом и посыпав зеленью.",
        R.drawable.beef_in_french,
        listOf(
            IngredientEnum.Beef,
            IngredientEnum.Onion,
            IngredientEnum.Carrot,
            IngredientEnum.VegetableOil,
            IngredientEnum.Salt,
            IngredientEnum.Pepper
        )
    ),
    POTATO_SOUP_WITH_CHEESE(
        "PotatoSoupWithCheese",
        "Картофельный суп с сыром",
        CategoryEnum.Soup,
        "Нежный картофельный пюре, приправленный сыром и сливками, с добавлением хрустящих картофельных кусочков сверху.",
        "Варите картофельные кубики в бульоне с луком и сельдереем до мягкости.\n" +
                "Добавьте молоко, сыр и тертый чеснок, перемешайте до растворения сыра.\n" +
                "Подавайте суп горячим, посыпав сверху зеленью.",
        R.drawable.potato_soup_with_cheese,
        listOf(
            IngredientEnum.Potato,
            IngredientEnum.Onion,
            IngredientEnum.Salt,
            IngredientEnum.Pepper,
            IngredientEnum.Milk,
            IngredientEnum.Cheese
        )
    ),
    OLIVIER(
        "Olivier",
        "Оливье",
        CategoryEnum.Salads,
        "Классический русский салат, состоящий из отварного картофеля, моркови, огурцов, горошка, маринованных огурцов, вареного яйца, майонеза и зелени.",
        "Нарежьте отварную картошку, морковь, огурцы, горошек, яйца и ветчину.\n" +
                "Заправьте майонезом, добавьте соль и перец по вкусу.\n" +
                "Подавайте салат охлажденным, посыпав зеленью.",
        R.drawable.olivier,
        listOf(
            IngredientEnum.Potato,
            IngredientEnum.Carrot,
            IngredientEnum.Cucumbers,
            IngredientEnum.Pea,
            IngredientEnum.VegetableOil,
            IngredientEnum.SourCream,
            IngredientEnum.Eggs
        )
    ),
    RICE_WITH_VEGETABLES(
        "RiceWithVegetables",
        "Рис с овощами",
        CategoryEnum.Garnish,
        "Ароматный вареный рис, смешанный с тушеными овощами, приправленный специями и зеленью.",
        "Обжарьте лук и морковь на сковороде, добавьте нарезанные овощи (перец, горошек, кукурузу).\n" +
                "Добавьте отварной рис и специи (куркума, кинза, соль, перец).\n" +
                "Перемешайте все ингредиенты и подавайте горячим.",
        R.drawable.rice_with_vegetables,
        listOf(
            IngredientEnum.Rice,
            IngredientEnum.Onion,
            IngredientEnum.Carrot,
            IngredientEnum.Peppers,
            IngredientEnum.VegetableOil,
            IngredientEnum.Salt,
            IngredientEnum.Pepper
        )
    ),
    PORK_WITH_ORANGES(
        "PorkWithOranges",
        "Свинина с апельсинами",
        CategoryEnum.MeatDishes,
        "Нежное мясо свинины, запеченное с апельсиновым соусом, добавляющим блюду оригинальный цитрусовый вкус.",
        "В средней миске смешайте соус, оливковое масло, чеснок, соль и перец.\n" +
                "Выложите кусочки свинины в большую мискy, залейте смесью и оставьте мариноваться примерно 30 минут.\n" +
                "Обжарьте свинину на сковороде до золотистой корочки, добавьте апельсиновый сок и тушите до готовности.\n" +
                "Подавайте, посыпав апельсиновой цедрой.",
        R.drawable.pork_with_oranges,
        listOf(
            IngredientEnum.Pork,
            IngredientEnum.Oranges,
            IngredientEnum.Onion,
            IngredientEnum.Salt,
            IngredientEnum.Pepper,
            IngredientEnum.VegetableOil
        )
    ),
    SPINACH_WITH_EGG(
        "SpinachWithEgg",
        "Шпинатный суп с яйцом",
        CategoryEnum.Soup,
        "Деликатесный суп из свежего шпината с добавлением яиц, который придает блюду богатый вкус и питательные свойства.",
        "Нарежьте лук и чеснок, обжарьте на сковороде до золотистого цвета.\n" +
                "В кастрюлю положите шпинат и бульон, доведите до кипения, уменьшите огонь и варите 10 минут.\n" +
                "Разбейте яйца в чашку, аккуратно влейте в суп и тушите до готовности яиц.\n" +
                "Посолите и поперчите по вкусу.",
        R.drawable.spinach_with_egg,
        listOf(
            IngredientEnum.Spinach,
            IngredientEnum.Eggs,
            IngredientEnum.Onion,
            IngredientEnum.Lemon,
            IngredientEnum.Salt,
            IngredientEnum.Pepper
        )
    ),
    MEAT_PIE(
        "MeatPie",
        "Мясной пирог",
        CategoryEnum.MeatDishes,
        "Пирог с нежным мясным фаршем, обернутый хрустящим слоеным тестом.",
        "Для начала приготовьте тесто из муки, яиц, соли и воды.\n" +
                "\n" +
                "Затем обжарьте мелко нарезанный лук с фаршем до готовности и добавьте специи по вкусу.\n" +
                "\n" +
                "Раскатайте половину теста и уложите его в форму.\n" +
                "\n" +
                "На тесто выложите начинку из фарша, затем накройте второй половиной раскатанного теста.\n" +
                "\n" +
                "Смажьте верх теста яйцом и отправьте пирог в духовку до готовности.",
        R.drawable.meat_pie,
        listOf(
            IngredientEnum.Beef,
            IngredientEnum.Pork,
            IngredientEnum.Onion,
            IngredientEnum.Flour,
            IngredientEnum.Salt,
            IngredientEnum.Pepper,
            IngredientEnum.VegetableOil
        )
    ),
    STEWED_VEGETABLES_WITH_MUSHROOMS(
        "StewedVegetablesWithMushrooms",
        "Тушеные овощи с грибами",
        CategoryEnum.Garnish,
        "Медленно тушеные овощи - морковь, лук, грибы, баклажаны и тыква, приправленные специями и зеленью.",
        "Нарежьте морковь, лук, болгарский перец и грибы.\n" +
                "\n" +
                "Обжарьте лук с морковью, затем добавьте нарезанный перец и грибы.\n" +
                "\n" +
                "Посолите, поперчите, добавьте зелень и тушите под крышкой до мягкости овощей.",
        R.drawable.stewed_vegetables_with_mushrooms,
        listOf(
            IngredientEnum.Onion,
            IngredientEnum.Carrot,
            IngredientEnum.Mushrooms,
            IngredientEnum.VegetableOil,
            IngredientEnum.Salt,
            IngredientEnum.Pepper,
            IngredientEnum.Punch
        )
    ),
    STUFFED_WITH_PEPPERCORNS(
        "StuffedWithPeppercorns",
        "Фаршированные перцы с сыром",
        CategoryEnum.Snacks,
        "Сладкие перцы, наполненные ароматным фаршем, посыпанные тертым сыром и запеченные в духовке.",
        "Вырежьте семена из перцев и начините их фаршем из мяса и риса.\n" +
                "\n" +
                "Поставьте нарезанный сыр сверху и запеките в духовке до готовности.",
        R.drawable.stuffed_with_peppercorns,
        listOf(
            IngredientEnum.Peppers,
            IngredientEnum.Cheese,
            IngredientEnum.Rice,
            IngredientEnum.Onion,
            IngredientEnum.VegetableOil,
            IngredientEnum.Salt,
            IngredientEnum.Pepper
        )
    ),
    SALAD_WITH_CUCUMBERS_AND_TOPPINGS(
        "SaladWithCucumbersAndToppings",
        "Салат с огурцами и оливками",
        CategoryEnum.Salads,
        "Освежающий салат с хрустящими огурцами, сочными помидорами, ароматными оливками, заправленный оливковым маслом.",
        "Нарежьте огурцы, помидоры, зелень, добавьте кубики сыра и оливки.\n" +
                "\n" +
                "Заправьте салат оливковым маслом, лимонным соком, солью и перцем.",
        R.drawable.salad_with_cucumbers_and_toppings,
        listOf(
            IngredientEnum.Cucumbers,
            IngredientEnum.Olives,
            IngredientEnum.Onion,
            IngredientEnum.VegetableOil,
            IngredientEnum.Salt,
            IngredientEnum.Pepper,
            IngredientEnum.Dill
        )
    ),
    BAKED_TOMATOES_WITH_GARLIC(
        "BakedTomatoesWithGarlic",
        "Печеные томаты с чесноком",
        CategoryEnum.Snacks,
        "Сладкие помидоры, запеченные в духовке с ароматным чесноком и травами, получают неповторимый вкус и аромат.",
        "Нарежьте томаты пополам, выложите на противень.\n" +
                "\n" +
                "Посыпьте мелко нарезанным чесноком, зеленью, посыпьте солью и перцем.\n" +
                "\n" +
                "Запекайте в духовке до мягкости томатов.",
        R.drawable.baked_tomatoes_with_garlic,
        listOf(
            IngredientEnum.Tomatoes,
            IngredientEnum.Garlic,
            IngredientEnum.VegetableOil,
            IngredientEnum.Salt,
            IngredientEnum.Pepper,
            IngredientEnum.Punch
        )
    ),
    FISH_PLOW(
        "FishPlow",
        "Рыбный плов",
        CategoryEnum.Other,
        "Блюдо из рыбы и риса, тушенное с овощами и специями, обладающее насыщенным вкусом и ароматом.",
        "Обжарьте лук и морковь, затем добавьте рыбу (лучше всего кусочки свежей форели или лосося).\n" +
                "\n" +
                "Поджарьте немного риса, затем добавьте к нему куркуму, зиру и рыбу с овощами.\n" +
                "\n" +
                "Залейте плов кипятком, доведите до кипения и тушите под крышкой до готовности риса.",
        R.drawable.fish_plow,
        listOf(
            IngredientEnum.Fish,
            IngredientEnum.Rice,
            IngredientEnum.Onion,
            IngredientEnum.Carrot,
            IngredientEnum.Salt,
            IngredientEnum.Pepper,
            IngredientEnum.Dill
        )
    );


}