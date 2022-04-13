package search_problems.dna_search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Gene {
    private final List<Codon> codons = new ArrayList<>();

    public Gene(String geneStr) {
        for (int i = 0; i < geneStr.length()-3; i+=3) {
            codons.add(new Codon(geneStr.substring(i,i+3)));
        }
    }
    public boolean binaryContains(Codon toFind){
        Collections.sort(codons);
        int begin = 0;
        int end = codons.size()-1;
        while(begin<=end){
            int middle = (begin + end)/2;
            int res = toFind.compareTo(codons.get(middle));
            if(res>0) begin = middle+1;
            else if(res<0) end= middle-1;
            else return true;
        }
        return false;
    }

    public List<Codon> getCodons() {
        return codons;
    }
}
