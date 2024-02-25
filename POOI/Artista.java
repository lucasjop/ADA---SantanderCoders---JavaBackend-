package POOI;

import java.util.Objects;

public class Artista extends Pessoa {

    public Artista(String nome, String dataNascimento) {
        super(nome, dataNascimento);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Artista artista = (Artista) obj;
        return Objects.equals(getNome(), artista.getNome()) &&
                Objects.equals(getDataNascimento(), artista.getDataNascimento());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getDataNascimento());
    }
    
}
