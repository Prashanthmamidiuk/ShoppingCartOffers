package org.hmrc

class ShoppingCart {
  def checkout(shoppingList: List[String]) : String = {
    val totalCost = shoppingList.map(getPrice).sum.toDouble
    val cheapestOffer = cheapestFreeOffer(shoppingList)
    val offers = cheapestOffer + offerPriceFor(shoppingList, "Orange", 3)

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

  private def offerPriceFor(shoppingList: List[String], fruitType: String, offer: Int) = {
    val fruitCount = shoppingList.count(_ == fruitType)
    (fruitCount / offer) * getPrice(fruitType)
  }

  private def cheapestFreeOffer(shoppingList: List[String]) = {
    val sortedBasket = shoppingList
        .filter(fruit => fruit == "Apple" || fruit == "Banana")
        .sortBy(getPrice)

    sortedBasket.take(sortedBasket.size/2).map(getPrice).sum
  }

}
