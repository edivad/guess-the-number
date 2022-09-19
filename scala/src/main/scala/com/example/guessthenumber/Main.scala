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
      guessed = maybeNumber.fold({
        console.printLine("not a number")
        false
      })(number => {
        if (number == toBeGuessed) {
          console.printLine("Guessed")
          true
        } else if (number > toBeGuessed) {
          console.printLine("Too high")
          false
        } else {
          console.printLine("Too low")
          false
        }
      })
    }
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    new Program(new Random, new StdConsole).run()
  }
}
