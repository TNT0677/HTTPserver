object ServerRequest {
  import java.net._
  def main(args: Array[String]): Unit = {
    Listen()
  }
  def Listen(): Unit = {
    val chatter = new Thread(new Runnable {
      val req = new java.net.ServerSocket(80)
      override def run(): Unit = {
        while (true) {        
	      val assign = req.accept()
	      Handoff(assign)
	      Thread.sleep(1000)
        }
      }
    })
    chatter.start()
  }
  def Handoff(sock: java.net.Socket): Unit = {
    val talk = new Thread(new Runnable {
      override def run(): Unit = {
        sock.getOutputStream().write("Hello World!".getBytes)
        while (sock.isConnected()) {
          //println(java.lang.Thread.activeCount())
          val echo = sock.getInputStream().read()
          println(echo.toChar)
          Thread.sleep(1000)
        }
      }
    })
    talk.start()
  }
}

