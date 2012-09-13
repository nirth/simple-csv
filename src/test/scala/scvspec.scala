package eu.kiichigo.utils.simplecsv.specs

import org.junit.runner.RunWith
import org.specs._
import org.specs.matcher._
import org.specs.runner.{ JUnitSuiteRunner, JUnit }

import eu.kiichigo.utils.simplecsv.CSV;
import java.io.File;

@RunWith(classOf[JUnitSuiteRunner])
class CSVSpecTest extends Specification with JUnit /*with ScalaCheck*/ {
	
	"CSV factory" should {
		"parse csv from path" in {
			
		}
	}
}

object CSVSpecTest {
  def main(args: Array[String]) {
    new CSVSpecTest().main(args);
  }
}