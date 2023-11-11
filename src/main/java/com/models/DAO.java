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

	public List<JavaBeans> findAll() {
		String findAll = "SELECT * FROM Pessoas";
		List<JavaBeans> listaPessoas = new ArrayList<>();

		try {
			Connection conectar = connectDatabase();
			PreparedStatement pstmt = conectar.prepareStatement(findAll);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				listaPessoas.add(new JavaBeans(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}

			conectar.close();
			return listaPessoas;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public void findById(JavaBeans pessoas) {
		String findById = "SELECT * FROM Pessoas WHERE id = ?";

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

			}

			conectar.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void save(JavaBeans pessoas) {
		String save = "INSERT INTO Pessoas (nome, sobrenome, telefone) VALUES (?, ?, ?)";
		try {
			Connection conectar = connectDatabase();
			PreparedStatement pstmt = conectar.prepareStatement(save);
			pstmt.setString(1, pessoas.getNome());
			pstmt.setString(2, pessoas.getSobrenome());
			pstmt.setString(3, pessoas.getTelefone());
			pstmt.executeUpdate();
			conectar.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void updateById(JavaBeans pessoas) {
		String updateById = "UPDATE Pessoas SET nome = ?, sobrenome = ?, telefone = ? WHERE id = ?";
		try {
			Connection conectar = connectDatabase();
			PreparedStatement pstmt = conectar.prepareStatement(updateById);
			pstmt.setString(1, pessoas.getNome());
			pstmt.setString(2, pessoas.getSobrenome());
			pstmt.setString(3, pessoas.getTelefone());
			pstmt.setInt(4, pessoas.getId());
			pstmt.executeUpdate();
			conectar.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void deleteById(JavaBeans pessoas) {
		String deleteById = "DELETE FROM Pessoas WHERE id = ?";
		try {
			Connection conectar = connectDatabase();
			PreparedStatement pstmt = conectar.prepareStatement(deleteById);
			pstmt.setInt(1, pessoas.getId());
			pstmt.executeUpdate();
			conectar.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
}
