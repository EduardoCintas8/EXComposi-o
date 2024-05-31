package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import Entities.Department;
import Entities.HourContract;
import Entities.Worker;
import Entities.enums.WorkerLevel;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		String stLevel = null;
		WorkerLevel level = null;
		Date dat = null;
		int testar = 0;
		String dateD = null;
		Integer month = 0, year =0;

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		System.out.print("Enter department name :");
		String nameDpt = sc.nextLine();
		Department dpt = new Department(nameDpt);

		System.out.println("Enter worker data:");
		System.out.print("Name: ");
		String name = sc.nextLine();
		do {
			System.out.print("Level = (JUNIOR, MID_LEVEL, SENIOR) : ");
			stLevel = sc.next().toUpperCase();
			if (!validarWork(stLevel)) {
				System.out.println("Invalid level, enter again!");
				System.out.println();
			} else {
				level = WorkerLevel.valueOf(stLevel);
			}
		} while (!validarWork(stLevel));

		System.out.print("Base salary: R$ ");
		Double baseSalary = sc.nextDouble();

		Worker worker = new Worker(name, level, baseSalary, dpt);

		System.out.println();
		System.out.print("How may contracts to this worker? :");
		int qtdCont = sc.nextInt();
		HourContract hourcontract = new HourContract();

		for (int i = 0; i < qtdCont; i++) {
			System.out.println("Enter contract #" + (i + 1) + " data:");
			do {
				System.out.print("Date (DD/MM/YYYY): ");
				try {
					testar = 0;
					String date = sc.next();
					dat = formato.parse(date);
				} catch (ParseException ex) {
					System.out.println("Invalid type, type in the example format!");
					System.out.println();
					testar++;
				}
			} while (testar != 0);
			System.out.print("Value per hour: ");
			Double valueHour = sc.nextDouble();
			System.out.print("Duration: ");
			Integer duration = sc.nextInt();
			hourcontract = new HourContract(dat, valueHour, duration);
			worker.addContract(hourcontract);
		}

		System.out.println();
		do {
			try {
				testar = 0;
				System.out.print("Enter month and year to calculate income (MM/YYYY): ");
				dateD = sc.next();
				year = Integer.parseInt(dateD.substring(3));
				month = Integer.parseInt(dateD.substring(0, 2));
			} catch (NumberFormatException ex) {
				System.out.println("Invalid type, type in the example format!");
				testar++;
			}
		} while (testar != 0);

		System.out.println("Name: " + worker.getName());
		System.out.println("Department: " + worker.getDepartment().getName());
		System.out.println("Income for " + dateD + ": " + String.format("%.2f", worker.income(year, month)));
		sc.close();

	}

	public static Boolean validarWork(String level) {
		Boolean valida;
		if (level.toUpperCase().equals("JUNIOR") || level.toUpperCase().equals("MID_LEVEL")
				|| level.toUpperCase().equals("SENIOR")) {
			valida = true;
		} else {
			valida = false;
		}
		return valida;
	}

}
