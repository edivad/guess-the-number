package com.example.guessthenumber.programs

import cats.implicits.{toFlatMapOps, toFunctorOps}
import cats.instances.{int, string}
import cats.syntax.{flatMap, functor}
import cats.{Applicative, Id, Monad}
import com.example.guessthenumber.services.{Console, Random}

import scala.annotation.tailrec

class Program[F[_]: Monad](random: Random[F], console: Console[F]) {

  sealed trait Result
  case object InvalidInput extends Result
  case object Guessed      extends Result
  case object TooHigh      extends Result
  case object TooLow       extends Result

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
    val result = input.toIntOption
      .fold(InvalidInput) { number =>
        if (number == toBeGuessed) Guessed
        else if (number > toBeGuessed) TooHigh
        else TooLow
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
