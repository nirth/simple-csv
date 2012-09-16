package eu.kiichigo.utils.simplecsv.specs

import org.specs2.mutable._;
import org.specs2.runner._;
import org.junit.runner._;

import eu.kiichigo.utils.simplecsv.CSV;
import java.io.File;

class CSVSpecTest extends SpecificationWithJUnit {
	"Simple CSV Parser" should {

		"Simple CSV" in {
			val csv = CSV.fromFile(new File("target/test-classes/csvs/simple.csv"));
			"simple.csv file should contain 130 cells" in {
				csv.length must_== 130;
			}

			"simple.csv cell at 0:0 should be \"0000\"" in {
				csv.cellAt(0, 0).data must_== "0000";
				csv.cellAt(0, 10).data must_== "1000";
				csv.cellAt(5, 5).data must_== "0505";
			}
		}

		"Simple CSV with custom parser" in {
			val csv = CSV.fromFile(new File("target/test-classes/csvs/simple.csv"),
								 string => (string.substring(0, 2).toInt, string.substring(2, 4).toInt));

			"simple.csv file should contain 130 cells" in {
				csv.length must_== 130;
			}

			"simple.csv cell at 0:0 should be \"(00:00)\"" in {
				csv.cellAt(0, 0).data must_== (0,0);
				csv.cellAt(0, 10).data must_== (10, 0);
				csv.cellAt(5, 5).data must_== (5, 5);
			}

			"simple.csv should be able to parse rows" in {
				csv.rows.length must_== 10;
			}

			"simple.csv should be able to parse columns" in {
				csv.columns.length must_== 13;
			}
		}
	}

	"CSV with Column headers" should {

	}

	"CSV with Row headers" should {

	}

	"CSV with Row and Column headers" should {

	}
}
/*object CSVSpecTest extends Specification with JUnitRunner with ScalaCheck\*\/ {
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

		"Simple CSV with custom parser" in {
			"simple.csv file should contain 130 cells" in {
				simpleCSVParsed.length must_== 130;
			}

			"simple.csv cell at 0:0 should be \"(00:00)\"" in {
				simpleCSVParsed.cellAt(0, 0).data must_== (0,0);
				simpleCSVParsed.cellAt(0, 10).data must_== (10, 0);
				simpleCSVParsed.cellAt(5, 5).data must_== (5, 5);
			}

			"simple.csv should be able to parse rows" in {
				simpleCSVParsed.rows.length must_== 10;
			}

			"simple.csv should be able to parse columns" in {
				simpleCSVParsed.columns.length must_== 13;
			}
		}
	}

	"CSV with Column headers" should {

	}

	"CSV with Row headers" should {

	}

	"CSV with Row and Column headers" should {

	}
}
*/
/*
object CSVSpecTest {
  def main(args: Array[String]) {
    new CSVSpecTest().main(args);
  }
}
*/