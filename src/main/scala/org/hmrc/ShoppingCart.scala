package org.hmrc

class ShoppingCart {
  def checkout(shoppingList: List[String]) : String = {
    val totalCost = shoppingList.map(getPrice).sum.toDouble
    val offers = offerPriceFor(shoppingList, "Apple", 2) +
      offerPriceFor(shoppingList, "Orange", 3)

    s"£${(totalCost - offers) / 100}"
  }

  private def getPrice(item: String) = {
    item match {
      case "Apple" => 60
      case "Orange" => 25
      case item => throw new IllegalArgumentException(s"Item ${item} is invalid")
    }
  }

  private def offerPriceFor(shoppingList: List[String], fruitType: String, offer: Int) = {
    val fruitCount = shoppingList.count(_ == fruitType)
    (fruitCount / offer) * getPrice(fruitType)
  }

}
