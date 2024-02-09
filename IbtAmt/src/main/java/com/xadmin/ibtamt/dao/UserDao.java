package com.xadmin.ibtamt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.ibtamt.bean.User;


public class UserDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/esbuser?serverTimezone=UTC&useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "oladunjoye99";
	private String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	
	private static final String INSERT_AMT_SQL = "INSERT INTO ibt_amount_config" + "  (acsn, fsp, lower_bound, upper_bound) VALUES "
			+ " (?, ?, ?, ?);";

	private static final String SELECT_AMT_BY_ACSN = "select acsn,fsp,lower_bound,upper_bound from ibt_amount_config where acsn =?";
	private static final String SELECT_ALL_AMT = "select * from ibt_amount_config";
	private static final String DELETE_AMT_SQL = "delete from ibt_amount_config where acsn = ?;";
	private static final String UPDATE_AMT_SQL = "update ibt_amount_config set fsp = ?,lower_bound= ?, upper_bound =? where acsn = ?;";
	public UserDao() {
	
	}
	
	protected Connection getConnection()  {
		Connection connection = null;
		try {
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	public void insertUser(User user)throws SQLException {
		System.out.println(INSERT_AMT_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_AMT_SQL)) {
			preparedStatement.setInt(1, user.getAcsn());
			preparedStatement.setString(2, user.getFsp());
			preparedStatement.setFloat(3, user.getLower_bound());
			preparedStatement.setFloat(4, user.getUpper_bound());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
}
	
	public User selectUser(int acsn){
		User user = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AMT_BY_ACSN);) {
			preparedStatement.setInt(1, acsn);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String fsp = rs.getString("fsp");
				Float lower_bound = rs.getFloat("lower_bound");
				Float upper_bound = rs.getFloat("upper_bound");
				user = new User(acsn, fsp, lower_bound, upper_bound);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}

public List<User> selectAllUsers() {
		
		// using try-with-resources to avoid closing resources (boiler plate code)
		List<User> users = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_AMT);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int acsn = rs.getInt("acsn");
				String fsp = rs.getString("fsp");
				Float lower_bound = rs.getFloat("lower_bound");
				Float upper_bound = rs.getFloat("upper_bound");
				users.add(new User(acsn, fsp, lower_bound, upper_bound));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return users;
	}

public boolean updateUser(User user) throws SQLException {
	boolean rowUpdated;
	try (Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(UPDATE_AMT_SQL);) {
		statement.setString(1, user.getFsp());
		statement.setFloat(2, user.getLower_bound());
		statement.setFloat(3, user.getUpper_bound());
		statement.setInt(4, user.getAcsn());
		System.out.println("updated User:"+statement);

		rowUpdated = statement.executeUpdate() > 0;
	}
	return rowUpdated;
}
public boolean deleteUser(int acsn) throws SQLException {
	boolean rowDeleted;
	try (Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_AMT_SQL);) {
		statement.setInt(1, acsn);
		rowDeleted = statement.executeUpdate() > 0;
	}
	return rowDeleted;
}
		private void printSQLException(SQLException ex) {for (Throwable e : ex) {
		if (e instanceof SQLException) {
			e.printStackTrace(System.err);
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println("Message: " + e.getMessage());
			Throwable t = ex.getCause();
			while (t != null) {
				System.out.println("Cause: " + t);
				t = t.getCause();
			}
		}
	}
}
}	