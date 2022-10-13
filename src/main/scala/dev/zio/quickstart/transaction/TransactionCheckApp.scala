package dev.zio.quickstart.transaction

import zhttp.http.*
import zio.*
import zio.json.*
import scala.io.Source

object TransactionCheckApp:
  def transactionCheck: Http[Any, Nothing, Request, Response] =
    Http.collectZIO[Request] {
      case Method.POST -> !! / "transaction-check" =>
        val data = """{"src": "1", "dst": "5", amount: 10}""".fromJson[Transaction]
        val blackList = Source.fromFile("blacklist.txt")
        for
          r <- blackList.getLines() match
            case Right(transaction) =>
              if (transaction.getSrc == r || transaction.getDst == r)
                Response.text("Cancel").setStatus(Status.BadRequest)
              else Response.text("Success")
            case Left(value) => "Failed to take JSON"
        yield r
    }
