package cinema;

import java.util.Scanner;

public class Cinema {
    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }

    static void menu() {
        System.out.println("Enter the number of rows:");
        int numberOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numberOfSeats = scanner.nextInt();
        char[][] seats = createArray(numberOfRows, numberOfSeats);
        int option;
        do {
            System.out.println("\n1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            option = scanner.nextInt();
            System.out.println();
            switch (option) {
                case 1:
                    printArray(seats, numberOfRows, numberOfSeats);
                    break;
                case 2:
                    buyTicket(seats, numberOfRows, numberOfSeats);
                    break;
                case 3:
                    showStatistics(seats, numberOfRows, numberOfSeats);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Wrong option!");
                    break;
            }
        } while (option != 0);
    }

    static void showStatistics(char[][] array, int numberOfRows, int numberOfSeats) {
        int numberOfTickets = 0;
        int income = 0;
        int allSeats = numberOfRows * numberOfSeats;
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfSeats; j++) {
                if (array[i][j] == 'B') {
                    numberOfTickets++;
                    if(allSeats <= 60) {
                        income += 10;
                    } else if (i < numberOfRows / 2) {
                        income += 10;
                    } else {
                        income += 8;
                    }
                }
            }
        }

        System.out.printf("Number of purchased tickets: %d%n", numberOfTickets);

        float percentage = (float) numberOfTickets * 100 / allSeats;
        System.out.printf("Percentage: %.2f", percentage);
        System.out.println("%");

        System.out.printf("Current income: $%d%n", income);

        int totalIncome;
        if (allSeats <= 60) {
            totalIncome = allSeats * 10;
        } else if (numberOfRows % 2 == 0) {
            totalIncome = allSeats / 2 * (10 + 8);
        } else {
            totalIncome = numberOfSeats * (numberOfRows / 2 * (10 + 8) + 8);
        }
        System.out.printf("Total income: $%d%n", totalIncome);
    }

    static void buyTicket(char[][] seats, int numberOfRows, int numberOfSeats) {
        //Choosing row
        System.out.println("Enter a row number:");
        int selectedRow = choosingRowOrSeat(numberOfRows);
        //Choosing seat in that row
        System.out.println("Enter a seat number in that row:");
        int selectedSeat = choosingRowOrSeat(numberOfSeats);
        //Checking if seat is already selected
        while (seats[selectedRow - 1][selectedSeat - 1] == 'B') {
            System.out.println("\nThat ticket has already been purchased");
            //Checking if new input is in range
            System.out.println("Enter a row number:");
            selectedRow = choosingRowOrSeat(numberOfRows);
            System.out.println("Enter a seat number in that row:");
            selectedSeat = choosingRowOrSeat(numberOfSeats);
        }
        //Showing ticket price
        seats[selectedRow - 1][selectedSeat - 1] = 'B';
        if(numberOfRows * numberOfSeats <= 60) {
            System.out.println("\nTicket price: $10");
        } else if (selectedRow <= numberOfRows / 2) {
            System.out.println("\nTicket price: $10");
        } else {
            System.out.println("\nTicket price: $8");
        }
    }

    static int choosingRowOrSeat(int numberOfRowsOrSeats) {
        int selection = scanner.nextInt();
        if (selection <= 0 || selection > numberOfRowsOrSeats) {
            System.out.println("Wrong input!");
        }
        while (selection <= 0 || selection > numberOfRowsOrSeats) {
            selection = scanner.nextInt();
            if (selection <= 0 || selection > numberOfRowsOrSeats) {
                System.out.println("Wrong input!");
            }
        }
        return selection;
    }

    static char[][] createArray(int numberOfRows, int numberOfSeats) {
        char[][] loop = new char[numberOfRows][numberOfSeats];
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfSeats; j++) {
                loop[i][j] = 'S';
            }
        }
        return loop;
    }

    static void printArray(char[][] array, int numberOfRows, int numberOfSeats) {
        System.out.println("\nCinema:");
        for (int i = 0; i < numberOfRows + 1; i++) {
            for (int j = 0; j < numberOfSeats + 1; j++) {
                if (i == 0) {
                    if (j == 0) {
                        System.out.print("  ");
                    } else {
                        System.out.print(j + " ");
                    }
                } else {
                    if (j == 0) {
                        System.out.print(i + " ");
                    } else {
                        System.out.print(array[i - 1][j - 1] + " ");
                    }
                }
            }
            System.out.println();
        }
    }
}