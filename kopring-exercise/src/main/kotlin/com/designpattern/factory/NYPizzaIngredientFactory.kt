package com.designpattern.factory

class NYPizzaIngredientFactory : PizzaIngredientFactory {
    override fun createDough(): Dough = ThinCrustDough()
    override fun createSauce(): Sauce = MarinaraSauce()
    override fun createCheese(): Cheese = ReggianoCheese()
    override fun createVeggies(): List<Veggies> = mutableListOf(Garlic(), Onion(), Mushroom())
    override fun createPepperoni(): Pepperoni = SlicedPepperoni()
    override fun createClam(): Clams = FreshClams()

}