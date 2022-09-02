package org.hmrc

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ShoppingCartSpec extends AnyFlatSpec with Matchers {

  val shoppingCart = new ShoppingCart

  "shopping cart" should "calculate the price of items in the basket" in {
    val result = shoppingCart.compute(List("Apple", "Apple", "Orange", "Apple"))

    result shouldEqual "£2.05"
  }

  it should "calculate the price of basket, given basket has only Apple" in {
    val result = shoppingCart.compute(List("Apple"))

    result shouldEqual "£0.6"
  }

  it should "calculate the price of basket, given basket has only Orange" in {
    val result = shoppingCart.compute(List("Orange"))

    result shouldEqual "£0.25"
  }

  it should "return 0 if basket is empty" in {
    val result = shoppingCart.compute(List())

    result shouldEqual "£0.0"
  }

  it should "throw exception if basket has invalid item" in {
    val exception = intercept[IllegalArgumentException] {
      shoppingCart.compute(List("Apple", "Banana", "Orange"))
    }
    exception.getMessage shouldBe "Item Banana is invalid"
  }

}
