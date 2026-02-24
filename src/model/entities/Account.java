package model.entities;

import model.exceptions.BusinessException;

public class Account {

    private Integer number;
    private String holder;
    private Double balance;
    private Double withdrawLimit;

    public Account() {
    }

    public Account(Integer number, String holder, Double balance, Double withdrawLimit) {
        this.number = number;
        this.holder = holder;
        this.balance = balance;
        this.withdrawLimit = withdrawLimit;
    }

    // Getters e Setters (Encapsulamento padrão)
    public Integer getNumber() { return number; }
    public void setNumber(Integer number) { this.number = number; }

    public String getHolder() { return holder; }
    public void setHolder(String holder) { this.holder = holder; }

    public Double getBalance() { return balance; }
    public void setBalance(Double balance) { this.balance = balance; }

    public Double getWithdrawLimit() { return withdrawLimit; }
    public void setWithdrawLimit(Double withdrawLimit) { this.withdrawLimit = withdrawLimit; }

    public void deposit(double amount) {
        balance += amount;
    }

    /**
     * O metodo withdraw agora é "limpo".
     * Ele delega a validação e só executa a subtração se nenhuma exceção for lançada.
     */
    public void withdraw(double amount) {
        validateWithdraw(amount);
        balance -= amount;
    }

    /**
     * Metodo auxiliar de validação (Programação Defensiva).
     * Isolar a validação facilita a manutenção e testes unitários.
     */
    public void validateWithdraw(double amount) {
        if (amount > getWithdrawLimit()) {
            // Lança a exceção personalizada que será capturada na classe Program
            throw new BusinessException("Erro de saque: A quantia excede o limite de saque");
        }
        if (amount > getBalance()) {
            throw new BusinessException("Erro de saque: Saldo insuficiente");
        }
    }
}