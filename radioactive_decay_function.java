// Programul aduce activitatea din CE la zi, ca in Excel
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class radioactive_decay_function
{
  static double radioactiveDecay(double initialAmount, double halfLife, double time)
  {
    double lambda = Math.log(2) / halfLife;
    double remainingAmount = initialAmount * Math.exp(-lambda * time);

    return remainingAmount;
  }


  static double ZAIDfind(int zaid)
  {
    String fileName = "zaid_data.txt";
    int ZA_ID = 0, aux = 0;
    double hLife = 0;
    double halfLife = 0;


    try (Scanner scanner = new Scanner(new File(fileName))) {

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] parts = line.trim().split("\\s+"); // split by whitespace

        // Verificare daca sunt minim doua coloane per linie
        if (parts.length >= 2) {
          ZA_ID = Integer.parseInt(parts[0]);   // prima coloana este ZAID
          hLife = Double.parseDouble(parts[1]); // a doua coloana este T1/2
        }

        //System.out.println(line);

        if (ZA_ID == zaid) {
          halfLife = hLife;
          aux = 1;
          break; // oprire loop dupa gasirea zaid-ului cautat
        }
      }

      if (aux == 0) {
        System.out.println("Eroare: ZAID nu a fost gasit.");
      }

    } catch (FileNotFoundException e) {

      System.out.println("File not found !");
      e.printStackTrace();

    }

    if (aux == 0) {
      return Math.pow(10,7);
    } else {
      return halfLife;
    }
  }


  static double timeCalc(int day0, int month0, int year0, int day1, int month1, int year1)
  {
    int ndays = 0;
    int[] dpermonth = new int[12]; // numarul de zile din fiecare luna
    dpermonth[0] = 31;
    if (4 * (year0 / 4) != year0) {
      // an nedivizibil cu 4 (365 zile)
      dpermonth[1] = 28;
    }
    else if (4 * (year0 / 4) == year0) {
      // an bisect, divizibil cu 4 (366 zile)
      dpermonth[1] = 29;
    }
    dpermonth[2] = 31;
    dpermonth[3] = 30;
    dpermonth[4] = 31;
    dpermonth[5] = 30;
    dpermonth[6] = 31;
    dpermonth[7] = 31;
    dpermonth[8] = 30;
    dpermonth[9] = 31;
    dpermonth[10] = 30;
    dpermonth[11] = 31;


    if (year1 > year0) {
      // Calcularea nr. de zile ramase din luna
      if (month0 == 1) {
        ndays = 31 - day0 + 1;
      }
      if (month0 == 2) {
        if (4 * (year0 / 4) != year0) {
          // an nedivizibil cu 4 (365 zile)
          ndays = 28 - day0 + 1;
        }
        else if (4 * (year0 / 4) == year0) {
          // an bisect, divizibil cu 4 (366 zile)
          ndays = 29 - day0 + 1;
        }
      }
      if (month0 == 3) {
        ndays = 31 - day0 + 1;
      }
      if (month0 == 4) {
        ndays = 30 - day0 + 1;
      }
      if (month0 == 5) {
        ndays = 31 - day0 + 1;
      }
      if (month0 == 6) {
        ndays = 30 - day0 + 1;
      }
      if (month0 == 7) {
        ndays = 31 - day0 + 1;
      }
      if (month0 == 8) {
        ndays = 31 - day0 + 1;
      }
      if (month0 == 9) {
        ndays = 30 - day0 + 1;
      }
      if (month0 == 10) {
        ndays = 31 - day0 + 1;
      }
      if (month0 == 11) {
        ndays = 30 - day0 + 1;
      }
      if (month0 == 12) {
        ndays = 31 - day0 + 1;
      }

      // Calcularea nr. de zile pana la finalul anului din CE
      for (int i = month0 + 1; i <= 12; i++) {
        ndays += dpermonth[i - 1];
      }

      // Calcularea nr. de zile pana la anul in care s-au facut masuratorile
      for (int i = year0 + 1; i < year1; i++) {
        if (4 * (i / 4) != i) {
          // an normal
          ndays += 365;
        }
        else if (4 * (i / 4) == i) {
          // an bisect
          ndays += 366;
        }
      }

      // Calcularea restului de zile care au trecut din anul in care s-au facut masuratorile
      ndays += day1 - 1;

      if (4 * (year1 / 4) != year1) {
        dpermonth[1] = 28;
      }
      else if (4 * (year1 / 4) == year1) {
        dpermonth[1] = 29;
      }
  
      for (int i = month1 - 1; i > 0; i--) {
        ndays += dpermonth[i - 1];
      }
    }


    if (year1 == year0) { // data din CE si data masuratorii sunt in acelasi an
      if (month1 == month0) {
        ndays = day1 - day0;
      }

      else if (month1 != month0) {
        if (month0 == 1) {
          ndays = 31 - day0 + 1;
        }
        if (month0 == 2) {
          if (4 * (year0 / 4) != year0) {
            // an nedivizibil cu 4 (365 zile)
            ndays = 28 - day0 + 1;
          }
          else if (4 * (year0 / 4) == year0) {
            // an bisect, divizibil cu 4 (366 zile)
            ndays = 29 - day0 + 1;
          }
        }
        if (month0 == 3) {
          ndays = 31 - day0 + 1;
        }
        if (month0 == 4) {
          ndays = 30 - day0 + 1;
        }
        if (month0 == 5) {
          ndays = 31 - day0 + 1;
        }
        if (month0 == 6) {
          ndays = 30 - day0 + 1;
        }
        if (month0 == 7) {
          ndays = 31 - day0 + 1;
        }
        if (month0 == 8) {
          ndays = 31 - day0 + 1;
        }
        if (month0 == 9) {
          ndays = 30 - day0 + 1;
        }
        if (month0 == 10) {
          ndays = 31 - day0 + 1;
        }
        if (month0 == 11) {
          ndays = 30 - day0 + 1;
        }
        if (month0 == 12) {
          ndays = 31 - day0 + 1;
        }
      }
      for (int i = month0 + 1; i <= month1; i++) {
        if (i < month1) {
          ndays += dpermonth[i - 1];
        }
        else if (i == month1) {
          ndays += day1 - 1;
        }
      }
    }

    System.out.println("\nNumarul de zile de la data din CE la data masuratorilor: " + ndays);
  
    return ndays;
  }


  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in); // pentru a da valori de la tastatura (ca cin in c++)

    int zaid, day0, month0, year0, day1, month1, year1;
    double A0; // Activitatea din CE
    double A;  // Activitatea calculata
    double halfLife;
    double time;


    System.out.println("\nTastati ZAID-ul radionuclidului (ZAID = Z*1000+A)");
    System.out.println("Exemplu: ZAID(60Co) = 27*1000+60 = 27060");
    System.out.print("ZAID: ");
    zaid = scanner.nextInt();
    System.out.println("\nData de referinta din Certificatul de Etalonare (mm/dd/yyyy):");
    System.out.print("mm (ex: 9 = septembrie): ");
    month0 = scanner.nextInt();
    System.out.print("dd (ex: 3, 28): ");
    day0 = scanner.nextInt();
    System.out.print("yyyy (ex: 2020): ");
    year0 = scanner.nextInt();
    System.out.print("Activitatea la data din CE: ");
    A0 = scanner.nextDouble();
    System.out.println("\nData cand s-au efectuat masuratorile (mm/dd/yyyy):");
    System.out.print("mm: ");
    month1 = scanner.nextInt();
    System.out.print("dd: ");
    day1 = scanner.nextInt();
    System.out.print("yyyy: ");
    year1 = scanner.nextInt();


    halfLife = ZAIDfind(zaid);
    halfLife *= 365.2422; // convertire din ani (asa cum este trecut in documentul txt) in zile
    time = timeCalc(day0, month0, year0, day1, month1, year1); // este deja calculat in zile
    A = radioactiveDecay(A0, halfLife, time);


    System.out.println("Activitatea la data masuratorilor: " + A + "\n");

    scanner.close();
  }
}
