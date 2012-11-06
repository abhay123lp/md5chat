package Base;
/**
 *  Clase que implementa el algoritmo MD5 para encripatacion y seguridad de
 *  redes.
 * @author Mario
 */
import java.lang.Object.*;
import java.util.ArrayList;

public class md5 {

    /**
     * Constructor de la clase.
     */
    public md5(){

    }

    /**
     * Funcion F de auxiliar. Con 3 entradas de 32 bits y una salida de 32 bits
     * @param x un numero entero de 32 bits
     * @param y un numero entero de 32 bits
     * @param z un numero entero de 32 bits
     * @return Un numero de 32 bits resultado de las operaciones realizadas
     * durante la funcion.
     */
    public static int F(int x, int y, int z)
    {
         int f = (x & y) | ((~(x)) & z);
         return f;
    }

    /**
     * Funcion G de auxiliar. Con 3 entradas de 32 bits y una salida de 32 bits
     * @param x un numero entero de 32 bits
     * @param y un numero entero de 32 bits
     * @param z un numero entero de 32 bits
     * @return Un numero de 32 bits resultado de las operaciones realizadas
     * durante la funcion.
     */
    public static int G(int x, int y, int z)
    {
         int g =  (x & z) | (y & (~(z)));
         return g;
    }

    /**
     * Funcion H de auxiliar. Con 3 entradas de 32 bits y una salida de 32 bits
     * @param x un numero entero de 32 bits
     * @param y un numero entero de 32 bits
     * @param z un numero entero de 32 bits
     * @return Un numero de 32 bits resultado de las operaciones realizadas
     * durante la funcion.
     */
    public static int H(int x, int y, int z)
    {
         int h = x ^ y ^ z ;
         return h;
    }

    /**
     * Funcion I de auxiliar. Con 3 entradas de 32 bits y una salida de 32 bits
     * @param x un numero entero de 32 bits
     * @param y un numero entero de 32 bits
     * @param z un numero entero de 32 bits
     * @return Un numero de 32 bits resultado de las operaciones realizadas
     * durante la funcion.
     */
    public static int I(int x, int y, int z)
    {
         int i = y ^ (x | (~(z))) ;
         return i;
    }

    /**
     * Funcion que obtiene el valor en la posicion k de una tabla con 64
     * elementos construida con la funcion seno; donde cada elemento de la tabla
     * se conforma de la parte entera de la operacion:
     * 4294967296*abs(sin(i)); donde i es la i-esimo lugar de la tabla, 0,1,2,3,....,64
     * @param k Es la posicion de la tabla quiere obtener.
     * @return Regresa el numero entere que se encuentra en la posicion k.
     */
    public static int tabla(int k)
    {
        int T[]= new int[65];
        long e = 4294967296L;
        int l = 2147483647;
        l = l+1;
       // System.out.println(l);
       // T[0]=0;
        //T[1]=3614090360;


        for(int i=1; i<65; i++)
        {
            //T[i]=(int)((53687091*8) * Math.abs(Math.sin(i)));
            T[i]=(int)(e* Math.abs(Math.sin(i)));

            //System.out.println(i+" "+e* Math.abs(Math.sin(i)));
            //System.out.println(i+" "+(int)(e* Math.abs(Math.sin(i))));
        }
        return T[k];
    }

    /**
     * Primera funcion que realizara 16 operaciones y que seran guardadas en las
     * variables A, B, C y D.
     * @param a Variable A declarada con valores hexadecimales.
     * @param b Variable A declarada con valores hexadecimales.
     * @param c Variable A declarada con valores hexadecimales.
     * @param d Variable A declarada con valores hexadecimales.
     * @param k El k-esimo bit de 512 bits que consta el subloque.
     * @param s Numero de corrimientos aplicados.
     * @param i El i-esimo elemento de la tabla de 64 de funcion seno.
     * @param g Arreglo que continen bloques de 512 bits.
     * @param x El x-esimo bloque de 512 en el que fue dividida la oracion.
     * @param j El j-esimo subloque de 16 bits en el que fue dividido el
     * x-esimo bloque.
     * @return Entero que sera guardado en la variable A,B,C,o D.
     */
    public static int fun_F(int a, int b, int c, int d, int k, int s, int i, ArrayList<ArrayList<String>> g, int x, int j)
    {
        int re;
        int tab = tabla(i);
        int f = F(b,c,d);
        char r;
        int rb;
        r = g.get(x).get(j).charAt(k);
        rb = Integer.parseInt(""+r);
        a = b +((a + f + rb +  tab)<<s);

        return re=a;
    }

