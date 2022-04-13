package search_problems.dna_search;

import java.util.Comparator;

public class Codon implements Comparable<Codon>{
    public final Nucleotide first, second, third;
    private final Comparator<Codon> comparator = Comparator.comparing((Codon c) -> c.first)
            .thenComparing((Codon c) -> c.second)
            .thenComparing((Codon c) -> c.third);

    public Codon(String codonStr) {
        first = Nucleotide.valueOf(codonStr.substring(0, 1));
        second = Nucleotide.valueOf(codonStr.substring(1, 2));
        third = Nucleotide.valueOf(codonStr.substring(2, 3));
    }
    @Override
    public int compareTo(Codon o) {
        return comparator.compare(this, o);
    }
}
