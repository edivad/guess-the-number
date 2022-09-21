package com.example.guessthenumber.programs

import cats.implicits.{toFlatMapOps, toFunctorOps}
import cats.syntax.{flatMap, functor}
import cats.{Applicative, Id, Monad}
import com.example.guessthenumber.services.{Console, Random}

import scala.annotation.tailrec

class Program[F[_]: Monad](random: Random[F], console: Console[F]) {
  import Result.*

  enum Result:
    case InvalidInput, Guessed, TooHigh, TooLow

  def run(): F[Unit] = for {
    toBeGuessed <- random.nextInt(100)
    _           <- loop(toBeGuessed)
  } yield ()

  private def loop(toBeGuessed: Int): F[Unit] = for {
    input   <- console.readLine()
    result  <- check(toBeGuessed, input)
    message <- messageFor(result)
    _       <- console.printLine(message)
    _ <- result match {
      case Guessed => exit
      case _       => loop(toBeGuessed)
    }
  } yield ()

  private val exit = Applicative[F].pure(())

  private def check(toBeGuessed: Int, input: String): F[Result] = {
    val result: Result = input.toIntOption
      .map(_.compare(toBeGuessed))
      .fold(InvalidInput) {
        case 0  => Guessed
        case 1  => TooHigh
        case -1 => TooLow
      }
    Applicative[F].pure(result)
  }

  private def messageFor(result: Result): F[String] = {
    val str = result match
      case InvalidInput => "not a number"
      case Guessed      => "Guessed"
      case TooHigh      => "Too high"
      case TooLow       => "Too low"
    Applicative[F].pure(str)
  }
}
