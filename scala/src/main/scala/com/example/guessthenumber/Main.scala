package com.example.guessthenumber

import cats.Id
import com.example.guessthenumber.programs.Program
import com.example.guessthenumber.services.{Console, Random}

import scala.annotation.tailrec
import scala.io.StdIn

object Main {
  val random: Random[Id] = Random.make[Id]

  val console: Console[Id] = new Console[Id] {
    def printLine(s: String): Id[Unit] = println(s)
    def readLine(): Id[String]         = StdIn.readLine()
  }

  def main(args: Array[String]): Unit = {
    new Program(random, console).run()
  }
}
