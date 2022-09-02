package org.hmrc

class ShoppingCart {
  def checkout(shoppingList: List[String]) : String = {
    val totalCost = shoppingList.map(getPrice).sum.toDouble
    val offers = cheapestFreeOffer(shoppingList) + offerPriceForOrange(shoppingList)

    s"£${(totalCost - offers) / 100}"
  }

  private def getPrice(item: String) = {
    item match {
      case "Apple" => 60
      case "Orange" => 25
      case "Banana" => 20
      case item => throw new IllegalArgumentException(s"Item ${item} is invalid")
    }
  }

  private def offerPriceForOrange(shoppingList: List[String]) = {
    val fruitCount = shoppingList.count(_ == "Orange")
    (fruitCount / 3) * getPrice("Orange")
  }

  private def cheapestFreeOffer(shoppingList: List[String]) = {
    val sortedBasket = shoppingList
        .filter(fruit => fruit == "Apple" || fruit == "Banana")
        .sortBy(getPrice)

    sortedBasket.take(sortedBasket.size/2).map(getPrice).sum
  }

}