    /**
     * Segunda funcion que realizara 16 operaciones y que seran guardadas en las
     * variables A, B, C y D.
     * @param a Variable A declarada con valores hexadecimales.
     * @param b Variable A declarada con valores hexadecimales.
     * @param c Variable A declarada con valores hexadecimales.
     * @param d Variable A declarada con valores hexadecimales.
     * @param k El k-esimo bit de 512 bits que consta el subloque.
     * @param s Numero de corrimientos aplicados.
     * @param i El i-esimo elemento de la tabla de 64 de funcion seno.
     * @param g Arreglo que continen bloques de 512 bits.
     * @param x El x-esimo bloque de 512 en el que fue dividida la oracion.
     * @param j El j-esimo subloque de 16 bits en el que fue dividido el
     * x-esimo bloque.
     * @return Entero que sera guardado en la variable A,B,C,o D.
     */
    public static int fun_G(int a, int b, int c, int d, int k, int s, int i, ArrayList<ArrayList<String>> g, int x, int j)
    {
        int re;

        int tab = tabla(i);
        int f = G(b,c,d);
        char r;
        int rb;
        r = g.get(x).get(j).charAt(k);
        rb = Integer.parseInt(""+r);
        a = b +((a + f + rb +  tab)<<s);

        return re=a;
    }

    /**
     * Tercera funcion que realizara 16 operaciones y que seran guardadas en las
     * variables A, B, C y D.
     * @param a Variable A declarada con valores hexadecimales.
     * @param b Variable A declarada con valores hexadecimales.
     * @param c Variable A declarada con valores hexadecimales.
     * @param d Variable A declarada con valores hexadecimales.
     * @param k El k-esimo bit de 512 bits que consta el subloque.
     * @param s Numero de corrimientos aplicados.
     * @param i El i-esimo elemento de la tabla de 64 de funcion seno.
     * @param g Arreglo que continen bloques de 512 bits.
     * @param x El x-esimo bloque de 512 en el que fue dividida la oracion.
     * @param j El j-esimo subloque de 16 bits en el que fue dividido el
     * x-esimo bloque.
     * @return Entero que sera guardado en la variable A,B,C,o D.
     */
    public static int fun_H(int a, int b, int c, int d, int k, int s, int i, ArrayList<ArrayList<String>> g, int x, int j)
    {
        int re;
        int tab = tabla(i);
        int f = H(b,c,d);
        char r;
        int rb;
        r = g.get(x).get(j).charAt(k);
        rb = Integer.parseInt(""+r);
        a = b +((a + f + rb +  tab)<<s);

        return re=a;
    }

    /**
     * Cuarto funcion que realizara 16 operaciones y que seran guardadas en las
     * variables A, B, C y D.
     * @param a Variable A declarada con valores hexadecimales.
     * @param b Variable A declarada con valores hexadecimales.
     * @param c Variable A declarada con valores hexadecimales.
     * @param d Variable A declarada con valores hexadecimales.
     * @param k El k-esimo bit de 512 bits que consta el subloque.
     * @param s Numero de corrimientos aplicados.
     * @param i El i-esimo elemento de la tabla de 64 de funcion seno.
     * @param g Arreglo que continen bloques de 512 bits.
     * @param x El x-esimo bloque de 512 en el que fue dividida la oracion.
     * @param j El j-esimo subloque de 16 bits en el que fue dividido el
     * x-esimo bloque.
     * @return Entero que sera guardado en la variable A,B,C,o D.
     */
    public static int fun_I(int a, int b, int c, int d, int k, int s, int i, ArrayList<ArrayList<String>> g, int x, int j)
    {
        int re;
        int tab = tabla(i);
        int f = I(b,c,d);
        char r;
        int rb;
        r = g.get(x).get(j).charAt(k);
        rb = Integer.parseInt(""+r);
        a = b +((a + f + rb +  tab)<<s);

        return re=a;
    }

