package com.example.guessthenumber

import scala.io.StdIn

object Main {
  def main(args: Array[String]): Unit = {
    val toBeGuessed = util.Random.nextInt(100)
    var guessed     = false
    while (!guessed) {
      val input       = StdIn.readLine()
      val maybeNumber = input.toIntOption
      guessed = maybeNumber.fold({
        println("not a number")
        false
      })(number => {
        if (number == toBeGuessed) {
          println("Guessed")
          true
        } else if (number > toBeGuessed) {
          println("Too high")
          false
        } else {
          println("Too low")
          false
        }
      })
    }
  }
}
