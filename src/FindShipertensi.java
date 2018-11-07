import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author X441U
 */
public class FindShipertensi {
     public static ArrayList<String> readTeks(String bacateks) throws FileNotFoundException, IOException {
        File bacafile = new File(bacateks);
        FileReader inputDokumen = new FileReader(bacafile);
        BufferedReader bufferBaca = new BufferedReader(inputDokumen);
        StringBuffer content = new StringBuffer();
        String barisData;
        ArrayList<String> data = new ArrayList<String>();
        while ((barisData = bufferBaca.readLine()) != null) {
            content.append(barisData);
            content.append(System.getProperty("line.separator"));
            data.add(barisData);
        }
        return data;
}
     
      public static ArrayList<String> token(String kalimat) throws FileNotFoundException, IOException {
        ArrayList<String> listKata = new ArrayList<String>();
        StringTokenizer token = new StringTokenizer(kalimat, ",");//pemisahan kata dengan delimiter koma
        while (token.hasMoreTokens()) {
            listKata.add(token.nextToken());
        }
        return listKata;
    }
 
    public static String[][] saveToArray(ArrayList<String> input) throws FileNotFoundException, IOException{
        String[][] data=new String[input.size()][3];
        for (int i = 0; i < input.size(); i++) {
             ArrayList<String> item=token(input.get(i));
             for (int j = 0; j < item.size(); j++) {
                data[i][j]=item.get(j);//memasukkan data ke dalam array
            }
        }
        return data;
    }
  
    public static void input(){
        Scanner scan = new Scanner(System.in);
        int jumFitur=2;
        
        String arrayOfFitur[] = new String [jumFitur];
        for(int i =0; i< arrayOfFitur.length; i++){
            System.out.println("Masukkan fitur, 1. Panjang atau 2. Lebar " + (i+1) + " : ");
            arrayOfFitur[i] = scan.nextLine();
        }
        for(int i = 0; i < arrayOfFitur.length; i++){
            System.out.print(arrayOfFitur[i] + "\n");
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        //inisialisasi
        ArrayList<String> listData = readTeks("hipertensi.txt");
        String[][] dataTraining    = saveToArray(listData);
        String[][] label           = new String[7][4];
        String[][] fiturUsia       = new String[7][4];
        String[][] fiturBerat      = new String[7][4];
        String[][] hipous          = null;
        String[][] hipobe          = null;
        String[][] hahipu          = new String[7][4];
        String[][] hahipb          = new String[7][4];
        String[][] dataTes         = new String[2][2];
 
        //mengambil dataTraining
        System.out.println("===============================================");
        System.out.println("Class Label : " + dataTraining[0][0] + " , " + dataTraining[0][1] );
        System.out.println("Fitur       : " + dataTraining[1][0] + " , " + dataTraining[1][1] );
        for(int c = 2; c < 7 ; c++){
            System.out.println("Data Set ke-"  + (c-1) +  " " +dataTraining[c][0] + " , " + dataTraining[c][1] + " , " + dataTraining[c][2]);
        }
        System.out.println("===============================================");

        System.out.println("Hipotesis tidak mengidap Hipertensi");
        
        //pisah class label dan fitur
        for(int i=2;i<dataTraining.length;i++){
            label[i][2] = dataTraining[i][2];
            fiturUsia[i][0] = dataTraining[i][0];
            fiturBerat[i][1] = dataTraining[i][1];
            //mencari hipotesis
            if(label[i][2].equals(dataTraining[0][0])){
                if(hipous == null){
                       hipous = new String[7][2];
                       hipous[i][0] = fiturUsia[i][0];
                       hipous[i][1] = fiturBerat[i][1];
                }
                else{
                       hipous[i][0] = fiturUsia[i][0];
                       hipous[i][1] = fiturBerat[i][1];
                        if(hipous.equals(fiturUsia[i][0]))
                            hipous[i][0] = fiturUsia[i][0];
                        else
                             hipous[i][0] = "?";                       
                        if(hipous.equals(fiturBerat[i][1]))
                              hipous[i][1] = fiturBerat[i][1];                       
                        else
                         hipous[i][1] = "?";
                }             
                         System.out.println("<" + hipous[i][0] + " , " + hipous[i][1] + ">");
                         hahipu[i][0] = hipous[i][0];
                         hahipu[i][1] = hipous[i][1];
            }
            else{
                if(label[i][2].equals(dataTraining[0][1])){
                if(hipobe == null){
                       hipobe = new String[7][2];
                       hipobe[i][0] = fiturUsia[i][0];
                       hipobe[i][1] = fiturBerat[i][1];
                }
                else{
                       hipobe[i][0] = fiturUsia[i][0];
                       hipobe[i][1] = fiturBerat[i][1];
                        if(hipobe.equals(fiturUsia[i][0]))
                            hipobe[i][0] = fiturUsia[i][0];
                        else
                             hipobe[i][0] = "?";                      
                        if(hipobe[i][1]== fiturBerat[i][1])
                              hipobe[i][1] = fiturBerat[i][1];                        
                        else
                         hipobe[i][1] = "?";
                }             
                         System.out.println("Hipotesis mengidap hipertensi");
                         System.out.println("<" + hipobe[i][0] + "," + hipobe[i][1] + ">");
                         hahipb[i][0] = hipobe[i][0];
                         hahipb[i][1] = hipobe[i][1];
                }
            } 
        }
        System.out.println("=================================================");
        //input data tes
        Scanner sc = new Scanner (System.in);  
        for(int a=0;a<1;a++){
              for(int b=0;b<2;b++){
                  System.out.print("Masukkan Fitur (1. Usia, 2. Berat): ");
                  dataTes[a][b]=sc.nextLine();
              }
          }
        System.out.println("==================================================");
        //hasil
        System.out.println("Hasil :");
        if((dataTes[0][0].equals(hahipu[4][0]) || hahipu[4][0].equals("?") && (dataTes[0][1].equals(hahipu[4][1]) || hahipu[4][1].equals("?")))){
            System.out.println("No");
        }else{
            System.out.println("Bukan No");
        }
        if((dataTes[0][0].equals(hahipb[6][0]) || hahipb[6][0].equals("?") && (dataTes[0][1].equals(hahipb[6][1]) || hahipb[6][1].equals("?")))){
            System.out.println("Yes");
        }else{
            System.out.println("Bukan Yes");
        }
        System.out.println("==================================================");
    }
    }

