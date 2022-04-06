package simple_problems.dna;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompressedGeneTest {


    @Test
    void compress() {
        String originalString = "TTAAAAATDCCDCDCDCDA";
        CompressedGene compressedGene = new CompressedGene();
        compressedGene.compress(originalString);
        System.out.println(compressedGene.getBitSet().toString());
        assertEquals(originalString,compressedGene.decompress());
    }
}