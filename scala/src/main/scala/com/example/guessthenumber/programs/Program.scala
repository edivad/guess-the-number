package com.example.guessthenumber.programs

import cats.Id
import com.example.guessthenumber.services.{Console, Random}

import scala.annotation.tailrec

class Program(random: Random[Id], console: Console[Id]) {

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
    val input  = console.readLine()
    val result = check(toBeGuessed, input)
    console.printLine(messageFor(result))
    result match
      case Guessed => ()
      case _       => loop(toBeGuessed)
  }

  private def check(toBeGuessed: Int, input: String) =
    input.toIntOption
      .fold(InvalidInput) { number =>
        if (number == toBeGuessed) Guessed
        else if (number > toBeGuessed) TooHigh
        else TooLow
      }

  private def messageFor(result: Result) =
    result match
      case InvalidInput => "not a number"
      case Guessed      => "Guessed"
      case TooHigh      => "Too high"
      case TooLow       => "Too low"
}
