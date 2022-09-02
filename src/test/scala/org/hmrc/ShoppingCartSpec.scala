package org.hmrc

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ShoppingCartSpec extends AnyFlatSpec with Matchers {

  val shoppingCart = new ShoppingCart

  "shopping cart" should "calculate the price of items in the basket" in {
    val result = shoppingCart.checkout(List("Apple", "Apple", "Orange", "Apple"))

    result shouldEqual "£1.45"
  }

  it should "calculate the price of basket, given basket has only Apple" in {
    val result = shoppingCart.checkout(List("Apple"))

    result shouldEqual "£0.6"
  }

  it should "calculate the price of basket, given basket has only Orange" in {
    val result = shoppingCart.checkout(List("Orange"))

    result shouldEqual "£0.25"
  }

  it should "return 0 if basket is empty" in {
    val result = shoppingCart.checkout(List())

    result shouldEqual "£0.0"
  }

  it should "calculate the price of basket, given basket has only Banana" in {
    val result = shoppingCart.checkout(List("Banana"))

    result shouldEqual "£0.2"
  }

  it should "throw exception if basket has invalid item" in {
    val exception = intercept[IllegalArgumentException] {
      shoppingCart.checkout(List("Apple", "Pears", "Orange"))
    }
    exception.getMessage shouldBe "Item Pears is invalid"
  }

  "shopping cart with offers" should "apply buy one get one offer, given basket has 2 Apples" in {
    val result = shoppingCart.checkout(List("Apple", "Apple", "Orange"))

    result shouldEqual "£0.85"
  }

  it should "apply buy one get one offer, given basket has 5 Apples" in {
    val result = shoppingCart.checkout(List("Apple", "Apple", "Apple", "Apple", "Apple", "Orange"))

    result shouldEqual "£2.05"
  }

  it should "apply buy one get one offer, given basket has 5 Bananas" in {
    val result = shoppingCart.checkout(List("Banana", "Banana", "Banana", "Banana", "Banana", "Orange"))

    result shouldEqual "£0.85"
  }

  it should "apply 3 for 2 offer, given basket has 3 oranges" in {
    val result = shoppingCart.checkout(List("Apple", "Orange", "Orange", "Orange"))

    result shouldEqual "£1.1"
  }

  it should "apply 3 for 2 offer, given basket has 5 oranges" in {
    val result = shoppingCart.checkout(List("Apple", "Orange", "Orange", "Orange", "Orange", "Orange"))

    result shouldEqual "£1.6"
  }

  it should "apply buy one get one offer and 3 for 2 offer, given basket has 5 apples and 4 oranges" in {
    val result = shoppingCart.checkout(List("Apple", "Apple", "Apple", "Apple", "Apple", "Orange", "Orange", "Orange", "Orange"))

    result shouldEqual "£2.55"
  }

  it should "apply cheapest one is free offer, given basket has 2 apples and 1 banana" in {
    val result = shoppingCart.checkout(List("Apple", "Apple", "Banana"))

  result shouldEqual "£1.2"
}

  it should "apply cheapest one is free offer, given basket has 5 apples and 4 banana" in {
    val result = shoppingCart.checkout(List("Apple", "Apple", "Apple", "Apple", "Apple", "Banana", "Banana", "Banana", "Banana"))

    result shouldEqual "£3.0"
  }

  it should "apply cheapest one is free offer, given basket has 1 apple and 4 bananas" in {
    val result = shoppingCart.checkout(List("Apple", "Banana", "Banana", "Banana", "Banana"))

    result shouldEqual "£1.0"
  }

  it should "apply cheapest one is free offer, given basket has 5 apple, 5 Oranges and 3 bananas" in {
    val result = shoppingCart.checkout(List("Apple", "Apple", "Apple", "Apple", "Apple", "Orange", "Orange", "Orange", "Orange", "Orange", "Banana", "Banana", "Banana"))

    result shouldEqual "£3.4"
  }
}