    /**
     * Metodo que calcula el MD5. Contiene el algoritmo con los pasos necesarios
     * para la realizacion del calculo de la huella digital.
     * @param password Es una cadena de caracteres de la cual se calculara su
     * MD5
     * @return Regresa una cadena de MD5 ya encriptada del mensaje ingresado.
     */
    public String calcula_MD5(String password)
    {
        String m = password;
        String huella ="";
        ArrayList <String> mp = new ArrayList<String>();//arreglo que guarda todos los bits como unos o ceros en gurpos de 8 bits
        ArrayList <String> mj = new ArrayList<String>();//arreglo que guarda todos los bits como unos o ceros en gurpos de 16 bits
        ArrayList <Integer> mo = new ArrayList<Integer>();//arreglo que guarda todos los bits como enteros en grupos de 8 bits
        ArrayList <Integer> msub = new ArrayList<Integer>();//arreglo que guarda todos los bits como unos o ceros en gurpos de 16 bits
        ArrayList <String> bits = new ArrayList<String>();

        char c;
        int num;
        int lpm = 0;// longitud del mensaje original
        int lm = 0;//llevara el conteo de los bits

        //buffers
        int A, B, C, D;
        int AA, BB, CC, DD;

        A = 0x01234567;
        B = 0x89abcdef;
        C = 0xfedcba98;
        D = 0x76543210;

        for ( int i = 0; i < m.length(); i++ )
         {
          //extraemos los caracteres uno a uno de m
           c = m.charAt(i);
           num = (byte)c;
           mo.add(num);
           mp.add("0"+Integer.toBinaryString(num));
           lm = lm +8;
           lpm = lpm +8;
         }

        int top = 0;

        if(lm >= 0 && lm < 448)
          top = 448;
        if(lm >= 448 && lm < 960)
          top = 960;
        if(lm >= 960 && lm < 1984)
          top = 1984;
        if(lm >= 1984 && lm < 4032)
          top = 4032;
        if(lm >= 4032 && lm < 8128)
          top = 8128;
        if(lm >= 8128 && lm < 16320)
          top = 16320;
        if(lm >= 16320 && lm < 32704)
          top = 32704;


        int diferencia = top - lm;
        //rellenos para llegar a 448
        int r1 = 0x40;//equivalente en binario a 01000000
        int dif2 = (diferencia - 8) / 8;
        if(diferencia > 0)
        {
            mp.add("0"+Integer.toBinaryString(r1));
            mo.add(r1);
            lm = lm + 8;
            String hola="hola mundo";

            for(int i = 0; i < dif2; i++)
            {
                mp.add("00000000");
                mo.add(0x00);
                lm = lm + 8;
            }
            String cad = Integer.toBinaryString((top+64));
            int dif3 = 32 - cad.length() ;
            String cadAux = "";
            for(int i = 0; i < dif3; i++)
            {
                cadAux = cadAux + "0";
            }

            cad = cadAux + cad;
            cadAux = "";

            for(int i = 0; i < cad.length(); i++)
            {
                cadAux = cadAux + cad.charAt(i);
                if(i == 7  || i == 15 || i == 23 || i == 31)
                {
                    int b = 0;
                    mp.add(cadAux);
                    for(int k = 0; k < cadAux.length(); k++)
                    {
                        if(cadAux.charAt(k) == '1' && k == 0)
                            b = b + 128;
                        if(cadAux.charAt(k) == '1' && k == 1)
                            b = b + 64;
                        if(cadAux.charAt(k) == '1' && k == 2)
                            b = b + 32;
                        if(cadAux.charAt(k) == '1' && k == 3)
                            b = b + 16;
                        if(cadAux.charAt(k) == '1' && k == 4)
                            b = b + 8;
                        if(cadAux.charAt(k) == '1' && k == 5)
                            b = b + 4;
                        if(cadAux.charAt(k) == '1' && k == 6)
                            b = b + 2;
                        if(cadAux.charAt(k) == '1' && k == 7)
                            b = b + 1;
                    }
                    mo.add(b);
                    cadAux = "";
                }
            }

            for(int i = 0; i < 4; i++)
            {
                mp.add("00000000");
                mo.add(0x00);
            }

            //en esta parte guardamo en l arreglo mj nuevos grupos de 16 bits a partir del arreglo mp que tenia grupos de 8 bits
            String c1 = "";
            for(int i = 0; i < mp.size(); i++)
            {
              c1 = c1 + mp.get(i);
              if(i%2 == 1)
              {
                  mj.add(c1);
                  c1 = "";
              }
            }

            //guardamos ahora pero como valores enteros
            for(int i = 0; i < mj.size(); i++)
            {
                int v = 0;
                for(int k = 0; k < mj.get(i).length(); k++)
                {
                    if(mj.get(i).charAt(k) == '1' && k == 0)
                        v = v + 32768;
                    if(mj.get(i).charAt(k) == '1' && k == 1)
                        v = v + 16384;
                    if(mj.get(i).charAt(k) == '1' && k == 2)
                        v = v + 8192;
                    if(mj.get(i).charAt(k) == '1' && k == 3)
                        v = v + 4096;
                    if(mj.get(i).charAt(k) == '1' && k == 4)
                        v = v + 2048;
                    if(mj.get(i).charAt(k) == '1' && k == 5)
                        v = v + 1024;
                    if(mj.get(i).charAt(k) == '1' && k == 6)
                        v = v + 512;
                    if(mj.get(i).charAt(k) == '1' && k == 7)
                        v = v + 256;
                    if(mj.get(i).charAt(k) == '1' && k == 8)
                        v = v + 128;
                    if(mj.get(i).charAt(k) == '1' && k == 9)
                        v = v + 64;
                    if(mj.get(i).charAt(k) == '1' && k == 10)
                        v = v + 32;
                    if(mj.get(i).charAt(k) == '1' && k == 11)
                        v = v + 16;
                    if(mj.get(i).charAt(k) == '1' && k == 12)
                        v = v + 8;
                    if(mj.get(i).charAt(k) == '1' && k == 13)
                        v = v + 4;
                    if(mj.get(i).charAt(k) == '1' && k == 14)
                        v = v + 2;
                    if(mj.get(i).charAt(k) == '1' && k == 15)
                        v = v + 1;
                }
                msub.add(v);
            }
  
            int dif4 = msub.size() / 32;
            ArrayList <ArrayList<Integer>> g = new ArrayList<ArrayList<Integer>>();//arreglo que tendra arreglos de 32 bloques de 16 bits
            ArrayList <ArrayList<String>> g2 = new ArrayList<ArrayList<String>>();

            for(int i = 0; i < dif4; i++)
            {
                ArrayList <Integer> x = new ArrayList<Integer>();
                g.add(x);
                ArrayList <String> y = new ArrayList<String>();
                g2.add(y);
            }

            int cont = 0;
            int tm = 0;
            String cadena = "";
            String c3 = "";
            for(int i = 0; i < msub.size(); i++)
            {
                g.get(cont).add(msub.get(i));
                if(msub.get(i)!=0)
                {
                    cadena = Integer.toBinaryString(msub.get(i));
                    tm = 16 - cadena.length();
                    for(int k = 0; k < tm; k++)
                        c3 = c3 + "0";
                    bits.add(c3+Integer.toBinaryString(msub.get(i)));
                    g2.get(cont).add(c3+Integer.toBinaryString(msub.get(i)));
                    c3 = "";
                }
                else
                {
                    c3 = "0000000000000000";
                    bits.add(c3);
                    g2.get(cont).add(c3);
                    c3 = "";
                }
                if(i == 31)
                    cont++;
            }

            AA=A;
            BB=B;
            CC=C;
            DD=D;
           for(int i = 0; i < g.size(); i++)
           {
               for(int j = 0; j < g.get(i).size(); j++)
               {
                   A = fun_F(A,B,C,D, 0, 7, 1,g2,i,j);
                   D = fun_F(D,A,B,C, 1, 12, 2,g2,i,j);
                   C = fun_F(C,D,A,B, 2, 17, 3,g2,i,j);
                   B = fun_F(B,C,D,A, 3, 22, 4,g2,i,j);

                   A = fun_F(A,B,C,D, 4, 7, 5,g2,i,j);
                   D = fun_F(D,A,B,C, 5, 12, 6,g2,i,j);
                   C = fun_F(C,D,A,B, 6, 17, 7,g2,i,j);
                   B = fun_F(B,C,D,A, 7, 22, 8,g2,i,j);

                   A = fun_F(A,B,C,D, 8, 7, 9,g2,i,j);
                   D = fun_F(D,A,B,C, 9, 12, 10,g2,i,j);
                   C = fun_F(C,D,A,B, 10, 17, 11,g2,i,j);
                   B = fun_F(B,C,D,A, 11, 22, 12,g2,i,j);

                   A = fun_F(A,B,C,D, 12, 7, 13,g2,i,j);
                   D = fun_F(D,A,B,C, 13, 12, 14,g2,i,j);
                   C = fun_F(C,D,A,B, 14, 17, 15,g2,i,j);
                   B = fun_F(B,C,D,A, 15, 22, 16,g2,i,j);


                   A = fun_G(A,B,C,D, 1, 5, 17,g2,i,j);
                   D = fun_G(D,A,B,C, 6, 9, 18,g2,i,j);
                   C = fun_G(C,D,A,B, 11, 14, 19,g2,i,j);
                   B = fun_G(B,C,D,A, 0, 20, 20,g2,i,j);

                   A = fun_G(A,B,C,D, 5, 5, 21,g2,i,j);
                   D = fun_G(D,A,B,C, 10, 9, 22,g2,i,j);
                   C = fun_G(C,D,A,B, 15, 14, 23,g2,i,j);
                   B = fun_G(B,C,D,A, 4, 20, 24,g2,i,j);

                   A = fun_G(A,B,C,D, 9, 5, 25,g2,i,j);
                   D = fun_G(D,A,B,C, 14, 9, 26,g2,i,j);
                   C = fun_G(C,D,A,B, 3, 14, 27,g2,i,j);
                   B = fun_G(B,C,D,A, 8, 20, 28,g2,i,j);

                   A = fun_G(A,B,C,D, 13, 5, 29,g2,i,j);
                   D = fun_G(D,A,B,C, 2, 9, 30,g2,i,j);
                   C = fun_G(C,D,A,B, 7, 14, 31,g2,i,j);
                   B = fun_G(B,C,D,A, 12, 20, 32,g2,i,j);


                   A = fun_H(A,B,C,D, 5, 4, 33,g2,i,j);
                   D = fun_H(D,A,B,C, 8, 11, 34,g2,i,j);
                   C = fun_H(C,D,A,B, 11, 16, 35,g2,i,j);
                   B = fun_H(B,C,D,A, 14, 23, 36,g2,i,j);

                   A = fun_H(A,B,C,D, 1, 4, 37,g2,i,j);
                   D = fun_H(D,A,B,C, 4, 11, 38,g2,i,j);
                   C = fun_H(C,D,A,B, 7, 16, 39,g2,i,j);
                   B = fun_H(B,C,D,A, 10, 23, 40,g2,i,j);

                   A = fun_H(A,B,C,D, 13, 4, 41,g2,i,j);
                   D = fun_H(D,A,B,C, 0, 11, 42,g2,i,j);
                   C = fun_H(C,D,A,B, 3, 16, 43,g2,i,j);
                   B = fun_H(B,C,D,A, 6, 23, 44,g2,i,j);

                   A = fun_H(A,B,C,D, 9, 4, 45,g2,i,j);
                   D = fun_H(D,A,B,C, 12, 11, 46,g2,i,j);
                   C = fun_H(C,D,A,B, 15, 16, 47,g2,i,j);
                   B = fun_H(B,C,D,A, 2, 23, 48,g2,i,j);


                   A = fun_I(A,B,C,D, 0, 6, 49,g2,i,j);
                   D = fun_I(D,A,B,C, 7, 10, 50,g2,i,j);
                   C = fun_I(C,D,A,B, 14, 15, 51,g2,i,j);
                   B = fun_I(B,C,D,A, 5, 21, 52,g2,i,j);

                   A = fun_I(A,B,C,D, 12, 6, 53,g2,i,j);
                   D = fun_I(D,A,B,C, 3, 10, 54,g2,i,j);
                   C = fun_I(C,D,A,B, 10, 15, 55,g2,i,j);
                   B = fun_I(B,C,D,A, 1, 21, 56,g2,i,j);

                   A = fun_I(A,B,C,D,8, 6, 57,g2,i,j);
                   D = fun_I(D,A,B,C, 15, 10, 58,g2,i,j);
                   C = fun_I(C,D,A,B, 6, 15, 59,g2,i,j);
                   B = fun_I(B,C,D,A, 13, 21, 60,g2,i,j);

                   A = fun_I(A,B,C,D, 4, 6, 61,g2,i,j);
                   D = fun_I(D,A,B,C, 11, 10, 62,g2,i,j);
                   C = fun_I(C,D,A,B, 2, 15, 63,g2,i,j);
                   B = fun_I(B,C,D,A, 9, 21, 64,g2,i,j);

                   A = A + AA;
                   B = B + BB;
                   C = C + CC;
                   D = D + DD;
                }
           }

               huella = Integer.toHexString(D)+Integer.toHexString(C)+Integer.toHexString(B)+Integer.toHexString(A);
         }
        return huella;

    }

}
