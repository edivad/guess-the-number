package test

import cats.Id
import com.example.guessthenumber.programs.Program
import com.example.guessthenumber.services.{Console, Random}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike

import scala.collection.mutable.ListBuffer

class ProgramSpec extends AnyWordSpecLike with Matchers {
  import ProgramSpec.*

  "Guess the Number" when {
    "input is equal" should {
      "print a message" in {
        val in  = ListBuffer("42")
        val out = ListBuffer.empty[String]
        new Program(fixed(42), buffers(in, out)).run()
        out should be(ListBuffer("Guessed"))
      }
    }
    "input is higher" should {
      "print a message" in {
        val in  = ListBuffer("67", "42")
        val out = ListBuffer.empty[String]
        new Program(fixed(42), buffers(in, out)).run()
        out should be(ListBuffer("Too high", "Guessed"))
      }
    }
    "input is lower" should {
      "print a message" in {
        val in  = ListBuffer("27", "42")
        val out = ListBuffer.empty[String]
        new Program(fixed(42), buffers(in, out)).run()
        out should be(ListBuffer("Too low", "Guessed"))
      }
    }
    "input is not a number" should {
      "print a message" in {
        val in  = ListBuffer("not a number", "42")
        val out = ListBuffer.empty[String]
        new Program(fixed(42), buffers(in, out)).run()
        out should be(ListBuffer("not a number", "Guessed"))
      }
    }
  }
}

object ProgramSpec {
  def fixed(value: Int): Random = new Random {
    override def nextInt(n: Int): Int = value
  }

  def buffers(in: ListBuffer[String], out: ListBuffer[String]): Console[Id] = new Console[Id] {
    override def readLine(): Id[String]         = in.remove(0)
    override def printLine(s: String): Id[Unit] = out.addOne(s); ()
  }
}
