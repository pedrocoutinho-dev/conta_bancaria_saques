package application;

import model.entities.Reservation;
import model.exceptions.BusinessException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Program {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        // Define o formato de data esperado para entrada e saída
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        /* Bloco Try principal: captura qualquer falha no processo de reserva,
           desde erros de digitação até violações de regras de negócio. */
        try {
            System.out.print("Room number: ");
            int number = sc.nextInt();
            System.out.print("Date of checkin(dd/MM/yyyy): ");
            Date checkIn = sdf.parse(sc.next()); // Pode lançar ParseException
            System.out.print("Date of checkout(dd/MM/yyyy): ");
            Date checkOut = sdf.parse(sc.next()); // Pode lançar ParseException

            // Instancia a reserva. O construtor deve validar se o checkout é após o checkin
            Reservation reservation = new Reservation(number, checkIn, checkOut);
            System.out.println("Reservation: " + reservation);

            System.out.println();
            System.out.println("Enter data to update the reservation: ");
            System.out.print("Date of checkin(dd/MM/yyyy): ");
            checkIn = sdf.parse(sc.next());
            System.out.print("Date of checkout(dd/MM/yyyy): ");
            checkOut = sdf.parse(sc.next());

            /* Tenta atualizar as datas. O metodo updateDates lança BusinessException
               se as datas forem retroativas ou inválidas. */
            reservation.updateDates(checkIn, checkOut);
            System.out.println("Reservation: " + reservation);
        }
        // Captura erro caso o usuário digite a data num formato que o Java não entenda
        catch(ParseException pe) {
            System.out.println("Invalid date format entered.");
        }
        // Captura erros de domínio (ex: data de saída anterior à entrada)
        catch(BusinessException iae) {
            System.out.println("Error in reservation: " + iae.getMessage());
        }
        // Captura qualquer outro erro inesperado para que o programa não "quebre" feio
        catch (RuntimeException re) {
            System.out.println("Unexpected error occurred.");
        }

        sc.close();
    }
}