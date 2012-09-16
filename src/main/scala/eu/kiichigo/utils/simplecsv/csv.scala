package eu.kiichigo.utils.simplecsv;

import java.io.File;
import scala.collection.mutable.ArrayBuffer;

/**
 * Object CSV
 *
 * @varsion 0.1.1
 * @author David Sergey
 */
object CSV {

	/*
	 * Creates new instance of <code>eu.kiichigo.utils.simplecsv.CSV</code> from file provided in <code>file</code> argument.
	 *
	 * @param	java.io.File 	An instance of <code>java.ui.File</code> to parse.
	 * @param	String => Any	A closure, that will parse data contained in individual cell, default closure works just like identity: f(x) -> x.
	 * @
     */
	def fromFile(file:File, closure: String => Any = null, columnHeader:Boolean = false, rowHeader:Boolean = false):CSV = {
		val cells = new ArrayBuffer[Cell];

		var rowCount = 0;
		var columnCount = 0;
		var rc = 0;
		var cc = 0;

		var columnHeaders:Array[String] = null;
		var rowHeaders:Array[String] = null;

		if (!file.exists()) {
			// ToDo: throw an error.
		}

		for (row <- scala.io.Source.fromFile(file).getLines()) {
			columnCount = 0;

			if (columnHeader && rowCount == 0) {
				columnHeaders = row.split(",");
			} else {
				for (value <- row.split(",")) {
					rc = if (columnHeader) rowCount - 1 else rowCount;
					cc = if (rowHeader) columnCount -1 else columnCount;


					if (value != null && value.length >= 1) {
						if (rowHeader && columnCount == 0) {
							rowHeaders + value;
						} else {
							val v = if (closure == null)
									defaultCellClosure(value);
								else
									closure(value);

							val cell = new Cell(rc, cc, v,
								if (columnHeader) columnHeaders(cc).trim() else null,
								if (rowHeader) rowHeaders(rc).trim() else null
							);
							cells += cell;
						}
					}
					columnCount += 1;
				}
			}
			
			rowCount += 1;
		}

		return new CSV(cells.toList);
	}

	def fromString(string:String, closure: (String) => Any, columnHeader:Boolean = false, rowHeader:Boolean = false):CSV = {
		return null;
	}

	private def defaultCellClosure(input:String):Any = {
		return input;
	}
}

class CSV(private val data:List[Cell]) {

	def cellAt(row:Int, column:Int) = data.filter(cell => (cell.row == row && cell.column == column))(0);

	def column(index:Int) = data.filter(cell => (cell.column == index))(0);

	def row(index:Int) = data.filter(cell => (cell.row == index))(0);

	def flip = data.map(cell => new Cell(cell.column, cell.row, cell.data, cell.rowHeader, cell.columnHeader));

	def cells = data;

	def length = data.length;
}

case class Cell(val row:Int, val column:Int, val data:Any, val rowHeader:String = null, val columnHeader:String = null) {
	def header = if (rowHeader != null) rowHeader else columnHeader;

	override def toString = "[Cell row=" + row.toString() + " column=" + column.toString() + " data=" + data + " header=" + header + "]";
}

//val columns = row.split(""",<="([\w\(\.\)]+)""")