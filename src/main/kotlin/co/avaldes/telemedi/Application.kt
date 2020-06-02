package co.avaldes.telemedi

import io.micronaut.runtime.Micronaut

object Application {
  @JvmStatic
  fun main(args: Array<String>) {
      Micronaut.build()
          .packages("co.avaldes.telemedi")
          .mainClass(Application.javaClass)
          .start()
  }
}
