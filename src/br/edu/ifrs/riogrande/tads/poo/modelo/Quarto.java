package br.edu.ifrs.riogrande.tads.poo.modelo;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Quarto {
  private int numero;
  private int pessoas;

  private LocalDate entrada;

  private ArrayList<Cliente> hospedes = new ArrayList<>();

  // estabelece um relacionamento (do tipo AGREGAÇÃO)
  public void checkin(Cliente cliente, Cliente... clientes) {
    if ( ! hospedes.isEmpty()) { // está ocupado
      throw new QuartoOcupadoException();
    }
    if (clientes.length + 1 > pessoas) { // acima pessoas
      throw new CapacidadeException();
    }
    // ok
    hospedes.add(cliente); // aciona um
    // adiciona restante
    for (Cliente c : clientes) hospedes.add(c);

    entrada = LocalDate.now(); // guardar data do checkin
  }

  public int checkout(LocalDate saida) {
    hospedes.clear();
    return (int) ChronoUnit.DAYS.between(entrada, saida);
  }

  public ArrayList<Cliente> getHospedes() {
      return hospedes;
  }

  public Quarto(int numero, int pessoas) {
    this.numero = numero;
    this.pessoas = pessoas;
  }

  public int getNumero() {
    return numero;
  }

  public void setNumero(int numero) {
    this.numero = numero;
  }

  public int getPessoas() {
    return pessoas;
  }

  public void setPessoas(int pessoas) {
    this.pessoas = pessoas;
  }

  @Override
  public String toString() {
    return "Quarto [numero=" + numero + ", pessoas=" + pessoas + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + numero;
    result = prime * result + pessoas;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Quarto other = (Quarto) obj;
    if (numero != other.numero)
      return false;
    if (pessoas != other.pessoas)
      return false;
    return true;
  }
}
