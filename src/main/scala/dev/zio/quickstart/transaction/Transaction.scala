package dev.zio.quickstart.transaction

import java.util.UUID
import zio.json.*

case class Transaction(src: String, dst: String, amount: Int){
  def getSrc: String = {
    src
  }
  def getDst: String = {
    dst
  }
  def getAmount: Int = {
    amount
  }
}

object Transaction:
  given JsonEncoder[Transaction] =
    DeriveJsonEncoder.gen[Transaction]
  given JsonDecoder[Transaction] =
    DeriveJsonDecoder.gen[Transaction]

