package model.entities;

import model.exceptions.BusinessException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {

    private Integer roomNumber;
    private Date checkIn;
    private Date checkOut;

    // Static para não instanciar um novo formatador para cada reserva (otimização de memória)
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    // O construtor já lança exceção se a tentativa de criar o objeto for inválida
    public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
        if (!checkOut.after(checkIn)) {
            throw new BusinessException("Check-out date must be after check-in date");
        }
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    /**
     * Calcula a diferença entre datas em dias usando TimeUnit.
     * Transforma milissegundos em um valor legível de noites.
     */
    public long duration() {
        long diff = checkOut.getTime() - checkIn.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    /**
     * Metodo central de atualização com múltiplas validações.
     * Note que validamos datas futuras E a ordem cronológica.
     */
    public void updateDates(Date checkIn, Date checkOut) {
        Date now = new Date();

        // Regra 1: Datas de atualização não podem ser no passado
        if (checkIn.before(now) || checkOut.before(now)) {
            throw new BusinessException("Reservation dates for update must be future dates");
        }

        // Regra 2: Check-out deve ser posterior ao Check-in
        if (!checkOut.after(checkIn)) {
            throw new BusinessException("Check-out date must be after check-in date");
        }

        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    @Override
    public String toString() {
        return "Room "
                + roomNumber
                + ", check-in: "
                + sdf.format(checkIn)
                + ", check-out: "
                + sdf.format(checkOut)
                + ", "
                + duration()
                + " nights";
    }
}