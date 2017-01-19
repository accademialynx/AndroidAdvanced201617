package com.lynxspa.androidadvanced201617.Utils;

/**
 * Created by Samuele on 17/01/2017.
 */

public enum SurveyType {

      GPS(0),WiFi(1),NFC(2),Beacon(3);
      int codice;



      SurveyType(int i) {
            this.codice= i;
      }

      public int getCodice() {
            return codice;
      }

      public SurveyType fromCode(int codice) {
            SurveyType s = null;
            switch (codice) {
                  case 0:
                         s= SurveyType.GPS;
                  case 1:
                         s= SurveyType.WiFi;
                  case 2:
                        s= SurveyType.NFC;
                  case 3:
                        s=  SurveyType.Beacon;
            }

            return s;
      }
}
