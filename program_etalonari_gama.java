import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;


public class program_etalonari_gama
{
  // Sample average
  static double average(int[] pArray)
  {
    if (pArray.length > 0) {
      double sum = 0;
      double avg;

      for (int i = 0; i < pArray.length; i++) {
        sum += pArray[i];
      }
      avg = sum / pArray.length;

      return avg;
    }

    return Math.pow(10,7); // returneaza 10^7 daca array-ul introdus nu are dimensiune
  }


  // Sample standard deviation
  static double stdev(int[] pArray)
  {
    if (pArray.length > 0) {
      double sum = 0;
      double avg;
      double s;


      // Calcul valoare medie
      for (int i = 0; i < pArray.length; i++) {
        sum += pArray[i];
      }
      avg = sum / pArray.length;
      
      // Calcul dev. standard
      sum = 0;
      for (int i = 0; i < pArray.length; i++) {
        sum += (pArray[i] - avg) * (pArray[i] - avg);
      }
      s = Math.sqrt(sum / (pArray.length - 1));


      return s;
    }

    return Math.pow(10,7); // returneaza 10^7 daca array-ul introdus nu are dimensiune
  }


  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    int[] n = new int[3]; // 3 seturi de masuratori, pentru fond, etalon si beneficiar
    String undo;

    System.out.println("Numarul de masuratori pentru fond, sursa etalon si sursa beneficiar:");
    System.out.print("n_fond: ");        n[0] = scanner.nextInt();
    System.out.print("n_etalon: ");      n[1] = scanner.nextInt();
    System.out.print("n_beneficiar: ");  n[2] = scanner.nextInt();

    // Vectori cu valorile masurate pentru fond, cu sursa etalon si cu sursa beneficiar
    int[] fond = new int[n[0]];
    int[] etalon = new int[n[1]];
    int[] beneficiar = new int[n[2]];


    // Valori masurate S. Fond
    for (int i = 0; i < n[0]; i++) {
      if (i == 0) {
        System.out.println("\nValorile masurate pentru sursa de Fond:");
      }

      System.out.print((i + 1) + ". ");
      fond[i] = scanner.nextInt();
      scanner.nextLine(); // consume the leftover newline

      System.out.println("Tasteaza u daca valoarea introdusa anterior este gresita");
      undo = scanner.nextLine();

      if (undo.equalsIgnoreCase("u")) {
        System.out.print("Reintrodu valoarea: ");
        fond[i] = scanner.nextInt();
        scanner.nextLine(); // consume newline again
      }
    }

    // Valori masurate S. Etalon
    for (int i = 0; i < n[1]; i++) {
      if (i == 0) {
        System.out.println("\nValorile masurate pentru sursa Etalon:");
      }

      System.out.print((i + 1) + ". ");
      etalon[i] = scanner.nextInt();
      scanner.nextLine();

      System.out.println("Tasteaza u daca valoarea introdusa anterior este gresita");
      undo = scanner.nextLine();

      if (undo.equalsIgnoreCase("u")) {
        System.out.print("Reintrodu valoarea: ");
        etalon[i] = scanner.nextInt();
        scanner.nextLine();
      }
    }

    // Valori masurate S. Beneficiar
    for (int i = 0; i < n[2]; i++) {
      if (i == 0) {
        System.out.println("\nValorile masurate pentru sursa Beneficiar:");
      }

      System.out.print((i + 1) + ". ");
      beneficiar[i] = scanner.nextInt();
      scanner.nextLine();

      System.out.println("Tasteaza u daca valoarea introdusa anterior este gresita");
      undo = scanner.nextLine();

      if (undo.equalsIgnoreCase("u")) {
        System.out.print("Reintrodu valoarea: ");
        beneficiar[i] = scanner.nextInt();
        scanner.nextLine();
      }
    }



    // Umplere fisier TXT
    String fileName = "rezultat.txt";

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

      // ===== FOND =====
      writer.write("----- FOND -----");
      writer.newLine();

      System.out.print("\nA = ");
      String A = scanner.nextLine();

      writer.write("A = " + A);
      writer.newLine();

      writer.write("Valori masurate:");
      writer.newLine();

      for (int i = 0; i < n[0]; i++) {
        writer.write((i + 1) + ". " + fond[i]);
        writer.newLine();
      }

      writer.write("Valoarea medie: ");
      writer.write(String.valueOf(average(fond)));
      writer.newLine();

      writer.write("Abaterea individuala, s_n-1: ");
      writer.write(String.valueOf(stdev(fond)));
      writer.newLine();

      writer.write("Abaterea medie, s_n: ");
      writer.write(String.valueOf(stdev(fond) / Math.sqrt(fond.length)));
      writer.newLine();

      writer.write("s_n (%): ");
      writer.write(100 * (stdev(fond) / Math.sqrt(fond.length)) / average(fond) + " %");
      writer.newLine();

