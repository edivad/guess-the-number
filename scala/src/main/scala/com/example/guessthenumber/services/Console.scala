package com.example.guessthenumber.services

trait Console[F[_]] {
  def printLine(s: String): F[Unit]
  def readLine(): F[String]
}
