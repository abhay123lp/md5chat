/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgoritmoMD5;

import java.util.ArrayList;

/**
 *
 * @author Shadow
 */
public class MD5 {
    private String pass;
    private int table[];
    
    public MD5(){
        table=new int[65];
        for(int i=0;i<65;i++){
            table[i]=(int)(4294967296L*Math.abs(Math.sin(i)));
        }
    }
    
    public String getMd5(String pass){
        this.pass=pass;
        String hd="";
        //Paso 1
        int mSize=pass.length()*8;//tamaño total del mensaje en bits
        int t=mSize+64; //tamaño de m' sin relleno
        
        /*k representa el numero de veces que entra 512 en el mensaje
         * y esta constante se mad siempre a techo
         */
        int k=(int)Math.ceil((double)t/(double)512);
        
        int n=(k*512)-t;//se obtienen los bits de relleno representado por n
        
        //aqui se guardan los valores de cada caracter en binario
        ArrayList<String> binM=new ArrayList<String>();
        String bin;//valor binario representado en 8 bits
        
        byte data[]=pass.getBytes();//recuperar el valor ascii de cada caracter
        //representar el ascii de cada caracter en su forma binaria de 8 bits
        for(int i=0;i<data.length;i++){
            bin=Integer.toBinaryString(data[i]);
            switch(bin.length()){
                //Se acompleta con '0' para tener la representacion de 8 bits
                case 6:bin="00"+bin;break;
                case 7:bin="0"+bin;break;
            }
            binM.add(bin);
        }
        
        //agregar los bits de relleno en bloques de 8 bits
        if(n>0){
            binM.add("10000000");
            for(int i=1;i<n/8;i++){
                binM.add("00000000");
            }
        }
        
        //Paso 2
        /*longitud tras añadir los bits de relleno 
         * representado en 64 bits
         */
        int mp=binM.size()*8;//tamaño de m' con relleno
                
        /*p1 y p2 son de 32 bits
         * p1 mas significativos
         * p2 menos significaivos
         * bin64 representacion binaria del tamaño de m'
         * ba son p1 y p2 intercabiados
         */
        String bin64,p1,p2,ba;
        bin64=decBin(mp);
        
        p2=bin64.substring(bin64.length()-32, bin64.length());
        
        p1=bin64.substring(bin64.length()-64, bin64.length()-32);
     
        //intercambiar los 32 bits Mas significativos por los menos significativos
        ba=p2+p1;
        //agregar en ba a m' en bloques d 8 bits
        for(int i=0;i<64;i=i+8){
            binM.add(ba.substring(i, i+8));
        }
        
        //Paso 3
        //Buffer de 32 bits en hexadecimal
        
        int A = 0x01234567;
        int B = 0x89abcdef;
        int C = 0xfedcba98;
        int D = 0x76543210;
        
        //Paso 4
        
        //Almacenar los valores de A,B,C,D
        int AA=A;
        int BB=B;
        int CC=C;
        int DD=D;
        
        //se arman los bloques de 512 bits
        ArrayList<ArrayList<String>> m512=new ArrayList<ArrayList<String>>();
        //se guardan los subbloques de 16 bits
        ArrayList<String> subm16;

        /*se crean los sub-bloques de 16 bits
         * a partir de dataM, el cual contiene
         * bloques de 8 bits
         */
        
        for(int j=0;j<k;j++){
           subm16 = new ArrayList<String>();
           for(int i=j*64;i<64*(j+1);i=i+2){
               subm16.add(binM.get(i)+binM.get(i+1));
           }
           m512.add(subm16);
        }
                
        //k representa el numero de bloques de tamaño 512
        for(int x=0;x<k;x++){
            for(int j=0;j<32;j++){
                A = etapa1(A,B,C,D,0,7,1,m512,x,j);
                D = etapa1(D,A,B,C,1,12,2,m512,x,j);
                C = etapa1(C,D,A,B,2,17,3,m512,x,j);
                B = etapa1(B,C,D,A,3,22,4,m512,x,j);

                A = etapa1(A,B,C,D,4,7,5,m512,x,j);
                D = etapa1(D,A,B,C,5,12,6,m512,x,j);
                C = etapa1(C,D,A,B,6,17,7,m512,x,j);
                B = etapa1(B,C,D,A,7,22,8,m512,x,j);

                A = etapa1(A,B,C,D,8,7,9,m512,x,j);
                D = etapa1(D,A,B,C,9,12,10,m512,x,j);
                C = etapa1(C,D,A,B,10,17,11,m512,x,j);
                B = etapa1(B,C,D,A,11,22,12,m512,x,j);

                A = etapa1(A,B,C,D,12,7,13,m512,x,j);
                D = etapa1(D,A,B,C,13,12,14,m512,x,j);
                C = etapa1(C,D,A,B,14,17,15,m512,x,j);
                B = etapa1(B,C,D,A,15,22,16,m512,x,j);

                A = etapa2(A,B,C,D,1,5,17,m512,x,j);
                D = etapa2(D,A,B,C,6,9,18,m512,x,j);
                C = etapa2(C,D,A,B,11,14,19,m512,x,j);
                B = etapa2(B,C,D,A,0,20,20,m512,x,j);

                A = etapa2(A,B,C,D,5,5,21,m512,x,j);
                D = etapa2(D,A,B,C,10,9,22,m512,x,j);
                C = etapa2(C,D,A,B,15,14,23,m512,x,j);
                B = etapa2(B,C,D,A,4,20,24,m512,x,j);

                A = etapa2(A,B,C,D,9,5,25,m512,x,j);
                D = etapa2(D,A,B,C,14,9,26,m512,x,j);
                C = etapa2(C,D,A,B,3,14,27,m512,x,j);
                B = etapa2(B,C,D,A,8,20,28,m512,x,j);

                A = etapa2(A,B,C,D,13,5,29,m512,x,j);
                D = etapa2(D,A,B,C,2,9,30,m512,x,j);
                C = etapa2(C,D,A,B,7,14,31,m512,x,j);
                B = etapa2(B,C,D,A,12,20,32,m512,x,j);

                A = etapa3(A,B,C,D,5,4,33,m512,x,j);
                D = etapa3(D,A,B,C,8,11,34,m512,x,j);
                C = etapa3(C,D,A,B,11,16,35,m512,x,j);
                B = etapa3(B,C,D,A,14,23,36,m512,x,j);

                A = etapa3(A,B,C,D,1,4,37,m512,x,j);
                D = etapa3(D,A,B,C,4,11,38,m512,x,j);
                C = etapa3(C,D,A,B,7,16,39,m512,x,j);
                B = etapa3(B,C,D,A,10,23,40,m512,x,j);

                A = etapa3(A,B,C,D,13,4,41,m512,x,j);
                D = etapa3(D,A,B,C,0,11,42,m512,x,j);
                C = etapa3(C,D,A,B,3,16,43,m512,x,j);
                B = etapa3(B,C,D,A,6,23,44,m512,x,j);

                A = etapa3(A,B,C,D,9,4,45,m512,x,j);
                D = etapa3(D,A,B,C,12,11,46,m512,x,j);
                C = etapa3(C,D,A,B,15,16,47,m512,x,j);
                B = etapa3(B,C,D,A,2,23,48,m512,x,j);

                A = etapa4(A,B,C,D,0,6,49,m512,x,j);
                D = etapa4(D,A,B,C,7,10,50,m512,x,j);
                C = etapa4(C,D,A,B,14,15,51,m512,x,j);
                B = etapa4(B,C,D,A,5,21,52,m512,x,j);

                A = etapa4(A,B,C,D,12,6,53,m512,x,j);
                D = etapa4(D,A,B,C,3,10,54,m512,x,j);
                C = etapa4(C,D,A,B,10,15,55,m512,x,j);
                B = etapa4(B,C,D,A,1,21,56,m512,x,j);

                A = etapa4(A,B,C,D,8,6,57,m512,x,j);
                D = etapa4(D,A,B,C,15,10,58,m512,x,j);
                C = etapa4(C,D,A,B,6,15,59,m512,x,j);
                B = etapa4(B,C,D,A,13,21,60,m512,x,j);

                A = etapa4(A,B,C,D,4,6,61,m512,x,j);
                D = etapa4(D,A,B,C,11,10,62,m512,x,j);
                C = etapa4(C,D,A,B,2,15,63,m512,x,j);
                B = etapa4(B,C,D,A,9,21,64,m512,x,j);

                A = A + AA;
                B = B + BB;
                C = C + CC;
                D = D + DD;
            }
        }
        //Paso 5
        
        hd=hex32bits(D)+hex32bits(C)+hex32bits(B)+hex32bits(A);
        
        return hd;
    }
    
