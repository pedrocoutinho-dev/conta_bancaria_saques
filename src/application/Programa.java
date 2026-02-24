package application;

import model.entities.Account;
import model.exceptions.BusinessException;

import java.util.Locale;
import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        // O bloco try externo captura erros de entrada (ex: digitar letra onde pede número)
        try {
            System.out.println("Informe os dados da conta");
            System.out.print("Numero: ");
            int number = sc.nextInt();

            System.out.print("Titular: ");
            sc.nextLine(); // Consome a quebra de linha pendente do sc.nextInt()
            String holder = sc.nextLine();

            System.out.print("Saldo inicial: ");
            double balance = sc.nextDouble();

            System.out.print("Limite de saque: ");
            double withdrawLimit = sc.nextDouble();

            // Instanciação do objeto com os dados fornecidos
            Account acc = new Account(number, holder, balance, withdrawLimit);

            System.out.println();
            System.out.print("Informe uma quantia para sacar: ");
            double amount = sc.nextDouble();

            /* Try/catch aninhado: foca especificamente no metodo withdraw.
               Trata erros de lógica de negócio (ex: saque maior que o saldo ou limite).
            */
            try {
                acc.withdraw(amount);
                System.out.printf("Novo saldo: %.2f%n", acc.getBalance());
            }
            catch (BusinessException e) {
                // Exibe a mensagem personalizada definida dentro da classe Account
                System.out.println("Erro de saque: " + e.getMessage());
            }
        }
        /* Captura RuntimeException (como InputMismatchException) para evitar
           que o programa trave se o usuário digitar dados inválidos.
        */
        catch (RuntimeException e) {
            System.out.println("Erro inesperado: Entrada de dados inválida.");
        }

        sc.close();
    }
}
