package utils;

import model.Paciente;

import java.io.*;
import java.util.List;

public class Serializable {
    public static void guardarPacientes(List<Paciente> pacientes, String caminho) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(caminho))) {
            out.writeObject(pacientes);
            System.out.println("Pacientes guardados com sucesso em " + caminho);
        } catch (IOException e) {
            System.err.println("Erro ao guardar pacientes: " + e.getMessage());
        }
    }

    public static List<Paciente> carregarPacientes(String caminho) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(caminho))) {
            return (List<Paciente>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar pacientes: " + e.getMessage());
            return null;
        }
    }
}