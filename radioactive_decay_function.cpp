#include <cmath>
#include <fstream>
#include <iostream>

using namespace std;


double radioactiveDecay(double initialAmount, double halfLife, double time) {
  double lambda = log(2) / halfLife;
  double remainingAmount = initialAmount * exp(-lambda * time);

  return remainingAmount;
}


double ZAIDfind(int zaid) {
  ifstream ifile;
  string fileName = "zaid_data.txt"; // numele fisierului care contine ZAID | T1/2
  int ZA_ID, aux = 0;
  double hLife; // hLife in ani (ca in Excel)
  double halfLife;


  ifile.open(fileName);

  if (!ifile) {
    cout << "\nEROARE: Nu s-a reusit deschiderea fisierului " << fileName << " !\n";
  }
  else {
    while (1) {
      ifile >> ZA_ID >> hLife;
      if (ifile.eof()) {
        break;
      }
      if (ZA_ID == zaid) {
        halfLife = hLife;
        break; // oprire loop dupa gasirea lui zaid dat de la tastatura
        aux = 1;
      }
    }

    if (aux == 0) {
      cout << "\nEROARE: ZAID nu a fost gasit.\n";
    }
  }

  ifile.close();

  if (aux == 0) {
    return pow(10,7); // returneaza 10^7 (o valoare mare pentru T1/2)
  }
  else return halfLife;
}


double timeCalc(int day0, int month0, int year0, int day1, int month1, int year1) {
  int ndays = 0;
  int dpermonth[12]; // numarul de zile din fiecare luna
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

  cout << "\nNumarul de zile de la data din CE la data masuratorilor: " << ndays << endl;
  return ndays;
}


int main() {
  int zaid, day0, month0, year0, day1, month1, year1;
  double A0; // activitatea din CE
  double A;  // activitatea calculata
  double halfLife; // timpul de injumatatire al radionuclidului ZAID
  double time; // diferenta de timp de la data din CE si data masuratorilor


  // Determinarea activitatii la data masuratorilor

  cout << "Tastati ZAID-ul radionuclidului (ZAID = Z*1000+A)\nExemplu: "
          "ZAID(60Co) = 27*1000+60=27060\nZAID: ";
  cin >> zaid;
  cout << "\nData de referinta din Certificatul de Etalonare (mm/dd/yyyy):\n";
  cout << "mm (ex: 9 = septembrie): ";
  cin >> month0;
  cout << "dd (ex: 3, 28): ";
  cin >> day0;
  cout << "yyyy (ex: 2020): ";
  cin >> year0;
  cout << "Activitatea la data din CE: ";
  cin >> A0;
  cout << "\nData cand s-au efectuat masuratorile (mm/dd/yyyy):\n";
  cout << "mm: ";
  cin >> month1;
  cout << "dd: ";
  cin >> day1;
  cout << "yyyy: ";
  cin >> year1;


  halfLife = ZAIDfind(zaid);
  halfLife *= 365.2422; // convertire din ani (asa cum este trecut in documentul txt) in zile
  time = timeCalc(day0, month0, year0, day1, month1, year1); // este deja calculat in zile
  A = radioactiveDecay(A0, halfLife, time);

  cout << "Activitatea la data masuratorilor: " << A << endl;

  return 0;
}
