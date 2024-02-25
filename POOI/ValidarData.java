package POOI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidarData {

    // Função para validar a data
    public static boolean validarData(String data) {
        // Verifica o formato DD/MM/AAAA
        String regex = "^\\d{2}/\\d{2}/\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);
        
        if (!matcher.matches()) {
            return false; // Formato inválido
        }

        // Tenta converter a string para uma data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate.parse(data, formatter);
            return true; // Data válida
        } catch (DateTimeParseException e) {
            return false; // Data inválida
        }
    }
}