    //funciones auxiliares
    public int F(int x,int y,int z){
        return (x & y) | ((~x) & z);
    }
    
    public int G(int x,int y,int z){
        return (x & z) | (y & (~z));
    }
    
    public int H(int x,int y,int z){
        return x ^ y ^z;
    }
    
    public int I(int x,int y,int z){
        return y ^ (x | (~z));
    }
    
    //funciones: etapas
    //Pendiente revisar etapas
    
    public int etapa1(int a,int b,int c,int d, int k,int s,int i,ArrayList<ArrayList<String>> m512,int x,int j){
        char binChar=m512.get(x).get(j).charAt(k);
        int binInt=Integer.parseInt(""+binChar);
        return b + ((a + F(b,c,d) + binInt + table[i]) << s);
    }
    
    public int etapa2(int a,int b,int c,int d, int k,int s,int i,ArrayList<ArrayList<String>> m512,int x,int j){
        char binChar=m512.get(x).get(j).charAt(k);
        int binInt=Integer.parseInt(""+binChar);
        return b + ((a + G(b,c,d) + binInt + table[i]) << s);
    }
    
    public int etapa3(int a,int b,int c,int d, int k,int s,int i,ArrayList<ArrayList<String>> m512,int x,int j){
        char binChar=m512.get(x).get(j).charAt(k);
        int binInt=Integer.parseInt(""+binChar);
        return b + ((a + H(b,c,d) + binInt + table[i]) << s);
    }
    
    public int etapa4(int a,int b,int c,int d, int k,int s,int i,ArrayList<ArrayList<String>> m512,int x,int j){
        char binChar=m512.get(x).get(j).charAt(k);
        int binInt=Integer.parseInt(""+binChar);
        return b + ((a + I(b,c,d) + binInt + table[i]) << s);
    }
    
    //funciones extra
    private static String decBin(int dec){
        String bin64="";
        while(dec>0){
            bin64=(dec%2)+bin64;
            dec=dec/2;
        }
        
        int x=64-bin64.length();
        
        for(int i=0;i<x;i++)
            bin64='0'+bin64;
        return bin64;
    }
    
    public String hex32bits(int X){
        String hex=Integer.toHexString(X);
        if(hex.length()<8){
            for(int i=0;i<8-hex.length();i++)
                hex='0'+hex;
        }
        return hex;
    }
    
    public static void main(String args[]){
        System.out.println("d41d8cd98f00b204e9800998ecf8427e");
        MD5 f2=new MD5();
        System.out.println(f2.getMd5("d41d8cd98f00b204e9800998ecf8427ed41d8cd98f00b204e9800998ecf8427e"));
    }
    
}
