package com.busoftinc.lkc.sqlite;


import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Data {
	
	private Connection connect() {
		// SQLite connection string
		String url = "jdbc:sqlite:D://Works//otherworkspace//mCoaching.db";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	/**
	 * select all rows in the warehouses table
	 */
	public String selectAll() {
		String results = "";
		String sql = "select lkc_id " +
					"||';'||ifnull(strftime('%Y%m%d%H%M%S',createdate),'') " +
					"||';'||ifnull(createdby,'') " +
					"||';'||ifnull(strftime('%Y%m%d%H%M%S',updatedate),'') " +
					"||';'||ifnull(updatedby,'') " +
					"||';'||ifnull(akhir_iterasi,'') " +
					"||';'||ifnull(sales_rep,'') " +
					"||';'||ifnull(pertemuan,'') " +
					"||';'||ifnull(total_nilai,'') " +
					"||';'||ifnull(avg_nilai,'') " +
					"||';'||ifnull(prestasi,'') " +
					"||';'||ifnull(kekuatan,'') " +
					"||';'||ifnull(kemampuan,'') " +
					"||';'||ifnull(tindak_lanjut,'') " +
					"||';'||ifnull(strftime('%Y%m%d%H%M%S',startdate),'') " +
					"||';'||ifnull(strftime('%Y%m%d%H%M%S',completedate),'') as csv " +
					"from lkc_order";

		try (Connection conn = this.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			while (rs.next()) {
				results += rs.getString("csv");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		Data app = new Data();
		System.out.println(app.selectAll());
	}
}
