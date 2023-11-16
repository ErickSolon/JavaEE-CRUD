package com.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAO {
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/crud";
	private String usuario = "admin";
	private String senha = "admin";

	public Connection connectDatabase() {
		Connection conn = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, usuario, senha);
			return conn;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public List<JavaBeans> findAll(int page, int size) {
	    int offset = (page <= 0 ? 0 : (page - 1) * size);
	    
	    String findAll = "SELECT * FROM Pessoas INNER JOIN FormasContato ON Pessoas.id = FormasContato.id LIMIT ?, ?";
	    List<JavaBeans> listaPessoas = new ArrayList<>();

	    try {
	        Connection conectar = connectDatabase();
	        PreparedStatement pstmt = conectar.prepareStatement(findAll);

	        pstmt.setInt(1, offset);
	        pstmt.setInt(2, size);

	        ResultSet rs = pstmt.executeQuery();

	        while (rs.next()) {
	            listaPessoas.add(new JavaBeans(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
	                    rs.getString(6), rs.getString(7)));
	        }

	        conectar.close();
	        return listaPessoas;
	    } catch (Exception e) {
	        System.out.println(e);
	        return null;
	    }
	}


	public void findById(JavaBeans pessoas) {
		String findById = "SELECT * FROM Pessoas INNER JOIN FormasContato ON Pessoas.id = FormasContato.id WHERE Pessoas.id = ?";

		try {
			Connection conectar = connectDatabase();
			PreparedStatement pstmt = conectar.prepareStatement(findById);
			pstmt.setInt(1, pessoas.getId());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				pessoas.setId(rs.getInt(1));
				pessoas.setNome(rs.getString(2));
				pessoas.setSobrenome(rs.getString(3));
				pessoas.setTelefone(rs.getString(4));
				pessoas.setEmail(rs.getString(6));
				pessoas.setLinkedIn(rs.getString(7));
			}

			conectar.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void save(JavaBeans pessoas) {
		String save = "INSERT INTO Pessoas (nome, sobrenome, telefone) VALUES (?, ?, ?);";
		String saveFormasContato = "INSERT INTO FormasContato (id, Email, LinkedIn) VALUES (LAST_INSERT_ID(), ?, ?);";

		try {
			Connection conectar = connectDatabase();
			PreparedStatement pstmt = conectar.prepareStatement(save, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, pessoas.getNome());
			pstmt.setString(2, pessoas.getSobrenome());
			pstmt.setString(3, pessoas.getTelefone());
			pstmt.executeUpdate();

			ResultSet generatedKeys = pstmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				int id = generatedKeys.getInt(1);
				pstmt = conectar.prepareStatement(saveFormasContato);
				pstmt.setString(1, pessoas.getEmail());
				pstmt.setString(2, pessoas.getLinkedIn());
				pstmt.executeUpdate();
			}

			conectar.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void updateById(JavaBeans pessoas) {
		String updatePessoas = "UPDATE Pessoas SET nome = ?, sobrenome = ?, telefone = ? WHERE id = ?;";
		String updateFormasContato = "UPDATE FormasContato SET Email = ?, LinkedIn = ? WHERE id = ?;";

		try {
			Connection conectar = connectDatabase();
			PreparedStatement pstmt = conectar.prepareStatement(updatePessoas);
			pstmt.setString(1, pessoas.getNome());
			pstmt.setString(2, pessoas.getSobrenome());
			pstmt.setString(3, pessoas.getTelefone());
			pstmt.setInt(4, pessoas.getId());
			pstmt.executeUpdate();

			pstmt = conectar.prepareStatement(updateFormasContato);
			pstmt.setString(1, pessoas.getEmail());
			pstmt.setString(2, pessoas.getLinkedIn());
			pstmt.setInt(3, pessoas.getId());
			pstmt.executeUpdate();

			conectar.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void deleteById(JavaBeans pessoas) {
		String deletePessoas = "DELETE FROM Pessoas WHERE id = ?;";
		String deleteFormasContato = "DELETE FROM FormasContato WHERE id = ?;";

		try {
			Connection conectar = connectDatabase();
			PreparedStatement pstmt = conectar.prepareStatement(deleteFormasContato);
			pstmt.setInt(1, pessoas.getId());
			pstmt.executeUpdate();

			pstmt = conectar.prepareStatement(deletePessoas);
			pstmt.setInt(1, pessoas.getId());
			pstmt.executeUpdate();

			conectar.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
