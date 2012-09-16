package eu.kiichigo.utils.simplecsv.specs

import org.junit.runner.RunWith
import org.specs._
import org.specs.matcher._
import org.specs.runner.{ JUnitSuiteRunner, JUnit }

import eu.kiichigo.utils.simplecsv.CSV;
import java.io.File;

@RunWith(classOf[JUnitSuiteRunner])
class CSVSpecTest extends Specification with JUnit /*with ScalaCheck*/ {
	"Simple CSV Parser" should {
		var simpleCSV:CSV = null;
		var simpleCSVParsed:CSV = null;
		var rowHeaders:CSV = null;
		var columnHeaders:CSV = null;
		var rowAndColumnHeaders:CSV = null;

		doBefore {
			simpleCSV = CSV.fromFile(new File("target/test-classes/csvs/simple.csv"));
			simpleCSVParsed = CSV.fromFile(new File("target/test-classes/csvs/simple.csv"),
									 string => (string.substring(0, 2).toInt, string.substring(2, 4).toInt));
			rowHeaders = CSV.fromFile(new File("target/test-classes/csvs/row-headers.csv"));
			columnHeaders = CSV.fromFile(new File("target/test-classes/csvs/column-headers.csv"));
			rowAndColumnHeaders = CSV.fromFile(new File("target/test-classes/csvs/row-column-headers.csv"));
		}

		"Simple CSV" in {
			"simple.csv file should contain 130 cells" in {
				simpleCSV.length must_== 130;
			}

			"simple.csv cell at 0:0 should be \"0000\"" in {
				simpleCSV.cellAt(0, 0).data must_== "0000";
				simpleCSV.cellAt(0, 10).data must_== "1000";
				simpleCSV.cellAt(5, 5).data must_== "0505";
			}
		}

		"Simple CSV parsed" in {
			"simple.csv file should contain 130 cells" in {
				simpleCSVParsed.length must_== 130;
			}

			"simple.csv cell at 0:0 should be \"(00:00)\"" in {
				simpleCSVParsed.cellAt(0, 0).data must_== (0,0);
				simpleCSVParsed.cellAt(0, 10).data must_== (10, 0);
				simpleCSVParsed.cellAt(5, 5).data must_== (5, 5);
			}
		}
	}
}

object CSVSpecTest {
  def main(args: Array[String]) {
    new CSVSpecTest().main(args);
  }
}