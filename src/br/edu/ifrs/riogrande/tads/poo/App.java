// pacote é o domínio qualificado:
package br.edu.ifrs.riogrande.tads.poo;

import java.time.LocalDate;

import br.edu.ifrs.riogrande.tads.poo.modelo.CapacidadeException;
import br.edu.ifrs.riogrande.tads.poo.modelo.Cliente;
import br.edu.ifrs.riogrande.tads.poo.modelo.Quarto;
import br.edu.ifrs.riogrande.tads.poo.modelo.QuartoOcupadoException;

// main não é parte do modelo/dominio/logica...
class App { 
  public static void main(String[] args) {
    // classes com a lógica têm seu próprio pacote
    // "lógica de negócio", "domain logic/rules"
    // negocio (business, biz), logica, 
    // dominio (domain), modelo (model)
    
    Quarto quarto404 = new Quarto(404, 2);
    Quarto quarto500 = new Quarto(500, 3);
    
    Cliente annie = new Cliente("12345678901", "Annie");
    Cliente pedro = new Cliente("09876543212", "Pedro");
    Cliente adriana = new Cliente("88722244455", "Adriana");
    
    System.out.println(quarto404);
    System.out.println(pedro);

    System.out.println(quarto404.getHospedes()); // []
    System.out.println(quarto404.getHospedes().size() == 0);
    
    quarto404.checkin(annie);
    
    System.out.println(quarto404.getHospedes()); // [Annie]
    System.out.println(quarto404.getHospedes().size() == 1);

    try {
      quarto404.checkin(pedro);
    } catch (QuartoOcupadoException e) { // tratamento exceção
      System.err.println("Quarto ocupado");
    } catch (CapacidadeException e) {
      System.err.println("Acima da capacidade");
    }

    System.out.println(quarto500.getHospedes()); // []
    
    quarto500.checkin(adriana, annie, pedro);

    // [Adriana, Annie, Pedro]
    System.out.println(quarto500.getHospedes()); 

    Cliente erick = new Cliente("23499988822", "Erick");
    Cliente jaime = new Cliente("88822233454", "Jaime");

    int diarias = quarto500.checkout(LocalDate.of(2024, 7, 28));
    System.out.println(diarias); // 2

    quarto500.checkin(erick, jaime);

    // [Erick, Jaime]
    System.out.println(quarto500.getHospedes());

  }
}
