package com.example.guessthenumber

import scala.io.StdIn

class Random {
  def nextInt(n: Int): Int = util.Random.nextInt(n)
}

trait Console {
  def printLine(s: String): Unit
  def readLine(): String
}
class StdConsole extends Console {
  def printLine(s: String): Unit = println(s)
  def readLine(): String         = StdIn.readLine()
}

class Program(random: Random, console: Console) {
  def run(): Unit = {
    val toBeGuessed = random.nextInt(100)
    var guessed     = false
    while (!guessed) {
      val input       = console.readLine()
      val maybeNumber = input.toIntOption
      val out = maybeNumber.fold("not a number") { number =>
        if (number == toBeGuessed) "Guessed"
        else if (number > toBeGuessed) "Too high"
        else "Too low"
      }
      console.printLine(out)
      guessed = maybeNumber.fold(false) { number =>
        if (number == toBeGuessed) true
        else if (number > toBeGuessed) false
        else false
      }
    }
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    new Program(new Random, new StdConsole).run()
  }
}
