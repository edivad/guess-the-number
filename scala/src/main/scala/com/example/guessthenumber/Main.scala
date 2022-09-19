package com.example.guessthenumber

import scala.annotation.tailrec
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

  sealed trait Result
  case object InvalidInput extends Result
  case object Guessed      extends Result
  case object TooHigh      extends Result
  case object TooLow       extends Result

  def run(): Unit = {
    loop(random.nextInt(100))
  }

  @tailrec
  private def loop(toBeGuessed: Int): Unit = {
    val input       = console.readLine()
    val maybeNumber = input.toIntOption
    val result = maybeNumber.fold(InvalidInput.asInstanceOf[Result]) { number =>
      if (number == toBeGuessed) Guessed
      else if (number > toBeGuessed) TooHigh
      else TooLow
    }
    val out = result match
      case InvalidInput => "not a number"
      case Guessed      => "Guessed"
      case TooHigh      => "Too high"
      case TooLow       => "Too low"
    console.printLine(out)
    result match
      case Guessed => ()
      case _       => loop(toBeGuessed)
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    new Program(new Random, new StdConsole).run()
  }
}
