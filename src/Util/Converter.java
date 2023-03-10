package Util;

public class Converter {
    public static int bin2dec(Boolean cromosoma[], int tamGenes[], int x) {
        int tam = tamGenes[x];
        int ini = 0;
        for(int i = 0; i < x; i++) {
            ini += tamGenes[i];
        }
        int ret = 0;
        for(int i = 0 + ini; i < tam + ini; i++) {
            if((Boolean) cromosoma[i])
                ret += Math.pow(2,tam + ini - i - 1);
        }
        return ret;
    }
}
