package search_problems.dna_search;

import org.junit.jupiter.api.Test;
import search_problems.GenericSearch;

import static org.junit.jupiter.api.Assertions.*;

class GeneTest {
    private final String original= "ACGTGGCTCTCAAGGTACGTA";
    private final String toFind= "TGG";

    @Test
    void staticBinaryContains(){
        Gene gene = new Gene(original);
        assertTrue(gene.binaryContains(new Codon(toFind)));
    }
    @Test
    void genericBinaryContains(){
        Gene gene = new Gene(original);
        assertTrue(GenericSearch.binaryContains(gene.getCodons(),new Codon(toFind)));
    }
    @Test
    void genericLinearContains(){
        Gene gene = new Gene(original);
        assertTrue(GenericSearch.linearContains(gene.getCodons(),new Codon(toFind)));
    }
}