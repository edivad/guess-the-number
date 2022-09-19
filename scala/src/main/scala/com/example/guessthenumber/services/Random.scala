package com.example.guessthenumber.services

import cats.Applicative

trait Random[F[_]] {
  def nextInt(n: Int): F[Int]
}

object Random {
  def make[F[_]: Applicative]: Random[F] = new Random[F] {
    def nextInt(n: Int): F[Int] = Applicative[F].pure(util.Random.nextInt(n))
  }
}
