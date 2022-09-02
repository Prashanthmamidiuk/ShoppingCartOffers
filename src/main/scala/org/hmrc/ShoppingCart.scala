package org.hmrc

class ShoppingCart {
  def compute(shoppingList: List[String]) : String = {
    val totalCost = shoppingList.map {
      case "Apple" => 60
      case "Orange" => 25
      case item => throw new IllegalArgumentException(s"Item ${item} is invalid")
    }.sum.toDouble

    s"£${totalCost/100}"
  }

}
