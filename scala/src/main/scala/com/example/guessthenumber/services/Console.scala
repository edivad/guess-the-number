package com.example.guessthenumber.services

trait Console {
  def printLine(s: String): Unit
  def readLine(): String
}