      System.out.print("Timpul de masurare pentru Fond, in secunde: ");
      double T_fond = scanner.nextDouble();
      scanner.nextLine();

      writer.write("T_masurare (s): ");
      writer.write(String.valueOf(T_fond));
      writer.newLine();

      writer.write("R_fond (imp/s): ");
      writer.write(String.valueOf(average(fond) / T_fond));
      writer.newLine();


      // ===== SURSA ETALON =====
      writer.newLine();
      writer.write("----- SURSA ETALON -----");
      writer.newLine();

      writer.write("CE/indicativ: ");
      System.out.print("\nCE/indicativ S. Etalon: ");
      String SEG = scanner.nextLine();
      writer.write(SEG);
      writer.newLine();

      writer.write("Geometrie: ");
      System.out.print("Geometrie S. Etalon: ");
      String geometrie = scanner.nextLine();
      writer.write(geometrie);
      writer.newLine();

      writer.write("Radionuclid: ");
      System.out.print("Radionuclid Etalon (ex: Cs-137): ");
      String radionuclid = scanner.nextLine();
      writer.write(radionuclid);
      writer.newLine();

      writer.write("Valori masurate:");
      writer.newLine();

      for (int i = 0; i < n[1]; i++) {
        writer.write((i + 1) + ". " + etalon[i]);
        writer.newLine();
      }

      writer.write("Valoarea medie: ");
      writer.write(String.valueOf(average(etalon)));
      writer.newLine();

      writer.write("Abaterea individuala, s_n-1: ");
      writer.write(String.valueOf(stdev(etalon)));
      writer.newLine();

      writer.write("Abaterea medie, s_n: ");
      writer.write(String.valueOf(stdev(etalon) / Math.sqrt(etalon.length)));
      writer.newLine();

      writer.write("s_n (%): ");
      writer.write(100 * (stdev(etalon) / Math.sqrt(etalon.length)) / average(etalon) + " %");
      writer.newLine();

      System.out.print("Timpul de masurare pentru S. Etalon, in secunde: ");
      double T_etalon = scanner.nextDouble();
      scanner.nextLine();

      writer.write("u_fond (%): ");
      double R_fond = average(fond) / T_fond;
      double s_nproc_fond = (stdev(fond) / Math.sqrt(fond.length)) / average(fond);
      double u_fond_etalon = ( R_fond / (average(etalon) / T_etalon - R_fond) ) * s_nproc_fond;
      writer.write(100 * u_fond_etalon + " %");
      writer.newLine();

      writer.write("T_masurare (s): ");
      writer.write(String.valueOf(T_etalon));
      writer.newLine();

      writer.write("N'_gama (imp/s): ");
      writer.write(String.valueOf(average(etalon) / T_etalon));
      writer.newLine();

      writer.write("Valoarea conv. adev.:");
      writer.newLine();

      writer.write("N_gama (s^-1): ");
      double N_gama_etalon = (average(etalon) / T_etalon) / (1 - 0.00001 * (average(etalon) / T_etalon)) - R_fond;
      writer.write(String.valueOf(N_gama_etalon));
      writer.newLine();

      writer.write("A_CE, Bq adusa la data masurarii (la zi): ");
      System.out.print("Activitatea in Bq adusa la zi din CE, pentru s. etalon: ");
      double A_etalon = scanner.nextDouble();
      scanner.nextLine();
      writer.write(String.valueOf(A_etalon));
      writer.newLine();

      System.out.print("Incertitudinea in Activitate, in Bq, adusa la zi din CE, pentru s. etalon: ");
      double s_n_etalon = scanner.nextDouble();
      scanner.nextLine();
      writer.write("Incert. (Bq), k=2: ");
      writer.write(String.valueOf(s_n_etalon));
      writer.newLine();

      double CE_etalon = s_n_etalon / A_etalon;
      writer.write("Incert. (%), k=1: " + 100 * CE_etalon / 2 + " %");
      writer.newLine();

      writer.write("CE, k=2: " + 100 * CE_etalon + " %");
      writer.newLine();


      // ===== SURSA BENEFICIAR =====
      writer.newLine();
      writer.write("----- SURSA BENEFICIAR -----");
      writer.newLine();

      writer.write("CE/indicativ: ");
      System.out.print("\nCE/indicativ S. Beneficiar: ");
      SEG = scanner.nextLine();
      writer.write(SEG);
      writer.newLine();

      writer.write("Geometrie: ");
      System.out.print("Geometrie S. Beneficiar: ");
      geometrie = scanner.nextLine();
      writer.write(geometrie);
      writer.newLine();

      writer.write("Radionuclid: ");
      System.out.print("Radionuclid Beneficiar (ex: Cs-137): ");
      radionuclid = scanner.nextLine();
      writer.write(radionuclid);
      writer.newLine();

      writer.write("Valori masurate:");
      writer.newLine();

      for (int i = 0; i < n[2]; i++) {
        writer.write((i + 1) + ". " + beneficiar[i]);
        writer.newLine();
      }

      writer.write("Valoarea medie: ");
      writer.write(String.valueOf(average(beneficiar)));
      writer.newLine();

      writer.write("Abaterea individuala, s_n-1: ");
      writer.write(String.valueOf(stdev(beneficiar)));
      writer.newLine();

      writer.write("Abaterea medie, s_n: ");
      writer.write(String.valueOf(stdev(beneficiar) / Math.sqrt(beneficiar.length)));
      writer.newLine();

      writer.write("s_n (%): ");
      writer.write(100 * (stdev(beneficiar) / Math.sqrt(beneficiar.length)) / average(beneficiar) + " %");
      writer.newLine();

      System.out.print("Timpul de masurare pentru S. Beneficiar, in secunde: ");
      double T_beneficiar = scanner.nextDouble();
      scanner.nextLine();

      writer.write("u_fond (%): ");
      double u_fond_beneficiar = ( R_fond / (average(beneficiar) / T_beneficiar - R_fond) ) * s_nproc_fond;
      writer.write(100 * u_fond_beneficiar + " %");
      writer.newLine();

      writer.write("T_masurare (s): ");
      writer.write(String.valueOf(T_beneficiar));
      writer.newLine();

      writer.write("N'_gama (imp/s): ");
      writer.write(String.valueOf(average(beneficiar) / T_beneficiar));
      writer.newLine();

      writer.write("Valoarea conv. adev.:");
      writer.newLine();

      writer.write("N_gama (s^-1): ");
      double N_gama_beneficiar = (average(beneficiar) / T_beneficiar) / (1 - 0.00001 * (average(beneficiar) / T_beneficiar)) - R_fond;
      writer.write(String.valueOf(N_gama_beneficiar));
      writer.newLine();

      writer.write("A_masurata (Bq): ");
      double A_masurata = (N_gama_beneficiar * A_etalon) / N_gama_etalon;
      writer.write(String.valueOf(A_masurata));
      writer.newLine();

      writer.write("Incert. (Bq), k=2: ");
      double s_n_masurat = (CE_etalon / 2)*(CE_etalon / 2) + ((stdev(beneficiar) / Math.sqrt(beneficiar.length)) / average(beneficiar))*((stdev(beneficiar) / Math.sqrt(beneficiar.length)) / average(beneficiar)) + u_fond_beneficiar*u_fond_beneficiar + ((stdev(etalon) / Math.sqrt(etalon.length)) / average(etalon))*((stdev(etalon) / Math.sqrt(etalon.length)) / average(etalon)) + u_fond_etalon*u_fond_etalon;
      s_n_masurat = Math.sqrt(s_n_masurat) * 2;
      writer.write(String.valueOf(s_n_masurat * A_masurata));
      writer.newLine();

      writer.write("Incert. (%): " + s_n_masurat * 100 + " %");
      writer.newLine();

      writer.write("A_CE, Bq adusa la data masurarii (la zi): ");
      System.out.print("Activitatea in Bq adusa la zi din CE, pentru s. beneficiar: ");
      double A_beneficiar = scanner.nextDouble();
      scanner.nextLine();
      writer.write(String.valueOf(A_beneficiar));
      writer.newLine();

      System.out.print("Incertitudinea in Activitate, in Bq, adusa la zi din CE, pentru s. beneficiar: ");
      double s_n_beneficiar = scanner.nextDouble();
      scanner.nextLine();
      writer.write("Incert. (Bq), k=2: ");
      writer.write(String.valueOf(s_n_beneficiar));
      writer.newLine();

      writer.write("Incert. (%): " + 100 * s_n_beneficiar / A_beneficiar + " %");
      writer.newLine();

      writer.newLine();
      writer.write("deltaA: " + 100 * (A_masurata - A_beneficiar) / A_beneficiar + " %");
      writer.newLine();

      writer.newLine();
      writer.write("A_CA (Bq): " + A_masurata);
      writer.newLine();

      writer.write("Incert. (Bq), k=2: " + s_n_masurat * A_masurata);
      writer.newLine();

      writer.write("Incert. (%): "+ s_n_masurat * 100 + " %");
      writer.newLine();

      writer.write("MEDIA: " + (A_masurata + A_beneficiar) / 2);
      writer.newLine();

      writer.write("Incert., k=2: " + s_n_masurat * (A_masurata + A_beneficiar) / 2);
      writer.newLine();

      writer.write("Incert. (%): " + s_n_masurat * 100 + " %");
      writer.newLine();

      writer.newLine();
      writer.write("END.");

    } catch (IOException e) {
      
      e.printStackTrace();

    }


    scanner.close();
  }
}
